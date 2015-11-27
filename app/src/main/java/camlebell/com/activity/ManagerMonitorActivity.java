package camlebell.com.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;

import java.util.ArrayList;

import camlebell.com.Adapter.MonitorAdapter;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.fragment.DrawHomeFragment;
import camlebell.com.fragment.VideoFragment;
import camlebell.com.model.MonitorInfo;
import camlebell.com.myapplication.R;

/**
 * @author sunyan
 * 监控视屏
 */
public class ManagerMonitorActivity extends ToolbarBaseActivity implements DrawHomeFragment.OnHomeFragmentInteractionListener {
    public DisplayMetrics dm;
    private ArrayList<MonitorInfo> mMonitorInfoList;
    private MonitorAdapter monitorAdapter;
    private Fragment fragment;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manager_monitor;
    }

    @Override
    protected void findViews() {
        fm = getSupportFragmentManager();
//        vMonitorListView = findView(R.id.fragmentContainer);
        fragment = fm.findFragmentById(R.id.fragmentContainer);
    }

    @Override
    protected void iniData() {
        setTitle("视频监控");
        if (fragment == null) {
            fragment = new VideoFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }


    @Override
    protected void setListener() {

    }


    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }
}
