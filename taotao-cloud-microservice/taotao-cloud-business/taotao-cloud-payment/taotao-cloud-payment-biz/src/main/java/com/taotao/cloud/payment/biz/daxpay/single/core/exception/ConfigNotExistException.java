package com.taotao.cloud.payment.biz.daxpay.single.core.exception;

import com.taotao.cloud.payment.biz.daxpay.core.code.DaxPayErrorCode;

/**
 * 配置不存在
 * @author xxm
 * @since 2024/6/27
 */
public class ConfigNotExistException extends PayFailureException{

    public ConfigNotExistException(String message) {
        super(DaxPayErrorCode.CONFIG_NOT_EXIST,message);
    }

    public ConfigNotExistException() {
        super(DaxPayErrorCode.CONFIG_NOT_EXIST,"配置不存在");
    }
}
