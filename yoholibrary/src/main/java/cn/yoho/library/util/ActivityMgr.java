package cn.yoho.library.util;

import android.app.Activity;

import java.util.Stack;

/**
 * activity 管理类
 * 使用方法：
 * 	 在activity的基类中onCreate函数中添加acivity，在onDestory函数中移除activity
 */
public class ActivityMgr {
	
	private Stack<Activity> mActivites = new Stack<Activity>();
	private String tag = "";
	private Stack<Activity> activities;
	private static ActivityMgr __instance;
	
	public static ActivityMgr getInstance(){
		if (__instance == null){
			__instance = new ActivityMgr();
		}
		return __instance;
	}
	
	private ActivityMgr(){}
	
	
	public void putActivity(Activity act){
		if (act != null){
			mActivites.add(act);
		}
	}
	
	
	public void removeActivity(Activity act){
		if (act != null){
			mActivites.remove(act);
			act.finish();
		}
	}
	
	public void clearActivity(){
		for (Activity act : mActivites){
			if (act != null)
				act.finish();
		}
		mActivites.clear();
	}
	
	public Activity getCurrentActivity(){
		return mActivites.lastElement();
	}

	
	public void activityReset(String tag){
		if(!this.tag.equalsIgnoreCase(tag)){
			this.tag = tag;
			activities = new Stack<Activity>();
		}
	}
	
	public void add(Activity activity){
		if(activity != null)
			activities.add(activity);
	}
	
	public void removeAll(){
		for (Activity activity : activities){
			if (activity != null)
				activity.finish();
		}
		activities.clear();
		this.tag = "";
	}
}
