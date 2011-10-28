package com.fanfq.surfaceview;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	private MySurfaceView msv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        msv = new MySurfaceView(MainActivity.this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//������Ļ��û��title��
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//����Ϊȫ����ʾ
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//����Ϊ����
        
        setContentView(msv);
        
    }
}