/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.web.source;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.app.entity.source.SourceConfig;
import com.thinkgem.jeesite.modules.app.entity.source.SourceUser;
import com.thinkgem.jeesite.modules.app.service.source.SourceConfigService;
import com.thinkgem.jeesite.modules.app.service.source.SourceUserService;
import com.thinkgem.jeesite.utils.Consts;
import com.thinkgem.jeesite.utils.GsonUtil;
import com.thinkgem.jeesite.utils.HttpUtils;

/**
 * 用户来源统计Controller
 * @author lianghairui
 * @version 2017-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/app/source/sourceUser")
public class SourceUserController extends BaseController {

	@Autowired
	private SourceUserService sourceUserService;
	
	@Autowired
	private SourceConfigService sourceConfigService;
	
	static final String temple = "{\"code\":%s,\"data\":\"%s\"}";
	
	@ModelAttribute
	public SourceUser get(@RequestParam(required=false) String id) {
		SourceUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sourceUserService.get(id);
		}
		if (entity == null){
			entity = new SourceUser();
		}
		return entity;
	}
	
	@RequiresPermissions("app:source:sourceUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SourceUser sourceUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SourceUser> page = sourceUserService.findPage(new Page<SourceUser>(request, response), sourceUser); 
		model.addAttribute("page", page);
		return "modules/app/source/sourceUserList";
	}

	@RequiresPermissions("app:source:sourceUser:view")
	@RequestMapping(value = "form")
	public String form(SourceUser sourceUser, Model model) {
		model.addAttribute("sourceUser", sourceUser);
		return "modules/app/source/sourceUserForm";
	}

	@RequiresPermissions("app:source:sourceUser:edit")
	@RequestMapping(value = "save")
	public String save(SourceUser sourceUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sourceUser)){
			return form(sourceUser, model);
		}
		sourceUserService.save(sourceUser);
		addMessage(redirectAttributes, "保存用户来源统计成功");
		return "redirect:"+Global.getAdminPath()+"/app/source/sourceUser/?repage";
	}
	
    @ResponseBody
    @RequestMapping(value = "saveSourceUser")
    public String saveSourceUser(SourceUser sourceUser, HttpServletRequest request) throws Exception {
        System.out.println(GsonUtil.toJson(sourceUser));
        if (StringUtils.isBlank(sourceUser.getSourceId())
                || StringUtils.isBlank(sourceUser.getUserId()) || sourceUser.getType() == null) {
            return end(Consts.SourceUserUploadCode.PARAMS_INVALID, "参数错误！");
        }
        SourceConfig sourceConfig = new SourceConfig();
        sourceConfig.setResourceId(sourceUser.getSourceId());
        sourceConfig.setDelFlag("0");
        sourceConfig.setStatus(Consts.SourceConfigStatus.ON);
        List<SourceConfig> sourceConfigs = sourceConfigService.findList(sourceConfig);
        if (CollectionUtils.isEmpty(sourceConfigs)) {
            return end(Consts.SourceUserUploadCode.SOURCE_CONFIG_NOT_EXSIT, "游戏没有配置！");
        }
        sourceUser.setIp(HttpUtils.getRequestorIp(request));
        sourceUserService.save(sourceUser);
        return end(Consts.SourceUserUploadCode.SUCCESS, "上报成功");
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
	
	@RequiresPermissions("app:source:sourceUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SourceUser sourceUser, RedirectAttributes redirectAttributes) {
		sourceUserService.delete(sourceUser);
		addMessage(redirectAttributes, "删除用户来源统计成功");
		return "redirect:"+Global.getAdminPath()+"/app/source/sourceUser/?repage";
	}

}