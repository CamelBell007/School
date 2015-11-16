package camlebell.com.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import camlebell.com.myapplication.R;

//import cn.yoho.news.R;

public abstract class ToolbarBaseActivity extends AppCompatActivity {

	protected Toolbar vToolbar;
	protected TextView vToolbarTitle;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());

		vToolbar = (Toolbar) findViewById(R.id.toolbar);
		vToolbarTitle = (TextView)findViewById(R.id.home_action_title_text);
		vToolbar.setTitle("");
		setSupportActionBar(vToolbar);
		getSupportActionBar().setDisplayUseLogoEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//		getSupportActionBar().setDisplayShowTitleEnabled(false);

		findViews();
		iniData();
		setListener();
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				clickBackButtonListener();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected abstract int getContentView();

	// 控件初始化
	protected abstract void findViews();

	// Activity初始化
	protected abstract void iniData();

	// Activity初始化
	protected abstract void setListener();

	/**
	 * 初始化控件
	 * @param ViewId viewId
	 * @param <T> sub View type
	 * @return subView
	 */
	protected <T extends View> T findView(int ViewId) {
		return (T)findViewById(ViewId);
	}

	public void clickBackButtonListener(){
		finish();
	}

	public void setTitle(String title){
		vToolbarTitle.setText(title);
	}
	public void setTitle(int title){
		vToolbarTitle.setText(title);
	}

}
