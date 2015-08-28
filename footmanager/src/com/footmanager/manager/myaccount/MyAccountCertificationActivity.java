package com.footmanager.manager.myaccount;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.footmanager.MyAppInfo;
import com.footmanager.R;
import com.footmanager.util.SysUser;
import com.footmanager.util.ViewUtil;
import com.footmanager.util.photoutil.PhotoUtil;

public class MyAccountCertificationActivity extends Activity {
	//����������
	private View mainView;
    private View mainTitleView;
    private Button leftButton;
    private TextView titleTextView;
    private Button titleRightBtn;
    
    private String role;
    private Button editBtn;
    
    private Button photoBtn;
    private ImageView photoImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_account_certification_activity);
		
		MyAppInfo appInfo = (MyAppInfo)getApplicationContext();
	    SysUser user = appInfo.getUser();
	    role = user.getRole();
		
        findViewById();
        setListener();
        initLayout();
	}
	
	private void findViewById() {
		mainView = findViewById(R.id.mainView);
		mainTitleView = findViewById(R.id.mainTitle);
		leftButton = (Button)findViewById(R.id.title_btn);
		titleTextView = (TextView)findViewById(R.id.title_text);
		titleRightBtn = (Button)findViewById(R.id.title_right_btn);
		
		editBtn = (Button)findViewById(R.id.editBtn);
		photoBtn = (Button)findViewById(R.id.photoBtn);
		photoImage = (ImageView)findViewById(R.id.photo);
		
	}
	private void setListener() {
		//�����ϸ�ҳ��
		leftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		//�ύ��֤
		titleRightBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(ApplyAgentActivity.this,PromotionUseRuleActivity.class));
			}
		});
		//�༭
		editBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(ApplyAgentActivity.this,PromotionUseRuleActivity.class));
			}
		});
		//����
		photoBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PhotoDialog();
				//startActivity(new Intent(ApplyAgentActivity.this,PromotionUseRuleActivity.class));
			}
		});

	}
	
	//��ʼ������
	private void initLayout(){
		//��������Ŀ
		titleTextView.setText("ʵ����֤");
		if("manager".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.manager_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.manager_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_manager);
			//�����Ҳఴť��seletor
			titleRightBtn.setBackgroundResource(R.drawable.opinion_submit_btn_selector);
			
			editBtn.setBackgroundResource(R.drawable.manager_my_persons_agent_btn_selector);
		}else if("technician".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.technician_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.technician_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_technician);
			//�����Ҳఴť��seletor
			titleRightBtn.setBackgroundResource(R.drawable.opinion_submit_btn_selector_technician);
			
			editBtn.setBackgroundResource(R.drawable.technician_my_persons_agent_btn_selector);
		}else if("leader".equals(role)){
			//����ҳ�汳��
			mainView.setBackgroundResource(R.drawable.leader_main_bg);
			//����������
			mainTitleView.setBackgroundResource(R.drawable.leader_main_title_bar_bg);
			//��������ఴť��selector
			leftButton.setBackgroundResource(R.drawable.titlebar_back_btn_selector_leader);
			//�����Ҳఴť��seletor
			titleRightBtn.setBackgroundResource(R.drawable.opinion_submit_btn_selector_leader);
			
			editBtn.setBackgroundResource(R.drawable.leader_my_persons_agent_btn_selector);
		}
		
		String mOldPath = getIntent().getStringExtra("path");
		if(null != mOldPath){
			
			Bitmap mOldBitmap = PhotoUtil.getPhoneAlbum(mOldPath);
			// ��ʾͼƬ
			photoImage.setImageBitmap(mOldBitmap);
		}
	}

	/**
	 * ��Ƭ�Ի���
	 */
	private void PhotoDialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("�ϴ���Ƭ��������");
		builder.setItems(new String[] { "�����ϴ�", "�ϴ��ֻ��е���Ƭ" },
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Intent intent = null;
						switch (which) {
						case 0:
							intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							File dir = new File("/sdcard/XiaoGuanJia/Camera/");
							if (!dir.exists()) {
								dir.mkdirs();
							}
							ViewUtil.mUploadPhotoPath = "/sdcard/XiaoGuanJia/Camera/"
									+ UUID.randomUUID().toString();
							File file = new File(
									ViewUtil.mUploadPhotoPath);
							if (!file.exists()) {
								try {
									file.createNewFile();
								} catch (IOException e) {

								}
							}
							intent.putExtra(MediaStore.EXTRA_OUTPUT,
									Uri.fromFile(file));
							startActivityForResult(
											intent,
											ViewUtil.REQUESTCODE_UPLOADPHOTO_CAMERA);
							break;

						case 1:
							intent = new Intent(Intent.ACTION_PICK, null);
							intent.setDataAndType(
									MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
									"image/*");
							startActivityForResult(
									intent,
									ViewUtil.REQUESTCODE_UPLOADPHOTO_LOCATION);
							break;
						}
					}
				});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			/**
			 * ͨ�������ϴ�ͼƬ
			 */
			case ViewUtil.REQUESTCODE_UPLOADPHOTO_CAMERA:
				if (resultCode == RESULT_OK) {
					if (!Environment.getExternalStorageState().equals(
							Environment.MEDIA_MOUNTED)) {
						Toast.makeText(this, "SD������", Toast.LENGTH_SHORT).show();
						return;
					}
					
					/**
					 * ��ȡ��Ļ��Ⱥ͸߶�
					 */
					DisplayMetrics metric = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(metric);
					int mScreenWidth = metric.widthPixels;
					int mScreenHeight = metric.heightPixels;

					
					Intent intent = new Intent();
					intent.setClass(this, MyAccountCertificationActivity.class);
					String path = PhotoUtil.saveToLocal(PhotoUtil.createBitmap(
							ViewUtil.mUploadPhotoPath, mScreenWidth,
							mScreenHeight));
					intent.putExtra("path", path);
					startActivity(intent);
				} else {
					Toast.makeText(this, "ȡ���ϴ�", Toast.LENGTH_SHORT).show();
				}
			break;
			
			/**
			 * ͨ�������ϴ�ͼƬ
			 */
			case ViewUtil.REQUESTCODE_UPLOADPHOTO_LOCATION:
				Uri uri = null;
				if (data == null) {
					Toast.makeText(this, "ȡ���ϴ�", Toast.LENGTH_SHORT).show();
					return;
				}
				if (resultCode == RESULT_OK) {
					if (!Environment.getExternalStorageState().equals(
							Environment.MEDIA_MOUNTED)) {
						Toast.makeText(this, "SD������", Toast.LENGTH_SHORT).show();
						return;
					}
					uri = data.getData();
					String[] proj = { MediaStore.Images.Media.DATA };
					Cursor cursor = managedQuery(uri, proj, null, null, null);
					if (cursor != null) {
						int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
						if (cursor.getCount() > 0 && cursor.moveToFirst()) {
							String mPhotoPath = cursor.getString(column_index);
							Intent intent = new Intent();
							intent.setClass(this, MyAccountCertificationActivity.class);
							intent.putExtra("path", mPhotoPath);
							intent.putExtra("isSetResult", true);
							startActivityForResult(
									intent,
									ViewUtil.REQUESTCODE_WRITERECORD_CHANGE_PHOTO);
						}
					}
				} else {
					Toast.makeText(this, "��Ƭ��ȡʧ��", Toast.LENGTH_SHORT).show();
				}
			break;
		}
	}

}
