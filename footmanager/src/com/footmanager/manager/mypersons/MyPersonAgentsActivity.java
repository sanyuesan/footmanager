package com.footmanager.manager.mypersons;

import java.util.ArrayList;
import java.util.HashMap;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.manager.promotion.PromotionActivity;
import com.footmanager.manager.promotion.PromotionUseRuleActivity;
import com.footmanager.manager.promotion.PromotionViewActivity;
import com.footmanager.util.SysUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MyPersonAgentsActivity extends Activity {
	//标题栏布局
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private GridView promotionGridView;
    private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.manager_my_person_agents_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
	    
	    findViewById();
        setListener();
        init();
	}
	
	private void findViewById(){
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		promotionGridView = (GridView)findViewById(R.id.promotionGridView);
	}
	
	private void setListener(){
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	private void init(){
		initLayout();
		initMainGridView();
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		titleTextView.setText("我的人脉");
		
		if("manager".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
		}else if("technician".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}else if("leader".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			
		}
	}
	
	//初始化gridView
	private void initMainGridView(){
		int[] menuSelectors = {
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon
				};
		String[] menuTitles = { "名称", "名称", "名称", "名称","名称","名称","名称",
				"名称","名称","名称", "名称", "名称", "名称","名称","名称","名称",
				"名称","名称"}; 
		String[] menuIds = { "manager_store", "manager_promotion", "manager_obtain", "manager_job","manager_persons",
				"manager_discuss","manager_income","manager_agency","manager_recommend","manager_store", "manager_promotion", "manager_obtain", "manager_job","manager_persons",
				"manager_discuss","manager_income","manager_agency","manager_recommend"}; 
		
		 ArrayList<HashMap<String, Object>> listImageItem = new ArrayList<HashMap<String, Object>>();  
	      for(int i=0;i<menuSelectors.length;i++){  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("gridItemImage", menuSelectors[i]);//添加图像资源的ID  
	        map.put("gridItemText", menuTitles[i]);  
	        map.put("gridItemId", menuIds[i]);
	        listImageItem.add(map);  
	      }  
	      SimpleAdapter saImageItems = new SimpleAdapter(this,listImageItem,R.layout.main_gridview_item,  
	    		  new String[] {"gridItemImage","gridItemText","gridItemId"},new int[] {R.id.gridItemImage,R.id.gridItemText,R.id.gridItemId});  
	      //添加并且显示  
	      promotionGridView.setAdapter(saImageItems);  
	      //添加消息处理  
	      promotionGridView.setOnItemClickListener(new OnItemClickListener(){  
			  public void onItemClick(AdapterView<?> arg0,View arg1,int position,long arg3) {  
			      HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(position);  
			      gridViewClick((String)item.get("gridItemId"));
			  }
	      });  
	}
	//处理gridView点击事件
	private void gridViewClick(String itemId){
		Intent intent = new Intent(this, PromotionViewActivity.class);
		Bundle bundle = new Bundle();
    	bundle.putSerializable("proType", "pro");
    	intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_person_agents, menu);
		return true;
	}

}
