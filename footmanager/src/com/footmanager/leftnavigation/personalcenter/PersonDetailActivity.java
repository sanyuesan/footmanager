package com.footmanager.leftnavigation.personalcenter;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.leftnavigation.personalcenter.vo.PersonVo;
import com.footmanager.util.HttpUtil;
import com.footmanager.util.SysUser;
import com.google.gson.Gson;

public class PersonDetailActivity extends Activity {
	//标题栏布局
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private View mainView;
    private SysUser userInfo;
    private String role;
    private Handler handler;
    
    private EditText idEdt;
    private EditText nameEdt;
    private EditText mobileEdt;
    private EditText emailEdt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_detail_activity);
		
		handler = new Handler() {
	        public void handleMessage(Message msg) {
	        	if(msg.what == 1001){
	        		Bundle bundle = msg.getData();
	        		String detailInfo = bundle.getString("detailInfo");
	        		Gson gson = new Gson();
	        		PersonVo personInfo = gson.fromJson(detailInfo, PersonVo.class);
	        		idEdt.setText(personInfo.getStaff_id());
	        		nameEdt.setText(personInfo.getName());
	        		mobileEdt.setText(personInfo.getMobiphones());
	        		//emailEdt.setText(personInfo.get)
	        	}
	        }
		};
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
		userInfo = appInfo.getUser();
	    role = userInfo.getRole();
		
        findViewById();
        initLayout();
        setListener();
        initData();
	}

	
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		
		idEdt = (EditText)findViewById(R.id.id);
		nameEdt = (EditText)findViewById(R.id.name);
		mobileEdt = (EditText)findViewById(R.id.mobile);
		emailEdt = (EditText)findViewById(R.id.email);
	}
	private void setListener() {
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		//编辑个人资料
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent().setClass(PersonDetailActivity.this, PersonDetailModifyActivity.class);
	        	Bundle bundle = new Bundle();
	        	bundle.putSerializable("userId","111");
	        	intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		titleTextView.setText("个人资料");
		if("manager".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.person_titlebar_modify_selector_manager);
		}else if("technician".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.person_titlebar_modify_selector_technician);
		}else if("leader".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.person_titlebar_modify_selector_leader);
			
		}
	}
	
	private void initData(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					Map<String, String> headers = new HashMap<String, String>();
					headers.put("Authorization", "Bearer "+userInfo.getToken());
					String url = "http://139.129.18.61:3000/staffs/"+userInfo.getUserId();;
					String resultStr = HttpUtil.sendConnByGet(url, null,headers);
					Message msg = new Message();
    				Bundle mBundle = new Bundle();
    				mBundle.putString("detailInfo", resultStr);
    				msg.setData(mBundle);
    				msg.what = 1001;
    				handler.sendMessage(msg);
				}catch(Exception e){
					Log.e("login", "获取个人信息异", e);
				}
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person_detail, menu);
		return true;
	}

}
