package com.budou.snow;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

public abstract class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//透明导航栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}

		super.onCreate(savedInstanceState);
		init( );
	}
	
	private void init( ){
		setContentView( );
		findViews( );
		getData( );
		showConent( );
	}
	
	public abstract void setContentView( );
	public abstract void findViews( );
	public abstract void getData( );
	public abstract void showConent( );
}
