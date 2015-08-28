package com.footmanager.manager.store;

import java.util.ArrayList;
import java.util.HashMap;

import com.footmanager.R;
import com.footmanager.R.drawable;
import com.footmanager.R.id;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.main.TechnicianMainActivity;
import com.footmanager.manager.store.techniciansort.TechnicianSortActivity;
import com.footmanager.manager.team.TeamActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class StoreActivity extends Activity {
	
	 //����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private GridView storeGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.manager_store_activity);
		
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		storeGridView = (GridView)findViewById(R.id.storeGridView);
		
		initLayout();
		initMainGridView();
		
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(StoreActivity.this, TechnicianMainActivity.class));
				finish();
			}
		});
		
	}
	
	//��ʼ��gridView
	private void initMainGridView(){
		int[] menuSelectors = {
				R.drawable.manager_store_income_data_selector,
				R.drawable.manager_store_team_selector,
				R.drawable.manager_store_sort_selector
				};
		String[] menuTitles = { "Ӫҵ����", "�ҵ��Ŷ�", "��ʦ����"}; 
		
		String[] menuIds = { "store_income_data", "store_team", "store_sort"}; 
		
		 ArrayList<HashMap<String, Object>> listImageItem = new ArrayList<HashMap<String, Object>>();  
	      for(int i=0;i<menuSelectors.length;i++){  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("gridItemImage", menuSelectors[i]);//���ͼ����Դ��ID  
	        map.put("gridItemText", menuTitles[i]);  
	        map.put("gridItemId", menuIds[i]);
	        listImageItem.add(map);  
	      }  
	      SimpleAdapter saImageItems = new SimpleAdapter(this,listImageItem,R.layout.main_gridview_item,  
	    		  new String[] {"gridItemImage","gridItemText","gridItemId"},
	    		  new int[] {R.id.gridItemImage,R.id.gridItemText,R.id.gridItemId});  
	      //��Ӳ�����ʾ  
	      storeGridView.setAdapter(saImageItems);  
	      //�����Ϣ����  
	      storeGridView.setOnItemClickListener(new OnItemClickListener(){  
			public void onItemClick(AdapterView<?> arg0,View arg1,int position,long arg3) {  
			    HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(position); 
			    String itemId = (String)item.get("gridItemId");
			    if("store_team".equals(itemId)){
			    	startActivity(new Intent(StoreActivity.this,TeamActivity.class));
			    }
				if("store_sort".equals(itemId)){
					startActivity(new Intent(StoreActivity.this,TechnicianSortActivity.class));		    	
				}
				if("".equals(itemId)){
					
				}
			}
	      });  
	}
	
	//��ʼ������
	private void initLayout(){
		//����������
		mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
		//��������ఴť��selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
		//��������Ŀ
		titleTextView.setText("�ҵĵ���");
		
	}


}
