package com.taotao.cloud.office.execl.demo.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.taotao.cloud.common.utils.log.LogUtil;
import java.util.List;

/**
 * 读取头
 *

 */

public class CellDataDemoHeadDataListener implements ReadListener<CellDataReadDemoData> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private List<CellDataReadDemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(CellDataReadDemoData data, AnalysisContext context) {
        LogUtil.info("解析到一条数据:{}", JSON.toJSONString(data));
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LogUtil.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LogUtil.info("{}条数据，开始存储数据库！", cachedDataList.size());
        LogUtil.info("存储数据库成功！");
    }
}