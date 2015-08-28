package com.footmanager.leftnavigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.footmanager.R;
import com.footmanager.manager.applyagent.ApplyAgentActivity;
import com.footmanager.manager.attendance.AttendanceActivity;
import com.footmanager.manager.attendance.MyAttendanceActivity;
import com.footmanager.manager.message.MessageActivity;
import com.footmanager.manager.message.MessageListActivity;
import com.footmanager.manager.myaccount.MyAccountActivity;
import com.footmanager.manager.mypersons.MyPersonsActivity;
import com.footmanager.manager.mypromotion.MyPromotionActivity;
import com.footmanager.manager.promotion.PromotionActivity;
import com.footmanager.manager.store.StoreActivity;
import com.footmanager.manager.store.leader.StoreViewActivity;
import com.footmanager.manager.team.TeamActivity;
import com.footmanager.manager.vacation.VacationActivity;
import com.footmanager.util.BannerLayout;
import com.footmanager.util.FlipperLayout.OnOpenListener;
import com.footmanager.util.SysUser;
import com.footmanager.util.popupwindowutil.TitleMenuPopup;
import com.footmanager.util.popupwindowutil.TitleMenuPopup.OnItemOnClickListener;

/**
 * 菜单首页类
 * 
 */
public class Home {
	private Context mContext;
	private Activity mActivity;
	private View mHome;
	//标题栏左侧按钮
	private Button leftMenu;
	//标题栏右侧按钮
	private Button rightMenu;
	private View mainView;
	 //主菜单
    private GridView mainGridView;
    //标题栏布局
    private View mainTitleView;
    //轮播广告图
    private BannerLayout banner;
    //用户角色
    SysUser user;
    private String role;
    
    //定义标题栏弹窗按钮
  	private TitleMenuPopup titleMenuPopup;

	private OnOpenListener mOnOpenListener;
	private List<View> dots;

	/**
	 * 判断当前的path菜单是否已经显示
	 */
	private boolean mUgcIsShowing = false;
	
	public Home(Context context, Activity activity,SysUser mUser) {
		mContext = context;
		mActivity = activity;
		mHome = LayoutInflater.from(context).inflate(R.layout.home, null);
		
        user = mUser;
        role = user.getRole();
		
		findViewById();
		setListener();
		init();
		
	}

