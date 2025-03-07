package com.taotao.cloud.payment.biz.daxpay.single.core.exception;

import com.taotao.cloud.payment.biz.daxpay.core.code.DaxPayErrorCode;

/**
 * 支付方式未启用
 * @author xxm
 * @since 2024/6/17
 */
public class MethodNotEnableException extends PayFailureException{

    public MethodNotEnableException(String message) {
        super(DaxPayErrorCode.METHOD_NOT_ENABLE,message);
    }

    public MethodNotEnableException() {
        super(DaxPayErrorCode.METHOD_NOT_ENABLE,"支付方式未启用");
    }
}
