package com.footmanager.manager.attendance;

import java.util.HashMap;
import java.util.List;

import com.footmanager.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AttendanceAdapter extends BaseAdapter{

	//数据源
	private List<HashMap<String,Object>> list;
	private Context context;
	 
	 //构造函数
	public AttendanceAdapter (Context context,List<HashMap<String,Object>> list){
		  this.context = context;
		  this.list = list;
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
		if(type==-1){
			view = mInflater.inflate(R.layout.leader_department_attendance_view_item, null);
			TextView text1 = (TextView)view.findViewById(R.id.text1);
			TextView text2 = (TextView)view.findViewById(R.id.text2);
			TextView text3 = (TextView)view.findViewById(R.id.text3);
			if(null != data.get("name1")){
				text1.setText(data.get("name1").toString());
			}
			if(null != data.get("name2")){
				text2.setText(data.get("name2").toString());
			}else{
				text2.setVisibility(View.GONE);
			}
			if(null != data.get("name3")){
				text3.setText(data.get("name3").toString());
			}else{
				text3.setVisibility(View.GONE);
			}
		}
		if(type==0){
			view = mInflater.inflate(R.layout.leader_department_attendance_view_item_0, null);
			
			TextView text = (TextView)view.findViewById(R.id.text);
			if(null != data.get("title")){
				text.setText(data.get("title").toString());
			}
		}
		if(type == 1){
			view = mInflater.inflate(R.layout.leader_department_attendance_view_item_1, null);
			
			TextView text = (TextView)view.findViewById(R.id.text);
			if(null != data.get("title")){
				text.setText(data.get("title").toString());
			}
		}
		if(type == 2){
			view = mInflater.inflate(R.layout.leader_department_attendance_view_item_2, null);
			
			TextView text = (TextView)view.findViewById(R.id.text);
			text.getPaint().setFakeBoldText(true);
			if(null != data.get("title")){
				text.setText(data.get("title").toString());
			}
		}
				
		return view;
	}

}
