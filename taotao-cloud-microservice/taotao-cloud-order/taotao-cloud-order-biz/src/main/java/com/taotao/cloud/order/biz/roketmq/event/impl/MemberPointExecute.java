package com.taotao.cloud.order.biz.roketmq.event.impl;


import com.google.gson.Gson;
import com.taotao.cloud.common.utils.number.CurrencyUtil;
import com.taotao.cloud.order.api.dto.order.OrderMessage;
import com.taotao.cloud.order.api.enums.order.OrderPromotionTypeEnum;
import com.taotao.cloud.order.api.enums.order.PayStatusEnum;
import com.taotao.cloud.order.api.enums.trade.AfterSaleStatusEnum;
import com.taotao.cloud.order.biz.entity.aftersale.AfterSale;
import com.taotao.cloud.order.biz.entity.order.Order;
import com.taotao.cloud.order.biz.roketmq.event.AfterSaleStatusChangeEvent;
import com.taotao.cloud.order.biz.roketmq.event.OrderStatusChangeEvent;
import com.taotao.cloud.order.biz.service.order.OrderService;
import com.taotao.cloud.sys.api.enums.SettingEnum;
import com.taotao.cloud.sys.api.setting.PointSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员积分
 */
@Service
public class MemberPointExecute implements OrderStatusChangeEvent, AfterSaleStatusChangeEvent {

	/**
	 * 配置
	 */
	@Autowired
	private SettingService settingService;
	/**
	 * 会员
	 */
	@Autowired
	private MemberService memberService;
	/**
	 * 订单
	 */
	@Autowired
	private OrderService orderService;

	/**
	 * 非积分订单订单完成后赠送积分
	 *
	 * @param orderMessage 订单消息
	 */
	@Override
	public void orderChange(OrderMessage orderMessage) {

		switch (orderMessage.getNewStatus()) {
			case CANCELLED: {
				Order order = orderService.getBySn(orderMessage.getOrderSn());
				Long point = order.getPriceDetailDTO().getPayPoint();
				if (point <= 0) {
					return;
				}
				//如果未付款，则不去要退回相关代码执行
				if (order.getPayStatus().equals(PayStatusEnum.UNPAID.name())) {
					return;
				}
				String content = "订单取消，积分返还：" + point + "分";
				//赠送会员积分
				memberService.updateMemberPoint(point, PointTypeEnum.INCREASE.name(),
					order.getMemberId(), content);
				break;
			}
			case COMPLETED: {
				Order order = orderService.getBySn(orderMessage.getOrderSn());
				//如果是积分订单 则直接返回
				if (StringUtils.isNotEmpty(order.getOrderPromotionType())
					&& order.getOrderPromotionType().equals(OrderPromotionTypeEnum.POINTS.name())) {
					return;
				}
				//获取积分设置
				PointSetting pointSetting = getPointSetting();
				if (pointSetting.getConsumer() == 0) {
					return;
				}
				//计算赠送积分数量
				Double point = CurrencyUtil.mul(pointSetting.getConsumer(), order.getFlowPrice(),
					0);
				//赠送会员积分
				memberService.updateMemberPoint(point.longValue(), PointTypeEnum.INCREASE.name(),
					order.getMemberId(), "会员下单，赠送积分" + point + "分");
				break;
			}

			default:
				break;
		}
	}


	/**
	 * 提交售后后扣除积分
	 *
	 * @param afterSale 售后
	 */
	@Override
	public void afterSaleStatusChange(AfterSale afterSale) {
		if (afterSale.getServiceStatus().equals(AfterSaleStatusEnum.COMPLETE.name())) {
			//获取积分设置
			PointSetting pointSetting = getPointSetting();
			//计算扣除积分数量
			Double point = CurrencyUtil.mul(pointSetting.getMoney(),
				afterSale.getActualRefundPrice(), 0);
			//扣除会员积分
			memberService.updateMemberPoint(point.longValue(), PointTypeEnum.REDUCE.name(),
				afterSale.getMemberId(), "会员退款，回退积分" + point + "分");

		}
	}

	/**
	 * 获取积分设置
	 *
	 * @return 积分设置
	 */
	private PointSetting getPointSetting() {
		Setting setting = settingService.get(SettingEnum.POINT_SETTING.name());
		return new Gson().fromJson(setting.getSettingValue(), PointSetting.class);
	}
}