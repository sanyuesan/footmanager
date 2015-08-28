package com.footmanager.manager.attendance;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.footmanager.R;
import com.footmanager.manager.team.TeamListActivity;

public class DepartmentAttendanceActivity extends TabActivity {

    private TabHost tabHost;
    private RadioGroup radioGroup;
    
    private RadioButton rbtn1;
    private RadioButton rbtn2;
    
    private String deptType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leader_department_attendance_activity);
		
		//获取参数部门
		Intent intent = this.getIntent(); 
		deptType = (String)intent.getSerializableExtra("deptType");
        
        findViewById();
        setListener();
		
        initTabHost();
	}
	
	private void findViewById(){
		
		tabHost=this.getTabHost();  
		radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
		
		rbtn1 = (RadioButton)findViewById(R.id.tab_btn_1);
		rbtn2 = (RadioButton)findViewById(R.id.tab_btn_2);
	}
	
	private void setListener(){
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.tab_btn_1://早班
					tabHost.setCurrentTabByTag("tab1");
					break;
				case R.id.tab_btn_2://晚班
					tabHost.setCurrentTabByTag("tab2");
					break;
				default:
					//tabHost.setCurrentTabByTag("我的考试");
					break;
				}
			}
		});
	}
	//初始化tab标签
	private void initTabHost(){
		TabHost.TabSpec spec;
        Intent intent;
        for(int i=0;i<2;i++){
        	intent=new Intent().setClass(this, DepartmentAttendanceViewActivity.class);
        	Bundle bundle = new Bundle();
        	bundle.putSerializable("deptType", deptType);
        	if(i==0){
        		bundle.putSerializable("workType", "day");
        	}else if(i==1){
        		bundle.putSerializable("workType", "night");
        	}
        	intent.putExtras(bundle);
        	spec=tabHost.newTabSpec("tab"+(i+1)).setIndicator("tab"+(i+1)).setContent(intent);
        	tabHost.addTab(spec);
        }
        tabHost.setCurrentTab(0);
	}


}
