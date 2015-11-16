package cn.yoho.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.yoho.library.R;

/**
 * Created by Administrator on 2015/11/4.
 */
public class LoadMoreView extends RelativeLayout {
    static final int ROTATION_ANIMATION_DURATION = 1000;
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    private ImageView mIvProgress;
    private Animation mRotateAnimation;
    public LoadMoreView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        View v = LayoutInflater.from(context).inflate(R.layout.loadmore_layout,this,true);
        mIvProgress = (ImageView)v.findViewById(R.id.iv_progress);
        mRotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        mRotateAnimation.setDuration(ROTATION_ANIMATION_DURATION);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
    }

    public void startRotate(){
        mIvProgress.startAnimation(mRotateAnimation);
    }
}
