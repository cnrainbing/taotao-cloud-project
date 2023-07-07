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

package com.taotao.cloud.auth.biz.jpa.storage;

import com.taotao.cloud.auth.biz.jpa.converter.HerodotusToOAuth2RegisteredClientConverter;
import com.taotao.cloud.auth.biz.jpa.converter.OAuth2ToHerodotusRegisteredClientConverter;
import com.taotao.cloud.auth.biz.jpa.entity.HerodotusRegisteredClient;
import com.taotao.cloud.auth.biz.jpa.jackson2.OAuth2JacksonProcessor;
import com.taotao.cloud.auth.biz.jpa.service.HerodotusRegisteredClientService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>Description: 基于Jpa 的 RegisteredClient服务 </p>
 *
 *
 */
public class JpaRegisteredClientRepository implements RegisteredClientRepository {

    private static final Logger log = LoggerFactory.getLogger(JpaRegisteredClientRepository.class);

    private final HerodotusRegisteredClientService herodotusRegisteredClientService;
    private final Converter<HerodotusRegisteredClient, RegisteredClient> herodotusToOAuth2Converter;
    private final Converter<RegisteredClient, HerodotusRegisteredClient> oauth2ToHerodotusConverter;

    public JpaRegisteredClientRepository(
            HerodotusRegisteredClientService herodotusRegisteredClientService, PasswordEncoder passwordEncoder) {
        this.herodotusRegisteredClientService = herodotusRegisteredClientService;
        OAuth2JacksonProcessor jacksonProcessor = new OAuth2JacksonProcessor();
        this.herodotusToOAuth2Converter = new HerodotusToOAuth2RegisteredClientConverter(jacksonProcessor);
        this.oauth2ToHerodotusConverter =
                new OAuth2ToHerodotusRegisteredClientConverter(jacksonProcessor, passwordEncoder);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        log.info("Jpa Registered Client Repository save entity.");
        this.herodotusRegisteredClientService.save(toEntity(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        log.info("Jpa Registered Client Repository findById.");
        HerodotusRegisteredClient herodotusRegisteredClient = this.herodotusRegisteredClientService.findById(id);
        if (ObjectUtils.isNotEmpty(herodotusRegisteredClient)) {
            return toObject(herodotusRegisteredClient);
        }
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        log.info("Jpa Registered Client Repository findByClientId.");
        return this.herodotusRegisteredClientService
                .findByClientId(clientId)
                .map(this::toObject)
                .orElse(null);
    }

    public void remove(String id) {
        log.info("Jpa Registered Client Repository remove.");
        this.herodotusRegisteredClientService.deleteById(id);
    }

    private RegisteredClient toObject(HerodotusRegisteredClient herodotusRegisteredClient) {
        return herodotusToOAuth2Converter.convert(herodotusRegisteredClient);
    }

    private HerodotusRegisteredClient toEntity(RegisteredClient registeredClient) {
        return oauth2ToHerodotusConverter.convert(registeredClient);
    }
}