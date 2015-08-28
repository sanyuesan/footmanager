package com.footmanager.util.popupwindowutil;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.footmanager.R;
import com.footmanager.util.SizeUtil;

/**
 *	�������������ⰴť�ϵĵ������̳���PopupWindow��
 */
public class TitleMenuPopup extends PopupWindow {
	private Context mContext;

	//�б����ļ��
	protected final int LIST_PADDING = 10;
	
	//ʵ����һ������
	private Rect mRect = new Rect();
	
	//�����λ�ã�x��y��
	private final int[] mLocation = new int[2];
	
	//��Ļ�Ŀ�Ⱥ͸߶�
	private int mScreenWidth,mScreenHeight;

	//�ж��Ƿ���Ҫ��ӻ�����б�������
	private boolean mIsDirty;
	
	//λ�ò�������
	private int popupGravity = Gravity.NO_GRAVITY;	
	
	//����������ѡ��ʱ�ļ���
	private OnItemOnClickListener mItemOnClickListener;
	
	//�����б����
	private ListView mListView;
	
	//���嵯���������б�
	private ArrayList<String> mActionItems = new ArrayList<String>();			
	
	public TitleMenuPopup(Context context){
		//���ò��ֵĲ���
		this(context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}
	
	public TitleMenuPopup(Context context, int width, int height){
		this.mContext = context;
		
		//���ÿ��Ի�ý���
		setFocusable(true);
		//���õ����ڿɵ��
		setTouchable(true);	
		//���õ�����ɵ��
		setOutsideTouchable(true);
		
		//�����Ļ�Ŀ�Ⱥ͸߶�
		mScreenWidth = SizeUtil.getScreenWidth(mContext);
		mScreenHeight = SizeUtil.getScreenHeight(mContext);
		
		//���õ����Ŀ�Ⱥ͸߶�
		setWidth(width);
		setHeight(height);
		
		setBackgroundDrawable(new BitmapDrawable());
		
		//���õ����Ĳ��ֽ���
		setContentView(LayoutInflater.from(mContext).inflate(R.layout.title_menu, null));
		
		initUI();
	}
		
	/**
	 * ��ʼ�������б�
	 */
	private void initUI(){
		mListView = (ListView) getContentView().findViewById(R.id.title_list);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,long arg3) {
				//���������󣬵�����ʧ
				dismiss();
				
				if(mItemOnClickListener != null){
					mItemOnClickListener.onItemClick(mActionItems.get(index), index);
				}
					
			}
		}); 
	}
	
	/**
	 * ��ʾ�����б����
	 */
	public void show(View view){
		//��õ����Ļ��λ������
		view.getLocationOnScreen(mLocation);
		
		//���þ��εĴ�С
		mRect.set(mLocation[0], mLocation[1], mLocation[0] + view.getWidth(),mLocation[1] + view.getHeight());
		
		//�ж��Ƿ���Ҫ��ӻ�����б�������
		if(mIsDirty){
			populateActions();
		}
		
		//��ʾ������λ��
		showAtLocation(view, popupGravity, mScreenWidth - LIST_PADDING - (getWidth()/2), mRect.bottom);
	}
	
	/**
	 * ���õ����б�����
	 */
	private void populateActions(){
		mIsDirty = false;
		
		//�����б��������
		mListView.setAdapter(new BaseAdapter() {			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater mInflater = LayoutInflater.from(mContext);
				//����һ��View
				View view = null;
				view = mInflater.inflate(R.layout.title_menu_item, null);
				
				TextView textView = (TextView)view.findViewById(R.id.title_menu_text);
				
				/*if(convertView == null){
					textView = new TextView(mContext);
					textView.setTextColor(mContext.getResources().getColor(android.R.color.white));
					textView.setTextSize(14);
					//�����ı�����
					textView.setGravity(Gravity.CENTER);
					//�����ı���ķ�Χ
					textView.setPadding(0, 10, 0, 10);
					//�����ı���һ������ʾ�������У�
					textView.setSingleLine(true);
				}else{
					textView = (TextView) convertView;
				}*/
				
				String menuText = mActionItems.get(position);
				
				//�����ı�����
				textView.setText(menuText);
				//����������ͼ��ļ��
				//textView.setCompoundDrawablePadding(10);
				//���������ֵ���߷�һ��ͼ��
                //textView.setCompoundDrawablesWithIntrinsicBounds(item.mDrawable, null , null, null);
				
                return view;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return mActionItems.get(position);
			}
			
			@Override
			public int getCount() {
				return mActionItems.size();
			}
		}) ;
	}
	
	/**
	 * ���ò˵���
	 * @param actionItems
	 */
	public void setActionList(ArrayList<String> actionItems){
		mActionItems = actionItems;
		mIsDirty = true;
	}
	
	/**
	 * ����λ�õõ�������
	 */
	public String getAction(int position){
		if(position < 0 || position > mActionItems.size())
			return null;
		return mActionItems.get(position);
	}			
	
	/**
	 * ���ü����¼�
	 */
	public void setItemOnClickListener(OnItemOnClickListener onItemOnClickListener){
		this.mItemOnClickListener = onItemOnClickListener;
	}
	
	/**
	 *	�������������������ť�����¼�
	 */
	public static interface OnItemOnClickListener{
		public void onItemClick(String item , int position);
	}
}
