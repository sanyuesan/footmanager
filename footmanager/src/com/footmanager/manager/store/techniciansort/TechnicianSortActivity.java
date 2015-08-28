package com.footmanager.manager.store.techniciansort;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;

public class TechnicianSortActivity extends TabActivity {
	
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    //�û���ɫ
    private String role = "";
    
    private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.manager_technician_sort_tab_menu_bottom);
		
		//��ȡ��ǰ�û��Ľ�ɫ
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
        SysUser user = appInfo.getUser();
        role = user.getRole();
        
        mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		tabHost=this.getTabHost(); 
		
		initLayout();
        initTabHost();
        
        leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(StoreActivity.this, TechnicianMainActivity.class));
				finish();
			}
		});
        
        RadioGroup radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.tab_btn_1://��ӿ���
					tabHost.setCurrentTabByTag("tab1");
					break;
				case R.id.tab_btn_2://�ҵĿ���
					tabHost.setCurrentTabByTag("tab2");
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
		if("manager".equals(role)){
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
		}else{
			
		}
		//��������Ŀ
		titleTextView.setText("��ʦ����");
		
	}
	
	//��ʼ��tab��ǩ
	private void initTabHost(){
		
		TabHost.TabSpec spec;
        Intent intent;
        for(int i=0;i<2;i++){
        	intent=new Intent().setClass(this, TechnicianSortListActivity.class);
        	Bundle bundle = new Bundle();
        	if(i==0){
        		bundle.putSerializable("sortType", "sort");
        	}else if(i==1){
        		bundle.putSerializable("sortType", "click");
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
		getMenuInflater().inflate(R.menu.technician_sort, menu);
		return true;
	}

}
