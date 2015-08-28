package com.footmanager.manager.recommend;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;

public class RecommendActivity extends Activity {
	private View mainView;
	private GridView gridView;
	
	private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommend_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
		mainView = findViewById(R.id.mainView);
		gridView = (GridView)findViewById(R.id.gridView);
		
		initLayout();
		initMainGridView();
	}
	
	//初始化布局
	private void initLayout(){
		if("manager".equals(role) || "leader".equals(role)){
			mainView.setBackgroundResource(R.drawable.recommand_bg_manager);
		}else if("technician".equals(role)){
			mainView.setBackgroundResource(R.drawable.recommand_bg_technician);
		}
	}
	
	//初始化gridView
	private void initMainGridView(){
		int[] menuSelectors = {
				R.drawable.recommand_weixin_icon_selector,
				R.drawable.recommand_pengyouquang_icon_selector,
				R.drawable.recommand_qq_icon_selector,
				R.drawable.recommand_weibo_icon_selector,
				R.drawable.recommand_qq_weibo_icon_selector,
				R.drawable.recommand_message_icon_selector
				};
		String[] menuTitles = { "微信好友", "微信朋友圈", "腾讯好友", "新浪微博","腾讯微博","短信"}; 
		String[] menuIds = { "weixin", "pengyouquang", "qq", "weibo","qq_weibo","message"}; 
		
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
	      gridView.setAdapter(saImageItems);  
	      //添加消息处理  
	      gridView.setOnItemClickListener(new OnItemClickListener(){  
			  public void onItemClick(AdapterView<?> arg0,View arg1,int position,long arg3) {  
			      HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(position);  
			      gridViewClick((String)item.get("gridItemId"));
			  }
	      });  
	}
	//处理gridView点击事件
	private void gridViewClick(String itemId){
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recommend, menu);
		return true;
	}

}
