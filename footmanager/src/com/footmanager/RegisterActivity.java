package com.footmanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
	
	private Button registerBtn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.register_activity);
		
		//��ʼ��������
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		initLayout();
		
		//���ص��ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(StoreActivity.this, TechnicianMainActivity.class));
				finish();
			}
		});
		
		//ע��
		registerBtn = (Button) findViewById(R.id.registerBtn);
		registerBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(StoreActivity.this, TechnicianMainActivity.class));
				//finish();
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
		titleTextView.setText("��Աע��");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	

}
