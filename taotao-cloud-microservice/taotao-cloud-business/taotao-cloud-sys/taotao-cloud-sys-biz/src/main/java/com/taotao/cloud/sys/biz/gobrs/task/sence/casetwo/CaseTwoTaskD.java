package com.taotao.cloud.sys.biz.gobrs.task.sence.casetwo;

import com.gobrs.async.core.TaskSupport;
import com.gobrs.async.core.anno.Task;
import com.gobrs.async.core.task.AsyncTask;

/**
 * @program: gobrs-async
 * @ClassName CaseOneTaskD
 * @description:
 * @author: sizegang
 * @create: 2022-10-31
 **/
@Task
public class CaseTwoTaskD extends AsyncTask {

    @Override
    public Object task(Object o, TaskSupport support) {
        System.out.println("D任务执行");
        return "DResult";
    }
}