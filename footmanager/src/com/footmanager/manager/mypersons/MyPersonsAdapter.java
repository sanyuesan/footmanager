package com.footmanager.manager.mypersons;

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

public class MyPersonsAdapter extends BaseAdapter {

	//数据源
	private List<HashMap<String,Object>> list;
	private Context context;
	private int []type;
	 
	 //构造函数
	public MyPersonsAdapter (Context context,List<HashMap<String,Object>> list, int[] type){
		  this.context = context;
		  this.list = list;
		  this.type=type;
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
		int icon=(Integer) list.get(position).get("icon");
		String name = (String)list.get(position).get("name");
		String id = (String)list.get(position).get("id");
		String contactUser = (String)list.get(position).get("contactUser");
		String tel = (String)list.get(position).get("tel");
		String address = (String)list.get(position).get("address");
		String mobile = (String)list.get(position).get("mobile");
		//根据type不同的数据类型构造不同的View,也可以根据1,2,3天数构造不同的样式
		if(type[position]==0){
			view = mInflater.inflate(R.layout.manager_my_persons_agent_item, null);
			
			ImageView image=(ImageView)view.findViewById(R.id.icon);
			image.setImageResource(icon);
			
			TextView nameT = (TextView)view.findViewById(R.id.name);
			nameT.setText(id);
			
			TextView idT = (TextView)view.findViewById(R.id.id);
			idT.setText(id);
			
			TextView telT = (TextView)view.findViewById(R.id.tel);
			telT.setText(tel);
			
		}else{
			view = mInflater.inflate(R.layout.manager_my_persons_server_item, null);
			
			ImageView image=(ImageView)view.findViewById(R.id.icon);
			image.setImageResource(icon);
			
			TextView shopNameT = (TextView)view.findViewById(R.id.shopName);
			shopNameT.setText(name);
			TextView telT = (TextView)view.findViewById(R.id.tel);
			telT.setText(tel);
			TextView addressT = (TextView)view.findViewById(R.id.address);
			addressT.setText(address);
			TextView contactUserT = (TextView)view.findViewById(R.id.contactUser);
			contactUserT.setText(contactUser);
			TextView mobileT = (TextView)view.findViewById(R.id.mobile);
			mobileT.setText(mobile);
			
		}
				
		return view;
	}

}
