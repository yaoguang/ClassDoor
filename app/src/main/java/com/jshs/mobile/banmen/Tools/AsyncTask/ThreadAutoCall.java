package com.jshs.mobile.banmen.Tools.AsyncTask;

import android.os.AsyncTask;

/**
 * Created by leon on 15/8/11.
 */
public abstract class ThreadAutoCall extends AsyncTask {

	public abstract void doInThread() throws Exception;
	public abstract void onThreadEndCall( Exception e );

	@Override
	protected Object doInBackground(Object[] objects) {
		Exception exception = null;
		try {
			doInThread();
		} catch (Exception e) {
			exception = e;
		}
		return exception;
	}

	@Override
	protected void onPostExecute(Object o) {
		onThreadEndCall((Exception) o);
		super.onPostExecute(o);
	}
}
