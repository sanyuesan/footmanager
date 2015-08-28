package com.footmanager.leftnavigation.personalcenter;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.util.SysUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PersonDetailModifyActivity extends Activity {
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private View mainView;
    private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_detail_modify_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
        findViewById();
        setListener();
        init();
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		
	}
	private void setListener() {
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		//�����������
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		});
		
	}
	private void init(){
		initLayout();
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("���������޸�");
		if("manager".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_detail_save_btn_selector_manager);
		}else if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_detail_save_btn_selector_technician);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_detail_save_btn_selector_leader);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person_detail_modify, menu);
		return true;
	}

}
