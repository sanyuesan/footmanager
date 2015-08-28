package com.footmanager.leftnavigation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.footmanager.R;
import com.footmanager.util.SysUser;
import com.footmanager.util.ViewUtil;

/**
 * �˵�����
 * 
 */
public class Desktop {
	private Context mContext;
	private Activity mActivity;
	/**
	 * ��ǰ�����View
	 */
	private View mDesktop;
	private SysUser user;
	private String role;
	/**
	 * ����Ϊ�ؼ�,�Լ��鿴�����ļ�
	 */
	private LinearLayout mWallpager;
	//private ImageView mAvatar;
	private ListView mDisplay;
	/**
	 * ����������
	 */
	private DesktopAdapter mAdapter;
	/**
	 * �ӿڶ���,�����޸���ʾ��View
	 */
	private onChangeViewListener mOnChangeViewListener;

	public Desktop(Context context, Activity activity,SysUser mUser) {
		mContext = context;
		mActivity = activity;
		// �󶨲��ֵ���ǰView
		mDesktop = LayoutInflater.from(context).inflate(R.layout.desktop, null);
		
		user = mUser;
		role = user.getRole();
		
		findViewById();
		setListener();
		init();
	}

	/**
	 * �󶨽���UI
	 */
	private void findViewById() {
		mWallpager = (LinearLayout) mDesktop
				.findViewById(R.id.desktop_bg);
		//mAvatar = (ImageView) mDesktop.findViewById(R.id.desktop_avatar);
		mDisplay = (ListView) mDesktop.findViewById(R.id.desktop_display);

	}

	/**
	 * UI�¼�����
	 */
	private void setListener() {
	}

	/**
	 * �����ʼ��
	 */
	private void init() {
		mAdapter = new DesktopAdapter(mContext);
		mDisplay.setAdapter(mAdapter);
		
		if("manager".equals(role)){
			mDesktop.setBackgroundResource(R.drawable.manager_main_side_bg);
		}else if("leader".equals(role)){
			mDesktop.setBackgroundResource(R.drawable.manager_main_side_bg);
		}else if("technician".equals(role)){
			mDesktop.setBackgroundResource(R.drawable.technician_main_side_bg);
		}
	}


	/**
	 * �����޸ķ���
	 * 
	 * @param onChangeViewListener
	 */
	public void setOnChangeViewListener(
			onChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;
	}

	/**
	 * ��ȡ�˵�����
	 * 
	 * @return �˵������View
	 */
	public View getView() {
		return mDesktop;
	}

	/**
	 * �л���ʾ����Ľӿ�
	 * 
	 * @author rendongwei
	 * 
	 */
	public interface onChangeViewListener {
		public abstract void onChangeView(int arg0);
	}

	/**
	 * �˵�������
	 * 
	 * @author rendongwei
	 * 
	 */
	public class DesktopAdapter extends BaseAdapter {

		private Context mContext;
		int[] mIcon = {R.drawable.side_home_icon,R.drawable.side_person_info_icon,R.drawable.side_opinion_icon,
				R.drawable.side_customer_service_icon,R.drawable.side_score_icon,R.drawable.side_about_us_icon,
				R.drawable.side_experts_icon};
		String[] mName = { "��ҳ","��������", "�������",  "�ͷ��绰","���Ҵ��","����С�ܼ�","ע��"}; 
		
		private int mChoose = 0;

		public DesktopAdapter(Context context) {
			mContext = context;
		}

		public int getCount() {
			return 7;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public void setChoose(int choose) {
			mChoose = choose;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.desktop_item, null);
				holder = new ViewHolder();
				holder.layout = (LinearLayout) convertView
						.findViewById(R.id.desktop_item_layout);
				holder.icon = (ImageView) convertView
						.findViewById(R.id.desktop_item_icon);
				holder.name = (TextView) convertView
						.findViewById(R.id.desktop_item_name);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(mName[position]);
			if (position == mChoose) {
				holder.name.setTextColor(Color.parseColor("#ffffffff"));
				holder.icon.setImageResource(mIcon[position]);
				holder.layout.setBackgroundColor(Color.parseColor("#20000000"));
			} else {
				holder.name.setTextColor(Color.parseColor("#7fffffff"));
				holder.icon.setImageResource(mIcon[position]);
				holder.layout.setBackgroundResource(Color
						.parseColor("#00000000"));
			}
			convertView.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (mOnChangeViewListener != null) {
						switch (position) {
						case ViewUtil.HOME:
							mOnChangeViewListener.onChangeView(ViewUtil.HOME);
							break;

						case ViewUtil.PERSON:
							mOnChangeViewListener
									.onChangeView(ViewUtil.PERSON);
							break;
						case ViewUtil.OPINION:
							mOnChangeViewListener
									.onChangeView(ViewUtil.OPINION);
							break;
						case ViewUtil.SERVICE:
							mOnChangeViewListener.onChangeView(ViewUtil.SERVICE);
							break;
						case ViewUtil.SCORE:
							mOnChangeViewListener.onChangeView(ViewUtil.SCORE);
							break;
						case ViewUtil.ABOUT:
							mOnChangeViewListener.onChangeView(ViewUtil.ABOUT);
							break;
						case ViewUtil.EXPERTS:
							mOnChangeViewListener
									.onChangeView(ViewUtil.EXPERTS);
							break;
						default:
							mOnChangeViewListener.onChangeView(ViewUtil.HOME);
							break;
						}
						mChoose = position;
						notifyDataSetChanged();
					}

				}
			});
			return convertView;
		}

		class ViewHolder {
			LinearLayout layout;
			ImageView icon;
			TextView name;
		}
	}

}

