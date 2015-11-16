package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import cn.yoho.library.R;
import cn.yoho.library.widget.NewLoadingView;


/**
 * Created by Administrator on 2015/11/3.
 */
public class Custom1LoadingLayout extends LoadingLayout{

    private NewLoadingView mLoadingView;
    private final Matrix mHeaderImageMatrix;
    private float mRotationPivotX, mRotationPivotY;
    private final boolean mRotateDrawableWhilePulling;

    public Custom1LoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection,final PullToRefreshBase.AnimationStyle style, TypedArray attrs) {
        super(context, mode, scrollDirection,style,attrs);
        mRotateDrawableWhilePulling = attrs.getBoolean(R.styleable.PullToRefresh_ptrRotateDrawableWhilePulling, false);
        mLoadingView = (NewLoadingView)findViewById(R.id.load_view);
        mHeaderImage.setScaleType(ImageView.ScaleType.MATRIX);
        mHeaderImageMatrix = new Matrix();
        mHeaderImage.setImageMatrix(mHeaderImageMatrix);
        reset();
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.refresh_fl;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {
        if (null != imageDrawable) {
            mRotationPivotX = Math.round(imageDrawable.getIntrinsicWidth() / 2f);
            mRotationPivotY = Math.round(imageDrawable.getIntrinsicHeight() / 2f);
        }
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        float angle;
        if (mRotateDrawableWhilePulling) {
            angle = scaleOfLayout * 90f;
        } else {
            angle = Math.max(0f, Math.min(180f, scaleOfLayout * 360f - 180f));
        }

        mHeaderImageMatrix.setRotate(angle, mRotationPivotX, mRotationPivotY);
        mHeaderImage.setImageMatrix(mHeaderImageMatrix);
    }

    @Override
    protected void pullToRefreshImpl() {
    }

    @Override
    protected void refreshingImpl() {
        mHeaderImage.setVisibility(View.INVISIBLE);
        mLoadingView.setVisibility(VISIBLE);
        mLoadingView.startLoading();
        mHeaderText.setVisibility(View.INVISIBLE);
        mSubHeaderText.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void releaseToRefreshImpl() {
    }

    @Override
    protected void resetImpl() {
        resetImageRotation();
        if (mLoadingView != null)
            mLoadingView.stopLoading();
    }

    private void resetImageRotation() {
        if (null != mHeaderImageMatrix) {
            mHeaderImageMatrix.reset();
            mHeaderImage.setImageMatrix(mHeaderImageMatrix);
        }
        mHeaderImage.setVisibility(View.VISIBLE);
        mHeaderProgress.setVisibility(View.INVISIBLE);
        mLoadingView.setVisibility(INVISIBLE);
        mHeaderText.setVisibility(View.VISIBLE);
        mSubHeaderText.setVisibility(View.INVISIBLE);
    }
}
