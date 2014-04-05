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
	private double phase;// 0---1��Χ����ʾҪ���µķ���,0����ʼ��1����������

	public CountdownIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		RadialGradient rg = new RadialGradient(112, 112, 75, Color.argb(255,
				143, 201, 233), Color.argb(255, 166, 212, 235), TileMode.MIRROR);// ����
		this.paint.setShader(rg);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		RectF recf = new RectF(0, 0, getWidth(), getHeight());
		float f1 = (float) (360 * phase);
		float f2 = 270 - f1;
		canvas.drawArc(recf, f2, f1, true, paint);// f2:��ʼ�Ƕȣ�f1:ɨ�����
		super.onDraw(canvas);
	}

	public void setPhase(double phase) {
		if (phase < 0 || phase > 1) {
			throw new IllegalArgumentException();
		}
		this.phase = phase;
		invalidate();//�ػ����ڴ�ִ��onDraw()����
	}

}
