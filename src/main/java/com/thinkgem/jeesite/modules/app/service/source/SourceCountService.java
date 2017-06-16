/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.service.source;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.mysql.fabric.xmlrpc.base.Data;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.app.entity.source.DataCount;
import com.thinkgem.jeesite.modules.app.entity.source.SourceCount;
import com.thinkgem.jeesite.modules.app.entity.source.SourceUser;
import com.thinkgem.jeesite.modules.app.dao.source.SourceCountDao;
import com.thinkgem.jeesite.modules.app.dao.source.SourceUserDao;
import com.thinkgem.jeesite.utils.Consts;

/**
 * 渠道统计Service
 * @author lianghairui
 * @version 2017-05-30
 */
@Service
@Transactional(readOnly = true)
public class SourceCountService extends CrudService<SourceCountDao, SourceCount> {
    
    @Autowired
    private SourceUserDao sourceUserDao;

	public SourceCount get(String id) {
		return super.get(id);
	}
	
	public List<SourceCount> findList(SourceCount sourceCount) {
		return super.findList(sourceCount);
	}
	
	public Page<SourceCount> findPage(Page<SourceCount> page, SourceCount sourceCount) {
		return super.findPage(page, sourceCount);
	}
	
	public Page<SourceCount> findGroupPage(Page<SourceCount> page, SourceCount sourceCount) {
        List<DataCount> dataCounts = sourceUserDao.findGroupList(sourceCount);
        Map<String, List<DataCount>> map = new HashMap<String, List<DataCount>>();
        List<SourceCount> sourceCounts = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataCounts)) {
            for (DataCount dataCount : dataCounts) {
                if (map.containsKey(dataCount.getSourceId() + dataCount.getDay().toString())) {
                    List<DataCount> dCounts = map.get(dataCount.getSourceId() + dataCount.getDay().toString());
                    dCounts.add(dataCount);
                }else {
                    List<DataCount> dList = Lists.newArrayList();
                    dList.add(dataCount);
                    map.put(dataCount.getSourceId() + dataCount.getDay().toString(), dList);
                }
            }
            for (List<DataCount> dCounts : map.values()) {
                SourceCount sourceCount2 = new SourceCount();
                for (DataCount dataCount : dCounts) {
                    sourceCount2.setSourceId(dataCount.getSourceId());
                    sourceCount2.setCreateDate(dataCount.getDay());
                    if (dataCount.getType() == Consts.SourceUserType.CLICK_IMAGE) {
                        sourceCount2.setClickCount(dataCount.getCnt());
                    }else if (dataCount.getType() == Consts.SourceUserType.IN_DOWNLOAD) {
                        sourceCount2.setInDownloadCount(dataCount.getCnt());
                    }else if (dataCount.getType() == Consts.SourceUserType.DOWNLOAD_COMPLETE) {
                        sourceCount2.setCompleteCount(dataCount.getCnt());
                    }else {
                        sourceCount2.setOpenCount(dataCount.getCnt());
                    }
                }
                sourceCounts.add(sourceCount2);
            }
        }
        Page<SourceCount> resultPage = new Page<SourceCount>();
        resultPage.setList(sourceCounts);
        return resultPage;
	}
	
	@Transactional(readOnly = false)
	public void save(SourceCount sourceCount) {
		super.save(sourceCount);
	}
	
	@Transactional(readOnly = false)
	public void delete(SourceCount sourceCount) {
		super.delete(sourceCount);
	}
	
}