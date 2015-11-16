package cn.yoho.library.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.HashMap;
import java.util.Map;

import cn.yoho.library.R;


/**
 * 加载的动画,不同的icon依次渐现,可以通过{@link #startLoading()}开始加载动画
 * 加载结束时可通过{@link #stopLoading()}来结束动画
 * 热设置动画资源:即可以通过{@link #setImgResource(int[])}动态设置图片资源,即使原动画资源已经在运行动画
 * 动画有两种模式 AUTO 自动模式  以及 MANUAL 手动模式，缺省使用自动模式, 手动模式下需要调用
 * {@link #setMode(AnimationMode)} 设置,并且回调{@link #setProgress(int)}来更新进度
 * 本控件会根据设置的进度以及总icon的个数来计算合适显示下一个icon.
 * Created by luna.gu on 2015/10/27.
 */
public class NewLoadingView extends View {
    private static int ITEM_SPACING = 30;                //item之间的间距,也用于计算整个动画的居中位置
    private final static int ANIMATION_DURATION = 700;  //动画执行时间
    private final static int MANUAL_DURATION = 4000;     //手动模式下动画执行时间
    private final static int SHOW_INTEVAL = 800;         //延迟出现时间,每个item会根据此值依次显示

    private int[] drawables = {R.drawable.load_sneak, R.drawable.load_vedio,
            R.drawable.load_boy, R.drawable.load_magzine, R.drawable.load_map};
    private Bitmap[] mBitmaps = new Bitmap[drawables.length];
    private ValueAnimator valueAnimator; //属性动画
    private Paint mPaint = new Paint();  //画笔
    private Matrix mMatrix;              //变换的矩阵,设置便宜的位置以及角度
    private int bmpWidth;                //图片高度
    private int bmpHeight;               //图片高度
    private float offsetY;               //Y偏移量,用于矩阵位置转换
    private float offsetX;               //X偏移量,用于矩阵位置转换
    private float mCurrentValue;         //当前动画值范围值:【0,1】
    private float mTotalTime;            //动画执行的总时间
    private int roundNo;                 //执行了几遍动画,可以计算出 mCurrentValue 实际值
    private boolean hasStarted;          //动画是否已经执行

    /**
     * 手动模式下相关参数
     **/
    private int mProgress; //当前进度
    private Map<String, Float> mValueMap = new HashMap<>(); //存储每个icon出现时的差值
    private AnimationMode mMode = AnimationMode.AUTO;       //当前动画的驱动模式,默认是自动

    /**
     * 动画驱动模式
     **/
    public enum AnimationMode {
        AUTO,   //自动
        MANUAL  //手动
    }

    public NewLoadingView(Context context) {
        super(context);
        init();
    }

