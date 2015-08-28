package com.footmanager.manager.mypersons;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.manager.promotion.PromotionViewActivity;
import com.footmanager.util.SysUser;

public class MyPersonsListActivity extends Activity {
	private ListView listView;
	
	private String type;
	
	private View mainView;
	private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.manager_my_persons_list_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
		//获取参数 using:正在用,used:已过期
		Intent intent = this.getIntent(); 
		type = (String)intent.getSerializableExtra("type");
		
		findViewById();
		initLayout();
		initListData();
	}
	
	private void findViewById(){
		mainView = findViewById(R.id.mainView);
		listView = (ListView)findViewById(R.id.list);
	}
	
	private void initLayout(){
		if("manager".equals(role)){
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
		}else if("technician".equals(role)){
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
		}else if("leader".equals(role)){
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
		}
	}
	
	//初始化列表
	private void initListData(){
		int[] icons = {R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo};
		String[] id = { "1002","1002", "1002",  "1002","1002","1002","1002"}; 
		String[] names = { "首页","个人中心", "意见返馈",  "客服电话","给我打分","关于小管家","专家测评"};
		String[] contactUsers = { "2014-09-14","2014-09-14", "2014-09-14",  "2014-09-14","2014-09-14","2014-09-14","2014-09-14"};
		String[] tels = { "40","40", "40",  "40","40","40","40"};
		String[] addresses = { "20000","20", "20",  "20","20","20","20"};
		String[] mobiles = { "20000","20", "20",  "20","20","20","20"};
		
		//可以是城市的类型判断或者第几天判断，根据不同的需求构造不同的数组结构适应不同的应用
        int[] listType = {0,1,1,1,1,1,1};
        if("agent".equals(type)){
        	int[] listType_ = {0,0,0,0,0,0,0};
        	listType = listType_;
        }
		
        ArrayList<HashMap<String,Object>> mData= new ArrayList<HashMap<String,Object>>();;  
	 
	    for(int i =0; i < icons.length; i++) {  
	    	HashMap<String,Object> item = new HashMap<String,Object>();  
	        item.put("icon", icons[i]);  
	        item.put("name", names[i]);  
	        item.put("id", id[i]);
	        item.put("contactUser", contactUsers[i]);
        	item.put("tel", tels[i]);
        	item.put("address", addresses[i]);
        	item.put("mobile", mobiles[i]);
	        mData.add(item);   
	    }  
	    MyPersonsAdapter listItemAdapter= new MyPersonsAdapter(this,mData,listType);		
	    listView.setAdapter(listItemAdapter);
	    
	   /* SimpleAdapter adapter = null;
    	adapter = new SimpleAdapter(this,mData,R.layout.manager_my_promotion_list_item,  
    			new String[]{"promotionIcon","promotionName","promotionCount","promotionStartTime","promotionLimit","promotionUsedCount"},
    			new int[]{R.id.promotionIcon,R.id.promotionName,R.id.promotionCount,R.id.promotionStartTime,R.id.promotionLimit,R.id.promotionUsedCount});  
	    listView.setAdapter(adapter); 
	    
	    listView.setOnItemClickListener(new OnItemClickListener() {  
	        @Override  
	        public void onItemClick(AdapterView<?> adapterView, View view, int position,  
	            long id) { 
	        	Log.e("fanyajie", position+"");
	        	//View v=adapterView.getChildAt(position); 
	        	Intent intent = new Intent(MyPersonsListActivity.this,PromotionViewActivity.class);
	        	Bundle bundle = new Bundle();
	        	// using:正在用,used:已过期
	        	if("using".equals(type)){
	        		bundle.putSerializable("proType", "myProUsing");
	        	}else if("used".equals(type)){
	        		bundle.putSerializable("proType", "myProUsed");
	        	}
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	        //Toast.makeText(IconList.this,"您选择了标题：" + mListTitle[position] + "内容："+mListStr[position], Toast.LENGTH_LONG).show();  
	        }  
	    }); 
	    */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_persons_list, menu);
		return true;
	}

}
