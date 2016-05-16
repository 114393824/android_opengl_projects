package com.bn.st.d2;

import com.bn.utils.Constant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("HandlerLeak")
public class MyActivity extends Activity {

	public Handler hd;
	int flag;

	SensorManager mySensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		Constant.SCREEN_WIDTH = getWindowManager().getDefaultDisplay()
				.getWidth();
		Constant.SCREEN_HEIGHT = getWindowManager().getDefaultDisplay()
				.getHeight();

		float screenHeightTemp = Constant.SCREEN_HEIGHT;
		float screenWidthTemp = Constant.SCREEN_WIDTH;

		if (screenHeightTemp > screenWidthTemp) {
			Constant.SCREEN_WIDTH = screenHeightTemp;
			Constant.SCREEN_HEIGHT = screenWidthTemp;
		}

		Constant.screenRatio = Constant.SCREEN_WIDTH / Constant.SCREEN_HEIGHT;
		if (Math.abs(Constant.screenRatio - Constant.screenRatio854x480) < 0.001f) {
			Constant.screenId = 1;
		} else if (Math.abs(Constant.screenRatio - Constant.screenRatio480x320) < 0.01f) {
			Constant.screenId = 3;
		} else if (Math.abs(Constant.screenRatio - Constant.screenRatio960x540) < 0.001f) {
			Constant.screenId = 2;
		} else {
			Constant.screenId = 0;
		}

		Constant.ratio_height = Constant.SCREEN_HEIGHT / 480;
		Constant.ratio_width = Constant.SCREEN_WIDTH / 854;

		mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		flag = Settings.System.getInt(this.getContentResolver(),
				Settings.System.ACCELEROMETER_ROTATION, 0);
		Settings.System.putInt(this.getContentResolver(),
				Settings.System.ACCELEROMETER_ROTATION, 1);

		this.addHandlerMessage();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mySensorManager.unregisterListener(mySensorListener); // 取消注册监听器
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// ע�������
		mySensorManager.registerListener(mySensorListener,// 监听器对象
				mySensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), // 传感器类型
				// SensorManager.SENSOR_DELAY_UI //传感器事件传递的频度
				SensorManager.SENSOR_DELAY_GAME);
	}

	// 开发实现了SensorEventListener接口的传感器监听器
	private SensorEventListener mySensorListener = new SensorEventListener() {
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// 若使用传感器标志位为true,并且场景可触控标志位为true,可使用传感器。
			if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {// 判断是否为加速度传感器变化产生的数据
																	// 计算出重力在屏幕上的投影方向
																	// int[]
																	// directionDotXY
																	// =
																	// RotateUtil.getDirectionDot(event.values);
				// if (directionDotXY[1] < -20) {// right
				// MyGLSurfaceView.keyState = MyGLSurfaceView.keyState | 0x4;
				// } else if (directionDotXY[1] > 20) {// left
				// MyGLSurfaceView.keyState = MyGLSurfaceView.keyState | 0x8;
				// } else {// no left and no right
				// MyGLSurfaceView.keyState = MyGLSurfaceView.keyState & 0x3;
				// }
			}
		}
	};

	private void addHandlerMessage() {
		hd = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				// super.handleMessage(msg);
				switch (msg.what) {
				case 0:// 去欢迎界面
					gotoWelcomeView();
					break;
				case 1:// 去主菜单界面
					gotoMainMenuView();
					break;
				case 2:// 去模式选择界面
					gotoGameModeView();
					break;
				case 3:// 去选艇界面
					gotoXCView();
					break;
				case 4:// 去音效设置界面
					gotoMusicSetView();
					break;
				case 5:// 去游戏帮助界面
					gotoHelpView();
					break;
				case 6:// 去关于界面
					gotoAboutView();
					break;
				case 7:// 主界面退出游戏按钮
					Settings.System.putInt(
							MyActivity.this.getContentResolver(),
							Settings.System.ACCELEROMETER_ROTATION, flag);
					System.exit(0);
					break;
				case 8:// 去游戏界面-计时模式
					break;
				case 9:// 去游戏界面-竞速模式
					break;
				case 10:// 去游戏记录查询界面
					break;
				case 11:// 显示是否破纪录对话框
					break;
				case 12:// 显示当前名次对话框
					break;
				}
			}

		};

		hd.sendEmptyMessage(0);
	}

	private void gotoWelcomeView() {

	}

	private void gotoMainMenuView() {

	}

	private void gotoGameModeView() {

	}

	private void gotoXCView() {

	}

	private void gotoMusicSetView() {

	}

	private void gotoHelpView() {

	}

	private void gotoAboutView() {

	}
}
