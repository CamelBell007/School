package camlebell.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

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

	public static final int GOTO_SELECTLOGIN = 0;
	public static final int GOTO_MAIN = 1;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.app_start, null);
		setContentView(view);
		String userName = PreferencesHelper
				.getSharedPreferences(SplashActivity.this,
						PreferencesContact.LOGIN_USERNAME,
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
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void login(final String userName, final String passWord) {
		String json = PackagePostData.signin(userName, passWord);
		BaseAsyncHttp.postEntity("/login", json, new HttpResponseHandler(
				LoginBean.class,SplashActivity.this) {
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

		});
	}

	@Override
	public void onBackPressed() {

	}





}
