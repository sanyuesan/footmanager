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
	//����������
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
		
		//��ȡ���� pubV:����,eveV:�¼�
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
		//����ѡ��
		selectBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 showDialog(0);
			}
		});
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		if("pubV".equals(type)){
			titleTextView.setText("����");
		}else if("eveV".equals(type)){
			titleTextView.setText("�¼�");
		}
		//����������
		mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
		//��������ఴť��selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		//�����Ҳఴť��seletor
		rightButton.setBackgroundResource(R.drawable.opinion_submit_btn_selector_technician);
	}
	
	 /**
     * �������ڼ�ʱ��ѡ��Ի���
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
            c.get(Calendar.YEAR), // �������
            c.get(Calendar.MONTH), // �����·�
            c.get(Calendar.DAY_OF_MONTH) // ��������
        );
        return dialog;
    }


}
