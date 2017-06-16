/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.entity.source;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 渠道统计Entity
 * @author lianghairui
 * @version 2017-05-30
 */
public class SourceCount extends DataEntity<SourceCount> {
	
	private static final long serialVersionUID = 1L;
	private String sourceId;		// source_id
	private Integer clickCount;		// click_count
	private Integer inDownloadCount;		// in_download_count
	private Integer completeCount;		// complete_count
	private Integer openCount;		// open_count
	private Date beginTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String beginTimeStr;		// 开始时间
	private String endTimeStr;		// 结束时间
	private String ip;		// ip
	
	private Integer type;
	
	public SourceCount() {
		super();
	}

	public SourceCount(String id){
		super(id);
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}
	
	public Integer getInDownloadCount() {
		return inDownloadCount;
	}

	public void setInDownloadCount(Integer inDownloadCount) {
		this.inDownloadCount = inDownloadCount;
	}
	
	public Integer getCompleteCount() {
		return completeCount;
	}

	public void setCompleteCount(Integer completeCount) {
		this.completeCount = completeCount;
	}
	
	public Integer getOpenCount() {
		return openCount;
	}

	public void setOpenCount(Integer openCount) {
		this.openCount = openCount;
	}
	
	@Length(min=0, max=30, message="ip长度必须介于 0 和 30 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
	
}