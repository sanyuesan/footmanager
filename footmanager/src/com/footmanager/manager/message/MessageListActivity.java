package com.footmanager.manager.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.HttpUtil;
import com.footmanager.util.SysUser;
import com.footmanager.util.listutil.XListView;
import com.footmanager.util.listutil.XListView.IXListViewListener;
import com.footmanager.util.listutil.XListViewFooter;

public class MessageListActivity extends Activity implements OnScrollListener, IXListViewListener  {
	private XListView listView;
	
	private String type;
	private String role;
	
	private View mainView;
	private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    
    //private View footer;
    //private View footerFinished;
    private int currPage = 0;
    private int pageSize = 20;
    private ArrayList<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
    private SimpleAdapter adapter;
    boolean loadingFlag = false;// 上拉加载数据标识加载是否完成，默认完成
    boolean finishLoaded = false;//是否还有更多数据标识
    
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
        	if(msg.what == 20){
        		adapter.notifyDataSetChanged();
        		//listView.setAdapter(adapter);
        	}else if(msg.what == 404){
        		finishLoaded = true;
        		Toast.makeText(getApplicationContext(), "网络不可用，请检查网络", Toast.LENGTH_LONG).show();
        	}
        	onLoad();
        }
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manager_message_list_activity);
		//listview页脚
	    //footer = getLayoutInflater().inflate(R.layout.listview_footer, null);
	    //footerFinished = getLayoutInflater().inflate(R.layout.listview_footer_finished, null);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
		//获取参数 send:发送,receive:接收
		Intent intent = this.getIntent(); 
		type = (String)intent.getSerializableExtra("type");
		
		findViewById();
		setListener();
		initLayout();
		initData();
		
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		listView = (XListView)findViewById(R.id.list);
		listView.setOnScrollListener(this);
		listView.setPullLoadEnable(true);// 启用加载更多
		listView.setXListViewListener(this);
		adapter = new SimpleAdapter(MessageListActivity.this,listData,R.layout.manager_message_list_item,  
				new String[]{"messageContent","messageSendUser","messageSendDate"},
				new int[]{R.id.messageContent,R.id.messageSendUser,R.id.messageSendDate});
		listView.setAdapter(adapter);
		
		if("technician".equals(role)){
			leftButton = (Button)findViewById(R.id.title_btn);
			titleTextView = (TextView)findViewById(R.id.title_text);
		}
	}
	
	private void setListener() {
		if("technician".equals(role)){
			//返回上个页面
			leftButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					finish();
				}
			});
		}
		
	}
	private void initData(){
		/*new Thread(new Runnable() {
            public void run() {
                try {
                	initListData(); 
                    // 通过handler来发送消息
                    handler.sendEmptyMessage(20);
                    Looper.prepare();
                } catch (Exception e) {
                    Log.e("com.footmanager", e.toString());
                }
            }
        }).start();*/
		onLoadMore();
	}
	
	//初始化布局
	private void initLayout(){
		if("technician".equals(role)){
			//标题栏题目
			titleTextView.setText("消息");
			//设置页面背景
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//标题栏背景
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//标题栏左侧按钮的selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
		}else if("manager".equals(role)){
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//标题栏不可见
			mainTitleView.setVisibility(View.GONE);
		}else if("leader".equals(role)){
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//标题栏不可见
			mainTitleView.setVisibility(View.GONE);
		}
	}
	

	//初始化菜单列表
	private void initListData(){
		String url = "http://bus.51you.com/web/phone/prod/tourpre/newquerytourpre.jsp";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//params.add(new BasicNameValuePair("deptCode","PEK"));
		params.add(new BasicNameValuePair("pageSize",pageSize+""));
		params.add(new BasicNameValuePair("pageNo",currPage+""));
		
		//String result = null;
		String result = HttpUtil.sendByPost(this,url, params,null);
		//String result = HttpUtil.sendByGet(url, "deptCode=PEK&pageSize=20&pageNo=0");
		//HttpUtil.getJSONByVolley(this, url+"?deptCode=PEK&pageSize=20&pageNo=0");
		if(null != result && !"".equals(result)){
			//Gson gson = new Gson();
			//prodsdata = gson.fromJson(pageStr, PreSaleDetailVo.class);
			
			JSONObject resultJson = JSONObject.fromObject(result);
			JSONArray dataArr = resultJson.getJSONObject("page").getJSONArray("data");
			//当数据小于pageSize时，说明为最后一页，没有更多数据了
			if(dataArr.length() != 0){
				finishLoaded = false;
			}else{
				//footerIcon.setVisibility(View.GONE);
				//footerText.setText("没有更多结果了");
				finishLoaded = true;
				//mhandle.sendMessage(mhandle.obtainMessage(123, listData));
				
			}
			currPage++;
			for(int i =0; i < dataArr.length(); i++) { 
				JSONObject obj = dataArr.getJSONObject(i);
				Map<String,Object> item = new HashMap<String,Object>();  
				item.put("messageContent", obj.getString("name"));  
				item.put("messageSendUser", obj.getString("code"));  
				item.put("messageSendDate", obj.getString("createTime"));
				listData.add(item);   
			}  
		}
	}
	
	/**
     * firstVisibleItem：第一个可见item 
     * visibleItemCount：可见item数量
     * totalItemCount：总条目数量
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
    }

    /**
     * 监听滚动状态改变：1-手指正在滑动 2-手指停止滑动 3-组件停止滚动
     */
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/** 停止刷新， */
	private void onLoad() {
		//listView.stopRefresh();
		if(!finishLoaded){
			listView.stopLoadMore(XListViewFooter.STATE_NORMAL);
		}else{
			listView.stopLoadMore(XListViewFooter.STATE_END);
		}
		//listView.setRefreshTime("刚刚");
	}

	@Override
	public void onRefresh() {
		/*handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				initListData(); 
				adapter.notifyDataSetChanged();
				handler.sendMessage(handler.obtainMessage(123, listData));
				listView.setAdapter(mAdapter1);
				onLoad();
			}
		}, 2000);*/
		
	}

	@Override
	public void onLoadMore() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				initListData();
				handler.sendEmptyMessage(20);
			}
		}).start();
		
	}

}
