package com.fanfq.firstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WelcomeView extends SurfaceView implements SurfaceHolder.Callback{

	private MainActivity mActivity;
	private Paint paint;
	private int currentAlpha=0;//��ǰ�Ĳ�͸��ֵ
	private Bitmap mBitmap;
	private int sleepSpan=50;//������ʱ��ms
	private int currentX;
	private int currentY;
	
	public WelcomeView(MainActivity activity) {
		super(activity);
		this.mActivity = activity;
		this.getHolder().addCallback(this);//�����������ڻص��ӿڵ�ʵ����
		paint = new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����
		mBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), R.drawable.xiaoban_bg);
	}
	
	public void onDraw(Canvas canvas){	
		//���ƺ��������屳��
		paint.setColor(Color.BLACK);//���û�����ɫ
		paint.setAlpha(255);
		canvas.drawRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_WIDTH, paint);
		
		//����ƽ����ͼ
		if(mBitmap==null)return;
		paint.setAlpha(currentAlpha);		
		canvas.drawBitmap(mBitmap, currentX, currentY, paint);	
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(){
			public void run(){
				currentX = Constant.SCREEN_WIDTH/2 - mBitmap.getWidth()/2;
				currentY = Constant.SCREEN_HEIGHT/2 - mBitmap.getHeight()/2;
				
				for(int i=255;i>-10;i=i-10)
				{//��̬����ͼƬ��͸����ֵ�������ػ�	
					currentAlpha=i;
					if(currentAlpha<0)
					{
						currentAlpha=0;
					}
					SurfaceHolder myholder=WelcomeView.this.getHolder();
					Canvas canvas = myholder.lockCanvas();//��ȡ����
					try{
						synchronized(myholder){
							onDraw(canvas);//����
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
					finally{
						if(canvas != null){
							myholder.unlockCanvasAndPost(canvas);
						}
					}
					
					try
					{
						if(i==255)
						{//������ͼƬ����ȴ�һ��
							Thread.sleep(1000);
						}
						Thread.sleep(sleepSpan);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
