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

package com.taotao.cloud.goods.biz.listener;

import com.taotao.cloud.stream.framework.rocketmq.RocketmqSendCallbackBuilder;
import com.taotao.cloud.stream.framework.rocketmq.tags.GoodsTagsEnum;
import com.taotao.cloud.stream.properties.RocketmqCustomProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 生成es商品索引监听器
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:18:31
 */
@Component
public class GeneratorEsGoodsIndexListener {

    /** rocketMq */
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /** rocketMq配置 */
    @Autowired
    private RocketmqCustomProperties rocketmqCustomProperties;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void generatorEsGoodsIndex(GeneratorEsGoodsIndexEvent esGoodsIndexEvent) {
        String destination =
                rocketmqCustomProperties.getGoodsTopic() + ":" + GoodsTagsEnum.GENERATOR_GOODS_INDEX.name();

        // 发送mq消息
        rocketMQTemplate.asyncSend(
                destination, esGoodsIndexEvent.getGoodsId(), RocketmqSendCallbackBuilder.commonCallback());
    }
}
