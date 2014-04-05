package com.onyas.vdun;

import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int UPDATE_OTP = 1;
	private static final int UPDATE_ARC = 2;
	private int REFRESH_INTERVAL_SEC = 60; // 间隔时间（秒）
	private int REFERSH_INTERVAL_PROGRESS = 1000;// 更新的频率，每1000毫称刷新一次
	private String secretStr = "com.onyas";
	private long timeOffset = 0;// timeOffset是时间偏移量

	private Timer timer;
	private double phase;// 0---1范围，表示要更新的幅度,0代表开始，1代表更新完毕
	private double phasestep;// 每次更新的幅度，比如，60秒刷新一次otp,1秒刷新一次扇形动画，那么一次otp的更新需要60次，每次更新的幅度为1/60;

	private TextView tv_otp;
	private CountdownIndicator pb_progress;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case UPDATE_OTP:
				updateOTP();
				break;
			case UPDATE_ARC:
				pb_progress.setPhase(1-phase);
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv_otp = (TextView) findViewById(R.id.tv_otp);
		pb_progress = (CountdownIndicator) findViewById(R.id.pb_progress);

		phasestep = REFERSH_INTERVAL_PROGRESS
				/ (double) (REFRESH_INTERVAL_SEC * 1000);
		timer = new Timer();
		timer.schedule(new OTPTask(), 0, REFERSH_INTERVAL_PROGRESS);

		updateOTP();
	}

	/**
	 * 更新OTP
	 */
	private void updateOTP() {
		try {
			Mac mac = Mac.getInstance("HMACSHA1");
			mac.init(new SecretKeySpec(secretStr.getBytes(), ""));
			PasscodeGenerator pcg = new PasscodeGenerator(mac, 6,
					REFRESH_INTERVAL_SEC);// 6表示6位动态密码
			String otpCode = pcg.generateTimeoutCode(true, timeOffset);
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

	private class OTPTask extends TimerTask {
		@Override
		public void run() {
			phase += phasestep;
			if (phase >= 1) {
				phase = 0;
				// 更新OTP
				handler.sendEmptyMessage(UPDATE_OTP);
			}
			// 更新扇形动画
			handler.sendEmptyMessage(UPDATE_ARC);
		}
	}
}
