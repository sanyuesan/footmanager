package com.footmanager.manager.store.leader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;

public class StoreViewActivity extends Activity {
	private String role;
	
	private View mainView;
	private View mainTitleView;
    private Button leftButton;
    private Button rightButton;
    private TextView titleTextView;
    
    private ExpandableListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_view_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
	    
	    findViewById();
		setListener();
		initLayout();
		initData();
		
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		listView = (ExpandableListView)findViewById(R.id.list);
		
	}
	
	private void setListener() {
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		//ʵʱ����
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//finish();
			}
		});
		
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("��������");
		if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}else if("manager".equals(role)){
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}else if("leader".equals(role)){
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}
	}
	
	/**
	 * ��ʼ���б�����
	 */
	private void initData(){
		String[] groups = {"��ֵ","��Ŀ����","�ӷ�״̬"};
		String[][] items = {{"С��","����","����"},{"�ŷ�","����","����"},{"����","����","����"}};
		StoreViewExpandableListAdapter adapter = new StoreViewExpandableListAdapter(StoreViewActivity.this,groups,items);
		listView.setAdapter(adapter);
	}


}
