package camlebell.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import camlebell.com.myapplication.R;

/**
 * @author sunyan
 * 异常信息处理
 */
public class ExceptionMessageActivity extends Activity {
    private RelativeLayout vAreaSelectorLayout;
    private RelativeLayout vShowOrNotLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception_message);

        vAreaSelectorLayout = (RelativeLayout)findViewById(R.id.area_selector_layout);
        vShowOrNotLayout = (RelativeLayout)findViewById(R.id.show_or_not_layout);
        vAreaSelectorLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(vShowOrNotLayout.getVisibility()==View.GONE){
                    vShowOrNotLayout.setVisibility(View.VISIBLE);
                }else{
                    vShowOrNotLayout.setVisibility(View.GONE);
                }
            }
        });
    }

}
