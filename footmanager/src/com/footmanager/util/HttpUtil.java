package com.footmanager.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class HttpUtil {
	
	/**
     * �ж������Ƿ���ͨ
     * @param context
     * @return
     */ 
    public static boolean isNetworkConnected(Context context){ 
    	if(null != context){
    		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE); 	
    		if (connectivityManager == null){
                return false;
            }else{
                // ��ȡNetworkInfo����
                NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
                
                if (networkInfo != null && networkInfo.length > 0){
                    for (int i = 0; i < networkInfo.length; i++){
                       // System.out.println(i + "===״̬===" + networkInfo[i].getState());
                       // System.out.println(i + "===����===" + networkInfo[i].getTypeName());
                        // �жϵ�ǰ����״̬�Ƿ�Ϊ����״̬
                        if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED){
                            return true;
                        }
                    }
                }
            } 
    	}
    	return false;
    }
    
    /**
     * ����δ���ܣ���������
     * @param context
     */
    public static void setNetworkConnect(final Context context){
    	/*AlertDialog.Builder builder = new Builder(context); 
        builder.setTitle("��������"); 
        builder.setMessage("���粻���ã�����������"); 
           
        builder.setPositiveButton("��������",new DialogInterface.OnClickListener() { 
               
            @Override 
            public void onClick(DialogInterface dialog, int which) { 
                //��ȡϵͳ�汾�� 
                /* Build.VERSION_CODES
                    1 (0x00000001)           Android 1.0             BASE
                    2 (0x00000002)           Android 1.1             BASE_1_1
                    3 (0x00000003)           Android 1.5             CUPCAKE
                    4 (0x00000004)           Android 1.6             DONUT
                    5 (0x00000005)           Android 2.0             ECLAIR
                    6 (0x00000006)           Android 2.0.1          ECLAIR_0_1
                    7 (0x00000007)           Android 2.1             ECLAIR_MR1
                    8 (0x00000008)           Android 2.2             FROYO
                    9 (0x00000009)           Android 2.3             GINGERBREAD
                    10 (0x0000000a)         Android 2.3.3          GINGERBREAD_MR1
                    11 (0x0000000b)         Android 3.0             HONEYCOMB
                    12 (0x0000000c)         Android 3.1             HONEYCOMB_MR1
                    13 (0x0000000d)         Android 3.2             HONEYCOMB_MR2 * 
                int currentapiVersion=android.os.Build.VERSION.SDK_INT; 
                System.out.println("currentapiVersion = " + currentapiVersion); 
                Intent intent; 
                if(currentapiVersion < 11){ 
                    intent = new Intent(); 
                    intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings"); 
                }else{ 
                    //3.0�Ժ� 
                    //intent = new Intent( android.provider.Settings.ACTION_WIRELESS_SETTINGS); 
                    intent = new Intent( android.provider.Settings.ACTION_SETTINGS); 
                } 
                context.startActivity(intent); 
            } 
        }); 
        builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() { 
               
            @Override 
            public void onClick(DialogInterface dialog, int which) { 
                   
            } 
        }); 
        builder.create().show();
        */
    	Toast.makeText(context, "���粻���ã���������", Toast.LENGTH_LONG).show();
        
    }
	
	/**
	 * get������������
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendByGet(Context context,String url, String params) {
		String result = null;
		try{
			//����url
			String tmpUrl = url;
			if (params != null) {
				tmpUrl += "?" + params;
			}
			HttpGet httpGet = new HttpGet(tmpUrl);
			HttpResponse response = new DefaultHttpClient().execute(httpGet); 
			if(response.getStatusLine().getStatusCode() != 200){
				result = EntityUtils.toString(response.getEntity());
			}
		}catch(Exception e){
			Log.e("footmanager", e.toString());
		}
		return result;
	}
	
	/**
	 * post������������
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendByPost(Context context,String url, List<NameValuePair> params,Map<String,String> headers) {
		String result = null;
        try{
        	HttpPost httpPost = new HttpPost(url);
        	httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
        	httpPost.addHeader("Content-Type", "application/json");
        	if(null != headers){
        		for(Map.Entry<String, String> entry:headers.entrySet()){
        			httpPost.addHeader(entry.getKey(),entry.getValue());
        		}
        	}
        	HttpResponse response = new DefaultHttpClient().execute(httpPost); 
        	if(response.getStatusLine().getStatusCode() == 200){
        		result = EntityUtils.toString(response.getEntity());
        	}else{
        		Toast.makeText(context, "��������ʧ�ܣ���������", Toast.LENGTH_LONG);
        	}
        }catch(Exception e){
        	Log.e("footmanager", e.toString());
        }
        
		return result;
	}
	
	/**
     * HttpURLConnection post��ʽ��������
     * 
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
	public static String sendConnByPost(String url, String params, Map<String,String> headerMap) throws Exception {
        String retStr = "";
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(512);
        try {
            URL urlObj = new URL(url);
            //��http����
            HttpURLConnection httpConn = (HttpURLConnection)(urlObj.openConnection());
            //����http��������
            httpConn.setConnectTimeout(30000);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            //��������header
            if(headerMap!=null && !headerMap.isEmpty()){
            	//httpsConnection.setRequestProperty("Content-Type","multipart/form-data");
            	for(String key:headerMap.keySet()){
            		if(StringUtils.isNotBlank(key) && headerMap.get(key)!=null){
            			httpConn.setRequestProperty(key, headerMap.get(key));
            		}
            	}
            }
            OutputStream out = httpConn.getOutputStream();
            if(null != params){
            	out.write(params.getBytes("UTF-8"));
            }
            //��������
            httpConn.connect();

            //������Ӧ����
            InputStream reader = httpConn.getInputStream();
            byte[] bytes = new byte[1024];
            int length = reader.read(bytes);

            do {
                buffer.write(bytes, 0, length);
                length = reader.read(bytes);
            } while (length > 0);
            
            out.close();
            reader.close();
            httpConn.disconnect();

            //������Ӧ����,������Ϊutf-8����
            retStr = new String(buffer.toByteArray(), "ISO-8859-1");
            retStr = new String(retStr.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retStr;
    }
	
	
	 /**
     * HttpURLConnection get��ʽ��������
     * 
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendConnByGet(String url, String params, Map<String,String> headerMap) throws Exception {
        String retStr = null;
        //����url
        String tmpUrl = url;
        if (params != null) {
            tmpUrl += "?" + params;
          //ͳһ����һ��ʱ����������ڷ����ظ���������
          //  params += "&currentTimeMillis="+System.currentTimeMillis();
        }
        URL urlObj = new URL(tmpUrl);

        //��http����
        HttpURLConnection httpConn = (HttpURLConnection)(urlObj.openConnection());

        //����http��������
        httpConn.setConnectTimeout(30000);
        httpConn.setRequestMethod("GET");
        //��������header
        if(headerMap!=null && !headerMap.isEmpty()){
        	//httpsConnection.setRequestProperty("Content-Type","multipart/form-data");
        	for(String key:headerMap.keySet()){
        		if(StringUtils.isNotBlank(key) && headerMap.get(key)!=null){
        			httpConn.setRequestProperty(key, headerMap.get(key));
        		}
        	}
        }

        //��������
        httpConn.connect();

        //������Ӧ����
        InputStream isr = httpConn.getInputStream();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int b;
        while ((b = isr.read()) != -1) {
            bao.write(b);
        }
        isr.close();

        //�ر�http����
        httpConn.disconnect();

        //������Ӧ����,������Ϊutf-8����
        retStr = new String(bao.toByteArray(), "ISO-8859-1");
        retStr = new String(retStr.getBytes("ISO-8859-1"), "UTF-8");

        //retStr=new String(bao.toByteArray(), "UTF-8");
        return retStr;
    }
    
    /**
     * HttpURLConnection put��ʽ��������
     * 
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
	public static String sendConnByPut(String url, String params, Map<String,String> headerMap) throws Exception {
        String retStr = "";
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(512);
        try {
            URL urlObj = new URL(url);
            //��http����
            HttpURLConnection httpConn = (HttpURLConnection)(urlObj.openConnection());
            //����http��������
            httpConn.setConnectTimeout(30000);
            httpConn.setRequestMethod("PUT");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            //��������header
            if(headerMap!=null && !headerMap.isEmpty()){
            	//httpsConnection.setRequestProperty("Content-Type","multipart/form-data");
            	for(String key:headerMap.keySet()){
            		if(StringUtils.isNotBlank(key) && headerMap.get(key)!=null){
            			httpConn.setRequestProperty(key, headerMap.get(key));
            		}
            	}
            }
            OutputStream out = httpConn.getOutputStream();
            if(null != params){
            	out.write(params.getBytes("UTF-8"));
            }
            //��������
            httpConn.connect();

            //������Ӧ����
            InputStream reader = httpConn.getInputStream();
            byte[] bytes = new byte[1024];
            int length = reader.read(bytes);

            do {
                buffer.write(bytes, 0, length);
                length = reader.read(bytes);
            } while (length > 0);
            
            out.close();
            reader.close();
            httpConn.disconnect();

            //������Ӧ����,������Ϊutf-8����
            retStr = new String(buffer.toByteArray(), "ISO-8859-1");
            retStr = new String(retStr.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retStr;
    }
	
	/**
	* �ӷ�����ȡͼƬ
	*http://bbs.3gstdy.com
	* @param url
	* @return
	*/
	public static Bitmap getHttpBitmap(String url) {
	     URL myFileUrl = null;
	     Bitmap bitmap = null;
	     try {
	          myFileUrl = new URL(url);
	     } catch (MalformedURLException e) {
	          e.printStackTrace();
	     }
	     try {
	          HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
	          conn.setConnectTimeout(0);
	          conn.setDoInput(true);
	          conn.connect();
	          InputStream is = conn.getInputStream();
	          bitmap = BitmapFactory.decodeStream(is);
	          is.close();
	     } catch (IOException e) {
	          e.printStackTrace();
	     }
	     return bitmap;
	}
	

}
