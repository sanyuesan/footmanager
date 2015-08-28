package com.footmanager.main;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.Scroller;

/**
 * A HorizontalScrollView (HSV) implementation that disallows touch events (so no scrolling can be done by the user).
 * 
 * This HSV MUST contain a single ViewGroup as its only child, and this ViewGroup will be used to display the children Views
 * passed in to the initViews() method.
 */
public class TechnicianHorizontalScrollView extends HorizontalScrollView {
	
	private final String tag = "MyHorizontalScrollView";
	
	private TechnicianHorizontalScrollView me;//��ǰ�ؼ�
	private View leftMenu;//��߲˵�
	private View rightMenu;//�ұ߲˵�
	private boolean leftMenuOut = false;//��߲˵�״̬
	private boolean rightMenuOut = false;//��߲˵�״̬
	private int ENLARGE_WIDTH = 0;//��չ���
	
    public TechnicianHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public TechnicianHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TechnicianHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }

    void init(Context context) {
        // remove the fading as the HSV looks better without it
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(false);
        me = this;
        me.setVisibility(View.INVISIBLE);
    }
    
    
    /**
     * @param children
     *            The child Views to add to parent.
     * @param scrollToViewIdx
     *            The index of the View to scroll to after initialisation.
     * @param sizeCallback
     *            A SizeCallback to interact with the HSV.
     */
    public void initViews(View[] children, SizeCallback sizeCallback,View leftMenu,View rightMenu) {
        this.leftMenu = leftMenu;
        this.rightMenu = rightMenu;
        ENLARGE_WIDTH = leftMenu.getMeasuredWidth()/2;
        ViewGroup parent = (ViewGroup) getChildAt(0);

        // Add all the children, but add them invisible so that the layouts are calculated, but you can't see the Views
        for (int i = 0; i < children.length; i++) {
            children[i].setVisibility(View.INVISIBLE);
            parent.addView(children[i]);
        }

        // Add a layout listener to this HSV
        // This listener is responsible for arranging the child views.
        OnGlobalLayoutListener listener = new MyOnGlobalLayoutListener(parent, children, sizeCallback);
        getViewTreeObserver().addOnGlobalLayoutListener(listener);
    }

    
    
    

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // Do not allow touch events.
        return false;
    }

    /**
     * An OnGlobalLayoutListener impl that passes on the call to onGlobalLayout to a SizeCallback, before removing all the Views
     * in the HSV and adding them again with calculated widths and heights.
     */
    class MyOnGlobalLayoutListener implements OnGlobalLayoutListener {
        ViewGroup parent;
        View[] children;
        int scrollToViewPos = 0;
        SizeCallback sizeCallback;

        /**
         * @param parent
         *            The parent to which the child Views should be added.
         * @param children
         *            The child Views to add to parent.
         * @param scrollToViewIdx
         *            The index of the View to scroll to after initialisation.
         * @param sizeCallback
         *            A SizeCallback to interact with the HSV.
         */
        public MyOnGlobalLayoutListener(ViewGroup parent, View[] children, SizeCallback sizeCallback) {
            this.parent = parent;
            this.children = children;
            this.sizeCallback = sizeCallback;
        }

        @Override
        public void onGlobalLayout() {
            // System.out.println("onGlobalLayout");

            //final HorizontalScrollView me = MyHorizontalScrollView.this;

            // The listener will remove itself as a layout listener to the HSV
            me.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            // Allow the SizeCallback to 'see' the Views before we remove them and re-add them.
            // This lets the SizeCallback prepare View sizes, ahead of calls to SizeCallback.getViewSize().
            sizeCallback.onGlobalLayout();

            parent.removeViewsInLayout(0, children.length);

            final int w = me.getMeasuredWidth();
            final int h = me.getMeasuredHeight();

            // System.out.println("w=" + w + ", h=" + h);

            // Add each view in turn, and apply the width and height returned by the SizeCallback.
            int[] dims = new int[2];
            scrollToViewPos = 0;
            for (int i = 0; i < children.length; i++) {
                sizeCallback.getViewSize(i, w, h, dims);
                children[i].setVisibility(View.VISIBLE);
                parent.addView(children[i], dims[0], dims[1]);
                if (i == 0) {
                    scrollToViewPos += dims[0];
                }
                Log.d(tag, children[i]+": w=" + dims[0] + ", h=" + dims[1]);
                Log.d(tag, "scrollToViewIdx:"+0+",scrollToViewPos:"+scrollToViewPos);
            }

            // For some reason we need to post this action, rather than call immediately.
            // If we try immediately, it will not scroll.
            
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    me.scrollBy(scrollToViewPos,0);
                    //��Ϊ��Щ�ؼ�Ĭ�϶�Ϊ���أ��ؼ�������ɺ����ó���ʾ
                    me.setVisibility(View.VISIBLE);
                    leftMenu.setVisibility(View.VISIBLE);
                    rightMenu.setVisibility(View.VISIBLE);
                }
            });
        }
    }
    
    /**
     * �����߰�ť
     * @param leftButtonWidth ��߰�ť�Ŀ��
     */
    public void clickLeftButton(int rightButtonWidth){
    	//���
		rightMenu.setVisibility(View.GONE);
		leftMenu.setVisibility(View.VISIBLE);
    	int menuWidth = leftMenu.getMeasuredWidth()/2;//-ENLARGE_WIDTH;
        System.out.println("leftmenuWidth:"+menuWidth);
        if (!leftMenuOut) {
            int left = 0;
            me.smoothScrollTo(left, 0);
        } else {
            int left = menuWidth;
            me.smoothScrollTo(left, 0);
        }
        leftMenuOut = !leftMenuOut;
    }
    
    /**
     * ����ұ߰�ť 
     * @param rightButtonWidth �ұ߰�ť�Ŀ��
     */
    public void clickRightButton(int rightButtonWidth){
    	//�ұ�
		leftMenu.setVisibility(View.GONE);
		rightMenu.setVisibility(View.VISIBLE);
		int menuWidth = rightMenu.getMeasuredWidth()/2;// - ENLARGE_WIDTH;
        if (!rightMenuOut) {
        	int right = menuWidth + me.getMeasuredWidth();
        	System.out.println("rightmenuWidth:"+right);
            me.smoothScrollTo(right, 0);
        } else {
        	int right = menuWidth;
        	System.out.println("rightmenuWidth:"+right);
            me.smoothScrollTo(right, 0);
        }
        rightMenuOut = !rightMenuOut;
    }
    
    

    @Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(ev);
    	return false;
	}

	/**
     * Callback interface to interact with the HSV.
     */
    public interface SizeCallback {
        /**
         * Used to allow clients to measure Views before re-adding them.
         */
        public void onGlobalLayout();

        /**
         * Used by clients to specify the View dimensions.
         * 
         * @param idx
         *            Index of the View.
         * @param w
         *            Width of the parent View.
         * @param h
         *            Height of the parent View.
         * @param dims
         *            dims[0] should be set to View width. dims[1] should be set to View height.
         */
        public void getViewSize(int idx, int w, int h, int[] dims);
    }
}
