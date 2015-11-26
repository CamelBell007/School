package camlebell.com.preferences;

import android.content.Context;


import camlebell.com.model.LoginBean;
import camlebell.com.MyApplcation;
/**
 *
 *
 */
public class PreferencesUtility {

    private static Context getContext() {
        return MyApplcation.getInstance();

    }

    public static void setLoginPreferences(Context context,
                                           LoginBean bean, String passWord) {

        PreferencesHelper.setEditor(context, PreferencesContact.LOGIN_USERNAME,
                bean.detail.name);
        PreferencesHelper.setEditor(context, PreferencesContact.LOGIN_PASSWORD,
                passWord);
        PreferencesHelper.setEditor(context, PreferencesContact.LOGIN_USERCODE,
                bean.detail.userCode);
        PreferencesHelper.setEditor(context,
                PreferencesContact.LOGIN_USERID,
                bean.detail.userId);
        PreferencesHelper.setEditor(context,
                PreferencesContact.LOGIN_CLASSNAME,
                bean.detail.className);
        PreferencesHelper.setEditor(context,
                PreferencesContact.LOGIN_TEACHERNAME,
                bean.detail.teacherName);
        PreferencesHelper.setEditor(context,
                PreferencesContact.LOGIN_TEACHERPHONE,
                bean.detail.teacherPhone);
    }

}
