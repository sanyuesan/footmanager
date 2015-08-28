package com.footmanager.main;

import android.view.View;
import android.widget.Button;

import com.footmanager.main.TechnicianHorizontalScrollView.SizeCallback;

/**
 * Copyright (c) 2011,
 * All rights reserved.
 * 类说明
 * @author 许美镇
 * @version Revision:1.0 Date:(2012-5-22)
 * 
 */
public class SizeCallbackForMenu implements SizeCallback {
    private View leftMenu;
    private int leftButtonWidth;

    public SizeCallbackForMenu(View leftMenu) {
        super();
        this.leftMenu = leftMenu;
        //this.leftButton = leftButton;
    }

    @Override
    public void onGlobalLayout() {
    	leftButtonWidth = leftMenu.getMeasuredWidth()/2;
        System.out.println("leftButtonWidth=" + leftButtonWidth);
    }

    @Override
    public void getViewSize(int idx, int w, int h, int[] dims) {
        dims[0] = w;
        dims[1] = h;
        if (idx != 1) {
        	//当视图不是中间的视图
            dims[0] = w - leftButtonWidth;;
        }
    }
}


