package com.footmanager.manager.team;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.util.SysUser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TeamListViewActivity extends Activity {
	
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.team_list_view_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
        findViewById();
        setListener();
        initLayout();
		
		
	}
	
	private void findViewById() {
		//��ʼ��������
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
	}
	private void setListener() {
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("������");
		
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.team_list_view, menu);
		return true;
	}

}
