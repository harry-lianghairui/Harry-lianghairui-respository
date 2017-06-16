/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.web.source;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.app.entity.source.SourceCount;
import com.thinkgem.jeesite.modules.app.service.source.SourceCountService;

/**
 * 游戏统计Controller
 * @author lianghairui
 * @version 2017-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/app/source/sourceCount")
public class SourceCountController extends BaseController {

	@Autowired
	private SourceCountService sourceCountService;
	
	@ModelAttribute
	public SourceCount get(@RequestParam(required=false) String id) {
		SourceCount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sourceCountService.get(id);
		}
		if (entity == null){
			entity = new SourceCount();
		}
		return entity;
	}
	
	@RequiresPermissions("app:source:sourceCount:view")
	@RequestMapping(value = {"list", ""})
	public String list(SourceCount sourceCount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SourceCount> page = sourceCountService.findPage(new Page<SourceCount>(request, response), sourceCount); 
		model.addAttribute("page", page);
		return "modules/app/source/sourceCountList";
	}
	
	@RequiresPermissions("app:source:sourceCount:view")
	@RequestMapping(value = {"groupList"})
	public String groupList(SourceCount sourceCount, HttpServletRequest request, HttpServletResponse response, Model model) {
	    if (StringUtils.isNotBlank(sourceCount.getBeginTimeStr())) {
            sourceCount.setBeginTime(DateUtils.parseDate(sourceCount.getBeginTimeStr()));
        }
	    if (StringUtils.isNotBlank(sourceCount.getEndTimeStr())) {
	        sourceCount.setEndTime(DateUtils.parseDate(sourceCount.getEndTimeStr()));
	    }
	    Page<SourceCount> page = sourceCountService.findGroupPage(new Page<SourceCount>(request, response), sourceCount); 
	    model.addAttribute("page", page);
	    return "modules/app/source/sourceCountGroupList";
	}

	@RequiresPermissions("app:source:sourceCount:view")
	@RequestMapping(value = "form")
	public String form(SourceCount sourceCount, Model model) {
		model.addAttribute("sourceCount", sourceCount);
		return "modules/app/source/sourceCountForm";
	}

	@RequiresPermissions("app:source:sourceCount:edit")
	@RequestMapping(value = "save")
	public String save(SourceCount sourceCount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sourceCount)){
			return form(sourceCount, model);
		}
		sourceCountService.save(sourceCount);
		addMessage(redirectAttributes, "保存游戏统计成功");
		return "redirect:"+Global.getAdminPath()+"/app/source/sourceCount/?repage";
	}
	
	@RequiresPermissions("app:source:sourceCount:edit")
	@RequestMapping(value = "delete")
	public String delete(SourceCount sourceCount, RedirectAttributes redirectAttributes) {
		sourceCountService.delete(sourceCount);
		addMessage(redirectAttributes, "删除游戏统计成功");
		return "redirect:"+Global.getAdminPath()+"/app/source/sourceCount/?repage";
	}

}