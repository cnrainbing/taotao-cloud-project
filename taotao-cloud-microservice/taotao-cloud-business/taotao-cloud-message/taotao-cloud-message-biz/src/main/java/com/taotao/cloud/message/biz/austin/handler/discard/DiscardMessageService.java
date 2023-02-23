package com.taotao.cloud.message.biz.austin.handler.discard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.taotao.cloud.message.biz.austin.common.constant.CommonConstant;
import com.taotao.cloud.message.biz.austin.common.domain.AnchorInfo;
import com.taotao.cloud.message.biz.austin.common.enums.AnchorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 丢弃模板消息
 *
 * @author 3y.
 */
@Service
public class DiscardMessageService {

	private static final String DISCARD_MESSAGE_KEY = "discardMsgIds";

	@Autowired
	private ConfigService config;

	@Autowired
	private LogUtils logUtils;


	/**
	 * 丢弃消息，配置在apollo
	 *
	 * @param taskInfo
	 * @return
	 */
	public boolean isDiscard(TaskInfo taskInfo) {
		// 配置示例:	["1","2"]
		JSONArray array = JSON.parseArray(
			config.getProperty(DISCARD_MESSAGE_KEY, CommonConstant.EMPTY_VALUE_JSON_ARRAY));

		if (array.contains(String.valueOf(taskInfo.getMessageTemplateId()))) {
			logUtils.print(AnchorInfo.builder().businessId(taskInfo.getBusinessId())
				.ids(taskInfo.getReceiver()).state(AnchorState.DISCARD.getCode()).build());
			return true;
		}
		return false;
	}

}