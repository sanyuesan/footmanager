package com.footmanager.manager.store.leader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.footmanager.R;

public class StoreViewExpandableListAdapter extends BaseExpandableListAdapter {
	private Context context;
	//����Դ
	private String[] groups;
	private String[][] items;
	
	public StoreViewExpandableListAdapter(Context mContext,String[] mGroups,String[][] mItems){
		context = mContext;
		groups = mGroups;
		items = mItems;
		
	}

	//��ȡָ����λ�á�ָ�����б�������б�������  
    @Override  
    public Object getChild(int groupPosition, int childPosition)  
    {  
        return items[groupPosition][childPosition];  
    }  
    @Override  
    public long getChildId(int groupPosition, int childPosition)  
    {  
        return childPosition;  
    }  
    @Override  
    public int getChildrenCount(int groupPosition)  
    {  
        return items[groupPosition].length;  
    }  
    
    //�÷�������ÿ����ѡ������  
    @Override  
    public View getChildView(int groupPosition, int childPosition,  
            boolean isLastChild, View convertView, ViewGroup parent){
    	LayoutInflater mInflater = LayoutInflater.from(context);
		//����һ��View
		View view = null;
		view = mInflater.inflate(R.layout.leader_department_attendance_view_item, null);
		TextView text1 = (TextView)view.findViewById(R.id.text1);
		TextView text2 = (TextView)view.findViewById(R.id.text2);
		TextView text3 = (TextView)view.findViewById(R.id.text3);
		text1.setText(items[groupPosition][childPosition]);
		text2.setText(items[groupPosition][childPosition]);
		text3.setText(items[groupPosition][childPosition]);
    	
        return view;  
    }  
    //��ȡָ����λ�ô���������  
    @Override  
    public Object getGroup(int groupPosition)  
    {  
        return groups[groupPosition];  
    }  
    @Override  
    public int getGroupCount()  
    {  
        return groups.length;  
    }  
    @Override  
    public long getGroupId(int groupPosition)  
    {  
        return groupPosition;  
    }  
    //�÷�������ÿ����ѡ������  
    @Override  
    public View getGroupView(int groupPosition, boolean isExpanded,  
            View convertView, ViewGroup parent){
    	LayoutInflater mInflater = LayoutInflater.from(context);
		//����һ��View
		View view = null;
		view = mInflater.inflate(R.layout.leader_department_attendance_view_item_1, null);
		
		TextView text = (TextView)view.findViewById(R.id.text);
		if(null != groups[groupPosition]){
			text.setText(groups[groupPosition]);
		}
        return view;  
    }  
    @Override  
    public boolean isChildSelectable(int groupPosition, int childPosition)  
    {  
        return true;  
    }  
    @Override  
    public boolean hasStableIds()  
    {  
        return true;  
    } 

}
