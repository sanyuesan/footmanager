package com.footmanager.manager.mypersons;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.manager.mypromotion.MyPromotionListActivity;
import com.footmanager.util.SysUser;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MyPersonsActivity extends TabActivity {
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private TabHost tabHost;
    private RadioGroup radioGroup;
    private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.manager_my_persons_activity);
		
		//��ȡ��ǰ�û��Ľ�ɫ
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
        SysUser user = appInfo.getUser();
        role = user.getRole();
        
        findViewById();
        setListener();
        init();
		
	}
	
	private void findViewById(){
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		tabHost=this.getTabHost(); 
		radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
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
				case R.id.tab_btn_1://�Ƽ�Ȧ
					tabHost.setCurrentTabByTag("tab1");
					break;
				case R.id.tab_btn_2://����Ȧ
					tabHost.setCurrentTabByTag("tab2");
					break;
				case R.id.tab_btn_3://����Ȧ
					tabHost.setCurrentTabByTag("tab3");
					break;
				default:
					//tabHost.setCurrentTabByTag("�ҵĿ���");
					break;
				}
			}
		});
	}
	private void init(){
		initLayout();
        initTabHost();
	}
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("�ҵ�����");
		if("manager".equals(role)){
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
		}else if("technician".equals(role)){
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}else if("leader".equals(role)){
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
		}
		
	}
	
	//��ʼ��tab��ǩ
	private void initTabHost(){
		
		TabHost.TabSpec spec;
        Intent intent;
        intent=new Intent().setClass(this, RecommendCircleActivity.class);
        spec=tabHost.newTabSpec("tab1").setIndicator("tab1").setContent(intent);
    	tabHost.addTab(spec);
        for(int i=1;i<3;i++){
        	intent=new Intent().setClass(this, MyPersonsListActivity.class);
        	Bundle bundle = new Bundle();
        	if(i==1){
        		bundle.putSerializable("type", "agent");
        	}else if(i==2){
        		bundle.putSerializable("type", "server");
        	}
        	intent.putExtras(bundle);
        	spec=tabHost.newTabSpec("tab"+(i+1)).setIndicator("tab"+(i+1)).setContent(intent);
        	tabHost.addTab(spec);
        }
        tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_persons, menu);
		return true;
	}

}
