package com.taotao.cloud.payment.biz.daxpay.single.service.service.notice.notify;

import cn.bootx.platform.common.jackson.util.JacksonUtil;
import cn.bootx.platform.starter.redis.delay.service.DelayJobService;
import com.taotao.cloud.payment.biz.daxpay.core.enums.MerchantNotifyTypeEnum;
import com.taotao.cloud.payment.biz.daxpay.service.code.DaxPayCode;
import com.taotao.cloud.payment.biz.daxpay.service.common.context.MchAppLocal;
import com.taotao.cloud.payment.biz.daxpay.service.common.local.PaymentContextLocal;
import com.taotao.cloud.payment.biz.daxpay.service.convert.order.pay.PayOrderConvert;
import com.taotao.cloud.payment.biz.daxpay.service.convert.order.refund.RefundOrderConvert;
import com.taotao.cloud.payment.biz.daxpay.service.convert.order.transfer.TransferOrderConvert;
import com.taotao.cloud.payment.biz.daxpay.service.dao.notice.notify.MerchantNotifyTaskManager;
import com.taotao.cloud.payment.biz.daxpay.service.entity.notice.notify.MerchantNotifyTask;
import com.taotao.cloud.payment.biz.daxpay.service.entity.order.pay.PayOrder;
import com.taotao.cloud.payment.biz.daxpay.service.entity.order.refund.RefundOrder;
import com.taotao.cloud.payment.biz.daxpay.service.entity.order.transfer.TransferOrder;
import com.taotao.cloud.payment.biz.daxpay.service.enums.NotifyContentTypeEnum;
import com.taotao.cloud.payment.biz.daxpay.service.service.config.MerchantNotifyConfigService;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 商户订阅消息通知服务类
 * @author xxm
 * @since 2024/7/30
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantNotifyTaskService {

    private final MerchantNotifyTaskManager taskManager;

    private final MerchantNotifyConfigService notifyConfigService;

    private final DelayJobService delayJobService;

    /**
     * 注册支付通知
     */
    public void registerPayNotice(PayOrder order) {
        if (this.nonRegister(NotifyContentTypeEnum.PAY)){
            return;
        }
        var noticeResult = PayOrderConvert.CONVERT.toResult(order);
        var task = new MerchantNotifyTask()
                // 时间序列化进行了重写, 所以使用Jackson的序列化工具类
                .setContent(JacksonUtil.toJson(noticeResult))
                .setNotifyType(NotifyContentTypeEnum.PAY.getCode())
                .setSendCount(0)
                .setTradeId(order.getId())
                .setTradeNo(order.getOrderNo());
        taskManager.save(task);
        delayJobService.registerByTransaction(task.getId(), DaxPayCode.Event.MERCHANT_NOTIFY_SENDER, 0);
        log.info("注册支付通知");
    }

    /**
     * 注册退款通知
     */
    public void registerRefundNotice(RefundOrder order) {
        if (this.nonRegister(NotifyContentTypeEnum.REFUND)){
            log.info("支付退款无需回调，订单号：{}",order.getRefundNo());
            return;
        }
        var noticeResult = RefundOrderConvert.CONVERT.toResult(order);
        var task = new MerchantNotifyTask()
                // 时间序列化进行了重写, 所以使用Jackson的序列化工具类
                .setContent(JacksonUtil.toJson(noticeResult))
                .setNotifyType(NotifyContentTypeEnum.REFUND.getCode())
                .setSendCount(0)
                .setTradeId(order.getId())
                .setTradeNo(order.getRefundNo());
        taskManager.save(task);
        delayJobService.registerByTransaction(task.getId(), DaxPayCode.Event.MERCHANT_NOTIFY_SENDER, 0);
        log.info("注册退款通知");
    }

    /**
     * 注册转账通知
     */
    public void registerTransferNotice(TransferOrder order) {
        if (this.nonRegister(NotifyContentTypeEnum.TRANSFER)){
            return;
        }
        var noticeResult = TransferOrderConvert.CONVERT.toResult(order);
        var task = new MerchantNotifyTask()
                // 时间序列化进行了重写, 所以使用Jackson的序列化工具类
                .setContent(JacksonUtil.toJson(noticeResult))
                .setNotifyType(NotifyContentTypeEnum.TRANSFER.getCode())
                .setSendCount(0)
                .setTradeId(order.getId())
                .setTradeNo(order.getTransferNo());
        taskManager.save(task);
        delayJobService.registerByTransaction(task.getId(), DaxPayCode.Event.MERCHANT_NOTIFY_SENDER, 0);
        log.info("注册转账通知");
    }

    /**
     * 判断是否 不需要注册通知
     * true 不需要
     * false 需要
     */
    private boolean nonRegister(NotifyContentTypeEnum notifyType) {
        MchAppLocal mchAppInfo = PaymentContextLocal.get().getMchAppInfo();

        // 判断是否开启消息通知功能, 不需要注册通知
        if (!Objects.equals(mchAppInfo.getNotifyType(), MerchantNotifyTypeEnum.HTTP.getCode())){
            return true;
        }

        // http方式且地址
        if (StrUtil.isBlank(mchAppInfo.getNotifyUrl())){
            return true;
        }
        // 判断是否订阅该类型的通知
        return !notifyConfigService.getSubscribeByAppIdAndType(mchAppInfo.getAppId(), notifyType.getCode());
    }

}
