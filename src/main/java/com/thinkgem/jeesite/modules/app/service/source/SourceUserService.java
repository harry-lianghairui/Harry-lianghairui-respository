/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.service.source;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.app.dao.source.SourceConfigDao;
import com.thinkgem.jeesite.modules.app.dao.source.SourceCountDao;
import com.thinkgem.jeesite.modules.app.dao.source.SourceUserDao;
import com.thinkgem.jeesite.modules.app.entity.source.SourceCount;
import com.thinkgem.jeesite.modules.app.entity.source.SourceUser;

/**
 * 用户来源统计Service
 * @author lianghairui
 * @version 2017-05-30
 */
@Service
@Transactional(readOnly = true)
public class SourceUserService extends CrudService<SourceUserDao, SourceUser> {
    
    @Autowired
    private SourceCountDao sourceCountDao;
    
    @Autowired
    private SourceConfigDao sourceConfigDao;

	public SourceUser get(String id) {
		return super.get(id);
	}
	
	public List<SourceUser> findList(SourceUser sourceUser) {
		return super.findList(sourceUser);
	}
	
	public Page<SourceUser> findPage(Page<SourceUser> page, SourceUser sourceUser) {
		return super.findPage(page, sourceUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SourceUser sourceUser) {
	    List<SourceUser> sourceUsers = super.findList(sourceUser);
	    //一个用户同一渠道同一种操作类型只有一条记录
	    if (CollectionUtils.isEmpty(sourceUsers)) {
	        sourceUser.setCount(1);
	        super.save(sourceUser);
	        SourceCount sourceCount = new SourceCount();
	        sourceCount.setSourceId(sourceUser.getSourceId());
	        sourceCount.setType(sourceUser.getType());
	        List<SourceCount> sourceCounts = sourceCountDao.findList(sourceCount);
	        if (CollectionUtils.isEmpty(sourceCounts)) {
	            if (sourceUser.getType() == 0) {
                    sourceCount.setClickCount(1);
                }else if (sourceUser.getType() == 1) {
                    sourceCount.setInDownloadCount(1);
                }else if (sourceUser.getType() == 3) {
                    sourceCount.setCompleteCount(1);
                }else {
                    sourceCount.setOpenCount(1);
                }
	            sourceCount.preInsert();
	            sourceCountDao.insert(sourceCount);
	        }else {
	            sourceCount.setId(sourceCounts.get(0).getId());
	            sourceCount.preUpdate();
	            sourceCountDao.increaseSourceCount(sourceCount);
	        }
        }else {
            //加1操作
            super.save(sourceUsers.get(0));
        }
	}
	
	@Transactional(readOnly = false)
	public void delete(SourceUser sourceUser) {
		super.delete(sourceUser);
	}
	
}