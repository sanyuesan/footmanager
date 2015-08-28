package com.footmanager;

import android.util.Base64;

public class Test {
	
	public static void main(String[] args) {
		try{
			String str = "12365:111";
			System.out.println("$$$$$" + Base64.encodeToString(str.getBytes(), Base64.DEFAULT) +"$$$$");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