    public NewLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();
        for (int i = 0; i < drawables.length; i++) {
            Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), drawables[i]);
            mBitmaps[i] = mBitmap;
        }
        bmpWidth = mBitmaps[0].getWidth();
        bmpHeight = mBitmaps[0].getHeight();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(800, 1000);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        computeCanvasSize(true);
        drawBmp(canvas);
    }

    /**
     * 计算画布大小  用于后面设置矩阵的偏移量
     */
    private void computeCanvasSize(boolean needCheck) {
        if (needCheck && offsetX != 0 && offsetY != 0) {  //避免每次都计算
            return;
        }
        int canvasWidth = getMeasuredWidth();
        int canvasHeight = getMeasuredHeight();
        offsetX = (canvasWidth - bmpWidth * mBitmaps.length - ITEM_SPACING * (mBitmaps.length - 1)) / 2; //X方向偏移值
        offsetY = canvasHeight / 2 - bmpHeight / 2;  //计算Y方向偏移值
    }

    /**
     * 绘制图形
     *
     * @param canvas 画布
     */
    private void drawBmp(Canvas canvas) {
        if (mMode == AnimationMode.AUTO) {
            setProperties(canvas);
        } else if (mMode == AnimationMode.MANUAL) {
            setPropertyInManualMode(canvas);
        }
    }

    /**
     * 执行动画
     */
    private void startAnimation() {
        final int duration = mMode == AnimationMode.AUTO ? ANIMATION_DURATION : MANUAL_DURATION;
        valueAnimator = ValueAnimator.ofFloat(0, 1.0f);                //属性动画的起始值设置
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);          //设置轮播模式为无限次,循环播放
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);            //循环播放方式为重新开始，即【0--->1】
        valueAnimator.setDuration(duration);                 //不想解释
        valueAnimator.setInterpolator(new LinearInterpolator());       //差时器设置为线性,即【匀速动画】
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (Float) animation.getAnimatedValue();
                if (currentValue < mCurrentValue) { //进入了下一轮
                    roundNo++; //自动计数
                }
                mCurrentValue = currentValue;
                mTotalTime = duration * roundNo + animation.getCurrentPlayTime();
                invalidate();  //刷新View
            }
        });
        valueAnimator.start();
    }

    /**
     * 设置属性  以及  绘制图形
     *
     * @param canvas 画布
     */
    private void setProperties(Canvas canvas) {
        final int duration = mMode == AnimationMode.AUTO ? ANIMATION_DURATION : MANUAL_DURATION;
        for (int i = 0; i < mBitmaps.length; i++) {
            if (mTotalTime - i * SHOW_INTEVAL > 0) {  //根据每个icon展现的延迟  决定何时展现动画
                float itemCurrentValue = (mCurrentValue + roundNo * 1.0f) //根据延迟以及圈数计算出--
                        - (SHOW_INTEVAL * 1.0f / duration) * i;    //属于每个icon的实际运转时间
                transferMatrix(itemCurrentValue % 1.0f, itemCurrentValue <= 1.0f, i);
                canvas.drawBitmap(mBitmaps[i], mMatrix, mPaint);
            }
        }
    }


    private void setPropertyInManualMode(Canvas canvas) {
        int showNum = mProgress / (100 / drawables.length) + 1;
        if (showNum > drawables.length) { //处理临界值
            showNum = drawables.length;
        }
        float mTotalValue = (mCurrentValue + roundNo * 1.0f);
        storeValueInMap(showNum, mTotalValue);
        for (int i = 0; i < showNum; i++) {
            float itemCurrentValue = (mCurrentValue + roundNo * 1.0f)
                    - getValueFromMap((i + 1));
            transferMatrix(itemCurrentValue % 1.0f, itemCurrentValue <= 1.0f, i);
            canvas.drawBitmap(mBitmaps[i], mMatrix, mPaint);
        }
    }

    private void storeValueInMap(int position, Float value) {
        String key = "postion" + position;
        if (mValueMap.containsKey(key)) {
            return;
        }
        mValueMap.put(key, value);
    }

    private float getValueFromMap(int position) {
        String key = "postion" + position;
        float value = mValueMap.get(key);
        return value;
    }

    /**
     * 转换矩形  设置画笔透明度  以及  矩阵角度、偏移度
     *
     * @param currentValue  当前动画值
     * @param considerAlpha 是否考虑采用透明显示(根据当前的值，避免重复变换透明度)
     * @param index         当前是绘制的第几个图形
     */
    private void transferMatrix(float currentValue, boolean considerAlpha, int index) {
        int alpha = (int) (currentValue * 255);        //换算透明度
        mPaint.setAlpha(considerAlpha ? alpha : 255); //设置透明度

        int width = mBitmaps[index].getWidth();  //图形宽度(用于计算旋转中心点)
        int height = mBitmaps[index].getHeight();//图形高度(用于计算旋转中心点)
        mMatrix.reset();                                                              //矩阵重置
        mMatrix.preTranslate(offsetX + (ITEM_SPACING + width) * index, offsetY);      //设置矩阵偏移度
        float rotate = currentValue / 1.0f * 365;                                      //计算角度
        mMatrix.postRotate(rotate, offsetX + (ITEM_SPACING + width) * index + width / 2, offsetY + height / 2);//设置矩阵角度
    }

    /**
     * 开始加载,通过此方法可以展示动画
     */
    public void startLoading() {
        if (valueAnimator != null && valueAnimator.isRunning()) {
            return;
        }
        if (!hasStarted) {
            resetAnimation();
            startAnimation();
            hasStarted = true;
        }
    }


    /**
     * 暴漏给外面的方法，可以通过此方法暂停显示动画
     */
    public void stopLoading() {
        resetAnimation();
    }

    /**
     * 重置变量以及动画
     * 可以再次开启
     */
    private void resetAnimation() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        mPaint.setAlpha(0);
        mCurrentValue = 0;
        mTotalTime = 0;
        roundNo = 0;
        hasStarted = false;
        mProgress = 0;
        mValueMap.clear();
        invalidate();
    }

    /**
     * 设置每个icon之间的间距
     *
     * @param itemSpacing 间距值
     */
    public void setItemSpacing(int itemSpacing) {
        if (itemSpacing >= 0 && itemSpacing <= bmpWidth * 2) {
            ITEM_SPACING = itemSpacing;
        }
    }

    /**
     * 设置资源
     *
     * @param imgs 图片资源
     */
    public void setImgResource(int[] imgs) {
        if (imgs != null && imgs.length > 0) {
            drawables = imgs;
            mBitmaps = new Bitmap[drawables.length];
        }
        init();
        computeCanvasSize(false);
    }

    /**
     * 设置进度(仅在手动模式下起作用)
     *
     * @param progress 当前进度
     */
    public void setProgress(int progress) {
        if (progress < 0 || progress > 100) {
            return;
        }
        mProgress = progress;
    }

    /**
     * 设置动画的驱动模式
     *
     * @param mode 模式
     */
    public void setMode(AnimationMode mode) {
        if (mode == null || (mode != AnimationMode.AUTO && mode != AnimationMode.MANUAL)) {
            mode = AnimationMode.AUTO;
        }
        this.mMode = mode;
    }

}
