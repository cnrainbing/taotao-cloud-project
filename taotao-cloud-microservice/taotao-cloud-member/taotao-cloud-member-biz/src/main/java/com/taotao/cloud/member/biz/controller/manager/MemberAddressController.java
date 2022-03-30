package com.taotao.cloud.member.biz.controller.manager;

import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.model.PageModel;
import com.taotao.cloud.common.model.PageParam;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.logger.annotation.RequestLogger;
import com.taotao.cloud.member.api.vo.MemberAddressVO;
import com.taotao.cloud.member.biz.entity.MemberAddress;
import com.taotao.cloud.member.biz.service.IMemberAddressService;
import com.taotao.cloud.member.biz.service.memberAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 管理端,会员地址API
 *
 * 
 * @since 2020-02-25 14:10:16
 */
@Validated
@RestController
@RequestMapping("/member/manager/member-address")
@Tag(name = "管理端-会员地址API", description = "管理端-会员地址API")
public class MemberAddressController {

    @Autowired
    private IMemberAddressService memberAddressService;

	@Operation(summary = "会员地址分页列表", description = "会员地址分页列表", method = CommonConstant.GET)
	@RequestLogger(description = "会员地址分页列表")
	@PreAuthorize("@el.check('admin','timing:list')")
    @GetMapping("/{memberId}")
    public Result<PageModel<MemberAddressVO>> getByPage(@Validated PageParam page,
		@Parameter(description = "会员地址ID", required = true) @PathVariable("memberId") String memberId) {
        return Result.success(memberAddressService.getAddressByMember(page, memberId));
    }

	@Operation(summary = "删除会员收件地址", description = "删除会员收件地址", method = CommonConstant.DELETE)
	@RequestLogger(description = "删除会员收件地址")
	@PreAuthorize("@el.check('admin','timing:list')")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delShippingAddressById(
		@Parameter(description = "会员地址ID", required = true)@PathVariable String id) {
        return Result.success(memberAddressService.removeMemberAddress(id));
    }

	@Operation(summary = "修改会员收件地址", description = "修改会员收件地址", method = CommonConstant.PUT)
	@RequestLogger(description = "修改会员收件地址")
	@PreAuthorize("@el.check('admin','timing:list')")
    @PutMapping
    public Result<Boolean> editShippingAddress(@Valid MemberAddress shippingAddress) {
        //修改会员地址
        return Result.success(memberAddressService.updateMemberAddress(shippingAddress));
    }

	@Operation(summary = "新增会员收件地址", description = "新增会员收件地址", method = CommonConstant.POST)
	@RequestLogger(description = "新增会员收件地址")
	@PreAuthorize("@el.check('admin','timing:list')")
    @PostMapping
    public Result<Boolean> addShippingAddress(@Valid MemberAddress shippingAddress) {
        //添加会员地址
        return Result.success(memberAddressService.saveMemberAddress(shippingAddress));
    }


}