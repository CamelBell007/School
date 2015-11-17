package camlebell.com.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.SingleKindDeviceAdapter;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.fragment.DrawHomeFragment;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.DeviceInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.myapplication.R;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 单一设备列表
 */
public class SingleKindDeviceListActivity extends ToolbarBaseActivity implements DrawHomeFragment.OnHomeFragmentInteractionListener {
    public DisplayMetrics dm;
    private ListView vDeviceSingleListView;
    private SingleKindDeviceAdapter allKindDeviceAdapter;
    private ArrayList<DeviceInfo> mDeviceInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail_device;
    }

    @Override
    protected void findViews() {
        vDeviceSingleListView = findView(R.id.manager_single_device_list);
    }

    @Override
    protected void iniData() {
        setTitle("冷藏设备");
        DeviceInfo workOne = new DeviceInfo("12325","冷藏柜1","工作中","冷藏设备","王志远","正常");
        DeviceInfo workTwo = new DeviceInfo("123345","冷藏柜2","工作中","冷藏设备","张无忌","正常");
        DeviceInfo workThree = new DeviceInfo("12315","冷藏柜3","工作中","冷藏设备","赵敏","异常");
        DeviceInfo workFour = new DeviceInfo("12213","冷藏柜4","工作中","冷藏设备","黎明","正常");
        DeviceInfo workFive = new DeviceInfo("12245","冰箱1","工作中","冷藏设备","赵书敏","异常");
        DeviceInfo workSix = new DeviceInfo("12313","冰箱2","工作中","冷藏设备","黎明哥","正常");
        mDeviceInfoList = new ArrayList<>();
        mDeviceInfoList.add(workOne);
        mDeviceInfoList.add(workTwo);
        mDeviceInfoList.add(workThree);
        mDeviceInfoList.add(workFour);
        mDeviceInfoList.add(workFive);
        mDeviceInfoList.add(workSix);

        allKindDeviceAdapter = new SingleKindDeviceAdapter(SingleKindDeviceListActivity.this,mDeviceInfoList);
        vDeviceSingleListView.setAdapter(allKindDeviceAdapter);
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
    private void getDeviceList(String treeGradeId,String deviceTypeId) {

        HttpManager.getDeviceListInfo(treeGradeId,deviceTypeId, new AbstractResponseListener<ResultInfo<DeviceInfo>>() {
            @Override
            public void onResponseStart() {
            }

            @Override
            public void onResponseSuccess(ResultInfo<DeviceInfo> message) {
                super.onResponseSuccess(message);

            }

            @Override
            public void onResponseFailed(String reason) {
                Toast.makeText(SingleKindDeviceListActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
