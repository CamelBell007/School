package cn.yoho.library.util;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenUtil {

	public static int ScreenWidth = -1;
	public static int ScreenHeight = -1;
	
	/**
	 * 获取手机屏幕密度
	 */
	public static float getDensity(Context context){
		return context.getResources().getDisplayMetrics().density;
	}
	
	/**
	 * 获取设备显示指标
	 * 
	 * @param context 上下文
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		if (context != null) {
			return context.getResources().getDisplayMetrics();
		}
		return null;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * 
	 * @param context
	 *            上下文，一般为Activity
	 * @param dpValue
	 *            dp数据值
	 * @return px像素值
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * 
	 * @param context
	 *            上下文，一般为Activity
	 * @param pxValue
	 *            px像素值
	 * @return dp数据值
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取屏幕大小，单位px
	 * 
	 * @param activity
	 *            Activity
	 * @return 屏幕大小对象
	 */
	public static Point getScreenSize(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		size.set(display.getWidth(), display.getHeight());
		return size;
	}

	public static int getScreenWidth(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		return display.getWidth();
	}

	public static int getScreenHeight(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		return display.getHeight();
	}

	/**
	 * 获取系统状态栏高度
	 * 
	 * @param activity
	 *            Activity
	 * @return 状态栏高度
	 * 
	 */
	public static int getStatusBarHeight(Activity activity) {
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			Field field = clazz.getField("status_bar_height");
			int dpHeight = Integer.parseInt(field.get(object).toString());
			return activity.getResources().getDimensionPixelSize(dpHeight);
		} catch (Exception e1) {
			e1.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 获取底部导航栏高度
	 * @param context
	 * @return
	 */
	public static int getNavigationBarHeight(Context context) {
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}

	public static int getFontSize(Activity activity) {
		return getFontSize(activity, 5);
	}

	public static int getFontSize(Activity activity, int n) {
		int screenWidth = getScreenWidth(activity);
		int rate = (int) (n * (float) screenWidth / 320);
		return rate < n * 3 ? n * 3 : rate;
	}
}
