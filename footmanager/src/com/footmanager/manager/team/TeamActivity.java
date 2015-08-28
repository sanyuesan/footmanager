package com.footmanager.manager.team;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;

public class TeamActivity extends TabActivity {
	
	 //����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    //�û���ɫ
    private String role = "";
    
    private TabHost tabHost;
    private RadioGroup radioGroup;
    
    private RadioButton rbtn1;
    private RadioButton rbtn2;
    private RadioButton rbtn3;
    private RadioButton rbtn4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.team_tab_menu_bottom);
		//��ȡ��ǰ�û��Ľ�ɫ
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
        SysUser user = appInfo.getUser();
        role = user.getRole();
        
        findViewById();
        setListener();
		
        initLayout();
        initTabHost();
	}
	
	private void findViewById(){
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		
		tabHost=this.getTabHost();  
		radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
		
		rbtn1 = (RadioButton)findViewById(R.id.tab_btn_1);
		rbtn2 = (RadioButton)findViewById(R.id.tab_btn_2);
		rbtn3 = (RadioButton)findViewById(R.id.tab_btn_3);
		rbtn4 = (RadioButton)findViewById(R.id.tab_btn_4);
	}
	
	private void setListener(){
		 leftButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//startActivity(new Intent(StoreActivity.this, TechnicianMainActivity.class));
					finish();
				}
			});
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.tab_btn_1://����
					tabHost.setCurrentTabByTag("tab1");
					break;
				case R.id.tab_btn_2://������
					tabHost.setCurrentTabByTag("tab2");
					break;
				case R.id.tab_btn_3://����
					tabHost.setCurrentTabByTag("tab3");
					break;
				case R.id.tab_btn_4://���ڲ�
					tabHost.setCurrentTabByTag("tab4");
					break;
				default:
					//tabHost.setCurrentTabByTag("�ҵĿ���");
					break;
				}
			}
		});
	}
	
	//��ʼ������
	private void initLayout(){
		rbtn1.setChecked(true);
		rightButton.setVisibility(View.GONE);
		//��������Ŀ
		titleTextView.setText("�ҵ��Ŷ�");
		if("manager".equals(role)){
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
		}else if("leader".equals(role)){
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
		}
		
	}
	
	//��ʼ��tab��ǩ
	private void initTabHost(){
		String departments = getResources().getString(R.string.departments);
		String[] depts = departments.split(",");
		
		rbtn1.setText(depts[0]);
		rbtn2.setText(depts[1]);
		rbtn3.setText(depts[2]);
		rbtn4.setText(depts[3]);
		
		TabHost.TabSpec spec;
        Intent intent;
        for(int i=0;i<4;i++){
        	intent=new Intent().setClass(this, TeamListActivity.class);
        	spec=tabHost.newTabSpec("tab"+(i+1)).setIndicator("tab"+(i+1)).setContent(intent);
        	tabHost.addTab(spec);
        }
        tabHost.setCurrentTab(0);
	}


}
