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
	//标题栏布局
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.manager_promotion_use_rule_activity);
		
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		initLayout();
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏背景
		mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
		//标题栏左侧按钮的selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
		//标题栏题目
		titleTextView.setText("使用说明");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.promotion_use_rule, menu);
		return true;
	}

}
