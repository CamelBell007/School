package cn.yoho.yohobase.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.io.File;

import cn.yoho.yohobase.R;

/**
 * Created by bruce.lu on 2015/9/7.
 */
public enum HttpService {
    INSTANCE;

    private Context mContext;
    private RequestQueue mRequestQueue;

    /**
     * 默认的参数
     */
    private DisplayImageOptions mOptions;
    private DisplayImageOptions mOptionsNoCache;
    private File mCacheDir;

    /**
     * 初始化HttpService
     * @param context
     * @return
     */
    public HttpService build(Context context, File cacheDir){
        mContext = context;
        mCacheDir = cacheDir;
        mRequestQueue = Volley.newRequestQueue(mContext);
        initUILImageLoader();
        return this;
    }

    /**
     * add request to queue without cache
     *
     * @param request
     */
    public void startRequest(Request<?> request) {
        mRequestQueue.add(request);
    }

    /**
     * @param request
     */
    public void startCacheRequest(Request<?> request) {
        request.setShouldCache(true);
        mRequestQueue.add(request);
    }

    private void initUILImageLoader() {
        mOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mOptionsNoCache = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(300)).bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        com.nostra13.universalimageloader.core.ImageLoaderConfiguration config = new com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder(mContext)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) // 以MD5的方式命名缓存
                .tasksProcessingOrder(QueueProcessingType.LIFO).defaultDisplayImageOptions(mOptions)
                .diskCache(new UnlimitedDiskCache(mCacheDir)) // 不限制的缓存，速度最快
                .imageDownloader(new BaseImageDownloader(mContext)) // 缓存图片的方式
                .build();

        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);
    }

    /**
     * 显示一个图片没有内存缓存
     * @param uri
     * @param imageView
     */
    public void displayImageNoCache(String uri, ImageView imageView) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_stub).showImageOnFail(R.drawable.ic_error).showImageForEmptyUri(R.drawable.ic_empty).cacheInMemory(false)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer()).build();
        displayImage(uri, imageView, options, null, null);
    }

    /**
     * 显示一个图片没有内存缓存
     * @param uri
     * @param imageView
     */
    public void displayImageNoCache(String uri, ImageView imageView, int roundWidth) {
        displayImageNoCache(uri, imageView, roundWidth, 0);
    }

    /**
     * 显示一个图片没有内存缓存
     * @param uri
     * @param imageView
     */
    public void displayImageNoCache(String uri, ImageView imageView, int roundWidth, int defaultId) {
        BitmapDisplayer displayer = null;
        if (roundWidth > 0) {
            displayer = new RoundedBitmapDisplayer(roundWidth / 2);
        } else {
            displayer = new SimpleBitmapDisplayer();
        }
        int defaultResId = defaultId == 0 ? R.drawable.ic_stub : defaultId;
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(defaultResId).showImageOnFail(defaultResId).showImageForEmptyUri(defaultResId).cacheInMemory(false)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).displayer(displayer).build();
        displayImage(uri, imageView, options, null, null);
    }

    /**
     * String imageUri = "http://site.com/image.png"; // from Web
     * String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
     * String imageUri = "content://media/external/audio/albumart/13"; // from content provider String imageUri = "assets://image.png"; // from assets String imageUri =
     * "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
     *
     * @param uri
     * @param imageView
     */
    public void displayImage(final String uri, final ImageView imageView) {
        displayImage(uri, imageView, mOptions, null, null);
    }

    /**
     * String imageUri = "http://site.com/image.png"; // from Web
     * String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
     * String imageUri = "content://media/external/audio/albumart/13"; // from content provider String imageUri = "assets://image.png"; // from assets String imageUri =
     * "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
     *
     * @param uri
     * @param imageView
     * @param fade 是否渐隐渐现
     */
    public void displayImage(final String uri, final ImageView imageView, boolean fade) {
        if (!fade) {
            mOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer()).build();
        }
        displayImage(uri, imageView, mOptions, null, null);
    }

    /**
     * String imageUri = "http://site.com/image.png"; // from Web
     * String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
     * String imageUri = "content://media/external/audio/albumart/13"; // from content provider String imageUri = "assets://image.png"; // from assets String imageUri =
     * "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
     *
     * @param uri
     * @param imageView
     */
    public void displayImage(final String uri, final ImageView imageView, ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        displayImage(uri, imageView, mOptions, listener, progressListener);
    }

    /**
     * 异步显示图片 圆角
     *
     * @param uri
     * @param imageView
     * @param roundWidth
     */
    public void displayImage(String uri, ImageView imageView, int roundWidth) {
        displayImage(uri, imageView, roundWidth, 0);
    }

    /**
     * 异步显示图片，图片可以带圆角与默认图片
     *
     * @param uri 图片uri
     * @param imageView 显示控件
     * @param roundWidth 控件宽度，必须为px
     * @param defaultId 默认图片
     */
    public void displayImage(final String uri, final ImageView imageView, int roundWidth, int defaultId) {
        displayImage(uri, imageView, roundWidth, defaultId, null, null);
    }

    /**
     * 异步显示图片，监听回调方法 通过imageLoadinglistener可以监听当前的进度
     *
     * @param uri 图片uri
     * @param imageView 显示控件
     * @param roundWidth 控件宽度，必须为px
     * @param defaultId 默认图片
     * @param listener 通过imageLoadinglistener可以监听当前的进度
     */
    public void displayImage(final String uri, final ImageView imageView, int roundWidth, int defaultId, ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        DisplayImageOptions options;
        if (roundWidth > 0) {
            BitmapDisplayer diaplayer = new RoundedBitmapDisplayer(roundWidth / 2);
            options = new DisplayImageOptions.Builder().showImageOnLoading(defaultId).showImageOnFail(defaultId).cacheInMemory(true).cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).displayer(diaplayer).build();
        } else {
            options = new DisplayImageOptions.Builder().showImageOnLoading(defaultId).showImageOnFail(defaultId).cacheInMemory(true).cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer()).build();
        }
        displayImage(uri, imageView, options, listener, progressListener);
    }

    /**
     * 异步显示一张图片
     *
     * @param uri
     * @param imageView
     * @param options
     */
    private void displayImage(String uri, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener, ImageLoadingProgressListener progressListener) {
        if (TextUtils.isEmpty(uri)) {
            imageView.setImageResource(R.drawable.ic_empty);
        } else {
            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(uri, imageView, options, listener, progressListener);
        }
    }

    /**
     * 通过URI同步获取图片的bitmap
     *
     * @param uri
     * @return
     */
    public Bitmap loadImageSync(String uri) {
        return com.nostra13.universalimageloader.core.ImageLoader.getInstance().loadImageSync(uri);
    }

    /**
     * 通过URI同步获取图片的bitmap
     *
     * @param uri
     * @return
     */
    public Bitmap loadImageSyncNoCache(String uri) {
        return com.nostra13.universalimageloader.core.ImageLoader.getInstance().loadImageSync(uri, mOptionsNoCache);
    }

    /**
     * 异步获取一张图片
     *
     * @param uri
     * @param listener
     */
    public void loadImageSync(String uri, ImageLoadingListener listener) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().loadImage(uri, listener);
    }

    /**
     * 异步获取一张图片不缓存到memcache中
     * @param uri
     * @param imageView
     * @param listener
     */
    public void loadImageSyncAndBitmapNoCache(String uri, ImageView imageView, ImageLoadingListener listener) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(uri, imageView, mOptionsNoCache, listener);
    }

    /**
     * 异步获取一张图片
     * @param uri
     * @param imageView
     * @param listener
     */
    public void loadImageSyncAndBitmap(String uri, ImageView imageView, ImageLoadingListener listener) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(uri, imageView, mOptions, listener);
    }

    public void loadImageByOptionsSync(String uri, ImageLoadingListener listener) {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().loadImage(uri, mOptions, listener);
    }


    /**
     * 关闭缓存
     */
    public void stop() {
        clearMemoryCache();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().stop();
    }

    /**
     * 清除内存缓存
     */
    public void clearMemoryCache() {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().clearMemoryCache();
    }

    /**
     * 清除硬盘缓存
     */
    public void clearDiskCache() {
        clearMemoryCache();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().clearDiskCache();
    }
}
