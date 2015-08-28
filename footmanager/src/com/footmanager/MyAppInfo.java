package com.footmanager;

import com.footmanager.util.SysUser;

import android.app.Application;

public class MyAppInfo extends Application {
	private SysUser user;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
	
	

}
