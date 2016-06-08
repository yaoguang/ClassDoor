package com.jshs.mobile.banmen.BaseContent;

import org.greenrobot.eventbus.EventBus;


public class EventCenter {

	public static void post(BaseEvent event){
		EventBus.getDefault().post(event);
	}

	public static void register( Object aObject ){
		EventBus.getDefault().register(aObject);
	}

	public static void unRegister( Object aObject ){
		EventBus.getDefault().unregister(aObject);
	}
}
