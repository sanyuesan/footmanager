package com.footmanager.manager.applyagent;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.manager.promotion.PromotionActivity;
import com.footmanager.manager.promotion.PromotionUseRuleActivity;
import com.footmanager.util.SysUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ApplyAgentActivity extends Activity {
	//标题栏布局
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button titleRightBtn;
    
    private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manager_apply_agent_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
        findViewById();
        setListener();
        initLayout();
		
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		titleRightBtn = (Button)findViewById(R.id.title_right_btn);
		
	}
	private void setListener() {
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		//提交申请
		titleRightBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(ApplyAgentActivity.this,PromotionUseRuleActivity.class));
			}
		});

	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		titleTextView.setText("申请代理");
		if("manager".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			//设置右侧按钮的seletor
			titleRightBtn.setBackgroundResource(R.drawable.manager_apply_agent_title_right_btn_selector);
		}else if("technician".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			//设置右侧按钮的seletor
			titleRightBtn.setBackgroundResource(R.drawable.technician_apply_agent_title_right_btn_selector);
		}else if("leader".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			//设置右侧按钮的seletor
			titleRightBtn.setBackgroundResource(R.drawable.leader_apply_agent_title_right_btn_selector);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.apply_agent, menu);
		return true;
	}

}
