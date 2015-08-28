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
    
    //����������
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
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mOnOpenListener != null) {
					mOnOpenListener.open();
				}
			}
		});
		//�޸�����
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent().setClass(mActivity, ModifyPwdActivity.class);
	        	Bundle bundle = new Bundle();
	        	bundle.putSerializable("userId","111");
	        	intent.putExtras(bundle);
				mActivity.startActivity(intent);
			}
		});
		
		//����
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
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("��������");
		if("manager".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.manager_main_side_btn_selector);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.modify_pwd_btn_press_selector_manager);
			
			personTopBg.setBackgroundResource(R.drawable.person_info_top_bg_manager);
			personCenterBg.setBackgroundResource(R.drawable.person_center_bg_manager);
		}else if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.technician_main_side_btn_selector);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.modify_pwd_btn_press_selector_technician);
			
			personTopBg.setBackgroundResource(R.drawable.person_info_top_bg_technician);
			personCenterBg.setBackgroundResource(R.drawable.person_center_bg_technician);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.leader_main_side_btn_selector);
			//�����Ҳఴť��seletor
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
