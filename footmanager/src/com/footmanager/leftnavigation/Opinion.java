package com.footmanager.leftnavigation;

import com.footmanager.R;
import com.footmanager.util.SysUser;
import com.footmanager.util.FlipperLayout.OnOpenListener;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Opinion {
	private Context mContext;
	private Activity mActivity;
	private View mOpinion;
	private String role;
    private SysUser user;
    private OnOpenListener mOnOpenListener;
    
    //����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button rightButton;
    
    private View mainView;
	
	public Opinion(Context context, Activity activity,SysUser mUser) {
		mContext = context;
		mActivity = activity;
		mOpinion = LayoutInflater.from(context).inflate(R.layout.left_navigation_opinion_activity, null);
		
        user = mUser;
        role = user.getRole();
        
        findViewById();
        setListener();
        init();
	}
	
	private void findViewById() {
		mainView = mOpinion.findViewById(R.id.mainView);
		mainTitleView = mOpinion.findViewById(R.id.mainTitle);
		leftButton = (Button)mOpinion.findViewById(R.id.title_btn);
		titleTextView = (TextView)mOpinion.findViewById(R.id.title_text);
		rightButton = (Button)mOpinion.findViewById(R.id.title_right_btn);
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
		titleTextView.setText("�������");
		if("manager".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.manager_main_side_btn_selector);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.opinion_submit_btn_selector);
		}else if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.technician_main_side_btn_selector);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.opinion_submit_btn_selector_technician);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.leader_main_side_btn_selector);
			//�����Ҳఴť��seletor
			rightButton.setBackgroundResource(R.drawable.opinion_submit_btn_selector_leader);
			
		}
	}
	public View getView() {
		return mOpinion;
	}

	public void setOnOpenListener(OnOpenListener onOpenListener) {
		mOnOpenListener = onOpenListener;
	}

}
