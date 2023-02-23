package com.taotao.cloud.operation.biz.controller.business.buyer;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.operation.biz.model.entity.Feedback;
import com.taotao.cloud.operation.biz.service.business.FeedbackService;
import com.taotao.cloud.web.request.annotation.RequestLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.shardingsphere.distsql.parser.autogen.CommonDistSQLStatementParser.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,意见反馈接口
 */
@RestController
@Tag(name = "买家端,意见反馈接口")
@RequestMapping("/buyer/other/feedback")
public class FeedbackBuyerController {

	/**
	 * 意见反馈
	 */
	@Autowired
	private FeedbackService feedbackService;

	@RequestLogger
	@PreAuthorize("hasAuthority('sys:resource:info:roleId')")
	@PreventDuplicateSubmissions
	@Operation(summary = "添加意见反馈")
	@PostMapping()
	public Result<Object> save(@Valid Feedback feedback) {
		feedback.setUserName(UserContext.getCurrentUser().getNickName());
		feedbackService.save(feedback);
		return Result.success();
	}

}