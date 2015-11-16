package camlebell.com.activity;

import android.app.Activity;
import android.os.Bundle;

import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.myapplication.R;

/**
 * @author sunyan
 * 异常信息处理
 */
public class ExceptionMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception_message);
    }

}
