/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.dao.source;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.app.entity.source.SourceConfig;

/**
 * 渠道DAO接口
 * @author lianghairui
 * @version 2017-05-30
 */
@MyBatisDao
public interface SourceConfigDao extends CrudDao<SourceConfig> {
	
}