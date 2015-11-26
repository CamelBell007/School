package camlebell.com.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;

import java.util.ArrayList;

import camlebell.com.Adapter.MonitorAdapter;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.fragment.DrawHomeFragment;
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
    private ListView vMonitorListView;
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
//        vMonitorListView = findView(R.id.manager_monitor_list);
    }

    @Override
    protected void iniData() {
        setTitle("视频监控");
        MonitorInfo workOne = new MonitorInfo("12315","视角一","工作中");
        MonitorInfo workTwo = new MonitorInfo("12335","视角二","工作中");
        MonitorInfo workThree = new MonitorInfo("12215","视角三","工作中");
        MonitorInfo workFour = new MonitorInfo("12313","视角四","工作中");
        mMonitorInfoList = new ArrayList<>();
        mMonitorInfoList.add(workOne);
        mMonitorInfoList.add(workTwo);
        mMonitorInfoList.add(workThree);
        mMonitorInfoList.add(workFour);

        monitorAdapter = new MonitorAdapter(ManagerMonitorActivity.this,mMonitorInfoList);
        vMonitorListView.setAdapter(monitorAdapter);
    }

    @Override
    protected void setListener() {

    }


    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }
}
