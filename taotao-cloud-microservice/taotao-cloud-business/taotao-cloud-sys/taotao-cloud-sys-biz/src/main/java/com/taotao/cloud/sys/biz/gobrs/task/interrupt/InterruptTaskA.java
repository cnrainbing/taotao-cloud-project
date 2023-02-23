package com.taotao.cloud.sys.biz.gobrs.task.interrupt;

import com.gobrs.async.core.TaskSupport;
import com.gobrs.async.core.anno.Task;
import com.gobrs.async.core.task.AsyncTask;

/**
 * The type A service.
 *
 * @program: gobrs -async-starter
 * @ClassName AService
 * @description:
 * @author: sizegang
 * @create: 2022 -03-20
 */
@Task(failSubExec = true)
public class InterruptTaskA extends AsyncTask {

	/**
	 * The .
	 */
	int i = 10000;

	@Override
	public void prepare(Object o) {

	}

	@Override
	public String task(Object o, TaskSupport support) {

		try {
			System.out.println("InterruptTaskA Begin");
			Thread.sleep(300);
			for (int i1 = 0; i1 < i; i1++) {
				i1 += i1;
			}
			System.out.println("InterruptTaskA Finish");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "result";
	}

	@Override
	public boolean necessary(Object o, TaskSupport support) {
		return true;
	}


	@Override
	public void onSuccess(TaskSupport support) {

	}

}