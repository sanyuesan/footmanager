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
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.manager_my_persons_list_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
		//��ȡ���� using:������,used:�ѹ���
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
	
	//��ʼ���б�
	private void initListData(){
		int[] icons = {R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo,R.drawable.team_user_photo,R.drawable.team_user_photo,
				R.drawable.team_user_photo};
		String[] id = { "1002","1002", "1002",  "1002","1002","1002","1002"}; 
		String[] names = { "��ҳ","��������", "�������",  "�ͷ��绰","���Ҵ��","����С�ܼ�","ר�Ҳ���"};
		String[] contactUsers = { "2014-09-14","2014-09-14", "2014-09-14",  "2014-09-14","2014-09-14","2014-09-14","2014-09-14"};
		String[] tels = { "40","40", "40",  "40","40","40","40"};
		String[] addresses = { "20000","20", "20",  "20","20","20","20"};
		String[] mobiles = { "20000","20", "20",  "20","20","20","20"};
		
		//�����ǳ��е������жϻ��ߵڼ����жϣ����ݲ�ͬ�������첻ͬ������ṹ��Ӧ��ͬ��Ӧ��
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
	        	// using:������,used:�ѹ���
	        	if("using".equals(type)){
	        		bundle.putSerializable("proType", "myProUsing");
	        	}else if("used".equals(type)){
	        		bundle.putSerializable("proType", "myProUsed");
	        	}
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	        //Toast.makeText(IconList.this,"��ѡ���˱��⣺" + mListTitle[position] + "���ݣ�"+mListStr[position], Toast.LENGTH_LONG).show();  
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
