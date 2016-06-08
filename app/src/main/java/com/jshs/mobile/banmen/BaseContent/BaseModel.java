package com.jshs.mobile.banmen.BaseContent;

import java.io.Serializable;

/**
 * Created by SZH on 2016/6/4.
 */
public abstract class BaseModel implements Serializable{
	public String TAG() {
		return getClass().getSimpleName();
	}
}
