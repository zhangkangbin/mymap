package com.xiaobao.ui;

import com.xiaobao.map.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class myhome extends Activity implements OnClickListener {


	RelativeLayout relayout;
	RelativeLayout relayout2;
	RelativeLayout relayout3;
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.myhome);
		relayout=(RelativeLayout)findViewById(R.id.setting);
		relayout2=(RelativeLayout)findViewById(R.id.helper);
		relayout3=(RelativeLayout)findViewById(R.id.exit);
		relayout.setOnClickListener(this);
		relayout2.setOnClickListener(this);
		relayout3.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setting:

			break;
		case R.id.helper:

			break;
		case R.id.exit:
         Intent about=new Intent(this,about.class);
         startActivity(about);
			break;

		default:
			break;
		}
		
	}



}
