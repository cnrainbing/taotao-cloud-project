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

package com.taotao.cloud.auth.biz.jpa.converter;

import com.taotao.cloud.auth.biz.jpa.entity.HerodotusRegisteredClient;
import com.taotao.cloud.auth.biz.jpa.jackson2.OAuth2JacksonProcessor;
import java.util.ArrayList;
import java.util.List;
import org.dromara.hutool.core.date.DateUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.StringUtils;

/**
 * <p>Description: RegisteredClient 转 HerodotusRegisteredClient 转换器 </p>
 *
 *
 * @date : 2023/5/12 23:56
 */
public class OAuth2ToHerodotusRegisteredClientConverter
        extends AbstractOAuth2EntityConverter<RegisteredClient, HerodotusRegisteredClient> {

    private final PasswordEncoder passwordEncoder;

    public OAuth2ToHerodotusRegisteredClientConverter(
            OAuth2JacksonProcessor jacksonProcessor, PasswordEncoder passwordEncoder) {
        super(jacksonProcessor);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public HerodotusRegisteredClient convert(RegisteredClient registeredClient) {
        List<String> clientAuthenticationMethods = new ArrayList<>(
                registeredClient.getClientAuthenticationMethods().size());
        registeredClient
                .getClientAuthenticationMethods()
                .forEach(clientAuthenticationMethod ->
                        clientAuthenticationMethods.add(clientAuthenticationMethod.getValue()));

        List<String> authorizationGrantTypes =
                new ArrayList<>(registeredClient.getAuthorizationGrantTypes().size());
        registeredClient
                .getAuthorizationGrantTypes()
                .forEach(authorizationGrantType -> authorizationGrantTypes.add(authorizationGrantType.getValue()));

        HerodotusRegisteredClient entity = new HerodotusRegisteredClient();
        entity.setId(registeredClient.getId());
        entity.setClientId(registeredClient.getClientId());
        entity.setClientIdIssuedAt(DateUtil.toLocalDateTime(registeredClient.getClientIdIssuedAt()));
        entity.setClientSecret(encode(registeredClient.getClientSecret()));
        entity.setClientSecretExpiresAt(DateUtil.toLocalDateTime(registeredClient.getClientSecretExpiresAt()));
        entity.setClientName(registeredClient.getClientName());
        entity.setClientAuthenticationMethods(
                StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
        entity.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
        entity.setRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));
        entity.setPostLogoutRedirectUris(
                StringUtils.collectionToCommaDelimitedString(registeredClient.getPostLogoutRedirectUris()));
        entity.setScopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()));
        entity.setClientSettings(writeMap(registeredClient.getClientSettings().getSettings()));
        entity.setTokenSettings(writeMap(registeredClient.getTokenSettings().getSettings()));

        return entity;
    }

    private String encode(String value) {
        if (value != null) {
            return this.passwordEncoder.encode(value);
        }
        return null;
    }
}