package com.footmanager.manager.team;

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

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;

public class TeamListActivity extends Activity {
	private ListView listView;
	private View mainView;
	private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.team_list_activity);
		

		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
		listView = (ListView)findViewById(R.id.teamList);
		mainView = findViewById(R.id.mainView);
		
		initSideMenuList();
		
		initLayout();
		
	}
	
	private void initLayout(){
		if("manager".equals(role)){
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
		}else if("leader".equals(role)){
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
		}
	}
	
	//初始化菜单列表
	private void initSideMenuList(){
		int[] employeeIcons = {R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo};
		String[] employeeNos = { "1002","1002", "1002",  "1002","1002","1002","1002"}; 
		String[] employeeNames = { "首页","个人中心", "意见返馈",  "客服电话","给我打分","关于小管家","专家测评"};
		String[] employeePositions = { "员工","店长", "员工",  "员工","店长","店长","店长"};
		String[] employeeTels = { "13045214521","13045214521", "13045214521",  "13045214521","13045214521","13045214521","13045214521"};
		int[] employeeLevels = { R.drawable.team_user_star,R.drawable.team_user_star, R.drawable.team_user_star,  R.drawable.team_user_star,R.drawable.team_user_star,R.drawable.team_user_star,R.drawable.team_user_star};
	    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;  
	 
	    int lengh = employeeIcons.length;  
	    for(int i =0; i < lengh; i++) {  
	        Map<String,Object> item = new HashMap<String,Object>();  
	        item.put("employeePhoto", employeeIcons[i]);  
	        item.put("employeeNo", employeeNos[i]);  
	        item.put("employeeName", employeeNames[i]);
	        item.put("employeePosition", employeePositions[i]);
	        item.put("employeeTel", employeeTels[i]);
	        item.put("employeeLevel", employeeLevels[i]);
	        mData.add(item);   
	    }  
	    SimpleAdapter adapter = new SimpleAdapter(this,mData,R.layout.team_list_item,  
	        new String[]{"employeePhoto","employeeNo","employeeName","employeePosition","employeeTel","employeeLevel"},
	        new int[]{R.id.employeePhoto,R.id.employeeNo,R.id.employeeName,R.id.employeePosition,R.id.employeeTel,R.id.employeeLevel});  
	    listView.setAdapter(adapter); 
	    listView.setOnItemClickListener(new OnItemClickListener() {  
	        @Override  
	        public void onItemClick(AdapterView<?> adapterView, View view, int position,  
	            long id) { 
	        	Log.e("fanyajie", position+"");
	        	//View v=adapterView.getChildAt(position);  
	        	startActivity(new Intent(TeamListActivity.this,TeamListViewActivity.class));
	        //Toast.makeText(IconList.this,"您选择了标题：" + mListTitle[position] + "内容："+mListStr[position], Toast.LENGTH_LONG).show();  
	        }  
	    });  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store_team_list, menu);
		return true;
	}

}
