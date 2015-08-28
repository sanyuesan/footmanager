package com.footmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.footmanager.leftnavigation.About;
import com.footmanager.leftnavigation.Desktop;
import com.footmanager.leftnavigation.Person;
import com.footmanager.leftnavigation.Desktop.onChangeViewListener;
import com.footmanager.leftnavigation.Home;
import com.footmanager.leftnavigation.Opinion;
import com.footmanager.util.FlipperLayout;
import com.footmanager.util.FlipperLayout.OnOpenListener;
import com.footmanager.util.SysUser;
import com.footmanager.util.ViewUtil;

public class MainActivity extends Activity implements OnOpenListener {
	/**
	 * 当前显示内容的容器(继承于ViewGroup)
	 */
	private FlipperLayout mRoot;
	/**
	 * 菜单界面
	 */
	private Desktop mDesktop;
	/**
	 * 内容首页界面
	 */
	private Home mHome;
	private Person mPerson;
	private About mAbout;
	private Opinion mOpinion;
	/**
	 * 当前显示的View的编号
	 */
	private int mViewPosition;
	/**
	 * 退出时间
	 */
	private long mExitTime;
	/**
	 * 退出间隔
	 */
	private static final int INTERVAL = 2000;
	
	private SysUser user;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * 创建容器,并设置全屏大小
		 */
		mRoot = new FlipperLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		mRoot.setLayoutParams(params);
		/**
		 * 创建菜单界面和内容首页界面,并添加到容器中,用于初始显示
		 */
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    user = appInfo.getUser();
		mDesktop = new Desktop(this, this,user);
		mHome = new Home(this, this,user);
		mRoot.addView(mDesktop.getView(), params);
		mRoot.addView(mHome.getView(), params);
		setContentView(mRoot);
		setListener();
		
		
		
		
	}
	
	/**
	 * UI事件监听
	 */
	private void setListener() {
		mHome.setOnOpenListener(this);
		/**
		 * 监听菜单界面切换显示内容(onChangeViewListener接口在Desktop中定义)
		 */
		mDesktop.setOnChangeViewListener(new onChangeViewListener() {

			public void onChangeView(int arg0) {
				mViewPosition = arg0;
				switch (arg0) {
				case ViewUtil.HOME:
					mRoot.close(mHome.getView());
					break;
				case ViewUtil.PERSON:
					if (mPerson == null) {
						mPerson = new Person(MainActivity.this,MainActivity.this,user);
						mPerson.setOnOpenListener(MainActivity.this);
					}
					mRoot.close(mPerson.getView());
					break;
				case ViewUtil.ABOUT:
					if (mAbout == null) {
						mAbout = new About(MainActivity.this,MainActivity.this,user);
						mAbout.setOnOpenListener(MainActivity.this);
					}
					mRoot.close(mAbout.getView());
					break;
				case ViewUtil.OPINION:
					if (mOpinion == null) {
						mOpinion = new Opinion(MainActivity.this,MainActivity.this,user);
						mOpinion.setOnOpenListener(MainActivity.this);
					}
					mRoot.close(mOpinion.getView());
					break;
				case ViewUtil.EXPERTS:
					startActivity(new Intent(MainActivity.this,LoginActivity.class));
					break;
				default:
					break;
				}
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * 返回键监听
	 */
	public void onBackPressed() {
		/**
		 * 如果界面的path菜单没有关闭时,先将path菜单关闭,否则则判断两次返回时间间隔,小于两秒则退出程序
		 */
		exit();
	}
	
	/**
	 * 判断两次返回时间间隔,小于两秒则退出程序
	 */
	private void exit() {
		if (System.currentTimeMillis() - mExitTime > INTERVAL) {
			Toast.makeText(this, "再按一次返回键,可直接退出程序", Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();
		} else {
			finish();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
	}

	@Override
	public void open() {
		if (mRoot.getScreenState() == FlipperLayout.SCREEN_STATE_CLOSE) {
			mRoot.open();
		}		
	}

}
