package com.taotao.cloud.sys.biz.timetask.xxljob.statistics;

import com.taotao.cloud.web.timetask.EveryDayExecute;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 会员数据统计
 */
@Slf4j
@Component
public class MemberStatisticsExecute implements EveryDayExecute {

    /**
     * 会员统计
     */
    @Autowired
    private MemberStatisticsService memberStatisticsService;

    @Override
    public void execute() {

        try {
            //统计的时间（开始。结束时间）
            Date startTime, endTime;
            //初始值
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 1);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            endTime = calendar.getTime();
            //-1天，即为开始时间
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
            startTime = calendar.getTime();
            MemberStatisticsData memberStatisticsData = new MemberStatisticsData();
            memberStatisticsData.setMemberCount(memberStatisticsService.memberCount(endTime));
            memberStatisticsData.setCreateDate(startTime);
            memberStatisticsData.setActiveQuantity(memberStatisticsService.activeQuantity(startTime));
            memberStatisticsData.setNewlyAdded(memberStatisticsService.newlyAdded(startTime, endTime));
            memberStatisticsService.save(memberStatisticsData);
        } catch (Exception e) {
            log.error("每日会员统计功能异常：", e);
        }
    }
}