package camlebell.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import camlebell.com.Utils.Constants;
import camlebell.com.base.BaseBean;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;

/**
 * @author sunyan
 * 引导界面
 */
public class SelectLoginActivity extends Activity {
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
        //学习管理入口
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

                        Intent intent = new Intent();
                        intent.setClass(SelectLoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        SelectLoginActivity.this.finish();
                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(SelectLoginActivity.this, resp.resultNote, Toast.LENGTH_SHORT)
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
//                    showLoginModel(true);
                    Intent intent = new Intent(SelectLoginActivity.this,LoginActivity.class);
                    startActivity(intent);

                    break;
                case R.id.login_model_login_button:
//                    Intent intent = new Intent(SelectLoginActivity.this,LoginActivity.class);
//                    startActivity(intent);
//                    mUserName = vUserNameEdit.getText().toString().trim();
//                    mPassword = vPasswordEdit.getText().toString().trim();
//                    if(StrUtil.isEmpty(mUserName)){
//                        Toast.makeText(SelectLoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//                    if(StrUtil.isEmpty(mPassword)){
//                        Toast.makeText(SelectLoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    loginRequest(mUserName, mPassword);
                    break;
                default:
                    break;
            }
        }
    }


}
