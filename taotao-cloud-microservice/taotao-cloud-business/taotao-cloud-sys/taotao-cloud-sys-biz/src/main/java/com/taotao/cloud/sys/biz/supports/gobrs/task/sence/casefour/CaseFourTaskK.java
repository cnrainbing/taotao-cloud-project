/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.sys.biz.supports.gobrs.task.sence.casefour;

import com.gobrs.async.core.TaskSupport;
import com.gobrs.async.core.anno.Task;
import com.gobrs.async.core.task.AsyncTask;
import com.taotao.boot.common.utils.log.LogUtils;

/**
 * @program: gobrs-async @ClassName GobrsTaskB
 * @description:
 * @author: sizegang
 * @create: 2022-10-31
 */
@Task
public class CaseFourTaskK extends AsyncTask {

    @Override
    public Object task(Object o, TaskSupport support) {
        LogUtils.info("K任务执行");
        return "KResult";
    }
}
