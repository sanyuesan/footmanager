package com.footmanager.manager.store.leader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;

public class StoreViewActivity extends Activity {
	private String role;
	
	private View mainView;
	private View mainTitleView;
    private Button leftButton;
    private Button rightButton;
    private TextView titleTextView;
    
    private ExpandableListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_view_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
	    
	    findViewById();
		setListener();
		initLayout();
		initData();
		
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		listView = (ExpandableListView)findViewById(R.id.list);
		
	}
	
	private void setListener() {
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		//实时数据
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//finish();
			}
		});
		
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		titleTextView.setText("昨日数据");
		if("technician".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}else if("manager".equals(role)){
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}else if("leader".equals(role)){
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//标题栏
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}
	}
	
	/**
	 * 初始化列表数据
	 */
	private void initData(){
		String[] groups = {"产值","项目销售","钟房状态"};
		String[][] items = {{"小李","范范","范范"},{"张飞","范范","范范"},{"范范","范范","范范"}};
		StoreViewExpandableListAdapter adapter = new StoreViewExpandableListAdapter(StoreViewActivity.this,groups,items);
		listView.setAdapter(adapter);
	}


}
