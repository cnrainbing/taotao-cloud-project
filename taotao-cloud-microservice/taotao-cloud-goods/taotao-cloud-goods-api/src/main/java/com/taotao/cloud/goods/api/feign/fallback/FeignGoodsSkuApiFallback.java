package com.taotao.cloud.goods.api.feign.fallback;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.goods.api.feign.IFeignGoodsSkuApi;
import com.taotao.cloud.goods.api.model.vo.GoodsSkuSpecGalleryVO;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;

/**
 * FeignGoodsSkuServiceFallback
 *
 * @author shuigedeng
 * @since 2020/4/29 21:43
 */
public class FeignGoodsSkuApiFallback implements FallbackFactory<IFeignGoodsSkuApi> {
	@Override
	public IFeignGoodsSkuApi create(Throwable throwable) {
		return new IFeignGoodsSkuApi() {
			@Override
			public Result<Boolean> updateGoodsStuck(List<GoodsSkuSpecGalleryVO> goodsSkus) {
				return null;
			}

			@Override
			public Result<Boolean> updateBatchById(List<GoodsSkuSpecGalleryVO> goodsSkus) {
				return null;
			}

			@Override
			public Result<List<GoodsSkuSpecGalleryVO>> getGoodsSkuByIdFromCache(List<Long> skuIds) {
				return null;
			}

			@Override
			public Result<GoodsSkuSpecGalleryVO> getGoodsSkuByIdFromCache(Long skuId) {
				return null;
			}

			@Override
			public Result<Integer> getStock(String skuId) {
				return null;
			}
		};
	}
}