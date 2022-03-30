package com.taotao.cloud.goods.api.enums;

/**
 * 商品审核
 */
public enum GoodsSalesModeEnum {
	/**
	 * 需要审核 并且待审核
	 */
	RETAIL("零售"),
	/**
	 * 审核通过
	 */
	WHOLESALE("批发");

	private final String description;

	GoodsSalesModeEnum(String description) {
		this.description = description;

	}

	public String description() {
		return description;
	}

}