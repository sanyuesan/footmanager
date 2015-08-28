package com.footmanager.manager.promotion;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.util.SysUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PromotionViewActivity extends Activity {
	//标题栏布局
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private Button receiveBtn;
    private View outmodedInfoView;
    private View countInfoView;
    
    private String role;
    
    //获取参数 myProUsing:我的认领详情(正在用),myProUsed:(已过期)，pro:优惠券详情
    private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.manager_promotion_view_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
		Intent intent = this.getIntent(); 
		type = (String)intent.getSerializableExtra("proType");
		
		findViewById();
		setListener();
		initLayout();
	}
	
	private void findViewById(){
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		receiveBtn = (Button)findViewById(R.id.receiveBtn);
		
		outmodedInfoView = findViewById(R.id.myPromotionOutmodedImage);
		countInfoView = findViewById(R.id.myPromotionCountInfo);
	}
	
	private void setListener(){
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		//立即领取
		receiveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//finish();
			}
		});
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		if("pro".equals(type)){
			titleTextView.setText("xx优惠券");
			receiveBtn.setText("立即认领");
			outmodedInfoView.setVisibility(8);
			countInfoView.setVisibility(8);
		}else if("myProUsing".equals(type)){//正在用
			titleTextView.setText("详情");
			outmodedInfoView.setVisibility(8);
			receiveBtn.setVisibility(8);
		}else if("myProUsed".equals(type)){//已过期
			titleTextView.setText("详情");
			receiveBtn.setText("退款");
		}
		
		if("manager".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			
			receiveBtn.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
		}else if("technician".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			
			receiveBtn.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
		}else if("leader".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			
			receiveBtn.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.promotion_view, menu);
		return true;
	}

}
