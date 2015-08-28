package com.footmanager.manager.myaccount;

import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MyAccountIntoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_account_into_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_account_into, menu);
		return true;
	}

}
