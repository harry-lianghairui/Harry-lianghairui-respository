/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.service.source;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.app.entity.source.SourceConfig;
import com.thinkgem.jeesite.modules.app.dao.source.SourceConfigDao;

/**
 * 渠道Service
 * @author lianghairui
 * @version 2017-05-30
 */
@Service
@Transactional(readOnly = true)
public class SourceConfigService extends CrudService<SourceConfigDao, SourceConfig> {

	public SourceConfig get(String id) {
		return super.get(id);
	}
	
	public List<SourceConfig> findList(SourceConfig sourceConfig) {
		return super.findList(sourceConfig);
	}
	
	public Page<SourceConfig> findPage(Page<SourceConfig> page, SourceConfig sourceConfig) {
		return super.findPage(page, sourceConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(SourceConfig sourceConfig) {
		super.save(sourceConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(SourceConfig sourceConfig) {
		super.delete(sourceConfig);
	}
	
}