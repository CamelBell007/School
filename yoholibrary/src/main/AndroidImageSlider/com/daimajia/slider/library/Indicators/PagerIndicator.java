package com.daimajia.slider.library.Indicators;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.slider.library.Tricks.InfinitePagerAdapter;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;

import cn.yoho.library.R;


/**
 * Pager Indicator.
 */
public class PagerIndicator extends LinearLayout implements ViewPagerEx.OnPageChangeListener {

    private Context mContext;
    /**
     * bind this Indicator with {@link com.daimajia.slider.library.Tricks.ViewPagerEx}
     */
    private ViewPagerEx mPager;

    /**
     * Variable to remember the previous selected indicator.
     */
    private ImageView mPreviousSelectedIndicator;

    /**
     * Previous selected indicator position.
     */
    private int mPreviousSelectedPosition;

    /**
     * This value is from {@link com.daimajia.slider.library.SliderAdapter} getRealCount() represent
     * <p/>
     * the indicator count that we should draw.
     */
    private int mItemCount = 0;

    /**
     * Custom selected indicator style resource id.
     */
    private int mUserSetUnSelectedIndicatorResId;
    /**
     * Custom unselected indicator style resource id.
     */
    private int mUserSetSelectedIndicatorResId;

    private float mIndicatorWidth = pxFromDp(10);
    private float mIndicatorHeight = pxFromDp(10);

    private float mIndicatorMargin = pxFromDp(8);

    /**
     * Put all the indicators into a ArrayList, so we can remove them easily.
     */
    private ArrayList<ImageView> mIndicators = new ArrayList<ImageView>();


    public PagerIndicator(Context context) {
        this(context, null);
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PagerIndicator, 0, 0);

        mIndicatorWidth = attributes.getDimension(R.styleable.PagerIndicator_indicator_width, (int)mIndicatorWidth);
        mIndicatorHeight = attributes.getDimensionPixelSize(R.styleable.PagerIndicator_indicator_height, (int) mIndicatorHeight);
        mIndicatorMargin = attributes.getDimensionPixelSize(R.styleable.PagerIndicator_indicator_margin, (int) mIndicatorMargin);
        mUserSetSelectedIndicatorResId = attributes.getResourceId(R.styleable.PagerIndicator_selected_drawable,
                0);
        mUserSetUnSelectedIndicatorResId = attributes.getResourceId(R.styleable.PagerIndicator_unselected_drawable,
                0);
        attributes.recycle();
    }

    private float dpFromPx(float px) {
        return px / this.getContext().getResources().getDisplayMetrics().density;
    }

    private float pxFromDp(float dp) {
        return dp * this.getContext().getResources().getDisplayMetrics().density;
    }

    /**
     * clear self means unregister the dataset observer and remove all the child views(indicators).
     */
    public void destroySelf() {
        if (mPager == null || mPager.getAdapter() == null) {
            return;
        }
        removeAllViews();
    }

    /**
     * bind indicator with viewpagerEx.
     *
     * @param pager
     */
    public void setViewPager(ViewPagerEx pager) {
        if (pager.getAdapter() == null) {
            throw new IllegalStateException("Viewpager does not have adapter instance");
        }
        mPager = pager;
        mPager.addOnPageChangeListener(this);
        init();
    }

    public void setIndicatorSize(int width, int height){
        mIndicatorWidth = width;
        mIndicatorHeight = height;
    }

    public void setIndicatorMargin(int margin){
        mIndicatorMargin = margin;
    }

    /**
     * add indicator
     */
    public void init() {
        mItemCount = getShouldDrawCount();
        mPreviousSelectedIndicator = null;
        removeAllViews();
        for (int i = 0; i < mItemCount; i++) {
            ImageView indicator = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)mIndicatorWidth,(int)mIndicatorHeight);
            if (i == 0)
                params.rightMargin = (int)mIndicatorMargin;
            else if (i == mItemCount - 1)
                params.leftMargin = (int)mIndicatorMargin;
            else{
                params.leftMargin = (int)mIndicatorMargin;
                params.rightMargin = (int)mIndicatorMargin;
            }
            indicator.setImageResource(mUserSetUnSelectedIndicatorResId);
            indicator.setLayoutParams(params);
            addView(indicator);
            mIndicators.add(indicator);
        }
        setItemAsSelected(mPreviousSelectedPosition);
    }

    /**
     * since we used a adapter wrapper, so we can't getCount directly from wrapper.
     *
     * @return
     */
    private int getShouldDrawCount() {
        if (mPager.getAdapter() instanceof InfinitePagerAdapter) {
            return ((InfinitePagerAdapter) mPager.getAdapter()).getRealCount();
        } else {
            return mPager.getAdapter().getCount();
        }
    }

    private void setItemAsSelected(int position) {
        if (mPreviousSelectedIndicator != null) {
            mPreviousSelectedIndicator.setImageResource(mUserSetUnSelectedIndicatorResId);
        }
        if (position >= 0 && position < mItemCount){
            ImageView currentSelected = mIndicators.get(position);
            if (currentSelected != null) {
                currentSelected.setImageResource(mUserSetSelectedIndicatorResId);
                mPreviousSelectedIndicator = currentSelected;
            }
        }
        mPreviousSelectedPosition = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (mItemCount == 0) {
            return;
        }
        setItemAsSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
