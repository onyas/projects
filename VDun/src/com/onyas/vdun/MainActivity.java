package com.onyas.vdun;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int REFRESH_INTERVAL_SEC = 60; //间隔时间（秒）
	private String secretStr = "com.onyas";
	private long timeOffset = 0;//timeOffset是时间偏移量
	
	private TextView tv_otp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv_otp = (TextView) findViewById(R.id.tv_otp);
		
		try {
			Mac mac = Mac.getInstance("HMACSHA1");
			mac.init(new SecretKeySpec(secretStr.getBytes(), ""));
			PasscodeGenerator pcg = new PasscodeGenerator(mac, 6, REFRESH_INTERVAL_SEC);//6表示6位动态密码
			String otpCode = pcg.generateTimeoutCode(true,timeOffset);
			tv_otp.setText(otpCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
