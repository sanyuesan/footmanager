package com.footmanager.leftnavigation.personalcenter;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.leftnavigation.personalcenter.vo.PersonVo;
import com.footmanager.util.HttpUtil;
import com.footmanager.util.SysUser;
import com.google.gson.Gson;

public class PersonDetailActivity extends Activity {
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private View mainView;
    private SysUser userInfo;
    private String role;
    private Handler handler;
    
    private EditText idEdt;
    private EditText nameEdt;
    private EditText mobileEdt;
    private EditText emailEdt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_detail_activity);
		
		handler = new Handler() {
	        public void handleMessage(Message msg) {
	        	if(msg.what == 1001){
	        		Bundle bundle = msg.getData();
	        		String detailInfo = bundle.getString("detailInfo");
	        		Gson gson = new Gson();
	        		PersonVo personInfo = gson.fromJson(detailInfo, PersonVo.class);
	        		idEdt.setText(personInfo.getStaff_id());
	        		nameEdt.setText(personInfo.getName());
	        		mobileEdt.setText(personInfo.getMobiphones());
	        		//emailEdt.setText(personInfo.get)
	        	}
	        }
		};
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
		userInfo = appInfo.getUser();
	    role = userInfo.getRole();
		
        findViewById();
        initLayout();
        setListener();
        initData();
	}

	
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		
		idEdt = (EditText)findViewById(R.id.id);
		nameEdt = (EditText)findViewById(R.id.name);
		mobileEdt = (EditText)findViewById(R.id.mobile);
		emailEdt = (EditText)findViewById(R.id.email);
	}
	private void setListener() {
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		//�༭��������
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent().setClass(PersonDetailActivity.this, PersonDetailModifyActivity.class);
	        	Bundle bundle = new Bundle();
	        	bundle.putSerializable("userId","111");
	        	intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("��������");
		if("manager".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_titlebar_modify_selector_manager);
		}else if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_titlebar_modify_selector_technician);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_titlebar_modify_selector_leader);
			
		}
	}
	
	private void initData(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					Map<String, String> headers = new HashMap<String, String>();
					headers.put("Authorization", "Bearer "+userInfo.getToken());
					String url = "http://139.129.18.61:3000/staffs/"+userInfo.getUserId();;
					String resultStr = HttpUtil.sendConnByGet(url, null,headers);
					Message msg = new Message();
    				Bundle mBundle = new Bundle();
    				mBundle.putString("detailInfo", resultStr);
    				msg.setData(mBundle);
    				msg.what = 1001;
    				handler.sendMessage(msg);
				}catch(Exception e){
					Log.e("login", "��ȡ������Ϣ��", e);
				}
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person_detail, menu);
		return true;
	}

}
