/*
 * Copyright (c) 2018-2022 the original author or authors.
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/lgpl-3.0.html
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.sms.common.exception;

import java.io.Serial;
import java.util.Locale;

/**
 * 类型无效
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:47:39
 */
public class TypeIsNullException extends SmsException {

    @Serial
	private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MSG;

    static {
        Locale locale = Locale.getDefault();

        if (Locale.CHINA.equals(locale)) {
            DEFAULT_MSG = "类型无效";
        } else {
            DEFAULT_MSG = "type is null";
        }
    }

    /**
     * 类型无效
     */
    public TypeIsNullException() {
        super(DEFAULT_MSG);
    }
}