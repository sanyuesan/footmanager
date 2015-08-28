package com.footmanager.leftnavigation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footmanager.R;
import com.footmanager.util.FlipperLayout.OnOpenListener;
import com.footmanager.util.SysUser;

public class About {
	private Context mContext;
	private Activity mActivity;
	private View mAbout;
	private String role;
    private SysUser user;
    private OnOpenListener mOnOpenListener;
    
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    private View mainView;
    
	public About(Context context, Activity activity,SysUser mUser) {
		mContext = context;
		mActivity = activity;
		mAbout = LayoutInflater.from(context).inflate(R.layout.left_navigation_about_activity, null);
		
        user = mUser;
        role = user.getRole();
        
        findViewById();
        setListener();
        init();
	}
	
	private void findViewById() {
		mainView = mAbout.findViewById(R.id.mainView);
		
		mainTitleView = mAbout.findViewById(R.id.mainTitle);
		leftButton = (Button)mAbout.findViewById(R.id.title_btn);
		titleTextView = (TextView)mAbout.findViewById(R.id.title_text);
	}
	private void setListener() {
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mOnOpenListener != null) {
					mOnOpenListener.open();
				}
			}
		});
	}
	private void init(){
		initLayout();
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
			leftButton.setBackgroundResource(R.drawable.manager_main_side_btn_selector);
		}else if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.technician_main_side_btn_selector);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.leader_main_side_btn_selector);
			
		}
	}
	public View getView() {
		return mAbout;
	}

	public void setOnOpenListener(OnOpenListener onOpenListener) {
		mOnOpenListener = onOpenListener;
	}

}
