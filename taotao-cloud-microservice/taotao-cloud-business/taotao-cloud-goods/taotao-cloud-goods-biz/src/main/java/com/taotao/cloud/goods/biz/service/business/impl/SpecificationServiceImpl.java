package com.taotao.cloud.goods.biz.service.business.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.exception.BusinessException;
import com.taotao.cloud.common.utils.lang.StringUtils;
import com.taotao.cloud.goods.api.model.page.SpecificationPageQuery;
import com.taotao.cloud.goods.biz.mapper.ISpecificationMapper;
import com.taotao.cloud.goods.biz.model.entity.CategorySpecification;
import com.taotao.cloud.goods.biz.model.entity.Specification;
import com.taotao.cloud.goods.biz.repository.cls.SpecificationRepository;
import com.taotao.cloud.goods.biz.repository.inf.ISpecificationRepository;
import com.taotao.cloud.goods.biz.service.business.ICategoryService;
import com.taotao.cloud.goods.biz.service.business.ICategorySpecificationService;
import com.taotao.cloud.goods.biz.service.business.ISpecificationService;
import com.taotao.cloud.web.base.service.impl.BaseSuperServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品规格业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:55
 */
@AllArgsConstructor
@Service
public class SpecificationServiceImpl extends
	BaseSuperServiceImpl<ISpecificationMapper, Specification, SpecificationRepository, ISpecificationRepository, Long> implements ISpecificationService {

	/**
	 * 分类-规格绑定服务
	 */
	private final ICategorySpecificationService categorySpecificationService;
	/**
	 * 分类服务
	 */
	private final ICategoryService categoryService;

	@Override
	public Boolean deleteSpecification(List<Long> ids) {
		boolean result = false;
		for (Long id : ids) {
			//如果此规格绑定分类则不允许删除
			List<CategorySpecification> list = categorySpecificationService.list(
				new QueryWrapper<CategorySpecification>().eq("specification_id", id));

			if (!list.isEmpty()) {
				List<Long> categoryIds = new ArrayList<>();
				list.forEach(item -> categoryIds.add(item.getCategoryId()));
				throw new BusinessException(ResultEnum.SPEC_DELETE_ERROR.getCode(),
					JSONUtil.toJsonStr(categoryService.getCategoryNameByIds(categoryIds)));
			}
			//删除规格
			result = this.removeById(id);
		}
		return result;
	}

	@Override
	public IPage<Specification> getPage(SpecificationPageQuery specificationPageQuery) {
		LambdaQueryWrapper<Specification> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.like(StringUtils.isNotEmpty(specificationPageQuery.getSpecName()), Specification::getSpecName,
			specificationPageQuery.getSpecName());
		return this.page(specificationPageQuery.buildMpPage(), lambdaQueryWrapper);
	}

}