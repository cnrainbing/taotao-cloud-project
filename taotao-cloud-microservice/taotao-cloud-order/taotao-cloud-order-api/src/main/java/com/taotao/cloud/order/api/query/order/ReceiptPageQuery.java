package com.taotao.cloud.order.api.query.order;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taotao.cloud.common.model.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 发票搜索参数
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-21 16:59:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "发票搜索参数")
public class ReceiptPageQuery extends PageParam {

	@Schema(description = "发票抬头")
	private String receiptTitle;

	@Schema(description = "纳税人识别号")
	private String taxpayerId;

	@Schema(description = "会员ID")
	private String memberId;

	@Schema(description = "会员名称")
	private String memberName;

	@Schema(description = "店铺名称")
	private String storeName;

	@Schema(description = "商家ID")
	private String storeId;

	@Schema(description = "订单号")
	private String orderSn;

	@Schema(description = "发票状态")
	private String receiptStatus;

	public <T> QueryWrapper<T> wrapper() {
		QueryWrapper<T> queryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotEmpty(receiptTitle)) {
			queryWrapper.like("r.receipt_title", receiptTitle);
		}
		if (StrUtil.isNotEmpty(taxpayerId)) {
			queryWrapper.like("r.taxpayer_id", taxpayerId);
		}
		if (StrUtil.isNotEmpty(memberId)) {
			queryWrapper.eq("r.member_id", memberId);
		}
		if (StrUtil.isNotEmpty(storeName)) {
			queryWrapper.like("r.store_name", storeName);
		}
		if (StrUtil.isNotEmpty(storeId)) {
			queryWrapper.eq("r.store_id", storeId);
		}
		if (StrUtil.isNotEmpty(memberName)) {
			queryWrapper.like("r.member_name", memberName);
		}
		if (StrUtil.isNotEmpty(receiptStatus)) {
			queryWrapper.like("r.receipt_status", receiptStatus);
		}
		if (StrUtil.isNotEmpty(orderSn)) {
			queryWrapper.like("r.order_sn", orderSn);
		}
		queryWrapper.eq("r.delete_flag", false);
		return queryWrapper;
	}

}