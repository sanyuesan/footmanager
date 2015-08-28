package com.footmanager.manager.promotion;

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
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PromotionViewActivity extends Activity {
	//����������
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private Button receiveBtn;
    private View outmodedInfoView;
    private View countInfoView;
    
    private String role;
    
    //��ȡ���� myProUsing:�ҵ���������(������),myProUsed:(�ѹ���)��pro:�Ż�ȯ����
    private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.manager_promotion_view_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
		Intent intent = this.getIntent(); 
		type = (String)intent.getSerializableExtra("proType");
		
		findViewById();
		setListener();
		initLayout();
	}
	
	private void findViewById(){
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		receiveBtn = (Button)findViewById(R.id.receiveBtn);
		
		outmodedInfoView = findViewById(R.id.myPromotionOutmodedImage);
		countInfoView = findViewById(R.id.myPromotionCountInfo);
	}
	
	private void setListener(){
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		//������ȡ
		receiveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//finish();
			}
		});
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		if("pro".equals(type)){
			titleTextView.setText("xx�Ż�ȯ");
			receiveBtn.setText("��������");
			outmodedInfoView.setVisibility(8);
			countInfoView.setVisibility(8);
		}else if("myProUsing".equals(type)){//������
			titleTextView.setText("����");
			outmodedInfoView.setVisibility(8);
			receiveBtn.setVisibility(8);
		}else if("myProUsed".equals(type)){//�ѹ���
			titleTextView.setText("����");
			receiveBtn.setText("�˿�");
		}
		
		if("manager".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			
			receiveBtn.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
		}else if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			
			receiveBtn.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			
			receiveBtn.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.promotion_view, menu);
		return true;
	}

}
