package com.footmanager.manager.vacation;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footmanager.R;
import com.footmanager.manager.promotion.PromotionViewActivity;

public class VacationActivity extends Activity {
	//标题栏布局
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.technician_vacation_activity);
		
        findViewById();
        setListener();
        initLayout();
        
        initListData();
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		listView = (ListView)findViewById(R.id.list);
		
	}
	private void setListener() {
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		titleTextView.setText("假期申请");
		//设置页面背景
		mainView.setBackgroundResource(R.drawable.technician_main_bg);
		//标题栏背景
		mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
		//标题栏左侧按钮的selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
	}
	
	//初始化列表
	private void initListData(){
		String[] dates = { "2014-09-14","2014-09-14", "2014-09-14",  "2014-09-14","2014-09-14","2014-09-14","2014-09-14"};
		String[] names = { "事假","事假", "事假",  "事假","事假","事假","事假"}; 
		String[] days = { "2天","2天", "2天",  "2天","2天","2天","2天"};
		String[] states = { "未批准","批准", "批准",  "批准","未批准","批准","批准"};
		
		String[] btns = { "公假申请","事假申请", "临时假申请"};
		String[] btnIds = { "pubV","eveV", "timV"};
		
        ArrayList<HashMap<String,Object>> mData= new ArrayList<HashMap<String,Object>>();;  
	 
	    for(int i =0; i < dates.length; i++) {  
	    	HashMap<String,Object> item = new HashMap<String,Object>();  
	        item.put("date", dates[i]);  
	        item.put("name", names[i]);  
	        item.put("day", days[i]);
	        item.put("state", states[i]);
        	item.put("type", 1);
	        mData.add(item);   
	    } 
	    for(int i =0; i < btns.length; i++) {  
	    	HashMap<String,Object> item = new HashMap<String,Object>();  
	        item.put("btnText", btns[i]);  
	        item.put("itemId", btnIds[i]);
        	item.put("type", 2);
	        mData.add(item);   
	    }
	    VacationAdapter listItemAdapter= new VacationAdapter(this,mData,this);		
	    listView.setAdapter(listItemAdapter);
	    
	}

}
