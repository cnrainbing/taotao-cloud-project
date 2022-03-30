package com.taotao.cloud.report.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单统计概述
 */
@Data
public class OrderOverviewVO {

    @Schema(description =  "UV人次")
    private Long uvNum;

    /**
     * 下单统计
     */
    @Schema(description =  "下单数量")
    private Long orderNum;

    @Schema(description =  "下单人数")
    private Long orderMemberNum;

    @Schema(description =  "下单金额")
    private Double orderAmount;

    /**
     * 付款统计
     */
    @Schema(description =  "付款订单数量")
    private Long paymentOrderNum;

    @Schema(description =  "付款人数")
    private Long paymentsNum;

    @Schema(description =  "付款金额")
    private Double paymentAmount;


    /**
     * 退单统计
     */
    @Schema(description =  "退单笔数")
    private Long refundOrderNum;

    @Schema(description =  "退单金额")
    private Double refundOrderPrice;

    /**
     * 转换率
     */
    @Schema(description =  "下单转换率")
    private String orderConversionRate;

    @Schema(description =  "付款转换率")
    private String paymentsConversionRate;

    @Schema(description =  "整体转换率")
    private String overallConversionRate;

    public Long getUvNum() {
        if (uvNum == null) {
            return 0L;
        }
        return uvNum;
    }

    public Long getOrderNum() {
        if (orderNum == null) {
            return 0L;
        }
        return orderNum;
    }

    public Long getOrderMemberNum() {
        if (orderMemberNum == null) {
            return 0L;
        }
        return orderMemberNum;
    }

    public Double getOrderAmount() {
        if (orderAmount == null) {
            return 0D;
        }
        return orderAmount;
    }

    public Long getPaymentOrderNum() {
        if (paymentOrderNum == null) {
            return 0L;
        }
        return paymentOrderNum;
    }

    public Long getPaymentsNum() {
        if (paymentsNum == null) {
            return 0L;
        }
        return paymentsNum;
    }

    public Double getPaymentAmount() {
        if (paymentAmount == null) {
            return 0D;
        }
        return paymentAmount;
    }

    public Long getRefundOrderNum() {
        if (refundOrderNum == null) {
            return 0L;
        }
        return refundOrderNum;
    }

    public Double getRefundOrderPrice() {
        if (refundOrderPrice == null) {
            return 0D;
        }
        return refundOrderPrice;
    }
}