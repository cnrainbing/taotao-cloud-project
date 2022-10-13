package com.taotao.cloud.order.biz.repository.cls.trade;

import com.taotao.cloud.order.biz.model.entity.order.OrderLog;
import com.taotao.cloud.web.base.repository.BaseCrSuperRepository;

import javax.persistence.EntityManager;

/**
 * 订单日志数据处理层
 */
public class OrderLogRepository extends BaseCrSuperRepository<OrderLog, Long> {

	public OrderLogRepository(EntityManager em) {
		super(OrderLog.class, em);
	}


}