package com.jshs.mobile.banmen.Tools.AsyncTask;

/**
 * Created by leon on 16/4/25.
 */
public class TaskSingleUnit {

	SqlOperateSingleThread thread;
	TASK_PRIORITY priority = TASK_PRIORITY.DEFAULT_PRIORITY; //默认优先级是1

	public TaskSingleUnit(SqlOperateSingleThread thread, TASK_PRIORITY priority) {
		this.thread = thread;
		this.priority = priority;
	}
}
