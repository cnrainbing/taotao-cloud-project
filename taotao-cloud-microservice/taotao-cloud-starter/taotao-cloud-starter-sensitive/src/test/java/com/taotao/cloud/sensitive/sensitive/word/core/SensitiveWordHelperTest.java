package com.taotao.cloud.sensitive.sensitive.word.core;

import com.taotao.cloud.core.sensitive.word.api.ISensitiveWordReplace;
import com.taotao.cloud.core.sensitive.word.api.IWordResult;
import com.taotao.cloud.core.sensitive.word.replace.MySensitiveWordReplace;
import com.taotao.cloud.core.sensitive.word.support.result.WordResultHandlers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SensitiveWordHelperTest {

    /**
     * 是否包含
     */
    @Test
    public void containsTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

		Assertions.assertTrue(SensitiveWordHelper.contains(text));
    }

    /**
     * 返回所有敏感词
     */
    @Test
    public void findAllTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        List<String> wordList = SensitiveWordHelper.findAll(text);
        Assertions.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
    }

    /**
     * 返回所有敏感词
     */
    @Test
    public void findAllWordTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        List<String> wordList = SensitiveWordHelper.findAll(text, WordResultHandlers.word());
        Assertions.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
    }

    /**
     * 返回所有敏感词-包含下标志
     */
    @Test
    public void findAllRawTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        List<IWordResult> wordList = SensitiveWordHelper.findAll(text, WordResultHandlers.raw());
        Assertions.assertEquals("[WordResult{word='五星红旗', startIndex=0, endIndex=4}, WordResult{word='毛主席', startIndex=9, endIndex=12}, WordResult{word='天安门', startIndex=18, endIndex=21}]", wordList.toString());
    }


    /**
     * 返回所有第一个匹配的敏感词
     */
    @Test
    public void findFirstTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String word = SensitiveWordHelper.findFirst(text);
        Assertions.assertEquals("五星红旗", word);
    }

    /**
     * 返回所有第一个匹配的敏感词
     */
    @Test
    public void findFirstWordTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String word = SensitiveWordHelper.findFirst(text, WordResultHandlers.word());
        Assertions.assertEquals("五星红旗", word);
    }

    /**
     * 返回所有第一个匹配的敏感词
     */
    @Test
    public void findFirstRawTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        IWordResult word = SensitiveWordHelper.findFirst(text, WordResultHandlers.raw());
        Assertions.assertEquals("WordResult{word='五星红旗', startIndex=0, endIndex=4}", word.toString());
    }

    /**
     * 默认的替换策略
     */
    @Test
    public void replaceTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String result = SensitiveWordHelper.replace(text);
        Assertions.assertEquals("****迎风飘扬，***的画像屹立在***前。", result);
    }

    /**
     * 自定义字符的替换策略
     */
    @Test
    public void replaceCharTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String result = SensitiveWordHelper.replace(text, '0');
        Assertions.assertEquals("0000迎风飘扬，000的画像屹立在000前。", result);
    }

    /**
     * 忽略大小写
     */
    @Test
    public void ignoreCaseTest() {
        final String text = "fuCK the bad words.";

        String word = SensitiveWordHelper.findFirst(text);
        Assertions.assertEquals("fuCK", word);
    }

    /**
     * 忽略半角圆角
     */
    @Test
    public void ignoreWidthTest() {
        final String text = "ｆｕｃｋ the bad words.";

        String word = SensitiveWordHelper.findFirst(text);
        Assertions.assertEquals("ｆｕｃｋ", word);
    }

    /**
     * 自定替换策略
     */
    @Test
    public void defineReplaceTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        ISensitiveWordReplace replace = new MySensitiveWordReplace();
        String result = SensitiveWordHelper.replace(text, replace);

        Assertions.assertEquals("国家旗帜迎风飘扬，教员的画像屹立在***前。", result);
    }

}