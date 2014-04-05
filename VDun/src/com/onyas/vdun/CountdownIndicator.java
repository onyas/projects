package com.onyas.vdun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

public class CountdownIndicator extends View {

	private Paint paint;
	private double phase;// 0---1范围，表示要更新的幅度,0代表开始，1代表更新完毕

	public CountdownIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		RadialGradient rg = new RadialGradient(112, 112, 75, Color.argb(255,
				143, 201, 233), Color.argb(255, 166, 212, 235), TileMode.MIRROR);// 渐变
		this.paint.setShader(rg);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		RectF recf = new RectF(0, 0, getWidth(), getHeight());
		float f1 = (float) (360 * phase);
		float f2 = 270 - f1;
		canvas.drawArc(recf, f2, f1, true, paint);// f2:起始角度，f1:扫描幅度
		super.onDraw(canvas);
	}

	public void setPhase(double phase) {
		if (phase < 0 || phase > 1) {
			throw new IllegalArgumentException();
		}
		this.phase = phase;
		invalidate();//重画，在次执行onDraw()方法
	}

}
