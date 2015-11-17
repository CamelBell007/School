package camlebell.com;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import camlebell.com.Utils.Constants;
import cn.yoho.yohobase.net.HttpService;


/**
 * 
 * @author sunyan
 * 
 */
public class MyApplcation extends Application {

	public static int SCREEN_H = 0;

	public static int SCREEN_W = 0;

	public static final String APP_NAME = "wisdomKitchen_SCHOOL";
	/**
	 * PPI
	 */
	public static int SCREEN_DPI = 0;
////	//当前屏幕密度
//	public static double CURRENT_DENSITY = 0;
//	//基准屏幕密度
//	public static double STANDARD_DENSITY = 0;

	/**
	 * USER_AGENT
	 */
	public static String USER_AGENT;
	private final String CHECKSUM_KEY = "yohoidffdiohoy";

	/**
	 * 版本名
	 */
	public static String VERNAME;

	public static boolean isDebug = true;
	/**
	 * 当前离线下载是否被临时设置为取消状态
	 */
	public static boolean IsDownLoad = true;
	/**
	 * 语言
	 */
	public static String LANGUAGE;
	/**
	 * 地区
	 */
	public static String LOCALE;
	/**
	 * 设备ID
	 */
	public static String DEVICEID;

	public static Map<String, Object> mHashMap = new HashMap<String, Object>();

	public static String cacheDir;

	public static boolean isrunning = false;

	private DisplayImageOptions loadingOptions;

	/**
	 * 是否是登录中状态
	 */
	public static boolean isLogining = false;

	/**
	 * 是否点击推送打开
	 */
	public static boolean isNotificationIn = false;

	/**
	 * 首界面中的侧边栏是否打开
	 */
	public static boolean IS_CLOSE_MENU = false;

	public static String LAST_LANGUAGE = null;
	public static String NOW_LANGUAGE = null;

	private static MyApplcation context;

	@Override
	public void onCreate() {
		super.onCreate();
		TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = mTelephonyManager.getDeviceId();
		String androidId = Settings.Secure.getString(getContentResolver(),
				Settings.Secure.ANDROID_ID);
		if (deviceId == null) {
			deviceId = androidId;
		} else if (androidId != null) {
			deviceId = deviceId + androidId;
		}
		DEVICEID = deviceId;


		initUILImageLoader();

		File cacheDir = StorageUtils.getOwnCacheDirectory(
				getApplicationContext(),
				Constants.PROJECT_FILE_NAME + "/"
						+ Constants.CACHE_FILE_NAME);
		HttpService.INSTANCE.build(this,cacheDir);
	}

	/**
	 * 初始化ImageLoader的各项参数
	 */
	private void initUILImageLoader() {
//		File cacheDir = StorageUtils.getOwnCacheDirectory(
//				getApplicationContext(),
//				IYohoBoyConst.IRequestConst.PROJECT_FILE_NAME + "/"
//						+ IYohoBoyConst.IRequestConst.CACHE_FILE_NAME);
//
//		DisplayImageOptions options = new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.img_load)
//		.showImageForEmptyUri(R.drawable.img_load)
//		.showImageOnFail(R.drawable.img_load).cacheInMemory(true)
//		.cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
//		.displayer(new FadeInBitmapDisplayer(300))
//		.bitmapConfig(Bitmap.Config.RGB_565).build();
//
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				this)
//		.threadPriority(Thread.NORM_PRIORITY - 2)
//		.denyCacheImageMultipleSizesInMemory()
//		.diskCacheFileNameGenerator(new Md5FileNameGenerator())
//		// 以MD5的方式命名缓存
//		.tasksProcessingOrder(QueueProcessingType.LIFO)
//		.defaultDisplayImageOptions(options)
//		.diskCache(new UnlimitedDiskCache(cacheDir)) // 不限制的缓存，速度最快
//		.imageDownloader(new BaseImageDownloader(this)) // 缓存图片的方式
//		.build();
//		ImageLoader.getInstance().init(config);
	}

	public static String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);

			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = Settings.Secure.getString(
						context.getContentResolver(),
						Settings.Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
	
	/**
	 * 获取Context上下文对象
	 * 
	 * @return
	 */
	public static Context getContext() {
		return context;
	}
	
	/**
	 * 获取系统版本name
	 * 
	 * @return
	 */
	public static String getAppVersionName() {
		return getAppVersion(context).versionName;
	}

	/**
	 * 获取系统版本packageinfo
	 * 
	 * @param ctx
	 * @return
	 */
	private static PackageInfo getAppVersion(Context ctx) {
		PackageManager packageManager = ctx.getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return packInfo;
	}

    /**
     * 显示进度框
     */
    public static ProgressDialog showProgressDialog(Activity activity){
        ProgressDialog mProgressDialog = new ProgressDialog(activity);
//        mProgressDialog.setMessage(activity.getResources().getString(R.string.data_loading));
        mProgressDialog.setCancelable(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
        return mProgressDialog;
    }


}
