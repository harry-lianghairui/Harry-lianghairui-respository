/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.entity.source;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 渠道Entity
 * @author lianghairui
 * @version 2017-05-30
 */
public class SourceConfig extends DataEntity<SourceConfig> {
	
	private static final long serialVersionUID = 1L;
	private String resourceId;		// resource_id
	private String name;		// name
	private String pName;		// name
	private Integer fileSize;		// 文件大小
	private String version;		// version
	private String url;		// url
	private String backGround;		// back_ground
	private Integer status;		// status
	private String ip;		// ip
	
	public SourceConfig() {
		super();
	}

	public SourceConfig(String id){
		super(id);
	}

	@Length(min=0, max=30, message="resource_id长度必须介于 0 和 30 之间")
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	@Length(min=0, max=30, message="name长度必须介于 0 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="version长度必须介于 1 和 20 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=200, message="url长度必须介于 0 和 200 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=200, message="back_ground长度必须介于 0 和 200 之间")
	public String getBackGround() {
		return backGround;
	}

	public void setBackGround(String backGround) {
		this.backGround = backGround;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=30, message="ip长度必须介于 0 和 30 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Length(min=0, max=30, message="pName长度必须介于 0 和 30 之间")
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
	
}