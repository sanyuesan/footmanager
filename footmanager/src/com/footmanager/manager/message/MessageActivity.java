package com.footmanager.manager.message;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.HttpUtil;
import com.footmanager.util.SysUser;

public class MessageActivity extends TabActivity {
	//标题栏布局
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private TabHost tabHost;
    private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.manager_message_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		
		tabHost=this.getTabHost(); 
		
		initLayout();
        initTabHost();
        //返回
        leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(StoreActivity.this, TechnicianMainActivity.class));
				finish();
				//overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			}
		});
        //新建消息
        rightButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MessageActivity.this, MessageNewActivity.class));
			}
		});
        
        RadioGroup radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.tab_btn_1://添加考试
					tabHost.setCurrentTabByTag("tab1");
					break;
				case R.id.tab_btn_2://我的考试
					tabHost.setCurrentTabByTag("tab2");
					break;
				default:
					//tabHost.setCurrentTabByTag("我的考试");
					break;
				}
			}
		});
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		titleTextView.setText("消息");
		if("manager".equals(role)){
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.manager_message_title_right_btn_seletor);
		}else if("leader".equals(role)){
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.leader_message_title_right_btn_seletor);
		}
		
	}
	
	//初始化tab标签
	private void initTabHost(){
		//获取参数 send:发送,receive:接收
		TabHost.TabSpec spec;
        Intent intent;
        for(int i=0;i<2;i++){
        	intent=new Intent().setClass(this, MessageListActivity.class);
        	Bundle bundle = new Bundle();
        	if(i==0){
        		bundle.putSerializable("type", "send");
        	}else if(i==1){
        		bundle.putSerializable("type", "receive");
        	}
        	intent.putExtras(bundle);
        	spec=tabHost.newTabSpec("tab"+(i+1)).setIndicator("tab"+(i+1)).setContent(intent);
        	tabHost.addTab(spec);
        }
        tabHost.setCurrentTab(0);
	}

}
