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
	 * ��ǰ��ʾ���ݵ�����(�̳���ViewGroup)
	 */
	private FlipperLayout mRoot;
	/**
	 * �˵�����
	 */
	private Desktop mDesktop;
	/**
	 * ������ҳ����
	 */
	private Home mHome;
	private Person mPerson;
	private About mAbout;
	private Opinion mOpinion;
	/**
	 * ��ǰ��ʾ��View�ı��
	 */
	private int mViewPosition;
	/**
	 * �˳�ʱ��
	 */
	private long mExitTime;
	/**
	 * �˳����
	 */
	private static final int INTERVAL = 2000;
	
	private SysUser user;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * ��������,������ȫ����С
		 */
		mRoot = new FlipperLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		mRoot.setLayoutParams(params);
		/**
		 * �����˵������������ҳ����,����ӵ�������,���ڳ�ʼ��ʾ
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
	 * UI�¼�����
	 */
	private void setListener() {
		mHome.setOnOpenListener(this);
		/**
		 * �����˵������л���ʾ����(onChangeViewListener�ӿ���Desktop�ж���)
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
	 * ���ؼ�����
	 */
	public void onBackPressed() {
		/**
		 * ��������path�˵�û�йر�ʱ,�Ƚ�path�˵��ر�,�������ж����η���ʱ����,С���������˳�����
		 */
		exit();
	}
	
	/**
	 * �ж����η���ʱ����,С���������˳�����
	 */
	private void exit() {
		if (System.currentTimeMillis() - mExitTime > INTERVAL) {
			Toast.makeText(this, "�ٰ�һ�η��ؼ�,��ֱ���˳�����", Toast.LENGTH_SHORT).show();
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
