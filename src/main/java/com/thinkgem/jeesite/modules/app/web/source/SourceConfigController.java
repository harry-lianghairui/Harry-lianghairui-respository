/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.web.source;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.domain.Resp;
import com.thinkgem.jeesite.modules.app.entity.source.SourceConfig;
import com.thinkgem.jeesite.modules.app.service.source.SourceConfigService;
import com.thinkgem.jeesite.utils.Consts;
import com.thinkgem.jeesite.utils.GsonUtil;
import com.thinkgem.jeesite.utils.HttpUtils;

/**
 * 游戏Controller
 * @author lianghairui
 * @version 2017-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/app/source/sourceConfig")
public class SourceConfigController extends BaseController {

	@Autowired
	private SourceConfigService sourceConfigService;
	
	static final String temple = "{\"code\":%s,\"data\":\"%s\"}";
	
	@ModelAttribute
	public SourceConfig get(@RequestParam(required=false) String id) {
		SourceConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sourceConfigService.get(id);
		}
		if (entity == null){
			entity = new SourceConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("app:source:sourceConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(SourceConfig sourceConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SourceConfig> page = sourceConfigService.findPage(new Page<SourceConfig>(request, response), sourceConfig);
		setUserPageName(page);
		model.addAttribute("page", page);
		return "modules/app/source/sourceConfigList";
	}
	
	@ResponseBody
	@RequestMapping(value = {"getsourceConfigList"})
	public String getsourceConfigList(SourceConfig sourceConfig, HttpServletRequest request, HttpServletResponse response, Model model)
	        throws Exception {
	    sourceConfig.setStatus(Consts.SourceConfigStatus.ON);
	    sourceConfig.setDelFlag("0");
	    List<SourceConfig> sourceConfigs = sourceConfigService.findList(sourceConfig);
	    Resp resp = new Resp();
	    if (CollectionUtils.isEmpty(sourceConfigs)) {
	        resp.setCode(Consts.SourceConfigCode.SOURCE_CONFIG_NOT_EXSIT);
	        resp.setMsg("没有配置有效游戏");
        }else {
            resp.setCode(Consts.SourceConfigCode.SUCCESS);
            resp.setMsg("get data success");
            resp.setData(sourceConfigs);
        }
	    return GsonUtil.toJson(resp);
	}

	@RequiresPermissions("app:source:sourceConfig:view")
	@RequestMapping(value = "form")
	public String form(SourceConfig sourceConfig, Model model) {
		model.addAttribute("sourceConfig", sourceConfig);
		return "modules/app/source/sourceConfigForm";
	}

	@RequiresPermissions("app:source:sourceConfig:edit")
	@RequestMapping(value = "save")
	public String save(SourceConfig sourceConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sourceConfig)){
			return form(sourceConfig, model);
		}
		
		URL url = null;
		HttpURLConnection urlcon=null;
		try {
            url = new URL(sourceConfig.getUrl());
            urlcon=(HttpURLConnection)url.openConnection();
            int fileSize = urlcon.getContentLength()/1024/1024;
            if (urlcon.getContentLength()%(1024*1024) != 0) {
                fileSize = fileSize + 1;
            }
            sourceConfig.setFileSize(fileSize);
        } catch (Exception e) {
            logger.error("SourceConfigController.save get fileSizeFail:{}", e);
        }finally{
            IOUtils.close(urlcon);
        }
        
		if (sourceConfig.getFileSize() == null) {
            sourceConfig.setFileSize(0);
        }
		sourceConfigService.save(sourceConfig);
		addMessage(redirectAttributes, "保存游戏成功");
		return "redirect:"+Global.getAdminPath()+"/app/source/sourceConfig/?repage";
	}
	
	@RequiresPermissions("app:source:sourceConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(SourceConfig sourceConfig, RedirectAttributes redirectAttributes) {
		sourceConfigService.delete(sourceConfig);
		addMessage(redirectAttributes, "删除游戏成功");
		return "redirect:"+Global.getAdminPath()+"/app/source/sourceConfig/?repage";
	}

    @RequiresPermissions("app:source:sourceConfig:edit")
    @ResponseBody
    @RequestMapping(value = "updateStatus")
    public String updateStatus(SourceConfig sourceConfig, HttpServletRequest request) throws Exception {

        if (StringUtils.isBlank(sourceConfig.getId()) || sourceConfig.getStatus() == null) {
            return end(1, "参数错误！");
        }
        
        if (sourceConfig.getStatus() == Consts.SourceConfigStatus.ON) {
            SourceConfig sConfig = new SourceConfig();
            sConfig.setStatus(Consts.SourceConfigStatus.ON);
            sConfig.setResourceId(sourceConfig.getResourceId());
            sConfig.setDelFlag("0");
            List<SourceConfig> sourceConfigs = sourceConfigService.findList(sConfig);
            if (CollectionUtils.isNotEmpty(sourceConfigs)) {
                for (SourceConfig sourceConfig2 : sourceConfigs) {
                    sourceConfig2.setStatus(Consts.SourceConfigStatus.OFF);
                    sourceConfigService.save(sourceConfig2);
                }
            }
        }
        sourceConfig.setIp(HttpUtils.getRequestorIp(request));
        sourceConfigService.save(sourceConfig);
        return end(0, "修改成功");
    }
    
    /**
     * 返回json
     * 
     * @param retCode
     * @param retMessage
     * @return
     * @throws IOException
     */
    private String end(int retCode, String retMessage) throws IOException {
        return String.format(temple, retCode, retMessage);
    }
}