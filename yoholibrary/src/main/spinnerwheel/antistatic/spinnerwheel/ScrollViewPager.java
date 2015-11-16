/*
 *  Android Wheel Control.
 *  https://code.google.com/p/android-wheel/
 *  
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package antistatic.spinnerwheel;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ScrollViewPager extends ViewPager {
	public ScrollViewPager(Context context) {
		super(context);
	}

	public ScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override  
	public boolean onInterceptTouchEvent(MotionEvent p_event)  
	{  
		return true;  
	}  

	@Override  
	public boolean onTouchEvent(MotionEvent p_event)  
	{  
		if (p_event.getAction() == MotionEvent.ACTION_MOVE && getParent() != null)  
		{  
			getParent().requestDisallowInterceptTouchEvent(true);  
		}  

		return super.onTouchEvent(p_event);  
	}  

}
