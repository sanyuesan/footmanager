package com.footmanager.manager.vacation;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footmanager.R;
import com.footmanager.manager.promotion.PromotionViewActivity;

public class VacationActivity extends Activity {
	//����������
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.technician_vacation_activity);
		
        findViewById();
        setListener();
        initLayout();
        
        initListData();
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		
		listView = (ListView)findViewById(R.id.list);
		
	}
	private void setListener() {
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
		//��������Ŀ
		titleTextView.setText("��������");
		//����ҳ�汳��
		mainView.setBackgroundResource(R.drawable.technician_main_bg);
		//����������
		mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
		//��������ఴť��selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
	}
	
	//��ʼ���б�
	private void initListData(){
		String[] dates = { "2014-09-14","2014-09-14", "2014-09-14",  "2014-09-14","2014-09-14","2014-09-14","2014-09-14"};
		String[] names = { "�¼�","�¼�", "�¼�",  "�¼�","�¼�","�¼�","�¼�"}; 
		String[] days = { "2��","2��", "2��",  "2��","2��","2��","2��"};
		String[] states = { "δ��׼","��׼", "��׼",  "��׼","δ��׼","��׼","��׼"};
		
		String[] btns = { "��������","�¼�����", "��ʱ������"};
		String[] btnIds = { "pubV","eveV", "timV"};
		
        ArrayList<HashMap<String,Object>> mData= new ArrayList<HashMap<String,Object>>();;  
	 
	    for(int i =0; i < dates.length; i++) {  
	    	HashMap<String,Object> item = new HashMap<String,Object>();  
	        item.put("date", dates[i]);  
	        item.put("name", names[i]);  
	        item.put("day", days[i]);
	        item.put("state", states[i]);
        	item.put("type", 1);
	        mData.add(item);   
	    } 
	    for(int i =0; i < btns.length; i++) {  
	    	HashMap<String,Object> item = new HashMap<String,Object>();  
	        item.put("btnText", btns[i]);  
	        item.put("itemId", btnIds[i]);
        	item.put("type", 2);
	        mData.add(item);   
	    }
	    VacationAdapter listItemAdapter= new VacationAdapter(this,mData,this);		
	    listView.setAdapter(listItemAdapter);
	    
	}

}
