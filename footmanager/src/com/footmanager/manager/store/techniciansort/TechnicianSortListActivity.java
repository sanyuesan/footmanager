package com.footmanager.manager.store.techniciansort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.manager.team.TeamListActivity;
import com.footmanager.manager.team.TeamListViewActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TechnicianSortListActivity extends Activity {
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.manager_technician_sort_list_activity);
		
		listView = (ListView)findViewById(R.id.sortList);
		
		Intent intent = this.getIntent(); 
		String sortType = (String)intent.getSerializableExtra("sortType");
		
		initListData(sortType);
	}
	
	//初始化菜单列表
	private void initListData(String sortType){
		int[] employeeIcons = {R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo};
		String[] employeeNos = { "1002","1002", "1002",  "1002","1002","1002","1002"}; 
		String[] employeeNames = { "首页","个人中心", "意见返馈",  "客服电话","给我打分","关于小管家","专家测评"};
		int[] employeeLevels = { R.drawable.team_user_star,R.drawable.team_user_star, R.drawable.team_user_star,  R.drawable.team_user_star,R.drawable.team_user_star,R.drawable.team_user_star,R.drawable.team_user_star};
		String[] employeeSorts = { "40","40", "40",  "40","40","40","40"};
		String[] employeeClicks = { "20","20", "20",  "20","20","20","20"};
	    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;  
	 
	    int lengh = employeeIcons.length;  
	    for(int i =0; i < lengh; i++) {  
	        Map<String,Object> item = new HashMap<String,Object>();  
	        item.put("employeePhoto", employeeIcons[i]);  
	        item.put("employeeNo", employeeNos[i]);  
	        item.put("employeeName", employeeNames[i]);
	        item.put("employeeLevel", employeeLevels[i]);
        	item.put("employeeSortBell", employeeSorts[i]);
        	item.put("employeeClickBell", employeeClicks[i]);
	        mData.add(item);   
	    }  
	    SimpleAdapter adapter = null;
	    if("sort".equals(sortType)){
	    	adapter = new SimpleAdapter(this,mData,R.layout.manager_technician_sort_list_item,  
	    			new String[]{"employeePhoto","employeeNo","employeeName","employeeLevel","employeeSortBell","employeeClickBell"},
	    			new int[]{R.id.employeePhoto,R.id.employeeNo,R.id.employeeName,R.id.employeeLevel,R.id.employeeSortBell,R.id.employeeClickBell});  
	    }else if("click".equals(sortType)){
	    	adapter = new SimpleAdapter(this,mData,R.layout.manager_technician_sort_list_click_item,  
	    			new String[]{"employeePhoto","employeeNo","employeeName","employeeLevel","employeeSortBell","employeeClickBell"},
	    			new int[]{R.id.employeePhoto,R.id.employeeNo,R.id.employeeName,R.id.employeeLevel,R.id.employeeSortBell,R.id.employeeClickBell});  
	    }
	    listView.setAdapter(adapter); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.technician_sort_list, menu);
		return true;
	}

}
