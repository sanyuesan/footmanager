package com.footmanager.manager.myaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;

public class MyAccountActivity extends Activity {

	//标题栏布局
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private String role;
    private RadioGroup radioGroup;
    private RadioButton radioBtn1;
    private RadioButton radioBtn2;
    
    private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.my_account_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
	    findViewById();
	    setListener();
		initLayout();
		
		initListData();
        
	}
	
	private void findViewById(){
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		
		radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
		radioBtn1 = (RadioButton)findViewById(R.id.tab_btn_1);
		radioBtn2 = (RadioButton)findViewById(R.id.tab_btn_2);
		listView = (ListView)findViewById(R.id.list);
	}
	
	private void setListener(){
		//返回
        leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(StoreActivity.this, TechnicianMainActivity.class));
				finish();
			}
		});
        //实名认证
        rightButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyAccountActivity.this, MyAccountCertificationActivity.class));
			}
		});
        
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.tab_btn_1://转出
					startActivity(new Intent(MyAccountActivity.this,MyAccountOutActivity.class));
					break;
				case R.id.tab_btn_2://转入
					//startActivity(new Intent(MyAccountActivity.this, MyAccountIntoActivity.class));
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
		titleTextView.setText("我的账户");
		if("manager".equals(role)){
			radioBtn1.setText("转出");
			radioBtn2.setText("转入");
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.my_account_certification_btn_selector_leader);
		}else if("leader".equals(role)){
			radioGroup.setVisibility(View.GONE);
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.my_account_certification_btn_selector_manager);
		}else if("technician".equals(role)){
			radioGroup.setVisibility(View.GONE);
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.my_account_certification_btn_selector_technician);
		}
	}
	
	private void initListData(){
		String[] dates = { "2014-09-14","2014-09-14", "2014-09-14",  "2014-09-14","2014-09-14","2014-09-14","2014-09-14"};
		String[] types = { "收入","收入", "兑现",  "收入","收入","收入","兑现"}; 
		String[] banks = { "","", "",  "工商银行","","","工商银行"}; 
		String[] accounts = { "2000合币","2000合币", "2000合币",  "2000合币","2000合币","2000合币","2000合币"};
		int[] states = { -1,-1, -1, R.drawable.my_account_state_icon_ok,-1,-1,R.drawable.my_account_state_icon_audit};
		
		
		ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;  
	 
	    for(int i =0; i < dates.length; i++) {  
	        Map<String,Object> item = new HashMap<String,Object>();  
	        item.put("date", dates[i]);  
	        item.put("type", types[i]);  
	        item.put("bank", banks[i]);
	        item.put("account", accounts[i]);
	        item.put("state", states[i]);
	        mData.add(item);   
	    }  
	    SimpleAdapter adapter = null;
    	adapter = new SimpleAdapter(this,mData,R.layout.my_account_list_item,  
    			new String[]{"date","type","bank","account","state"},
    			new int[]{R.id.date,R.id.type,R.id.bank,R.id.account,R.id.state});  
	    listView.setAdapter(adapter); 
	}
	
}
