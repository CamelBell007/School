/*
 * Copyright (c) 2012, Gewara Corporation, All Rights Reserved
 */
package cn.yoho.library.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @description 封装Toast工具类
 * @version 1.0
 */
public class AppToast {

	private AppToast() {
	}
	
	/**
	 * @description 
	 * @param text
	 */
	public static void ShowToast(String text) {
		ShowToast(text, Toast.LENGTH_SHORT);
	}
	
	/**
	 * @description 
	 * @param text
	 * @param duration
	 */
	public static void ShowToast(String text, int duration) {
		ShowToast(Static.CONTEXT, text, duration);
	}

	/**
	 * @description
	 * @param context
	 * @param text
	 * @param duration
	 */
	public static void ShowToast(Context context, String text, int duration) {
		Toast.makeText(context, text, duration).show();
	}
	
	/**
	 * @description 
	 * @param resId String资源中ID
	 */
	public static void ShowToast(int resId) {
		ShowToast(resId, Toast.LENGTH_SHORT);
	}
	
	/**
	 * @description 
	 * @param resId 资源String中ID
	 * @param duration
	 */
	public static void ShowToast(int resId, int duration) {
		ShowToast(Static.CONTEXT, resId, duration);
	}

	/**
	 * @description
	 * @param context
	 * @param resId 资源String中ID
	 * @param duration
	 */
	public static void ShowToast(Context context, int resId, int duration) {
		Toast.makeText(context, context.getResources().getString(resId), duration).show();
	}
}
