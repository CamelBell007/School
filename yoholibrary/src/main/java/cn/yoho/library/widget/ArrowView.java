package cn.yoho.library.widget;


import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.yoho.library.R;


/**
 * 下拉控件使用，表示下拉距离
 */
public class ArrowView extends RelativeLayout {

	private static final int MAX_LEVEL = 10000;
	public static final int DIR_UP = 1;
	public static final int DIR_DOWN = 2;
	
	private ImageView vMaskImage;
	private ImageView vArrowImage;
	private ClipDrawable mClipDrawable;
	private int mLevel = 0;
	
	
	public ArrowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ArrowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ArrowView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context){
		View view = LayoutInflater.from(context).inflate(R.layout.arrow_layout, this);
		vMaskImage = (ImageView)view.findViewById(R.id.image_mask);
		vArrowImage = (ImageView)view.findViewById(R.id.image_arrow);
		mClipDrawable = (ClipDrawable) vMaskImage.getDrawable();
		mClipDrawable.setLevel(mLevel);
	}

	public void setArrowDirect(int dir){
		if (dir == DIR_DOWN){
			vMaskImage.setImageResource(R.drawable.arrow_down_clip);
			vArrowImage.setImageResource(R.drawable.prev_k);
		} else {
			vMaskImage.setImageResource(R.drawable.arrow_up_clip);
			vArrowImage.setImageResource(R.drawable.next_k);
		}
		mClipDrawable = (ClipDrawable) vMaskImage.getDrawable();
	}
	
	public void setLevelPercent(float percent){
		if (percent <= 0.4f){
			percent = 0.0f;
		}

		float level = 1.4f - percent;
		if (level <= 0.0f)
			level = 0.0f;
		mLevel = (int)(MAX_LEVEL * level);
		mClipDrawable.setLevel(mLevel);
		invalidate();
	}
	
}
