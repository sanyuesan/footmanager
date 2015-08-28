package com.footmanager.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.footmanager.R;


public class BannerLayout extends ViewGroup {
	private Context context;

	public static Scroller scroller;
	private float mLastMotionX;
//	private int mTouchSlop;
	
	private OnItemClickListener onItemClickListener;
	
	private int currentScreenIndex=0;
	
	private boolean moving=false;
	
	private boolean autoScroll=false;
	
	private int scrollTime=4*1000;
	
	private int currentWhat=0;
	
	//移动方向(true正向，true反向)
	private boolean moveDirection = true;
	
	private List<Bitmap> imageList;
	private List<View> dots; // 图片标题正文的那些点
	private List<String> titles;
	private TextView titleET;
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			//判断网络连接不可用时不再滚动？？//TODO:
			if(autoScroll && currentWhat==msg.what && null != imageList && imageList.size() > 0){
			//if(autoScroll && currentWhat==msg.what){
				currentScreenIndex=(currentScreenIndex+1)%getChildCount();
				scrollToScreen(currentScreenIndex);
				
				Log.i("TAG","handleMessage scrollToScreen:"+currentScreenIndex);
				
				if(autoScroll){
					handler.sendEmptyMessageDelayed(currentWhat, scrollTime);
				}
			}else if(404 == msg.what){
				Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_LONG).show();
			}
		}
	};
	
	 Handler imgHandler = new Handler() {
	        public void handleMessage(Message msg) {
	        	if(null != imageList){
	        		if(null != titleET && null != titles){
	    				titleET.setText(titles.get(0));
	    			}
	    			for(int i=0;i<imageList.size();i++){
	    				ImageView ii=new ImageView(context);//初始化ImageView
	    				ii.setImageBitmap(imageList.get(i));
	    				//ii.setImageDrawable(getResources().getDrawable(Integer.parseInt(imageList.get(i).toString())));//设置图片
	    				ii.setScaleType(ImageView.ScaleType.FIT_XY);
	    				ii.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
	    				ii.setAdjustViewBounds(true);
	    				addView(ii);//添加到LinearLayout中 
	    			}
	    		}
	        	autoScroll = true;
	        	handler.sendEmptyMessageDelayed(currentWhat, scrollTime);
	        }
	    };
	
	public BannerLayout(Context context) {
		super(context);
		this.context = context;
		initView();
	}
	
	public BannerLayout(Context context,List imageList) {
		super(context);
		this.context = context;
		this.imageList = imageList;
		initView();
	}

	public BannerLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		initView();
	}

	public BannerLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}

	private void initView() {
		new Thread(new Runnable() {
			public void run() {
				try {
					imageList = initListData(context);
					// 通过handler来发送消息
					imgHandler.sendMessage(imgHandler.obtainMessage(22, imageList));
					Looper.prepare();
				} catch (Exception e) {
					Log.e("com.manager", e.toString());
				}
			}
		}).start();
		this.scroller = new Scroller(context, new DecelerateInterpolator(4));//OvershootInterpolator(1.1f)
		
		this.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onClick(int position, View childview) {
				// TODO Auto-generated method stub
				Toast.makeText(context.getApplicationContext(), "点击了index："+position,Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	//初始化菜单列表
	private ArrayList<Bitmap> initListData(Context context){
			
		String url = "http://bus.51you.com/tour/self/tourselfdetail.jsp?source=b2c&id=T090337980";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		//String result = null;
		String result = HttpUtil.sendByPost(context,url, params,null);
		ArrayList<Bitmap> listData = new ArrayList<Bitmap>();
		if(null != result){
			JSONObject resultJson = JSONObject.fromObject(result);
			JSONArray dataArr = resultJson.getJSONObject("resultBean").getJSONArray("imgList");
			titles = new ArrayList<String>();
			for(int i =0; i < 5; i++) { 
				JSONObject obj = dataArr.getJSONObject(i);
				String imgUrl = obj.getString("picPath");
				titles.add(obj.getString("picName"));
				if(!"".equals(imgUrl)){
					Bitmap bitMap = HttpUtil.getHttpBitmap(imgUrl);
					listData.add(bitMap);
				}
			}  
		}
		return listData;
	    
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int maxHeight=-1;
		
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
			
			maxHeight=Math.max(maxHeight, getChildAt(i).getMeasuredHeight());
			
		}
		maxHeight=Math.min(maxHeight, MeasureSpec.getSize(heightMeasureSpec));
		
		Log.i("TAG","onMeasure Height:"+maxHeight);
		
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),maxHeight);
	}
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {

		final int count = getChildCount();

		int cLeft = 0;
		
		
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() == View.GONE)
				continue;
			
