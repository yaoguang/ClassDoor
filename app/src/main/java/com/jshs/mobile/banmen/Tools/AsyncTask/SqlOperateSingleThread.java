package com.jshs.mobile.banmen.Tools.AsyncTask;

import android.os.AsyncTask;

public abstract class SqlOperateSingleThread extends AsyncTask {

	public Object tag;

	public abstract void runOperate() throws Exception;
	public abstract void onOperatedEnd(Exception e);

	public SqlOperateSingleThread() {
	}

	public SqlOperateSingleThread(Object tag) {
		this.tag = tag;
	}

	@Override
	protected Object doInBackground(Object[] objects) {
		Exception exception = null;
		try {
			runOperate();
		} catch (Exception e) {
			exception = e;
		}
		return exception;
	}

	@Override
	protected void onPostExecute(Object o) {
		onOperatedEnd((Exception) o);
		if( mOperatetionEnd != null ) mOperatetionEnd.onEnd(this);
		super.onPostExecute(o);
	}

	public interface onOperatetionEnd{
		void onEnd(SqlOperateSingleThread aThread);
	}

	private onOperatetionEnd mOperatetionEnd;

	public void setOnOperatetionEndListener(onOperatetionEnd aLsner){
		mOperatetionEnd = aLsner;
	}
}
