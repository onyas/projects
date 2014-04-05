package com.onyas.vdun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RingView extends View {

	private Paint paint;
	private Context ctx;
	
	public RingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		ctx = context;
		paint = new Paint();
		this.paint.setAntiAlias(true);//没有锯齿
		this.paint.setStyle(Paint.Style.STROKE);//画空心
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		
		float center = getWidth()/2;
		int innerCircle = dip2px(ctx,83); //设置内圆半径
		int ringWidth = dip2px(ctx,5);  //设置圆环宽度
		
		//绘制内圆
		this.paint.setARGB(155, 167, 190, 206);
		this.paint.setStrokeWidth(2);//设置线条宽度
		canvas.drawCircle(center,center, innerCircle, this.paint);//innerCircle：半径

		//绘制圆环
		this.paint.setARGB(255, 212 ,225, 233);
		this.paint.setStrokeWidth(ringWidth);//中间圆环宽度
		canvas.drawCircle(center,center, innerCircle+1+ringWidth/2, this.paint);

		//绘制外圆
		this.paint.setARGB(155, 167, 190, 206);
		this.paint.setStrokeWidth(2);
		canvas.drawCircle(center,center, innerCircle+ringWidth, this.paint);

	}
	
	
	/**
	 * 根据手机的分辨率，从dp的单位转成px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}
