package com.footmanager.manager.vacation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.footmanager.R;

public class VacationApplyTemporaryActivity extends Activity {
	//标题栏布局
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.technician_vacation_apply_temporary_activity);
		
		findViewById();
        setListener();
        initLayout();
	}
	
	private void findViewById() {
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
	}
	private void setListener() {
		//返回上个页面
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		//提交
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		});
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		titleTextView.setText("临时假");
		//标题栏背景
		mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
		//标题栏左侧按钮的selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		//设置右侧按钮的seletor
		rightButton.setBackgroundResource(R.drawable.opinion_submit_btn_selector_technician);
	}


}
