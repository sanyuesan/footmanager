package com.footmanager.manager.mypromotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import android.widget.SimpleAdapter;

import com.footmanager.R;
import com.footmanager.manager.promotion.PromotionViewActivity;

public class MyPromotionListActivity extends Activity {
	private ListView listView;
	
	private String type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.manager_my_promotion_list_activity);
		
		listView = (ListView)findViewById(R.id.list);
		
		//获取参数 using:正在用,used:已过期
		Intent intent = this.getIntent(); 
		type = (String)intent.getSerializableExtra("type");
		
		initListData();
	}
	
	//初始化菜单列表
	private void initListData(){
		int[] promotionIcons = {R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo};
		String[] promotionCounts = { "1002","1002", "1002",  "1002","1002","1002","1002"}; 
		String[] promotionNames = { "首页","个人中心", "意见返馈",  "客服电话","给我打分","关于小管家","专家测评"};
		String[] promotionStartTimes = { "2014-09-14","2014-09-14", "2014-09-14",  "2014-09-14","2014-09-14","2014-09-14","2014-09-14"};
		String[] promotionLimits = { "40","40", "40",  "40","40","40","40"};
		String[] promotionUsedCounts = { "20000","20", "20",  "20","20","20","20"};
	    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;  
	 
	    for(int i =0; i < promotionIcons.length; i++) {  
	        Map<String,Object> item = new HashMap<String,Object>();  
	        item.put("promotionIcon", promotionIcons[i]);  
	        item.put("promotionName", promotionNames[i]);  
	        item.put("promotionCount", promotionCounts[i]);
	        item.put("promotionStartTime", promotionStartTimes[i]);
        	item.put("promotionLimit", promotionLimits[i]);
        	item.put("promotionUsedCount", promotionUsedCounts[i]);
	        mData.add(item);   
	    }  
	    SimpleAdapter adapter = null;
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
	        	Intent intent = new Intent(MyPromotionListActivity.this,PromotionViewActivity.class);
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.technician_sort_list, menu);
		return true;
	}

}
