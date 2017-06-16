/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.dao.source;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.app.entity.source.SourceCount;
import com.thinkgem.jeesite.modules.app.entity.source.SourceUser;

/**
 * 渠道统计DAO接口
 * @author lianghairui
 * @version 2017-05-30
 */
@MyBatisDao
public interface SourceCountDao extends CrudDao<SourceCount> {

    void increaseSourceCount(SourceCount sourceCount);
	
}