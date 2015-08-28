package com.footmanager.manager.vacation;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.footmanager.R;
import com.footmanager.manager.promotion.PromotionViewActivity;

public class VacationAdapter extends BaseAdapter {
	//数据源
		private List<HashMap<String,Object>> list;
		private Context context;
		private Activity mActivity;
		 
		 //构造函数
		public VacationAdapter (Context context,List<HashMap<String,Object>> list,Activity activity){
			  this.context = context;
			  this.list = list;
			  mActivity=activity;
		} 

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater mInflater = LayoutInflater.from(context);
			//产生一个View
			View view = null;
			HashMap data = list.get(position);
			int type = (Integer)data.get("type");
			
			//根据type不同的数据类型构造不同的View,也可以根据1,2,3天数构造不同的样式
			if(type==1){
				view = mInflater.inflate(R.layout.technician_vacation_list_item, null);
				TextView text1 = (TextView)view.findViewById(R.id.vacationDate);
				TextView text2 = (TextView)view.findViewById(R.id.vacationName);
				TextView text3 = (TextView)view.findViewById(R.id.vacationDays);
				TextView text4 = (TextView)view.findViewById(R.id.vacationState);
				
				text1.setText(data.get("date").toString());
				text2.setText(data.get("name").toString());
				text3.setText(data.get("day").toString());
				text4.setText(data.get("state").toString());
			}
			if(type==2){
				view = mInflater.inflate(R.layout.technician_vacation_apply_btn_item, null);
				String itemId = data.get("itemId").toString();
				TextView text = (TextView)view.findViewById(R.id.itemId);
				text.setText(itemId);
				
				Button btn = (Button)view.findViewById(R.id.btn);
				btn.setText(data.get("btnText").toString());
				//公假
				if("pubV".equals(itemId)){
					btn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
					    	Intent intent = new Intent(mActivity,VacationApplyActivity.class);
					    	Bundle bundle = new Bundle();
					    	//假期类型
					    	bundle.putSerializable("type", "pubV");
					    	intent.putExtras(bundle);
					    	mActivity.startActivity(intent);
						}
					});
				}
				//事假
				if("eveV".equals(itemId)){
					btn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
					    	Intent intent = new Intent(mActivity,VacationApplyActivity.class);
					    	Bundle bundle = new Bundle();
					    	//假期类型
					    	bundle.putSerializable("type", "eveV");
					    	intent.putExtras(bundle);
					    	mActivity.startActivity(intent);
						}
					});
				}
				//临时假
				if("timV".equals(itemId)){
					btn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
					    	Intent intent = new Intent(mActivity,VacationApplyTemporaryActivity.class);
					    	Bundle bundle = new Bundle();
					    	//假期类型
					    	bundle.putSerializable("type", "timV");
					    	intent.putExtras(bundle);
					    	mActivity.startActivity(intent);
						}
					});
				}
				
				
			}
					
			return view;
		}
}
