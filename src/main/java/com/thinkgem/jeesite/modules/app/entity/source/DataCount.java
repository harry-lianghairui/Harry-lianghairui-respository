/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.app.entity.source;

import java.sql.Date;


/**
 * 渠道统计Entity
 * @author lianghairui
 * @version 2017-05-30
 */
public class DataCount {
	
	private String sourceId;		// 类型
	private Integer type;		// 类型
	private Integer cnt;		// 数量
	private Date day;		// 数量
	
	public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getCnt() {
        return cnt;
    }
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
    public Date getDay() {
        return day;
    }
    public void setDay(Date day) {
        this.day = day;
    }
    public String getSourceId() {
        return sourceId;
    }
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
	
}