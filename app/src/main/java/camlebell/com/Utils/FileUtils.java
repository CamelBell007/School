package camlebell.com.Utils;

import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.utils.L;

import java.io.File;
import java.io.IOException;

/**
 * Created by sunyan on 2015/11/17.
 */
public class FileUtils {
    public static File getOwnCacheDirectory(Context context, String cacheDir) {
        File appCacheDir = null;
        if("mounted".equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = new File(Environment.getExternalStorageDirectory(), cacheDir);
        }

        if(appCacheDir == null || !appCacheDir.exists() && !appCacheDir.mkdirs()) {
            appCacheDir = context.getCacheDir();
        }

        return appCacheDir;
    }

    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if(!appCacheDir.exists()) {
            if(!appCacheDir.mkdirs()) {
                L.w("Unable to create external cache directory", new Object[0]);
                return null;
            }

            try {
                (new File(appCacheDir, ".nomedia")).createNewFile();
            } catch (IOException var4) {
                L.i("Can\'t create \".nomedia\" file in application external cache directory", new Object[0]);
            }
        }

        return appCacheDir;
    }

    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }
}
