package com.taotao.cloud.order.api.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 交易投诉 参数
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "交易投诉 参数")
public class OrderComplaintOperationDTO {

	@Schema(description = "要更改的状态状态")
	private String complainStatus;

	@Schema(description = "交易投诉主键")
	private Long complainId;

	@Schema(description = "商家申诉内容")
	private String appealContent;

	@Schema(description = "商家申诉上传的图片")
	private List<String> images;

	@Schema(description = "仲裁结果")
	private String arbitrationResult;

}