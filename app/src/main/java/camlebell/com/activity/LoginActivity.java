package camlebell.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import camlebell.com.Utils.Constants;
import camlebell.com.base.BaseBean;
import camlebell.com.model.LoginBean;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;
import camlebell.com.preferences.PreferencesUtility;
import cn.yoho.library.util.StrUtil;

/**
 * @author sunyan
 *         登录界面
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();

        final EditText vUserNameEdit = (EditText) findViewById(R.id.login_model_user_name_edit);
        final EditText vPasswordEdit = (EditText) findViewById(R.id.login_model_user_passwd_edit_edite);
        Button vLoginButton = (Button) findViewById(R.id.login_model_login_button);
        vLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUserName = vUserNameEdit.getText().toString().trim();
                String mPassword = vPasswordEdit.getText().toString().trim();
                if (StrUtil.isEmpty(mUserName)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (StrUtil.isEmpty(mPassword)) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginRequest(mUserName, mPassword);
            }
        });
    }


    /**
     * 请求登陆
     */
    public void loginRequest(final String userName, final String passWord) {
        String json = PackagePostData.signin(userName, passWord);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        LoginBean.class, this) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                        LoginBean bean = (LoginBean) resp;
                        PreferencesUtility.setLoginPreferences(LoginActivity.this,
                                bean, passWord);
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setClass(LoginActivity.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(LoginActivity.this, resp.resultNote, Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void uiStart() {
                    }

                    @Override
                    public void uiFinish() {
                    }

                });
    }



}
