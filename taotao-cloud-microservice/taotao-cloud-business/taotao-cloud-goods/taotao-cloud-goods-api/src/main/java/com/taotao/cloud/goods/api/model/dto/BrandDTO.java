package com.taotao.cloud.goods.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 品牌VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "品牌VO")
public class BrandDTO {

	private static final long serialVersionUID = 3829199991161122317L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "名称")
	@NotBlank(message = "名称不能为空")
	private String name;

	@Schema(description = "logo")
	@NotBlank(message = "logo不能为空")
	private String logo;

}