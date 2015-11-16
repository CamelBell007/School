package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import cn.yoho.library.R;
import cn.yoho.library.widget.ArrowView;


/**
 * Created by Administrator on 2015/11/3.
 */
public class Custom2LoadingLayout extends LoadingLayout{

    private ArrowView mArrowView;
    public Custom2LoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, final PullToRefreshBase.AnimationStyle style,TypedArray attrs) {
        super(context, mode, scrollDirection,style,attrs);
        mArrowView = (ArrowView)findViewById(R.id.pull_to_refresh_arrow);
        if (mode == PullToRefreshBase.Mode.PULL_FROM_START){
            mArrowView.setArrowDirect(ArrowView.DIR_DOWN);
        } else if (mode == PullToRefreshBase.Mode.PULL_FROM_END){
            mArrowView.setArrowDirect(ArrowView.DIR_UP);
        }
        reset();
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.refresh_fl;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        mArrowView.setLevelPercent(scaleOfLayout);
    }

    @Override
    protected void pullToRefreshImpl() {
    }

    @Override
    protected void refreshingImpl() {

    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {
        mHeaderProgress.setVisibility(INVISIBLE);
        mHeaderImage.setVisibility(INVISIBLE);
    }

}
