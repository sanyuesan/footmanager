package com.footmanager.manager.attendance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;
import com.footmanager.util.dateutil.KCalendar;
import com.footmanager.util.dateutil.KCalendar.OnCalendarClickListener;

public class MyAttendanceActivity extends Activity {

	
	//����������
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private String role;
    
    String date = null;// ����Ĭ��ѡ�е�����  ��ʽΪ ��2014-04-05�� ��׼DATE��ʽ   
    private TextView popupwindow_calendar_month;
    private KCalendar calendar;
    private RelativeLayout popupwindow_calendar_last_month;
    private RelativeLayout popupwindow_calendar_next_month;
    
    private View calendarHeader;
    

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.my_attendance_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
	    
	    findViewById();
        setListener();
        initLayout();
        
		popupwindow_calendar_month.setText(calendar.getCalendarYear() + "��" + calendar.getCalendarMonth() + "��");

		if (null != date) {

			int years = Integer.parseInt(date.substring(0,
					date.indexOf("-")));
			int month = Integer.parseInt(date.substring(
					date.indexOf("-") + 1, date.lastIndexOf("-")));
			popupwindow_calendar_month.setText(years + "��" + month + "��");

			calendar.showCalendar(years, month);
			calendar.setCalendarDayBgColor(date,
					R.drawable.calendar_date_focused);				
		}
		
		List<String> list = new ArrayList<String>(); //���ñ���б�
		list.add("2014-09-29");
		list.add("2014-10-02");
		calendar.addMarks(list, 0);
	}
	
	private void findViewById(){
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		calendarHeader = findViewById(R.id.calendarHeader);
		popupwindow_calendar_month = (TextView) findViewById(R.id.popupwindow_calendar_month);
		calendar = (KCalendar) findViewById(R.id.popupwindow_calendar);
		popupwindow_calendar_last_month = (RelativeLayout) findViewById(R.id.popupwindow_calendar_last_month);
		popupwindow_calendar_next_month = (RelativeLayout) findViewById(R.id.popupwindow_calendar_next_month);
	}
	
	private void setListener() {
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		//������ѡ�е�����
		calendar.setOnCalendarClickListener(new OnCalendarClickListener() {

			public void onCalendarClick(int row, int col, String dateFormat) {
				int month = Integer.parseInt(dateFormat.substring(
						dateFormat.indexOf("-") + 1,
						dateFormat.lastIndexOf("-")));
				
				//����ϸ��»��¸�������ʱ��ת
				/*if (calendar.getCalendarMonth() - month == 1//������ת
						|| calendar.getCalendarMonth() - month == -11) {
					calendar.lastMonth();
					
				} else if (month - calendar.getCalendarMonth() == 1 //������ת
						|| month - calendar.getCalendarMonth() == -11) {
					calendar.nextMonth();
					
				} else {
				}*/
				calendar.removeAllBgColor(); 
				calendar.setCalendarDayBgColor(dateFormat,
						R.drawable.calendar_date_focused);
				date = dateFormat;//��󷵻ظ�ȫ�� date
			}
		});

		//������ǰ�·�
		/*calendar.setOnCalendarDateChangedListener(new OnCalendarDateChangedListener() {
			public void onCalendarDateChanged(int year, int month) {
				popupwindow_calendar_month
						.setText(year + "��" + month + "��");
			}
		});
		
		//���¼�����ť
		popupwindow_calendar_last_month.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				calendar.lastMonth();
			}

		});
		
		//���¼�����ť
		popupwindow_calendar_next_month.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				calendar.nextMonth();
			}
		});*/
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		calendarHeader.setVisibility(View.GONE);
		//��ȡ��ǰ����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		titleTextView.setText(sdf.format(new Date()));
		if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			
		}
	}

}
