package com.bn.utils;

public class Constant {
	// 屏幕的大小
	public static float SCREEN_WIDTH;
	public static float SCREEN_HEIGHT;
	// 缩放比例
	public static float ratio_width;
	public static float ratio_height;
	// 为仪表板进行自适应的常量数据 0号为480x800 1号为480x854 2号为540x960 3号为320x480
	public static int screenId = 0;
	public static float screenRatio;

	public static final float screenRatio480x320 = 1.5f;// 屏幕宽高比
	public static final float screenRatio800x480 = 1.667f;
	public static final float screenRatio854x480 = 1.779f;
	public static final float screenRatio960x540 = 1.778f;
}
