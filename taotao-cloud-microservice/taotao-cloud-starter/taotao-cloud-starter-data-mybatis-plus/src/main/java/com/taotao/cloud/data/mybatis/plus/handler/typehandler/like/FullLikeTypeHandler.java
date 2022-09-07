package com.taotao.cloud.data.mybatis.plus.handler.typehandler.like;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.taotao.cloud.data.mybatis.plus.handler.typehandler.like.BaseLikeTypeHandler;
import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 * @author zuihou
 */
@Alias("fullLike")
public class FullLikeTypeHandler extends BaseLikeTypeHandler {
    public FullLikeTypeHandler() {
        super(SqlLike.DEFAULT);
    }
}