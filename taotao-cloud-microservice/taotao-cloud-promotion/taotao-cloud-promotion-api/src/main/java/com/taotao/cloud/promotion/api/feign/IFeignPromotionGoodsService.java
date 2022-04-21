/*
 * Copyright 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
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
package com.taotao.cloud.promotion.api.feign;

import com.taotao.cloud.common.constant.ServiceName;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.promotion.api.feign.fallback.FeignPromotionGoodsServiceFallback;
import com.taotao.cloud.promotion.api.query.PromotionGoodsSearchParams;
import com.taotao.cloud.promotion.api.vo.PromotionGoodsVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 远程调用售后模块
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@FeignClient(contextId = "IFeignPromotionGoodsService", value = ServiceName.TAOTAO_CLOUD_AFTERSALE_CENTER, fallbackFactory = FeignPromotionGoodsServiceFallback.class)
public interface IFeignPromotionGoodsService {

	@GetMapping(value = "/withdraw/info")
	Result<PromotionGoodsVO> getPromotionsGoods(PromotionGoodsSearchParams searchParams);

}
