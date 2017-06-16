/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.entity.source;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户来源统计Entity
 * @author lianghairui
 * @version 2017-05-30
 */
public class SourceUser extends DataEntity<SourceUser> {
	
	private static final long serialVersionUID = 1L;
	private String sourceId;		// source_id
	private String userId;		// user_id
	private Integer type;		// type
	private Integer count;		// count
	private String ip;		// ip
	
	public SourceUser() {
		super();
	}

	public SourceUser(String id){
		super(id);
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Length(min=0, max=30, message="ip长度必须介于 0 和 30 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
	
}