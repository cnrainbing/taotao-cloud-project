/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package com.taotao.cloud.sys.api.dto.log;


import java.sql.Timestamp;
import java.util.List;

public class LogQueryCriteria {

    private String blurry;

    private String logType;

    private List<Timestamp> createTime;

    private Integer type;

	public String getBlurry() {
		return blurry;
	}

	public void setBlurry(String blurry) {
		this.blurry = blurry;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public List<Timestamp> getCreateTime() {
		return createTime;
	}

	public void setCreateTime(List<Timestamp> createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}