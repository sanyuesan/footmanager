package com.footmanager.manager.vacation;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.footmanager.R;

public class VacationApplyActivity extends Activity {
	//标题栏布局
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private String type;
    private Button selectBtn;
    
    private EditText dateEt;
    private Calendar c = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.technician_vacation_apply_activity);
		
		//获取参数 pubV:公假,eveV:事假
		Intent intent = this.getIntent(); 
		type = (String)intent.getSerializableExtra("type");
		
		findViewById();
        setListener();
        initLayout();
	}
	
	private void findViewById() {
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		selectBtn = (Button)findViewById(R.id.selectBtn);
		dateEt = (EditText)findViewById(R.id.date);
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
		//日期选择
		selectBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 showDialog(0);
			}
		});
	}
	
	//初始化布局
	private void initLayout(){
		//标题栏题目
		if("pubV".equals(type)){
			titleTextView.setText("公假");
		}else if("eveV".equals(type)){
			titleTextView.setText("事假");
		}
		//标题栏背景
		mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
		//标题栏左侧按钮的selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		//设置右侧按钮的seletor
		rightButton.setBackgroundResource(R.drawable.opinion_submit_btn_selector_technician);
	}
	
	 /**
     * 创建日期及时间选择对话框
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        c= Calendar.getInstance();
        dialog = new DatePickerDialog(
            this,
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {
                	dateEt.setText(year + "-" + (month+1) + "-" + dayOfMonth);
                }
            }, 
            c.get(Calendar.YEAR), // 传入年份
            c.get(Calendar.MONTH), // 传入月份
            c.get(Calendar.DAY_OF_MONTH) // 传入天数
        );
        return dialog;
    }


}
