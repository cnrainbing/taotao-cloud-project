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

package com.taotao.cloud.auth.biz.authentication.login.oauth2.social.justauth.annotation;

import com.taotao.cloud.auth.biz.authentication.login.oauth2.social.justauth.condition.JustAuthEnabledCondition;
import java.lang.annotation.*;
import org.springframework.context.annotation.Conditional;

/**
 * <p>Description: JustAuth开启条件注解 </p>
 *
 *
 * @date : 2022/1/24 14:40
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(JustAuthEnabledCondition.class)
public @interface ConditionalOnJustAuthEnabled {}