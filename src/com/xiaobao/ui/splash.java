package com.xiaobao.ui;





import com.xiaobao.map.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;


import android.view.Window;
import android.view.WindowManager;



public class splash extends Activity {


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ȡ��������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);


		

		// ��ɴ����ȫ����ʾ // ȡ����״̬��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		new Thread(){

			@Override
			public void run() {
				super.run();
				try {
					sleep(1000);
				
					home();
					finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
	}

	
public void home(){
	Intent i=new Intent(this,MainActivity.class);
	startActivity(i);
}
}
