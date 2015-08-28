package com.footmanager.manager.mypersons;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.manager.promotion.PromotionActivity;
import com.footmanager.manager.promotion.PromotionUseRuleActivity;
import com.footmanager.util.SysUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class RecommendCircleActivity extends Activity {
	private View mainView;
	private Button agentFirst;
	private Button agentTwo;
	private Button agentThree;
	private Button agentFour;
	private Button agentFive;
	private Button agentSix;
	
	private String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏
		setContentView(R.layout.manager_my_persons_recommend_circle_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
	    
	    findViewById();
        setListener();
        initLayout();
		
	}
	
	private void findViewById(){
		mainView = findViewById(R.id.mainView);
		agentFirst = (Button)findViewById(R.id.agent_first);
		agentTwo = (Button)findViewById(R.id.agent_two);
		agentThree = (Button)findViewById(R.id.agent_three);
		agentFour = (Button)findViewById(R.id.agent_four);
		agentFive = (Button)findViewById(R.id.agent_five);
		agentSix = (Button)findViewById(R.id.agent_six);
	}
	
	private void setListener(){
		agentFirst.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecommendCircleActivity.this,MyPersonAgentsActivity.class);
				Bundle bundle = new Bundle();
	        	bundle.putSerializable("agentType", "first");
	        	intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		agentTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecommendCircleActivity.this,MyPersonAgentsActivity.class);
				Bundle bundle = new Bundle();
	        	bundle.putSerializable("agentType", "first");
	        	intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		agentThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecommendCircleActivity.this,MyPersonAgentsActivity.class);
				Bundle bundle = new Bundle();
	        	bundle.putSerializable("agentType", "first");
	        	intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
	
	private void initLayout(){
		agentFour.setVisibility(View.GONE);
		agentFive.setVisibility(View.GONE);
		agentSix.setVisibility(View.GONE);
		if("manager".equals(role)){
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			agentFirst.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
			agentTwo.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
			agentThree.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
			agentFour.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
			agentFive.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
			agentSix.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
		}else if("technician".equals(role)){
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			agentFirst.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
			agentTwo.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
			agentThree.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
			agentFour.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
			agentFive.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
			agentSix.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
		}else if("leader".equals(role)){
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			agentFirst.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
			agentTwo.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
			agentThree.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
			agentFour.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
			agentFive.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
			agentSix.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recommend_circle, menu);
		return true;
	}

}
