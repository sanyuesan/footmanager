package com.footmanager;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.footmanager.util.HttpUtil;
import com.footmanager.util.SysUser;

public class LoginActivity extends Activity {
	//private DrawerLayout mDrawerLayout = null;
		private Handler handler;
		private Button loginBtn;
		private ImageButton backPwdBtn;
		private ImageButton registerBtn;
		private EditText userEdit;
		private EditText pwdEidt;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
			setContentView(R.layout.login_activity);
			
			handler = new Handler() {
		        public void handleMessage(Message msg) {
		        	if(msg.what == 1001){
		        		Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
		        		Bundle mBundle = msg.getData();
		        		String loginResult = mBundle.getString("loginResult");
		        		String tokenStr = "";
		        		String roleCode = "";
		        		if(StringUtils.isNotEmpty(loginResult)){
							JSONObject resultJson = JSONObject.fromString(loginResult);
							if(resultJson.has("token_string")){
								tokenStr = resultJson.getString("token_string");
							}
							if(resultJson.has("role_code")){
								roleCode = resultJson.getString("role_code");
							}
						}
		        		
		        		if(StringUtils.isNotEmpty(tokenStr) && StringUtils.isNotEmpty(roleCode)){
		        			String userStr = userEdit.getText().toString().trim();
		        			MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
		        			SysUser user = new SysUser();
		        			user.setUserId(userStr);
		        			user.setUserName(userStr);
		        			String role = "";
		        			
		        			if("011".equals(roleCode)){
		        				//011   老板
		        				role = "manager";
		        			}else if(roleCode.indexOf("06")>=0){
		        				//06*  技师 
		        				role = "technician";
		        			}else if(roleCode.indexOf("01")>=0){
		        				//01*  (除了011以外的)  都是店长
		        				role = "leader";
		        			}
		        			user.setRole(role);
		        			user.setToken(tokenStr);
		        			appInfo.setUser(user);
		        			startActivity(new Intent(LoginActivity.this,MainActivity.class));
		        			finish();
		        		}else{
		        			Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
		        		}
		        		
		        	}else if(msg.what == 404){
		        		//finishLoaded = true;
		        		Toast.makeText(getApplicationContext(), "网络不可用，请检查网络", Toast.LENGTH_LONG).show();
		        	}
		        }
		    };
			
			findViewById();
			setListener();
			
		}
		
		/**
		 * 绑定界面UI
		 */
		private void findViewById() {
			loginBtn = (Button)findViewById(R.id.loginBtn);
			backPwdBtn = (ImageButton)findViewById(R.id.backPwdBtn);
			registerBtn = (ImageButton)findViewById(R.id.registerBtn);
			
			userEdit = (EditText)findViewById(R.id.loginUser);
			pwdEidt = (EditText)findViewById(R.id.loginPwd);
		}
		
		/**
		 * UI事件监听
		 */
		private void setListener() {
			//登录
			loginBtn.setOnClickListener(new OnClickListener(){
	            @Override
	            public void onClick(View v){
	        		new Thread(new Runnable() {
	        			@Override
	        			public void run() {
	        				String loginStr = login();
	        				Message msg = new Message();
	        				Bundle mBundle = new Bundle();
	        				mBundle.putString("loginResult", loginStr);
	        				msg.setData(mBundle);
	        				msg.what = 1001;
	        				handler.sendMessage(msg);
	        			}
	        		}).start();
	            }
	        });
			//找回密码
			backPwdBtn.setOnClickListener(new OnClickListener(){
	            @Override
	            public void onClick(View v){
	            	startActivity(new Intent(LoginActivity.this,ForgetPwdActivity.class));
	            }
	        });
			//注册
			registerBtn.setOnClickListener(new OnClickListener(){
	            @Override
	            public void onClick(View v){
	            	startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
	            }
	        });
		}
		/**
		 * 返回键监听
		 */
		public void onBackPressed() {
			finish();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
		
		//登录
		private String login(){
			String resultStr = null;
			try{
				String userName = userEdit.getText().toString();
				String pwd = pwdEidt.getText().toString();
				String base64Param =  Base64.encodeToString((userName+":"+pwd).getBytes(), Base64.DEFAULT);
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Authorization", "Basic "+base64Param);
				String url = "http://139.129.18.61:3000/tokens";
				
				StringBuffer params = new StringBuffer();
				params.append("&Username="+userName);
				params.append("&Password="+pwd);
				resultStr = HttpUtil.sendConnByPost(url, params.toString(),headers);
			}catch(Exception e){
				Log.e("login", "登录异常", e);
			}
			return resultStr;
		}
}
