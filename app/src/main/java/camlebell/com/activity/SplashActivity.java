package camlebell.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.internal.Utils;

import org.w3c.dom.Text;

import camlebell.com.Utils.Constants;
import camlebell.com.base.BaseBean;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.ResultInfo;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;
import cn.yoho.library.util.StrUtil;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 引导界面
 */
public class SplashActivity extends Activity {
    private LinearLayout vLoginModelLayout;
    private LinearLayout vLoadingModelLayout;
    private RelativeLayout vLoginBottomlLayout;

    private TextView vSchoolInputText;
    private EditText vUserNameEdit;
    private EditText vPasswordEdit;
    private Button vLoginButton;

    private String mUserName;
    private String mPassword;
    private String appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findView();
        initData();
        setListener();
    }


    private void findView() {
        vLoadingModelLayout = (LinearLayout) findViewById(R.id.loading_model_layout);
        vLoginModelLayout = (LinearLayout) findViewById(R.id.login_model_layout);
        vLoginBottomlLayout = (RelativeLayout) findViewById(R.id.login_model_bottom);

        vSchoolInputText = (TextView) findViewById(R.id.input_school_manager_text);
        vLoginButton = (Button) findViewById(R.id.login_model_login_button);

        vUserNameEdit = (EditText) findViewById(R.id.login_model_user_name_edit);
        vPasswordEdit = (EditText) findViewById(R.id.login_model_user_passwd_edit_edite);


    }
    private void initData() {
        showLoginModel(false);
    }
    private void setListener() {
        MyLoginClickListener loginClickListener = new MyLoginClickListener();
        vSchoolInputText.setOnClickListener(loginClickListener);
        vLoginButton.setOnClickListener(loginClickListener);

    }

    /**
     * 登陆是否显示
     * @param show
     */
    public void showLoginModel(boolean show){
            if(show){
                vLoginModelLayout.setVisibility(View.VISIBLE);
                vLoginButton.setVisibility(View.VISIBLE);
                vLoadingModelLayout.setVisibility(View.GONE);
            }else{
                vLoginModelLayout.setVisibility(View.GONE);
                vLoginButton.setVisibility(View.GONE);
                vLoadingModelLayout.setVisibility(View.VISIBLE);
            }
    }

    /**
     * 请求登陆
     */
    public void loginRequest(final String userName, final String passWord) {
        String json = PackagePostData.signin(userName, passWord);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                BaseBean.class, this) {
            @Override
            public void uiSuccess(BaseBean resp) {
//                LoginBean bean = (LoginBean) resp;
//                PreferencesUtility.setLoginPreferences(SplashActivity.this,
//                        userName, passWord);

//                mMainHandler.sendEmptyMessageDelayed(GOTO_MAIN, 2000);
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }

            @Override
            public void uiFail(BaseBean resp) {
                Toast.makeText(SplashActivity.this, resp.resultNote, Toast.LENGTH_SHORT)
                        .show();
//                mMainHandler.sendEmptyMessageDelayed(GOTO_SELECTLOGIN, 2000);
            }

            @Override
            public void uiStart() {
            }

            @Override
            public void uiFinish() {
            }

        });
    }


    public class MyLoginClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.input_school_manager_text:
                    showLoginModel(true);
                    break;
                case R.id.login_model_login_button:
                    mUserName = vUserNameEdit.getText().toString().trim();
                    mPassword = vPasswordEdit.getText().toString().trim();
                    if(StrUtil.isEmpty(mUserName)){
                        Toast.makeText(SplashActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(StrUtil.isEmpty(mPassword)){
                        Toast.makeText(SplashActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    loginRequest(mUserName, mPassword);
                    break;
                default:
                    break;
            }
        }
    }


}
