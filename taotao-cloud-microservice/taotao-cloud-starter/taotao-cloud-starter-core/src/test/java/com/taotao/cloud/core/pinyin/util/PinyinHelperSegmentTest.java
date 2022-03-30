package com.taotao.cloud.core.pinyin.util;

import com.taotao.cloud.common.utils.pinyin.util.PinyinHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * 分词测试
 */
public class PinyinHelperSegmentTest {

    /**
     * 默认格式-中文测试
     */
    @Test
    public void toPinyinDefaultTest() {
        String pinyin = PinyinHelper.toPinyin("重庆火锅");
        Assert.assertEquals("chóng qìng huǒ guō", pinyin);

        String pinyin2 = PinyinHelper.toPinyin("分词也很重要");
        Assert.assertEquals("fēn cí yě hěn zhòng yào", pinyin2);
    }

}