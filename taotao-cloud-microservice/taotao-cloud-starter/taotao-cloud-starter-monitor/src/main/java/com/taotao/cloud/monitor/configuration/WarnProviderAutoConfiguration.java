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
package com.taotao.cloud.monitor.configuration;

import com.taotao.cloud.common.constant.StarterName;
import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.core.monitor.Monitor;
import com.taotao.cloud.monitor.properties.WarnProperties;
import com.taotao.cloud.monitor.strategy.DefaultWarnStrategy;
import com.taotao.cloud.monitor.strategy.Rule;
import com.taotao.cloud.monitor.strategy.WarnStrategy;
import com.taotao.cloud.monitor.strategy.WarnTemplate;
import com.taotao.cloud.monitor.warn.WarnProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * HealthConfiguration
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-10 17:22:15
 */
@AutoConfiguration
@EnableConfigurationProperties(WarnProperties.class)
@ConditionalOnProperty(prefix = WarnProperties.PREFIX, name = "enabled", havingValue = "true")
public class WarnProviderAutoConfiguration implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtils.started(WarnProviderAutoConfiguration.class, StarterName.MONITOR_STARTER);
	}

	@Bean
	public WarnStrategy defaultWarnStrategy() {
		WarnTemplate warnTemplate = new WarnTemplate().register("", "参数:{name}({desc}),命中规则:{rule},当前值：{value}");
		return new DefaultWarnStrategy(warnTemplate, new Rule.RulesAnalyzer());
	}

	@ConditionalOnBean
	@Bean(destroyMethod = "close")
	public WarnProvider getWarnProvider(WarnProperties warnProperties, Monitor monitor) {
		return new WarnProvider(warnProperties, monitor);
	}


}