//			child.setVisibility(View.VISIBLE);
			final int childWidth = child.getMeasuredWidth();
			child.layout(cLeft, 0, cLeft +childWidth, child.getMeasuredHeight());

			cLeft += childWidth;
		}
	}

	@Override
	public void computeScroll() {
		if (scroller.computeScrollOffset()) {
			scrollTo(scroller.getCurrX(), 0);
			postInvalidate();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (getChildCount() == 0)
			return false;
		final int action = ev.getAction();
		final float x = ev.getX();
		switch (action) {
		case MotionEvent.ACTION_DOWN:

			autoScroll=false;
			
			currentWhat++;
			
			mLastMotionX = x;
			if (!scroller.isFinished()) {
				scroller.abortAnimation();
			}
			
			moving=false;
//			Log.i("TAG","ACTION_DOWN");
			
			return true;

		case MotionEvent.ACTION_MOVE:
			final int deltaX = (int) (mLastMotionX - x);
			if(deltaX>=0){
				moveDirection = true;
			}else{
				moveDirection = false;
			}
			boolean xMoved = Math.abs(deltaX) > 4;
			if(!moving && !xMoved)
				break;
			mLastMotionX = x;
			
			if((0==currentScreenIndex && deltaX<0) || (getChildCount()-1==currentScreenIndex && deltaX>0))
				scrollBy(deltaX/4, 0);
			else
				scrollBy(deltaX, 0);
			
			moving=true;
			
			return true;
		case MotionEvent.ACTION_UP:
			snapToDestination();
			
			if(!autoScroll)
			{
				startScroll();
			}
			if(!moving && null!=onItemClickListener)
			{
				final int screenWidth = getWidth();
				int index=(int) ((getScrollX()+x)/ screenWidth);
				onItemClickListener.onClick(index,getChildAt(index));
			}
				
			break;
		case MotionEvent.ACTION_CANCEL:
			snapToDestination();
			if(!autoScroll)
			{
				startScroll();
			}
			
		}
		return false;
	}
	private void scrollToScreen(int whichScreen)
	{
//		if (!scroller.isFinished())
//			return;
//		Log.e("TAG","scrollToScreen:"+whichScreen);
		if(whichScreen>=getChildCount())
			whichScreen=getChildCount()-1;
		
		int delta = 0;
		
		delta = whichScreen * getWidth() - getScrollX();
		
//		scroller.startScroll(getScrollX(), 0, delta, 0, Math.abs(delta) * 2);
		scroller.startScroll(getScrollX(), 0, delta, 0, 1500);
		invalidate();
		currentScreenIndex=whichScreen;
		if(null != dots){
			int oldIndex = 0;
			//判断手指滑动的方向
			if(moveDirection){
				oldIndex = currentScreenIndex-1;
			}else{
				oldIndex = currentScreenIndex+1;
			}
			//恢复方向
			moveDirection = true;
			if(oldIndex == -1){
				oldIndex = 4;
			}
			if(oldIndex == 5){
				oldIndex = 0;
			}
			if(null != titleET){
				titleET.setText(titles.get(currentScreenIndex));
			}
			dots.get(oldIndex).setBackgroundResource(R.drawable.dot_normal);
			dots.get(currentScreenIndex).setBackgroundResource(R.drawable.dot_focused);
		}
	}
	private void snapToDestination()
	{
		final int x=getScrollX();
		final int screenWidth = getWidth();
		//判断手势滑动的方向
		if(moveDirection){
			scrollToScreen((x + (screenWidth*7 / 8))/ screenWidth);
		}else{
			scrollToScreen((x + (screenWidth / 8))/ screenWidth);
		}
		
	}
	
	public int getCurrentScreenIndex() {
		return currentScreenIndex;
	}
	public void startScroll()
	{
		
		autoScroll=true;
		handler.sendEmptyMessageDelayed(currentWhat, scrollTime);
	}
	public boolean isScrolling()
	{
		return autoScroll;
	}
	public void stopScroll()
	{
		autoScroll=false;
		currentWhat++;
	}
	@Override
	protected void finalize() throws Throwable {

		Log.i("TAG","finalize===");

		super.finalize();
	}
	
	public OnItemClickListener getOnItemClickListener() {
		return onItemClickListener;
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public interface OnItemClickListener
	{
		public void onClick(int index,View childview);
	}

	public List<View> getDots() {
		return dots;
	}

	public void setDots(List<View> dots) {
		this.dots = dots;
	}

	public TextView getTitleET() {
		return titleET;
	}

	public void setTitleET(TextView titleET) {
		this.titleET = titleET;
	}
	
}