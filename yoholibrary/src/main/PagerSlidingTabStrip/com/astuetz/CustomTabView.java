package com.astuetz;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.yoho.library.R;


/**
 * Created by Bruce.Lu on 2015/10/28.
 */
public class CustomTabView extends RelativeLayout {

    private TextView vTabItemTxt;
    public CustomTabView(Context context) {
        super(context);
        init(context);
    }

    public CustomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        View v = LayoutInflater.from(context).inflate(R.layout.view_custom_tab,this,true);
        vTabItemTxt = (TextView) v.findViewById(R.id.tab_item_txt);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public TextView getTabItemTxt(){
        return vTabItemTxt;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
