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

package com.taotao.cloud.auth.biz.jpa.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.taotao.cloud.auth.biz.jpa.entity.HerodotusAuthorization;
import com.taotao.cloud.auth.biz.jpa.repository.HerodotusAuthorizationRepository;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * <p>Description: HerodotusAuthorizationService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 *
 *
 */
@Service
public class HerodotusAuthorizationService {

    private static final Logger log = LoggerFactory.getLogger(HerodotusAuthorizationService.class);

    private final HerodotusAuthorizationRepository herodotusAuthorizationRepository;

    @Autowired
    public HerodotusAuthorizationService(HerodotusAuthorizationRepository herodotusAuthorizationRepository) {
        this.herodotusAuthorizationRepository = herodotusAuthorizationRepository;
    }

    public Optional<HerodotusAuthorization> findByState(String state) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByState(state);
        log.info("HerodotusAuthorization Service findByState.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByAuthorizationCode(String authorizationCode) {
        Optional<HerodotusAuthorization> result =
                this.herodotusAuthorizationRepository.findByAuthorizationCodeValue(authorizationCode);
        log.info("HerodotusAuthorization Service findByAuthorizationCode.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByAccessToken(String accessToken) {
        Optional<HerodotusAuthorization> result =
                this.herodotusAuthorizationRepository.findByAccessTokenValue(accessToken);
        log.info("HerodotusAuthorization Service findByAccessToken.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByRefreshToken(String refreshToken) {
        Optional<HerodotusAuthorization> result =
                this.herodotusAuthorizationRepository.findByRefreshTokenValue(refreshToken);
        log.info("HerodotusAuthorization Service findByRefreshToken.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByOidcIdTokenValue(String idToken) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByOidcIdTokenValue(idToken);
        log.info("HerodotusAuthorization Service findByOidcIdTokenValue.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByUserCodeValue(String userCode) {
        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findByUserCodeValue(userCode);
        log.info("HerodotusAuthorization Service findByUserCodeValue.");
        return result;
    }

    public Optional<HerodotusAuthorization> findByDeviceCodeValue(String deviceCode) {
        Optional<HerodotusAuthorization> result =
                this.herodotusAuthorizationRepository.findByDeviceCodeValue(deviceCode);
        log.info("HerodotusAuthorization Service findByDeviceCodeValue.");
        return result;
    }

    public Optional<HerodotusAuthorization>
            findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(
                    String token) {

        Specification<HerodotusAuthorization> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("state"), token));
            predicates.add(criteriaBuilder.equal(root.get("authorizationCodeValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("accessTokenValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("refreshTokenValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("oidcIdTokenValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("userCodeValue"), token));
            predicates.add(criteriaBuilder.equal(root.get("deviceCodeValue"), token));

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(predicateArray)));
            return criteriaQuery.getRestriction();
        };

        Optional<HerodotusAuthorization> result = this.herodotusAuthorizationRepository.findOne(specification);
        log.info("HerodotusAuthorization Service findByDetection.");
        return result;
    }

    public void clearHistoryToken() {
        this.herodotusAuthorizationRepository.deleteByRefreshTokenExpiresAtBefore(LocalDateTime.now());
        log.info("HerodotusAuthorization Service clearExpireAccessToken.");
    }

    public List<HerodotusAuthorization> findAvailableAuthorizations(String registeredClientId, String principalName) {
        List<HerodotusAuthorization> authorizations = this.herodotusAuthorizationRepository
                .findAllByRegisteredClientIdAndPrincipalNameAndAccessTokenExpiresAtAfter(
                        registeredClientId, principalName, LocalDateTime.now());
        log.info("HerodotusAuthorization Service findAvailableAuthorizations.");
        return authorizations;
    }

    public int findAuthorizationCount(String registeredClientId, String principalName) {
        List<HerodotusAuthorization> authorizations = findAvailableAuthorizations(registeredClientId, principalName);
        int count = 0;
        if (CollectionUtils.isNotEmpty(authorizations)) {
            count = authorizations.size();
        }
        log.info("HerodotusAuthorization Service current authorization count is [{}].", count);
        return count;
    }

    public void saveAndFlush(HerodotusAuthorization entity) {
        herodotusAuthorizationRepository.save(entity);
    }

    public void deleteById(String id) {
        herodotusAuthorizationRepository.deleteById(id);
    }

    public HerodotusAuthorization findById(String id) {
        return herodotusAuthorizationRepository.findById(id).orElse(null);
    }

    public void updateAndFlush(HerodotusAuthorization entity) {
        HerodotusAuthorization existingAuthorization = this.findById(entity.getId());
        BeanUtil.copyProperties(
                entity, existingAuthorization, CopyOptions.create().ignoreNullValue());
        // 更新数据
        herodotusAuthorizationRepository.updateBy(existingAuthorization);
    }
}