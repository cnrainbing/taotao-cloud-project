package com.taotao.cloud.goods.biz.timetask;

import com.taotao.cloud.goods.biz.service.CommodityService;
import com.taotao.cloud.web.timetask.EveryHourExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 小程序直播状态获取
 *
 */
@Component
public class BroadcastExecute implements EveryHourExecute {

    @Autowired
    private CommodityService commodityService;

    @Override
    public void execute() {
        //同步直播商品状态
        commodityService.getGoodsWareHouse();
    }
}