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
 * 菜单界面
 * 
 */
public class Desktop {
	private Context mContext;
	private Activity mActivity;
	/**
	 * 当前界面的View
	 */
	private View mDesktop;
	private SysUser user;
	private String role;
	/**
	 * 以下为控件,自己查看布局文件
	 */
	private LinearLayout mWallpager;
	//private ImageView mAvatar;
	private ListView mDisplay;
	/**
	 * 桌面适配器
	 */
	private DesktopAdapter mAdapter;
	/**
	 * 接口对象,用来修改显示的View
	 */
	private onChangeViewListener mOnChangeViewListener;

	public Desktop(Context context, Activity activity,SysUser mUser) {
		mContext = context;
		mActivity = activity;
		// 绑定布局到当前View
		mDesktop = LayoutInflater.from(context).inflate(R.layout.desktop, null);
		
		user = mUser;
		role = user.getRole();
		
		findViewById();
		setListener();
		init();
	}

	/**
	 * 绑定界面UI
	 */
	private void findViewById() {
		mWallpager = (LinearLayout) mDesktop
				.findViewById(R.id.desktop_bg);
		//mAvatar = (ImageView) mDesktop.findViewById(R.id.desktop_avatar);
		mDisplay = (ListView) mDesktop.findViewById(R.id.desktop_display);

	}

	/**
	 * UI事件监听
	 */
	private void setListener() {
	}

	/**
	 * 界面初始化
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
	 * 界面修改方法
	 * 
	 * @param onChangeViewListener
	 */
	public void setOnChangeViewListener(
			onChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;
	}

	/**
	 * 获取菜单界面
	 * 
	 * @return 菜单界面的View
	 */
	public View getView() {
		return mDesktop;
	}

	/**
	 * 切换显示界面的接口
	 * 
	 * @author rendongwei
	 * 
	 */
	public interface onChangeViewListener {
		public abstract void onChangeView(int arg0);
	}

	/**
	 * 菜单适配器
	 * 
	 * @author rendongwei
	 * 
	 */
	public class DesktopAdapter extends BaseAdapter {

		private Context mContext;
		int[] mIcon = {R.drawable.side_home_icon,R.drawable.side_person_info_icon,R.drawable.side_opinion_icon,
				R.drawable.side_customer_service_icon,R.drawable.side_score_icon,R.drawable.side_about_us_icon,
				R.drawable.side_experts_icon};
		String[] mName = { "首页","个人中心", "意见返馈",  "客服电话","给我打分","关于小管家","注销"}; 
		
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

