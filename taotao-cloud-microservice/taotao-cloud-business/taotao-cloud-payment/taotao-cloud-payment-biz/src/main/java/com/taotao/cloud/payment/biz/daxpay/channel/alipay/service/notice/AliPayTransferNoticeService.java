package com.taotao.cloud.payment.biz.daxpay.channel.alipay.service.notice;

import cn.bootx.platform.core.util.JsonUtil;
import com.taotao.cloud.payment.biz.daxpay.channel.alipay.code.AliPayCode;
import com.taotao.cloud.payment.biz.daxpay.channel.alipay.result.notice.AliPayOrderChangedResult;
import com.taotao.cloud.payment.biz.daxpay.core.enums.CallbackStatusEnum;
import com.taotao.cloud.payment.biz.daxpay.core.enums.ChannelEnum;
import com.taotao.cloud.payment.biz.daxpay.core.enums.TradeTypeEnum;
import com.taotao.cloud.payment.biz.daxpay.core.enums.TransferStatusEnum;
import com.taotao.cloud.payment.biz.daxpay.service.common.context.CallbackLocal;
import com.taotao.cloud.payment.biz.daxpay.service.common.local.PaymentContextLocal;
import com.taotao.cloud.payment.biz.daxpay.service.service.record.callback.TradeCallbackRecordService;
import com.taotao.cloud.payment.biz.daxpay.service.service.trade.transfer.TransferCallbackService;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 支付宝 资金单据状态变更通知(转账消息)
 * @author xxm
 * @since 2024/7/30
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AliPayTransferNoticeService {
    private final TransferCallbackService transferCallbackService;
    private final TradeCallbackRecordService tradeCallbackRecordService;

    /**
     * 资金单据状态变更通知-转账回调
     */
    public String transferHandle(Map<String, String> map){
        // 执行回调数据解析, 返回响应对象
        String msg = this.callback(map);
        // 执行回调业务处理
        transferCallbackService.transferCallback();
        // 保存记录
        tradeCallbackRecordService.saveCallbackRecord();
        return msg;
    }

    /**
     * 回调处理
     */
    public String callback(Map<String, String> map) {
        CallbackLocal callbackInfo = PaymentContextLocal.get().getCallbackInfo();
        callbackInfo.setRawData(JsonUtil.toJsonStr(map))
                .setChannel(ChannelEnum.ALI.getCode())
                .setCallbackType(TradeTypeEnum.TRANSFER);

        // 通过 biz_content 获取值
        try {
            String bizContent = map.get("biz_content");
            var response = JsonUtil.toBean(bizContent, AliPayOrderChangedResult.class);
            callbackInfo.setCallbackData(BeanUtil.beanToMap(response));
            this.resolveData(response);
            return "success";
        } catch (Exception e) {
            log.error("支付宝转账回调异常: {}", e.getMessage());
            callbackInfo.setCallbackStatus(CallbackStatusEnum.EXCEPTION).setCallbackErrorMsg(e.getMessage());
            return "fail";
        }
    }

    /**
     * 解析数据
     */
    public void resolveData(AliPayOrderChangedResult response) {
        CallbackLocal callbackInfo = PaymentContextLocal.get().getCallbackInfo();

        // 设置网关订单号
        callbackInfo.setOutTradeNo(response.getPayFundOrderId());

        String status = response.getStatus();
        //  SUCCESS：转账成功；
        if (Objects.equals(status, AliPayCode.TransferStatus.SUCCESS)){
            callbackInfo.setTradeStatus(TransferStatusEnum.SUCCESS.getCode());
        }
        //  WAIT_PAY：等待支付；DEALING：处理中（适用于"单笔转账到银行卡"）
        if (List.of(AliPayCode.TransferStatus.DEALING, AliPayCode.TransferStatus.WAIT_PAY).contains(status)){
            callbackInfo.setTradeStatus(TransferStatusEnum.PROGRESS.getCode());
        }
        //  FAIL：失败（适用于"单笔转账到银行卡"）；
        if (Objects.equals(AliPayCode.TransferStatus.FAIL, status)){
            callbackInfo.setTradeStatus(TransferStatusEnum.FAIL.getCode()).setTradeErrorMsg(response.getSubOrderFailReason());
        }
        //  REFUND：退票（适用于"单笔转账到银行卡"）； CLOSED：订单超时关闭；
        if (List.of(AliPayCode.TransferStatus.CLOSED, AliPayCode.TransferStatus.REFUND).contains(status)){
            callbackInfo.setTradeStatus(TransferStatusEnum.CLOSE.getCode()).setTradeErrorMsg(response.getSubOrderFailReason());
        }
    }

}
