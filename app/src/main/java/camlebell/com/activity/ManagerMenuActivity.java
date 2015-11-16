package camlebell.com.activity;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import camlebell.com.Adapter.MenuViewPagerAdapter;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.fragment.DrawHomeFragment;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.DeviceTypeInfo;
import camlebell.com.model.DishTypeInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.myapplication.R;
import camlebell.com.widget.PagerSlidingTabStrip;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 菜肴管理
 */
public class ManagerMenuActivity extends ToolbarBaseActivity implements DrawHomeFragment.OnHomeFragmentInteractionListener {
    public ViewPager pager;
    public DisplayMetrics dm;
    public MenuViewPagerAdapter menuViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manager_menu;
    }

    @Override
    protected void findViews() {
        pager = (ViewPager) findViewById(R.id.menu_viewpager);
    }

    @Override
    protected void iniData() {
        setTitle(R.string.mananger_food);
        menuViewPagerAdapter = new MenuViewPagerAdapter(getSupportFragmentManager());
        // 为ViewPager实例添加自定义的Adapter
        pager.setAdapter(menuViewPagerAdapter);

    }

    @Override
    protected void setListener() {

    }


    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }

    /**
     * 设备型号列表
     */
    private void getDishTypeList(String treeGradeId) {

        HttpManager.getDishTypeInfo(treeGradeId, new AbstractResponseListener<ResultInfo<DishTypeInfo>>() {
            @Override
            public void onResponseStart() {
            }

            @Override
            public void onResponseSuccess(ResultInfo<DishTypeInfo> message) {
                super.onResponseSuccess(message);

            }

            @Override
            public void onResponseFailed(String reason) {
                Toast.makeText(ManagerMenuActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
