package com.footmanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.leftnavigation.about.AboutActivity;
import com.footmanager.leftnavigation.opinion.OpinionActivity;
import com.footmanager.manager.applyagent.ApplyAgentActivity;
import com.footmanager.manager.message.MessageActivity;
import com.footmanager.manager.mypersons.MyPersonsActivity;
import com.footmanager.manager.mypromotion.MyPromotionActivity;
import com.footmanager.manager.promotion.PromotionActivity;
import com.footmanager.manager.store.StoreActivity;
import com.footmanager.manager.team.TeamActivity;
import com.footmanager.util.BannerLayout;
import com.footmanager.util.SysUser;

@SuppressWarnings("unused")
public class TechnicianMainActivity extends Activity{
	
	private TechnicianHorizontalScrollView scrollView;
	//左侧菜单
    private View leftMenu;
    private View rightMenu;
    private View mainView;
    private Button leftButton;
    //private Button rightButton;
    //左侧菜单列表
    private ListView sideMenuListView;
    //主菜单
    private GridView mainGridView;
    //
    private BannerLayout banner;
    //用户角色
    private String role = "";
    //标题栏布局
    private View mainTitleView;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        LayoutInflater inflater = LayoutInflater.from(this);
        setContentView(inflater.inflate(R.layout.technician_main_side, null));
        
        MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
        SysUser user = appInfo.getUser();
        role = user.getRole();
        
        scrollView = (TechnicianHorizontalScrollView) findViewById(R.id.myScrollView);
        leftMenu = findViewById(R.id.leftmenu);
        rightMenu = findViewById(R.id.rightmenu);
        
        mainView = inflater.inflate(R.layout.technician_main, null);
        
        mainTitleView = (View)mainView.findViewById(R.id.mainTitle);
        
        leftButton = (Button) mainView.findViewById(R.id.title_btn);
        leftButton.getBackground();
        
        leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollView.clickLeftButton(leftButton.getMeasuredWidth());
			}
		});
        
        View leftView = new View(this);//左边透明视图
        View rightView = new View(this);//右边透明视图
        leftView.setBackgroundColor(Color.TRANSPARENT);
        rightView.setBackgroundColor(Color.TRANSPARENT);
        final View[] children = new View[] { leftView, mainView,rightView };
        //初始化滚动布局
        scrollView.initViews(children, new SizeCallbackForMenu(leftMenu),leftMenu,rightMenu);
        
        //初始化左侧菜单
        sideMenuListView = (ListView)findViewById(R.id.sideMenuList);
        initSideMenuList();
        
        //初始化页面主菜单
        mainGridView = (GridView)findViewById(R.id.mainGridView);
        initMainGridView();
        
        changeBgByRole();
        
        //设置banner的点击事件
        banner=(BannerLayout) findViewById(R.id.banner);
        banner.setOnItemClickListener(new com.footmanager.util.BannerLayout.OnItemClickListener() {
			
			@Override
			public void onClick(int index, View childview) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "点击了index："+index,Toast.LENGTH_SHORT).show();
			}
		});
        
	}
	
	//初始化菜单列表
	private void initSideMenuList(){
		int[] sideMenuListIcons = {R.drawable.side_home_icon,R.drawable.side_person_info_icon,R.drawable.side_opinion_icon,
				R.drawable.side_customer_service_icon,R.drawable.side_score_icon,R.drawable.side_about_us_icon,
				R.drawable.side_experts_icon};
		String[] mListTitle = { "首页","个人中心", "意见返馈",  "客服电话","给我打分","关于小管家","专家测评"}; 
		String[] itemIds = { "index", "personalCenter", "opinion", "serviceTel","evaluation",
				"aboutUs","expertsTest"}; 
	    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;  
	 
	    int lengh = mListTitle.length;  
	    for(int i =0; i < lengh; i++) {  
	        Map<String,Object> item = new HashMap<String,Object>();  
	        item.put("image", sideMenuListIcons[i]);  
	        item.put("title", mListTitle[i]);  
	        item.put("itemId", itemIds[i]);
	        mData.add(item);   
	    }  
	    SimpleAdapter adapter = new SimpleAdapter(this,mData,R.layout.side_menu_list,  
	        new String[]{"image","title","itemId"},new int[]{R.id.sideMenuListImage,R.id.sideMenuListTitle,R.id.itemId});  
	    sideMenuListView.setAdapter(adapter); 
	    sideMenuListView.setOnItemClickListener(new OnItemClickListener() {  
	        @Override  
	        public void onItemClick(AdapterView<?> adapterView, View view, int position,  
	            long id) { 
	        	//Log.e("fanyajie", position+"");
	        	//View v=adapterView.getChildAt(position);  
	        	HashMap<String, Object> item=(HashMap<String, Object>) adapterView.getItemAtPosition(position);  
	        	navigationClick((String)item.get("itemId"));
	        //Toast.makeText(IconList.this,"您选择了标题：" + mListTitle[position] + "内容："+mListStr[position], Toast.LENGTH_LONG).show();  
	        }  
	    });  
	}
	
	private void navigationClick(String itemId){
		//关于我们
		if("aboutUs".equals(itemId)){
			 startActivity(new Intent(this, AboutActivity.class));
		}
		//意见返馈
		if("opinion".equals(itemId)){
			 startActivity(new Intent(this, OpinionActivity.class));
		}
		
	}
	
	//初始化gridView
	private void initMainGridView(){
		int[] menuSelectors = null;
		String[] menuTitles = null;
		String[] menuIds = null;
		if("manager".equals(role)){
			int[] menuSelectors_ = {
					R.drawable.manager_store_selector,
					R.drawable.manager_promotion_selector,
					R.drawable.manager_obtain_selector,
					R.drawable.manager_job_selector,
					R.drawable.manager_persons_selector,
					R.drawable.manager_discuss_selector,
					R.drawable.manager_income_selector,
					R.drawable.manager_agency_selector,
					R.drawable.manager_recommend_selector};
			String[] menuTitles_ = { "我的店铺", "促销中心", "我的认领", "工作管理","我的人脉","我有话说","系统收入",
					"申请代理","我要推荐"}; 
			String[] menuIds_ = { "manager_store", "manager_promotion", "manager_obtain", "manager_job","manager_persons",
					"manager_discuss","manager_income","manager_agency","manager_recommend"}; 
			menuSelectors = menuSelectors_;
			menuTitles = menuTitles_;
			menuIds = menuIds_;
		}else if("technician".equals(role)){
			int[] menuSelectors_ = {
					R.drawable.technician_main_btn_achievenment_selector,
					R.drawable.technician_main_btn_attendance_selector,
					R.drawable.technician_main_btn_vacation_selector,
					R.drawable.technician_main_btn_info_selector,
					R.drawable.technician_main_btn_persons_selector,
					R.drawable.technician_main_btn_discuss_selector,
					R.drawable.technician_main_btn_income_selector,
					R.drawable.technician_main_btn_agency_selector,
					R.drawable.technician_main_btn_recommend_selector};
			String[] menuTitles_ = { "我的业绩", "我的出勤", "假期申请", "我的消息","我的人脉","我有话说","系统收入",
					"申请代理","我要推荐"}; 
			String[] menuIds_ = { "technician_achievenment", "technician_attendance", "technician_vacation", "technician_info",
					"technician_persons","technician_discuss","technician_income","technician_agency","technician_recommend"}; 
			menuSelectors = menuSelectors_;
			menuTitles = menuTitles_;
			menuIds = menuIds_;
		}else if("leader".equals(role)){
			int[] menuSelectors_ = {
					R.drawable.leader_store_selector,
					R.drawable.leader_team_selector,
					R.drawable.leader_attendance_selector,
					R.drawable.leader_job_selector,
					R.drawable.leader_persons_selector,
					R.drawable.leader_discuss_selector,
					R.drawable.leader_income_selector,
					R.drawable.leader_agency_selector,
					R.drawable.leader_recommend_selector};
			String[] menuTitles_ = { "我的店铺", "我的团队", "部门出勤", "工作管理","我的人脉","我有话说","系统收入",
					"申请代理","我要推荐"}; 
			String[] menuIds_ = { "leader_store", "leader_team", "leader_attendance", "leader_job","leader_persons",
					"leader_discuss","leader_income","leader_agency","leader_recommend"}; 
			menuSelectors = menuSelectors_;
			menuTitles = menuTitles_;
			menuIds = menuIds_;
		}
		
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
	      mainGridView.setAdapter(saImageItems);  
	      //添加消息处理  
	      mainGridView.setOnItemClickListener(new OnItemClickListener(){  
			  public void onItemClick(AdapterView<?> arg0,View arg1,int position,long arg3) {  
			      HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(position);  
			      gridViewClick((String)item.get("gridItemId"));
			  }
	      });  
	}
	//处理gridView点击事件
	private void gridViewClick(String itemId){
		//我的店铺
		if("manager_store".equals(itemId) || "leader_store".equals(itemId)){
			 startActivity(new Intent(this, StoreActivity.class));
		}
		//促销中心
		if("manager_promotion".equals(itemId)){
			 startActivity(new Intent(this, PromotionActivity.class));
		}
		//我的认领
		if("manager_obtain".equals(itemId)){
			startActivity(new Intent(this, MyPromotionActivity.class));
		}
		//工作管理
		if("manager_job".equals(itemId) || "technician_info".equals(itemId) || "leader_job".equals(itemId)){
			startActivity(new Intent(this, MessageActivity.class));
		}
		//我的人脉
		if("manager_persons".equals(itemId) || "technician_persons".equals(itemId) || "leader_persons".equals(itemId)){
			startActivity(new Intent(this, MyPersonsActivity.class));
		}
		//申请代理
		if("manager_agency".equals(itemId) || "technician_agency".equals(itemId) || "leader_agency".equals(itemId)){
			startActivity(new Intent(this, ApplyAgentActivity.class));
		}
		//我的团队
		if("leader_team".equals(itemId)){
			startActivity(new Intent(this,TeamActivity.class));
		}
		
		
	}
	
	//根据角色更换背景
	private void changeBgByRole(){
		if("manager".equals(role)){
			//页面背景
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.manager_main_side_btn_selector);
			//左侧菜单背景
			leftMenu.setBackgroundResource(R.drawable.manager_main_side_bg);
			//左侧菜单的选择事件
			sideMenuListView.setSelector(R.drawable.manager_main_side_menu_list_selector);
		}else if("leader".equals(role)){
			//页面背景
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//导航栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//左侧菜单背景//TODO:
			leftMenu.setBackgroundResource(R.drawable.manager_main_side_bg);
			//左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.leader_main_side_btn_selector);
			//左侧菜单的选择事件//TODO:
			sideMenuListView.setSelector(R.drawable.manager_main_side_menu_list_selector);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.technician_main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		banner.stopScroll();
		super.onPause();
	}

	@Override
	protected void onRestart() {
		if(!banner.isScrolling())
			banner.startScroll();
		super.onRestart();
	}

	@Override
	protected void onResume() {
		if(!banner.isScrolling())
			banner.startScroll();
		super.onResume();
	}


	@Override
	protected void onStop() {
		banner.stopScroll();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		banner.stopScroll();
		super.onDestroy();
	}

}
