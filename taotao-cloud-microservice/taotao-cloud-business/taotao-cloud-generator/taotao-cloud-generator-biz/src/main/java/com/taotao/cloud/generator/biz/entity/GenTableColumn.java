/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.generator.biz.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.common.utils.lang.StringUtils;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = GenTableColumn.TABLE_NAME)
@TableName(GenTableColumn.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = GenTableColumn.TABLE_NAME, comment = "代码生成业务字段表")
public class GenTableColumn extends BaseSuperEntity<GenTableColumn, Long> {

    public static final String TABLE_NAME = "tt_gen_table_column";

    /// **
    // * 归属表编号
    // */
    // @Column(name = "table_id", columnDefinition = "bigint not null comment '归属表编号'")
    // private Long tableId;

    /** 列名称 */
    @Column(name = "column_name", columnDefinition = "varchar(1024) not null comment '列名称'")
    private String columnName;

    /** 列描述 */
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    @Column(name = "column_comment", columnDefinition = "varchar(1024) not null comment '列描述'")
    private String columnComment;

    /** 列类型 */
    @Column(name = "column_type", columnDefinition = "varchar(1024) not null comment '列类型'")
    private String columnType;

    /** JAVA类型 */
    @Column(name = "java_type", columnDefinition = "varchar(1024) not null comment 'JAVA类型'")
    private String javaType;

    /** JAVA字段名 */
    @Column(name = "java_field", columnDefinition = "varchar(1024) not null comment 'JAVA字段名'")
    private String javaField;

    /** 是否主键（1是） */
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    @Column(name = "is_pk", columnDefinition = "varchar(1024) not null comment '是否主键（1是）'")
    private String isPk;

    /** 是否自增（1是） */
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    @Column(name = "is_increment", columnDefinition = "varchar(1024) not null comment ' 是否自增（1是）'")
    private String isIncrement;

    /** 是否必填（1是） */
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    @Column(name = "is_required", columnDefinition = "varchar(1024) not null comment '是否必填（1是）'")
    private String isRequired;

    /** 是否为插入字段（1是） */
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    @Column(name = "is_insert", columnDefinition = "varchar(1024) not null comment '是否为插入字段（1是）'")
    private String isInsert;

    /** 是否编辑字段（1是） */
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    @Column(name = "is_edit", columnDefinition = "varchar(1024) not null comment '是否编辑字段（1是）'")
    private String isEdit;

    /** 是否列表字段（1是） */
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    @Column(name = "is_list", columnDefinition = "varchar(1024) not null comment '是否列表字段（1是）'")
    private String isList;

    /** 是否查询字段（1是） */
    @TableField(updateStrategy = FieldStrategy.IGNORED, jdbcType = JdbcType.VARCHAR)
    @Column(name = "is_query", columnDefinition = "varchar(1024) not null comment '是否查询字段（1是）'")
    private String isQuery;

    /** 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围） */
    @Column(
            name = "query_type",
            columnDefinition =
                    "varchar(1024) not null comment '查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）'")
    private String queryType;

    /**
     * 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、image图片上传控件、upload文件上传控件、editor富文本控件）
     */
    @Column(
            name = "html_type",
            columnDefinition =
                    "varchar(1024) not null comment"
                        + " '显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、image图片上传控件、upload文件上传控件、editor富文本控件）'")
    private String htmlType;

    /** 字典类型 */
    @Column(name = "dict_type", columnDefinition = "varchar(1024) null comment '字典类型'")
    private String dictType;

    /** 排序 */
    @Column(name = "sort", columnDefinition = "int null comment '排序'")
    private Integer sort;

    public String getCapJavaField() {
        return org.apache.commons.lang3.StringUtils.capitalize(javaField);
    }

    public boolean isPk() {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk) {
        return isPk != null && StringUtils.equals("1", isPk);
    }

    public boolean isIncrement() {
        return isIncrement(this.isIncrement);
    }

    public boolean isIncrement(String isIncrement) {
        return isIncrement != null && StringUtils.equals("1", isIncrement);
    }

    public boolean isRequired() {
        return isRequired(this.isRequired);
    }

    public boolean isRequired(String isRequired) {
        return isRequired != null && StringUtils.equals("1", isRequired);
    }

    public boolean isInsert() {
        return isInsert(this.isInsert);
    }

    public boolean isInsert(String isInsert) {
        return isInsert != null && StringUtils.equals("1", isInsert);
    }

    public boolean isEdit() {
        return isInsert(this.isEdit);
    }

    public boolean isEdit(String isEdit) {
        return isEdit != null && StringUtils.equals("1", isEdit);
    }

    public boolean isList() {
        return isList(this.isList);
    }

    public boolean isList(String isList) {
        return isList != null && StringUtils.equals("1", isList);
    }

    public boolean isQuery() {
        return isQuery(this.isQuery);
    }

    public boolean isQuery(String isQuery) {
        return isQuery != null && StringUtils.equals("1", isQuery);
    }

    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(
                javaField,
                // BaseEntity
                "createBy",
                "createTime",
                "updateBy",
                "updateTime",
                // TreeEntity
                "parentName",
                "parentId");
    }

    public boolean isUsableColumn() {
        return isUsableColumn(javaField);
    }

    public static boolean isUsableColumn(String javaField) {
        // isSuperColumn()中的名单用于避免生成多余Domain属性，若某些属性在生成页面时需要用到不能忽略，则放在此处白名单
        return StringUtils.equalsAnyIgnoreCase(javaField, "parentId", "orderNum", "remark");
    }

    public String readConverterExp() {
        String remarks =
                org.apache.commons.lang3.StringUtils.substringBetween(this.columnComment, "（", "）");
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(remarks)) {
            for (String value : remarks.split(" ")) {
                if (StringUtils.isNotEmpty(value)) {
                    Object startStr = value.subSequence(0, 1);
                    String endStr = value.substring(1);
                    sb.append("").append(startStr).append("=").append(endStr).append(",");
                }
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            return this.columnComment;
        }
    }
}