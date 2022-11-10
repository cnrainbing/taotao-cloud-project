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
package com.taotao.cloud.mq.pulsar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-07 20:53:51
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 2011242218927120008L;

	private Long id;

	private Long tradeId;

	private Long goodsId;

	private BigDecimal goodsPrice;

	private Integer number;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

}