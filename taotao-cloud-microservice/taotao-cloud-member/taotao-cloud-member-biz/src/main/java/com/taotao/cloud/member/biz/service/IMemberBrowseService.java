package com.taotao.cloud.member.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.member.biz.entity.MemberBrowse;
import java.util.List;

/**
 * 会员浏览历史业务层
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-03-11 15:53:20
 */
public interface IMemberBrowseService extends IService<MemberBrowse> {

	/**
	 * 保存浏览历史
	 *
	 * @param footPrint 用户足迹
	 * @return 浏览历史
	 */
	MemberBrowse saveFootprint(MemberBrowse footPrint);

	/**
	 * 清空当前会员的足迹
	 *
	 * @return 处理结果
	 */
	boolean clean();

	/**
	 * 根据ID进行清除会员的历史足迹
	 *
	 * @param ids 商品ID列表
	 * @return 处理结果
	 */
	boolean deleteByIds(List<String> ids);

	/**
	 * 获取会员浏览历史分页
	 *
	 * @param pageVO 分页
	 * @return 会员浏览历史列表
	 */
	List<EsGoodsIndex> footPrintPage(PageVO pageVO);

	/**
	 * 获取当前会员的浏览记录数量
	 *
	 * @return 当前会员的浏览记录数量
	 */
	long getFootprintNum();
}