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
		this.paint.setAntiAlias(true);//û�о��
		this.paint.setStyle(Paint.Style.STROKE);//������
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		
		float center = getWidth()/2;
		int innerCircle = dip2px(ctx,83); //������Բ�뾶
		int ringWidth = dip2px(ctx,5);  //����Բ�����
		
		//������Բ
		this.paint.setARGB(155, 167, 190, 206);
		this.paint.setStrokeWidth(2);//�����������
		canvas.drawCircle(center,center, innerCircle, this.paint);//innerCircle���뾶

		//����Բ��
		this.paint.setARGB(255, 212 ,225, 233);
		this.paint.setStrokeWidth(ringWidth);//�м�Բ�����
		canvas.drawCircle(center,center, innerCircle+1+ringWidth/2, this.paint);

		//������Բ
		this.paint.setARGB(155, 167, 190, 206);
		this.paint.setStrokeWidth(2);
		canvas.drawCircle(center,center, innerCircle+ringWidth, this.paint);

	}
	
	
	/**
	 * �����ֻ��ķֱ��ʣ���dp�ĵ�λת��px(����)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}
