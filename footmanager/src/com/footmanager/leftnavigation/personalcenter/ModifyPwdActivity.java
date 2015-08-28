package com.footmanager.leftnavigation.personalcenter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.leftnavigation.personalcenter.vo.PersonVo;
import com.footmanager.util.HttpUtil;
import com.footmanager.util.SysUser;
import com.google.gson.Gson;

public class ModifyPwdActivity extends Activity {
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private View mainView;
    private SysUser userInfo;
    private String role;
    private Handler handler;
    
    private EditText oldPwdEdt;
    private EditText newPwdEdt;
    private EditText reNewPwdEdt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_modify_pwd_activity);
		handler = new Handler(){
			public void handleMessage(Message msg) {
	        	if(msg.what == 1001){
	        		Bundle bundle = msg.getData();
	        		String detailInfo = bundle.getString("resultStr");
	        		
	        	}
	        }
		};
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
		userInfo = appInfo.getUser();
	    role = userInfo.getRole();
		
        findViewById();
        setListener();
        init();
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		rightButton = (Button)findViewById(R.id.title_right_btn);
		
		oldPwdEdt = (EditText)findViewById(R.id.oldPwd);
		newPwdEdt = (EditText)findViewById(R.id.newPwd);
		reNewPwdEdt = (EditText)findViewById(R.id.reNewPwd);
	}
	private void setListener() {
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		//ȷ��
		rightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String oldPwdStr = oldPwdEdt.getText().toString();
				if(StringUtils.isEmpty(oldPwdStr)){
					Toast.makeText(getApplicationContext(), "������ԭ����", Toast.LENGTH_LONG).show();
					return;
				}
				String newPwdStr = newPwdEdt.getText().toString();
				if(StringUtils.isEmpty(newPwdStr)){
					Toast.makeText(getApplicationContext(), "������������", Toast.LENGTH_LONG).show();
					return;
				}
				String reNewPwdStr = reNewPwdEdt.getText().toString();
				if(StringUtils.isEmpty(reNewPwdStr)){
					Toast.makeText(getApplicationContext(), "������ȷ������", Toast.LENGTH_LONG).show();
					return;
				}
				if(newPwdStr.equals(reNewPwdStr)){
					Toast.makeText(getApplicationContext(), "��������ȷ�����벻��ͬ", Toast.LENGTH_LONG).show();
					return;
				}
				new Thread(new Runnable() {
					@Override
					public void run() {
						try{
							Map<String, String> headers = new HashMap<String, String>();
							headers.put("Authorization", "Bearer "+userInfo.getToken());
							String url = "http://139.129.18.61:3000/staffs/"+userInfo.getUserId()+"/passwd";
							StringBuffer params = new StringBuffer();
							params.append("&old="+oldPwdEdt.getText().toString());
							params.append("&new="+newPwdEdt.getText().toString());
							String resultStr = HttpUtil.sendConnByPut(url, params.toString(),headers);
							Message msg = new Message();
		    				Bundle mBundle = new Bundle();
		    				mBundle.putString("resultStr", resultStr);
		    				msg.setData(mBundle);
		    				msg.what = 1001;
		    				handler.sendMessage(msg);
						}catch(Exception e){
							Log.e("login", "��ȡ������Ϣ��", e);
						}
					}
				}).start();
			}
		});
		
	}
	private void init(){
		initLayout();
	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("�޸�����");
		if("manager".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_modify_pwd_ok_btn_selector_manager);
		}else if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_modify_pwd_ok_btn_selector_technician);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.person_modify_pwd_ok_btn_selector_leader);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify_pwd, menu);
		return true;
	}

}