	private void findViewById() {
		mainView = mHome.findViewById(R.id.mainView);
		leftMenu = (Button) mHome.findViewById(R.id.title_btn);
		rightMenu = (Button)mHome.findViewById(R.id.title_right_btn);
		mainTitleView = (View)mHome.findViewById(R.id.mainTitle);
		mainGridView = (GridView)mHome.findViewById(R.id.mainGridView);
		
		//实例化标题栏弹窗
		titleMenuPopup = new TitleMenuPopup(mContext, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		 //设置banner的点击事件
        banner=(BannerLayout) mHome.findViewById(R.id.banner);
        
        TextView bannerTitleET = (TextView)mHome.findViewById(R.id.banner_title);
        
        dots = new ArrayList<View>();
		dots.add(mHome.findViewById(R.id.v_dot0));
		dots.add(mHome.findViewById(R.id.v_dot1));
		dots.add(mHome.findViewById(R.id.v_dot2));
		dots.add(mHome.findViewById(R.id.v_dot3));
		dots.add(mHome.findViewById(R.id.v_dot4));
        
        //启动轮播广告
        /*if(!banner.isScrolling()){
        	banner.startScroll();
        }*/
        
        banner.setDots(dots);
        banner.setTitleET(bannerTitleET);
        
	}

	private void setListener() {
		leftMenu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (mOnOpenListener != null) {
					mOnOpenListener.open();
				}
			}
		});
		rightMenu.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (mOnOpenListener != null) {
					titleMenuPopup.show(v);
				}
			}
		});
		titleMenuPopup.setItemOnClickListener(new OnItemOnClickListener() {
			
			@Override
			public void onItemClick(String item, int position) {
				Toast.makeText(mContext, item, Toast.LENGTH_SHORT).show();
				
			}
		});
		
		/*banner.setOnItemClickListener(new com.footmanager.util.BannerLayout.OnItemClickListener() {
				
			@Override
			public void onClick(int index, View childview) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "点击了index："+index,Toast.LENGTH_SHORT).show();
			}
		});*/
		//添加消息处理  
	    mainGridView.setOnItemClickListener(new OnItemClickListener(){  
		  public void onItemClick(AdapterView<?> arg0,View arg1,int position,long arg3) {  
		      HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(position);  
		      gridViewClick((String)item.get("gridItemId"));
		  }
	    });  
	}

	private void init() {
		changeBgByRole();
		initMainGridView();
		ArrayList<String> titleMenuItems = new ArrayList<String>();
		titleMenuItems.add("发起聊天");
		titleMenuItems.add("发起聊天");
		titleMenuItems.add("发起聊天");
		titleMenuItems.add("发起聊天");
		titleMenuPopup.setActionList(titleMenuItems);
	}
	
	//根据角色更换背景
	private void changeBgByRole(){
		if("manager".equals(role)){
			//页面背景
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftMenu.setBackgroundResource(R.drawable.manager_main_side_btn_selector);
			//标题栏右侧按钮的selector
			rightMenu.setBackgroundResource(R.drawable.manager_main_right_btn_selector);
		}else if("leader".equals(role)){
			//页面背景
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//导航栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftMenu.setBackgroundResource(R.drawable.leader_main_side_btn_selector);
			//标题栏右侧按钮的selector
			rightMenu.setBackgroundResource(R.drawable.leader_main_right_btn_selector);
		}else if("technician".equals(role)){
			//页面背景
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//导航栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftMenu.setBackgroundResource(R.drawable.technician_main_side_btn_selector);
			//标题栏右侧按钮的selector
			rightMenu.setBackgroundResource(R.drawable.technician_main_right_btn_selector);
		}
		rightMenu.setText("ID:888888");
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
	      SimpleAdapter saImageItems = new SimpleAdapter(mActivity,listImageItem,R.layout.main_gridview_item,new String[] {"gridItemImage","gridItemText","gridItemId"},new int[] {R.id.gridItemImage,R.id.gridItemText,R.id.gridItemId}); 
	      //添加并且显示  
	      mainGridView.setAdapter(saImageItems);  
	}
	//处理gridView点击事件
	private void gridViewClick(String itemId){
		//我的店铺
		if("manager_store".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity, StoreActivity.class));
		}
		//
		if("leader_store".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity, StoreViewActivity.class));
		}
		//促销中心
		if("manager_promotion".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity, PromotionActivity.class));
		}
		//我的认领
		if("manager_obtain".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity, MyPromotionActivity.class));
		}
		//工作管理
		if("manager_job".equals(itemId) || "leader_job".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity, MessageActivity.class));
			//mActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
		}
		//我的人脉
		if("manager_persons".equals(itemId) || "technician_persons".equals(itemId) || "leader_persons".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity, MyPersonsActivity.class));
		}
		//申请代理
		if("manager_agency".equals(itemId) || "technician_agency".equals(itemId) || "leader_agency".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity, ApplyAgentActivity.class));
		}
		//我的团队
		if("leader_team".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity,TeamActivity.class));
		}
		//部门出勤
		if("leader_attendance".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity,AttendanceActivity.class));
		}
		//我的出勤
		if("technician_attendance".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity,MyAttendanceActivity.class));
		}
		//假期申请
		if("technician_vacation".equals(itemId)){
			mActivity.startActivity(new Intent(mActivity,VacationActivity.class));
		}
		//我的消息-技师
		if("technician_info".equals(itemId)){
			Intent intent=new Intent().setClass(mActivity, MessageListActivity.class);
        	Bundle bundle = new Bundle();
        	bundle.putSerializable("type", "receive");
        	intent.putExtras(bundle);
        	mActivity.startActivity(intent);
		}
		//系统收入
		if("manager_income".equals(itemId) || "technician_income".equals(itemId) || "leader_income".equals(itemId)){
			Intent intent=new Intent().setClass(mActivity, MyAccountActivity.class);
        	mActivity.startActivity(intent);
		}
		//我要推荐
		if("manager_recommend".equals(itemId) || "technician_recommend".equals(itemId) || "leader_recommend".equals(itemId)){
			//Intent intent=new Intent().setClass(mActivity, RecommendActivity.class);
        	//mActivity.startActivity(intent);
			
        	ShareMenuPopupWindow popMenu = new ShareMenuPopupWindow(mContext,mActivity);
        	if (popMenu!=null) {
    			if (popMenu.isShowing()) {
    				//取消半透明
    				WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
    				lp.alpha = 1.0f;
    				lp.dimAmount = 0.0f;
    				mActivity.getWindow().setAttributes(lp);
    				popMenu.dismiss();
    			}else {
    				View v=mActivity.getLayoutInflater().inflate(R.layout.share_menu_popupwindow, null);
    				popMenu.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    				//半透明
    				WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
    				lp.alpha = 0.5f;
    				lp.dimAmount = 0.5f;
    				mActivity.getWindow().setAttributes(lp);
    			}
    		}
        	
        	//mActivity.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		}
		
		
	}
	
	


	public View getView() {
		return mHome;
	}

	public void setOnOpenListener(OnOpenListener onOpenListener) {
		mOnOpenListener = onOpenListener;
	}
}

