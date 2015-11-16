package camlebell.com.activity;

import android.os.Bundle;

import java.util.ArrayList;

import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.ChannelInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.myapplication.R;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 登录界面
 */
public class LoginActivity extends ToolbarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void iniData() {

    }

    @Override
    protected void setListener() {

    }


}
