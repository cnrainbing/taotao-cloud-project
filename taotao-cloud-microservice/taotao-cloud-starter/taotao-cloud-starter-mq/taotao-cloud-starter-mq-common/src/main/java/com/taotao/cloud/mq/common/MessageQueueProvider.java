package com.taotao.cloud.mq.common;


import com.taotao.cloud.mq.common.producer.MessageQueueProducerException;
import com.taotao.cloud.mq.common.producer.MessageSendCallback;
import com.taotao.cloud.mq.common.producer.MessageSendResult;

/**
 * 消息队列生产者
 */
public interface MessageQueueProvider {

	/**
	 * 同步发送消息
	 *
	 * @param message
	 * @return
	 * @throws MessageQueueProducerException
	 */
	MessageSendResult syncSend(Message message) throws MessageQueueProducerException;

	/**
	 * 异步发送消息
	 *
	 * @param message
	 * @param messageCallback
	 * @throws MessageQueueProducerException
	 */
	void asyncSend(Message message, MessageSendCallback messageCallback)
		throws MessageQueueProducerException;
}