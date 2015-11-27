package camlebell.com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.wmclient.clientsdk.Constants;

import java.util.Timer;
import java.util.TimerTask;

import camlebell.com.MyApplcation;
import camlebell.com.base.BaseBean;
import camlebell.com.model.LoginBean;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;
import camlebell.com.preferences.PreferencesContact;
import camlebell.com.preferences.PreferencesHelper;
import camlebell.com.preferences.PreferencesUtility;


/**
 */

public class SplashActivity extends Activity {
    private MyApplcation clientApp;

    public static final int GOTO_SELECTLOGIN = 0;
    public static final int GOTO_MAIN = 1;
    private String mAccount;
    private String mPassword;

    //login
    private String mIpAddr;
    private int mPort;

    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GOTO_SELECTLOGIN: {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setClass(SplashActivity.this, SelectLoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                break;
                case GOTO_MAIN: {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setClass(getApplication(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                break;
                default:
                    break;
            }

        }
    };


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.ErrorCode_VersionTooLow:
//                    showUpdateDialog();
                    break;

                case Constants.ErrorCode_ResponseTimeout:
                    Toast.makeText(SplashActivity.this, "初始化超时!", Toast.LENGTH_SHORT).show();
                    break;

                case Constants.fail:
                    Toast.makeText(SplashActivity.this, "初始化失败!", Toast.LENGTH_SHORT).show();
                    break;

                case 0x55:
                    int nRet = login(mAccount, mPassword, mIpAddr, mPort);
                    //mProgressDialog.hide();

                    if (nRet == Constants.ErrorCode_VersionTooLow) {
                        mHandler.sendEmptyMessage(Constants.ErrorCode_VersionTooLow);
                    } else if (nRet == Constants.ErrorCode_ResponseTimeout) {
                        mHandler.sendEmptyMessage(Constants.ErrorCode_ResponseTimeout);
                    } else if (nRet != Constants.success) {
                        mHandler.sendEmptyMessage(Constants.fail);
                    } else {
                        //success, so goto device list
//                        Intent intent = new Intent(MyApplication.getInstance(), DeviceActivity.class);
//                        startActivity(intent);
                        clientApp.setHasLogin(true);
                    }

                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.app_start, null);
        setContentView(view);
        String userName = PreferencesHelper
                .getSharedPreferences(SplashActivity.this,
                        PreferencesContact.LOGIN_USERCODE,
                        "");
        String password = PreferencesHelper
                .getSharedPreferences(SplashActivity.this,
                        PreferencesContact.LOGIN_PASSWORD,
                        "");
        if (TextUtils.isEmpty(userName)
                || TextUtils.isEmpty(password)) {
            mMainHandler.sendEmptyMessageDelayed(
                    GOTO_SELECTLOGIN, 2000);
        } else {
            login(userName, password);
        }


        // 初始化视频监控
        MyApplcation myApplication = MyApplcation.getInstance();
        Context appContext = MyApplcation.getInstance();
        mAccount = "cblxx";
        mPassword = "123456";
        //login
        mIpAddr = myApplication.getServerAddress();
        mPort = myApplication.getServerPort();
        Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x55);
            }
        };
        timer.schedule(tast, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void login(final String userName, final String passWord) {
        String json = PackagePostData.signin(userName, passWord);
        BaseAsyncHttp.postUrlEntity(camlebell.com.Utils.Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        LoginBean.class, this) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                        LoginBean bean = (LoginBean) resp;
                        PreferencesUtility.setLoginPreferences(SplashActivity.this,
                                bean, passWord);

                        mMainHandler.sendEmptyMessageDelayed(GOTO_MAIN, 2000);
                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(SplashActivity.this, resp.resultNote, Toast.LENGTH_SHORT)
                                .show();
                        mMainHandler.sendEmptyMessageDelayed(GOTO_SELECTLOGIN, 2000);
                    }

                    @Override
                    public void uiStart() {
                    }

                    @Override
                    public void uiFinish() {
                    }

                }

        );
    }


    private int login(String account, String password, String ipAddr, int port) {
        clientApp = MyApplcation.getInstance();

        int nRet = clientApp.GetSdkInterface().login(account, password, ipAddr, port);
        if (nRet != Constants.success) {
            return nRet;
        }
        //save user info

//        clientApp.storeAccountInfo(account, password);

        return Constants.success;
    }


}
