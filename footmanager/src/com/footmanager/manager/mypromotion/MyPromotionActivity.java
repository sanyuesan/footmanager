package com.footmanager.manager.mypromotion;

import com.footmanager.R;
import com.footmanager.R.drawable;
import com.footmanager.R.id;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.manager.store.techniciansort.TechnicianSortListActivity;
import com.footmanager.util.SysUser;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MyPromotionActivity extends TabActivity {
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.manager_my_promotion_activity);
		
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
		//����������
		mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
		//��������ఴť��selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
		//��������Ŀ
		titleTextView.setText("�ҵ�����");
		
	}
	
	//��ʼ��tab��ǩ
	private void initTabHost(){
		
		TabHost.TabSpec spec;
        Intent intent;
        for(int i=0;i<2;i++){
        	intent=new Intent().setClass(this, MyPromotionListActivity.class);
        	Bundle bundle = new Bundle();
        	if(i==0){
        		bundle.putSerializable("type", "using");
        	}else if(i==1){
        		bundle.putSerializable("type", "used");
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
		getMenuInflater().inflate(R.menu.my_promotion, menu);
		return true;
	}

}
