package com.footmanager.manager.promotion;

import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class PromotionUseRuleActivity extends Activity {
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.manager_promotion_use_rule_activity);
		
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		initLayout();
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
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
		titleTextView.setText("ʹ��˵��");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.promotion_use_rule, menu);
		return true;
	}

}
