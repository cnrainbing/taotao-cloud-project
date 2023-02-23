package com.taotao.cloud.distribution.api.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 分销商品信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "分销商品信息")
public class DistributionGoodsVO {

	@Schema(description = "分销商品ID")
	private String id;

	@Schema(description = "商品名称")
	private String goodsName;

	@Schema(description = "规格")
	private String specs;

	@Schema(description = "库存")
	private Integer quantity;

	@Schema(description = "商品图片")
	private String thumbnail;

	@Schema(description = "商品价格")
	private BigDecimal price;

	@Schema(description = "商品编号")
	private String sn;

	@Schema(description = "商品ID")
	private String goodsId;

	@Schema(description = "规格ID")
	private String skuId;

	@Schema(description = "店铺名称")
	private String storeName;

	@Schema(description = "佣金金额")
	private BigDecimal commission;

	@Schema(description = "添加时间")
	private LocalDateTime createTime;

}