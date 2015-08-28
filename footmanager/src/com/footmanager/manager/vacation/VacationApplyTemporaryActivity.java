package com.footmanager.manager.vacation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.footmanager.R;

public class VacationApplyTemporaryActivity extends Activity {
	//����������
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
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		//�ύ
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
			}
		});
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("��ʱ��");
		//����������
		mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
		//��������ఴť��selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		//�����Ҳఴť��seletor
		rightButton.setBackgroundResource(R.drawable.opinion_submit_btn_selector_technician);
	}


}
