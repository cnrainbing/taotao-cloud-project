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

package com.taotao.cloud.generator.biz.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据库类型
 *
 * @author Lion Li
 */
@Getter
@AllArgsConstructor
public enum DataBaseType {

    /** MySQL */
    MY_SQL("MySQL"),

    /** Oracle */
    ORACLE("Oracle"),

    /** PostgreSQL */
    POSTGRE_SQL("PostgreSQL"),

    /** SQL Server */
    SQL_SERVER("Microsoft SQL Server");

    private final String type;

    public static DataBaseType find(String databaseProductName) {
        if (StringUtils.isBlank(databaseProductName)) {
            return null;
        }
        for (DataBaseType type : values()) {
            if (type.getType().equals(databaseProductName)) {
                return type;
            }
        }
        return null;
    }
}