package com.fanfq.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class MyView extends View{

	static final int ANGLE_MAX = 50;
	static final int SPEED = 4;
	static final int SCREEN_WIDTH = 480;
	static final int SCREEN_HEIGHT = 320;
	//
	static final int LEFT =2;
	static final int RIGHT=0;
	static final int UP=3;
	static final int DOWN=1;
	int angle = 1;
	int angleChange = 3;
	int radius = 20;
	int centerX = radius;
	int centerY = radius;
	long timeStamp = System.currentTimeMillis();
	int currPhoto;
	int direction = RIGHT;
	Bitmap bmpMan;
	Bitmap[] bmpPhotos;
	int[] imgIds={
			R.drawable.a,
			R.drawable.b,
			R.drawable.c,
			R.drawable.d
	};
	

	
	
	public MyView(Context context) {
		super(context);
		bmpMan = BitmapFactory.decodeResource(getResources(), R.drawable.man);
		bmpPhotos = new Bitmap[imgIds.length];
		for(int i=0;i<bmpPhotos.length;i++){
			bmpPhotos[i]=BitmapFactory.decodeResource(getResources(), imgIds[i]);
		}
	}
	
	protected void onDraw(Canvas canvas){
		Paint paint = new Paint();//�������ʶ���
		canvas.drawColor(Color.BLACK);//���Ʊ���
		canvas.drawBitmap(bmpMan, centerX-radius, centerY-radius,null);//����Ӣ��
		paint.setColor(Color.BLACK);//���û�����ɫ
		paint.setAntiAlias(true);//���ÿ����
		RectF oval = new RectF(centerX-radius-1,centerY-radius-2,centerX-radius-1+2*radius+2,centerY-radius-2+2*radius+4);//����RECRF����
		canvas.drawArc(oval, 360-angle+90*direction, 2*angle, true, paint);//��������ʵ���������
		if(System.currentTimeMillis()-timeStamp>5000){//���ʱ����5��
			timeStamp = System.currentTimeMillis();//���¼�¼ʱ��
			currPhoto = (currPhoto+1)%bmpPhotos.length;//������Ƭ
		}
		canvas.drawBitmap(bmpPhotos[currPhoto], 80,40, null);//������Ƭ
		super.onDraw(canvas);
	}

}
