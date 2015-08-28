package com.footmanager.manager.promotion;

import java.util.ArrayList;
import java.util.HashMap;

import com.footmanager.R;
import com.footmanager.R.layout;
import com.footmanager.R.menu;
import com.footmanager.manager.store.StoreActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PromotionActivity extends Activity {
	//����������
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button ruleBtn;
    
    private GridView promotionGridView;
    
    private Button consultBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
		setContentView(R.layout.manager_promotion_activity);
		
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		ruleBtn = (Button)findViewById(R.id.title_right_btn);
		
		initLayout();
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		//��ѯʹ�ù���
		ruleBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PromotionActivity.this,PromotionUseRuleActivity.class));
			}
		});
		
		promotionGridView = (GridView)findViewById(R.id.promotionGridView);
		initMainGridView();
		
		//������ѯ
		consultBtn = (Button)findViewById(R.id.consultBtn);
		consultBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//finish();
			}
		});
	}
	
	//��ʼ������
	private void initLayout(){
		//����������
		mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
		//��������ఴť��selector
		leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
		//��������Ŀ
		titleTextView.setText("��������");
	}
	
	//��ʼ��gridView
	private void initMainGridView(){
		int[] menuSelectors = {
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon,
				R.drawable.promotion_icon
				};
		String[] menuTitles = { "����", "����", "����", "����","����","����","����",
				"����","����","����", "����", "����", "����","����","����","����",
				"����","����"}; 
		String[] menuIds = { "manager_store", "manager_promotion", "manager_obtain", "manager_job","manager_persons",
				"manager_discuss","manager_income","manager_agency","manager_recommend","manager_store", "manager_promotion", "manager_obtain", "manager_job","manager_persons",
				"manager_discuss","manager_income","manager_agency","manager_recommend"}; 
		
		 ArrayList<HashMap<String, Object>> listImageItem = new ArrayList<HashMap<String, Object>>();  
	      for(int i=0;i<menuSelectors.length;i++){  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("gridItemImage", menuSelectors[i]);//���ͼ����Դ��ID  
	        map.put("gridItemText", menuTitles[i]);  
	        map.put("gridItemId", menuIds[i]);
	        listImageItem.add(map);  
	      }  
	      SimpleAdapter saImageItems = new SimpleAdapter(this,listImageItem,R.layout.main_gridview_item,  
	    		  new String[] {"gridItemImage","gridItemText","gridItemId"},new int[] {R.id.gridItemImage,R.id.gridItemText,R.id.gridItemId});  
	      //��Ӳ�����ʾ  
	      promotionGridView.setAdapter(saImageItems);  
	      //�����Ϣ����  
	      promotionGridView.setOnItemClickListener(new OnItemClickListener(){  
			  public void onItemClick(AdapterView<?> arg0,View arg1,int position,long arg3) {  
			      HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(position);  
			      gridViewClick((String)item.get("gridItemId"));
			  }
	      });  
	}
	//����gridView����¼�
	private void gridViewClick(String itemId){
		Intent intent = new Intent(this, PromotionViewActivity.class);
		Bundle bundle = new Bundle();
    	bundle.putSerializable("proType", "pro");
    	intent.putExtras(bundle);
		startActivity(intent);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.promotion, menu);
		return true;
	}

}
