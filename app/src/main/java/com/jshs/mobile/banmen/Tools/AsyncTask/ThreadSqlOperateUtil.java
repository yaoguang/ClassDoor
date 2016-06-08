package com.jshs.mobile.banmen.Tools.AsyncTask;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by leon on 15/8/5.
 */
public class ThreadSqlOperateUtil {

	public static ArrayList<TaskSingleUnit> tasks;
	public static boolean is_sinlge_task_running = false;

	public static void startSingleSqlTask(SqlOperateSingleThread async){
		startSingleSqlTask(async,true, TASK_PRIORITY.DEFAULT_PRIORITY );
	}

	public static void startSingleSqlTask(SqlOperateSingleThread async, boolean isAsync){
		startSingleSqlTask(async,isAsync, TASK_PRIORITY.DEFAULT_PRIORITY);
	}

	public static void startSingleSqlTask(final SqlOperateSingleThread async, boolean isAsync, TASK_PRIORITY aPriority){
		if( tasks == null ){
			tasks = new ArrayList<>();
		}

		if( isAsync ){
			async.setOnOperatetionEndListener(new SqlOperateSingleThread.onOperatetionEnd() {
				@Override
				public void onEnd(SqlOperateSingleThread aThread) {
					aThread.setOnOperatetionEndListener(null);
					if( tasks.size() != 0 ){
						int maxIndex = getMaxIndex();//找到优先级最大的执行
						tasks.get(maxIndex).thread.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);

						tasks.remove(maxIndex);
					}else {
						is_sinlge_task_running = false;
					}
				}
			});

			if( !is_sinlge_task_running ){
				is_sinlge_task_running = true;
				async.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
			}else {
				tasks.add(new TaskSingleUnit(async,aPriority));
			}
		}else {
			try {
				async.runOperate();
			} catch (Exception e) {
				async.onOperatedEnd(e);
			}
			async.onOperatedEnd(null);
		}
	}

	private static int getMaxIndex(){
		if( tasks == null || tasks.size() == 0 ) return 0;
		int maxIndex = 0;
		int len = tasks.size();
		TASK_PRIORITY maxPriority = TASK_PRIORITY.LOW_PRIORITY;
		for (int i = len -1; i > 0; i--) {
			if( tasks.get(i).priority.compareTo(maxPriority) == 1 ){
				maxIndex = i;
				maxPriority = tasks.get(i).priority;
			}
		}
		return maxIndex;
	}

	public static void startThread( ThreadAutoCall threadAutoCall ){
		startThread(threadAutoCall,true);
	}

	public static void startThread( ThreadAutoCall threadAutoCall, boolean isAsync ){
		if( isAsync ){
			try {
				threadAutoCall.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}catch (Exception e){
				e.printStackTrace();
			}
		}else {
			try {
				threadAutoCall.doInThread();
			}catch (Exception e){
				threadAutoCall.onThreadEndCall(e);
				return;
			}
			threadAutoCall.onThreadEndCall(null);
		}
	}
}
