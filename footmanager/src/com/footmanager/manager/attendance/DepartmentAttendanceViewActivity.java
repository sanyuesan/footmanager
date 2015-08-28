package com.footmanager.manager.attendance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.footmanager.R;
import com.footmanager.manager.mypersons.MyPersonsAdapter;

public class DepartmentAttendanceViewActivity extends Activity {
	private ListView listView;
	
	//工作类型
	private String workType;
	//部门
	private String deptType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leader_department_attendance_view_activity);
		
		Intent intent = this.getIntent(); 
		//获取部门
		deptType = (String)intent.getSerializableExtra("deptType");
		//获取工作类型
		workType = (String)intent.getSerializableExtra("workType");
		
		findViewById();
		initListData();
		
	}
	
	private void findViewById(){
		listView = (ListView)findViewById(R.id.list);
	}
	
	//初始化列表
	private void initListData(){
		int totalCount = 30;
		String[] titles = {"应到人员：30人","实到人员25人","请假人员3人","公假","私假","临时假","旷工人员2人"};
		String[] attendanceNames = { "张三","张三", "张三","张三","张三","张三","张三"}; 
		Map<String,Object> names = new HashMap<String,Object>();
		names.put("name_"+1, attendanceNames);
		//names.put("name_"+2, attendanceNames);
		names.put("name_"+3, attendanceNames);
		names.put("name_"+4, attendanceNames);
		names.put("name_"+5, attendanceNames);
		names.put("name_"+6, attendanceNames);
		
        ArrayList<HashMap<String,Object>> mData= new ArrayList<HashMap<String,Object>>();;  
	 
	    for(int i =0; i < titles.length; i++) {  
	    	HashMap<String,Object> item = new HashMap<String,Object>(); 
	    	int type = i;
	    	if(i==1 || i==2 || i==6){
	    		type = 1;
	    	}
	    	if(i==3 || i==4 || i==5){
	    		type = 2;
	    	}
	    	item.put("type",type);
	    	item.put("title", titles[i]);
	    	mData.add(item);   
	    	if(null != names.get("name_"+i)){
	    		String[] persons = (String[])names.get("name_"+i);
    			for(int j=0;j<persons.length;){
    				HashMap<String,Object> item1 = new HashMap<String,Object>(); 
    				item1.put("type", -1);
    				item1.put("name1", persons[j]);
    				if(j+1<persons.length-1){
    					item1.put("name2", persons[j+1]);
    				}
    				if(j+2<persons.length-1){
    					item1.put("name3", persons[j+2]);
    				}
    				mData.add(item1);   
    				j=j+3;
    			}
	    	}
	    }  
	    AttendanceAdapter listItemAdapter= new AttendanceAdapter(this,mData);		
	    listView.setAdapter(listItemAdapter);
	    
	}


}
