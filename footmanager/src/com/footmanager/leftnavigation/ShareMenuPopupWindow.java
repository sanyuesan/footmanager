package com.footmanager.leftnavigation;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.footmanager.R;

public class ShareMenuPopupWindow extends PopupWindow {
	private Context mContext;
	private Activity mActivity;
	private View popView;
	private LayoutInflater inflater;
	private GridView gridView;
	
	public ShareMenuPopupWindow(Context context, Activity activity) {
		super(context);
		mContext = context;
		mActivity = activity;
		inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		init();
	}
	
	@SuppressWarnings("deprecation")
	public void init(){
		
		popView = inflater.inflate(R.layout.share_menu_popupwindow, null);
		popView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		setContentView(popView);
		setWidth(LayoutParams.FILL_PARENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		setFocusable(true);
		
		gridView = (GridView)popView.findViewById(R.id.mainGridView);
		
		int[] menuSelectors = {
				R.drawable.recommand_weixin_icon_selector,
				R.drawable.recommand_pengyouquang_icon_selector,
				R.drawable.recommand_qq_icon_selector,
				R.drawable.recommand_weibo_icon_selector,
				R.drawable.recommand_qq_weibo_icon_selector,
				R.drawable.recommand_message_icon_selector
				};
		String[] menuTitles = { "΢�ź���", "΢������Ȧ", "��Ѷ����", "����΢��","��Ѷ΢��","����"}; 
		String[] menuIds = { "weixin", "pengyouquang", "qq", "weibo","qq_weibo","message"}; 
		
		ArrayList<HashMap<String, Object>> listImageItem = new ArrayList<HashMap<String, Object>>();  
	      for(int i=0;i<menuSelectors.length;i++){  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("gridItemImage", menuSelectors[i]);//���ͼ����Դ��ID  
	        map.put("gridItemText", menuTitles[i]);  
	        map.put("gridItemId", menuIds[i]);
	        listImageItem.add(map);  
	      }  
	      SimpleAdapter saImageItems = new SimpleAdapter(mContext,listImageItem,R.layout.main_gridview_item,new String[] {"gridItemImage","gridItemText","gridItemId"},new int[] {R.id.gridItemImage,R.id.gridItemText,R.id.gridItemId}); 
	      //��Ӳ�����ʾ  
	      gridView.setAdapter(saImageItems);  
	      
	    //�����Ϣ����  
	      gridView.setOnItemClickListener(new OnItemClickListener(){  
		  public void onItemClick(AdapterView<?> arg0,View arg1,int position,long arg3) {  
		      HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(position);  
		      gridViewClick((String)item.get("gridItemId"));
		  }
	    });
		
		popView.setFocusableInTouchMode(true);
		
		popView.setOnKeyListener(new View.OnKeyListener() {
				
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					
					if(keyCode == KeyEvent.KEYCODE_MENU && isShowing()){
						dismiss();
						return true;
					}
					return false;
				}
			});
	}
	
	//����gridView����¼�
	private void gridViewClick(String itemId){
		//�ҵĵ���
		if("weixin".equals(itemId)){
			Toast.makeText(mContext, "�����΢�ŷ���",Toast.LENGTH_SHORT).show();
		}
		//��������
		if("pengyouquang".equals(itemId)){
		}
		//�ҵ�����
		if("qq".equals(itemId)){
		}
	}
	
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		//ȡ����͸��
		WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
		lp.alpha = 1.0f;
		lp.dimAmount = 0.0f;
		mActivity.getWindow().setAttributes(lp);
	}
	
	
}
