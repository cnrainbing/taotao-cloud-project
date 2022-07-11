/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.jetcache.configuration;

import com.alicp.jetcache.anno.support.ConfigMap;
import com.alicp.jetcache.anno.support.DefaultSpringEncoderParser;
import com.alicp.jetcache.anno.support.DefaultSpringKeyConvertorParser;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cloud.common.utils.common.JsonUtil;
import com.taotao.cloud.jetcache.jackson.JacksonKeyConvertor;
import com.taotao.cloud.jetcache.jackson.JacksonValueDecoder;
import com.taotao.cloud.jetcache.jackson.JacksonValueEncoder;
import com.taotao.cloud.jetcache.properties.JetCacheProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * jetcache 配置
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-03 09:50:26
 */
@AutoConfiguration(before = com.alicp.jetcache.autoconfigure.JetCacheAutoConfiguration.class)
@EnableConfigurationProperties(JetCacheProperties.class)
@ConditionalOnProperty(prefix = JetCacheProperties.PREFIX, name = "enabled", havingValue = "true")
public class JetCacheAutoConfiguration implements InitializingBean {

	private ObjectMapper cacheMapper;

	@Bean("jacksonKeyConvertor")
	public JacksonKeyConvertor jacksonKeyConvertor() {
		return new JacksonKeyConvertor(JsonUtil.getInstance());
	}

	@Bean("jacksonValueDecoder")
	public JacksonValueDecoder jacksonValueDecoder() {
		return new JacksonValueDecoder(cacheMapper);
	}

	@Bean("jacksonValueEncoder")
	public JacksonValueEncoder jacksonValueEncoder() {
		return new JacksonValueEncoder(cacheMapper);
	}

	@Bean
	@ConditionalOnMissingBean
	public ConfigMap configMap() {
		return new ConfigMap();
	}

	@Bean
	public SpringConfigProvider springConfigProvider(ApplicationContext applicationContext) {
		DefaultSpringKeyConvertorParser convertorParser = new DefaultSpringKeyConvertorParser();
		convertorParser.setApplicationContext(applicationContext);
		SpringConfigProvider springConfigProvider = new SpringConfigProvider();
		springConfigProvider.setKeyConvertorParser(convertorParser);
		springConfigProvider.setApplicationContext(applicationContext);
		DefaultSpringEncoderParser encoderParser = new DefaultSpringEncoderParser();
		encoderParser.setApplicationContext(applicationContext);
		springConfigProvider.setEncoderParser(encoderParser);
		return springConfigProvider;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ObjectMapper mapper = JsonUtil.getInstance().copy();
		mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
		this.cacheMapper = mapper;
	}

}