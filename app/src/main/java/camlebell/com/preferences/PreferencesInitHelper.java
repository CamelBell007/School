package camlebell.com.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesInitHelper {

	private static SharedPreferences.Editor editor = null;
	private static SharedPreferences sharedInitPreferences = null;

	private PreferencesInitHelper() {

	}

	private static SharedPreferences.Editor getEditorObject(Context paramContext) {
		if (editor == null)
			editor = paramContext.getSharedPreferences("init",
					Activity.MODE_PRIVATE).edit();
		return editor;
	}

	public static int getSharedPreferences(Context paramContext,
			String paramString, int paramInt) {
		return getSharedPreferencesObject(paramContext).getInt(paramString,
				paramInt);
	}

	public static long getSharedPreferences(Context paramContext,
			String paramString, long paramLong) {
		return getSharedPreferencesObject(paramContext).getLong(paramString,
				paramLong);
	}

	public static String getSharedPreferences(Context paramContext,
			String paramString1, String paramString2) {
		return getSharedPreferencesObject(paramContext).getString(paramString1,
				paramString2);
	}

	public static Boolean getSharedPreferences(Context paramContext,
			String paramString, Boolean paramBoolean) {
		return getSharedPreferencesObject(paramContext).getBoolean(paramString,
				paramBoolean);
	}

	private static SharedPreferences getSharedPreferencesObject(
			Context paramContext) {
		if (sharedInitPreferences == null)
			sharedInitPreferences = paramContext.getSharedPreferences("init",
					Activity.MODE_PRIVATE);
		return sharedInitPreferences;
	}

	public static void setEditor(Context paramContext, String paramString1,
			String paramString2) {
		getEditorObject(paramContext).putString(paramString1, paramString2)
				.commit();
	}

	public static void clearData() {
		sharedInitPreferences.edit().clear().commit();
	}
}
