package com.footmanager.leftnavigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.footmanager.R;
import com.footmanager.leftnavigation.personalcenter.ModifyPwdActivity;
import com.footmanager.leftnavigation.personalcenter.PersonDetailActivity;
import com.footmanager.util.FlipperLayout.OnOpenListener;
import com.footmanager.util.SysUser;

public class Person {
	private Context mContext;
	private Activity mActivity;
	private View mPerson;
	private String role;
    private SysUser user;
    private OnOpenListener mOnOpenListener;
    
    //标题栏布局
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private View mainView;
    private View personTopBg;
    private View personCenterBg;
    private ImageButton personDetailBtn;
	
	public Person(Context context, Activity activity,SysUser mUser) {
		mContext = context;
		mActivity = activity;
		mPerson = LayoutInflater.from(context).inflate(R.layout.left_navigation_person_activity, null);
		
        user = mUser;
        role = user.getRole();
        
        findViewById();
        setListener();
        init();
	}
	
	private void findViewById() {
		mainView = mPerson.findViewById(R.id.mainView);
		mainTitleView = mPerson.findViewById(R.id.mainTitle);
		leftButton = (Button)mPerson.findViewById(R.id.title_btn);
		titleTextView = (TextView)mPerson.findViewById(R.id.title_text);
		rightButton = (Button)mPerson.findViewById(R.id.title_right_btn);
		
		personTopBg = mPerson.findViewById(R.id.personTopBg);
		personCenterBg = mPerson.findViewById(R.id.personCenterBg);
		personDetailBtn = (ImageButton)mPerson.findViewById(R.id.personDetailBtn);
		
	}
	private void setListener() {
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mOnOpenListener != null) {
					mOnOpenListener.open();
				}
			}
		});
		//修改密码
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent().setClass(mActivity, ModifyPwdActivity.class);
	        	Bundle bundle = new Bundle();
	        	bundle.putSerializable("userId","111");
	        	intent.putExtras(bundle);
				mActivity.startActivity(intent);
			}
		});
		
		//详情
		personDetailBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent().setClass(mActivity, PersonDetailActivity.class);
	        	Bundle bundle = new Bundle();
	        	bundle.putSerializable("userId","111");
	        	intent.putExtras(bundle);
				mActivity.startActivity(intent);
			}
		});
		
	}
	private void init(){
		initLayout();
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		titleTextView.setText("个人中心");
		if("manager".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.manager_main_side_btn_selector);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.modify_pwd_btn_press_selector_manager);
			
			personTopBg.setBackgroundResource(R.drawable.person_info_top_bg_manager);
			personCenterBg.setBackgroundResource(R.drawable.person_center_bg_manager);
		}else if("technician".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.technician_main_side_btn_selector);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.modify_pwd_btn_press_selector_technician);
			
			personTopBg.setBackgroundResource(R.drawable.person_info_top_bg_technician);
			personCenterBg.setBackgroundResource(R.drawable.person_center_bg_technician);
		}else if("leader".equals(role)){
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.leader_main_side_btn_selector);
			//设置右侧按钮的seletor
			rightButton.setBackgroundResource(R.drawable.modify_pwd_btn_press_selector_leader);
			
			personTopBg.setBackgroundResource(R.drawable.person_info_top_bg_leader);
			personCenterBg.setBackgroundResource(R.drawable.person_center_bg_manager);
			
		}
	}
	public View getView() {
		return mPerson;
	}

	public void setOnOpenListener(OnOpenListener onOpenListener) {
		mOnOpenListener = onOpenListener;
	}
}
