/**
 * Copyright (C) 2018-2020 All rights reserved, Designed By www.yixiang.co 注意：
 * 本软件为www.yixiang.co开发研制
 */
package com.taotao.cloud.sys.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.sys.biz.utils.GenUtil;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 字段配置表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-15 09:20:23
 */
@Entity
@Table(name = ColumnConfig.TABLE_NAME)
@TableName(ColumnConfig.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = ColumnConfig.TABLE_NAME, comment = "字段配置表")
public class ColumnConfig extends BaseSuperEntity<ColumnConfig, Long> {

	public static final String TABLE_NAME = "tt_sys_column_config";

	@Column(name = "table_name", nullable = false, columnDefinition = "varchar(64) not null comment '表名称'")
	private String tableName;

	/**
	 * 数据库字段名称
	 */
	@Column(name = "column_name", nullable = false, columnDefinition = "varchar(64) not null comment '数据库字段名称'")
	private String columnName;

	/**
	 * 数据库字段类型
	 */
	@Column(name = "column_type", nullable = false, columnDefinition = "varchar(64) not null comment '数据库字段类型'")
	private String columnType;

	/**
	 * 数据库字段键类型
	 */
	@Column(name = "key_type", nullable = false, columnDefinition = "varchar(64) not null comment '数据库字段键类型'")
	private String keyType;

	/**
	 * 字段额外的参数
	 */
	@Column(name = "extra", nullable = false, columnDefinition = "varchar(128) not null comment '字段额外的参数'")
	private String extra;

	/**
	 * 数据库字段描述
	 */
	@Column(name = "remark", nullable = false, columnDefinition = "varchar(256) not null comment '数据库字段描述'")
	private String remark;

	/**
	 * 必填
	 */
	@Column(name = "not_null", nullable = false, columnDefinition = "boolean default false comment '必填'")
	private Boolean notNull;

	/**
	 * 是否在列表显示
	 */
	@Column(name = "list_show", nullable = false, columnDefinition = "boolean default false comment '是否在列表显示'")
	private Boolean listShow;

	/**
	 * 是否表单显示
	 */
	@Column(name = "form_show", nullable = false, columnDefinition = "boolean default false comment '是否表单显示'")
	private Boolean formShow;

	/**
	 * 表单类型
	 */
	@Column(name = "form_type", nullable = false, columnDefinition = "varchar(64) not null comment '表单类型'")
	private String formType;

	/**
	 * 查询 1:模糊 2：精确
	 */
	@Column(name = "query_type", nullable = false, columnDefinition = "varchar(64) not null comment '查询 1:模糊 2：精确'")
	private String queryType;

	/**
	 * 字典名称
	 */
	@Column(name = "dict_name", nullable = false, columnDefinition = "varchar(64) not null comment '字典名称'")
	private String dictName;

	/**
	 * 日期注解
	 */
	@Column(name = "date_annotation", nullable = false, columnDefinition = "varchar(64) not null comment '日期注解'")
	private String dateAnnotation;

	public ColumnConfig() {
	}

	public ColumnConfig(String tableName, String columnName, Boolean notNull, String columnType,
		String remark, String keyType, String extra) {
		this.tableName = tableName;
		this.columnName = columnName;
		this.columnType = columnType;
		this.keyType = keyType;
		this.extra = extra;
		this.notNull = notNull;
		if (GenUtil.PK.equalsIgnoreCase(keyType) && GenUtil.EXTRA.equalsIgnoreCase(extra)) {
		    this.notNull = false;
		}
		this.remark = remark;
		this.listShow = true;
		this.formShow = true;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getNotNull() {
		return notNull;
	}

	public void setNotNull(Boolean notNull) {
		this.notNull = notNull;
	}

	public Boolean getListShow() {
		return listShow;
	}

	public void setListShow(Boolean listShow) {
		this.listShow = listShow;
	}

	public Boolean getFormShow() {
		return formShow;
	}

	public void setFormShow(Boolean formShow) {
		this.formShow = formShow;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDateAnnotation() {
		return dateAnnotation;
	}

	public void setDateAnnotation(String dateAnnotation) {
		this.dateAnnotation = dateAnnotation;
	}
}