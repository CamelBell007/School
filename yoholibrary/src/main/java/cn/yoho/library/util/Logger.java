/*
 * Created by fanchao
 * 
 * Date:2014年9月18日下午3:21:16 
 * 
 * Copyright (c) 2014, Show(R). All rights reserved.
 * 
 */
package cn.yoho.library.util;

import android.util.Log;

/**
 * Function: 日志类 发布关闭在Application中设置Logger#setDebug(true)
 * 
 * Date: 2014年9月18日 下午3:21:16
 * 
 * @author fanchao
 */
public final class Logger {

	private static boolean isDebug = true;

	private Logger() {
	}

	/**
	 * 设置Debug参数
	 * @param isDebug true关闭 false取消
	 */
	public static void setDebug(boolean isDebug) {
		Logger.isDebug = isDebug;
	}

	/**
	 * {@link Log#v(String, String, Throwable))}
	 * @param tag
	 * @param msg
	 * @param tr
	 */
	public static void v(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.v(tag, msg, tr);
        }
    }
 
	/**
	 * {@link Log#d(String, String)}
	 * @param tag
	 * @param msg
	 */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }
 
    /**
     * {@link Log#d(String, String, Throwable))}
     * @param tag
     * @param msg
     * @param tr
     */
    public static void d(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.d(tag, msg, tr);
        }
    }
 
    /**
	 * {@link Log#i(String, String)}
	 * @param tag
	 * @param msg
	 */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }
 
    /**
     * {@link Log#i(String, String, Throwable))}
     * @param tag
     * @param msg
     * @param tr
     */
    public static void i(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.i(tag, msg, tr);
        }
    }
 
    /**
     * {@link Log#w(String, String))}
     * @param tag
     * @param msg
     * @param tr
     */
    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }
 
    /**
     * {@link Log#w(String, String, Throwable))}
     * @param tag
     * @param msg
     * @param tr
     */
    public static void w(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.w(tag, msg, tr);
        }
    }
 
    /**
     * {@link Log#e(String, String))}
     * @param tag
     * @param msg
     * @param tr
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }
 
    /**
     * {@link Log#e(String, String, Throwable))}
     * @param tag
     * @param msg
     * @param tr
     */
    public static void e(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.e(tag, msg, tr);
        }
    }
 
    /**
     * {@link Log#wtf(String, String))}
     * @param tag
     * @param msg
     * @param tr
     */
    public static void wtf(String tag, String msg) {
        if (isDebug) {
            Log.wtf(tag, msg);
        }
    }
 
    /**
     * {@link Log#wtf(String, String, Throwable))}
     * @param tag
     * @param msg
     * @param tr
     */
    public static void wtf(String tag, String msg, Throwable tr) {
        if (isDebug) {
            Log.wtf(tag, msg, tr);
        }
    }
}
