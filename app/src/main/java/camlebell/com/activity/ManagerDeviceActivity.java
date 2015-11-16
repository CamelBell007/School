package camlebell.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.AllKindDeviceAdapter;
import camlebell.com.Adapter.AllKindPeopleAdapter;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.DeviceInfo;
import camlebell.com.model.DeviceTypeInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.model.WorkInfo;
import camlebell.com.myapplication.R;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 设备管理
 */
public class ManagerDeviceActivity extends ToolbarBaseActivity {
    private ListView vDeviceKindsListView;
    private AllKindDeviceAdapter allKindDeviceAdapter;
    private ArrayList<DeviceInfo> mDeviceInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manager_device;
    }

    @Override
    protected void findViews() {
        vDeviceKindsListView = findView(R.id.manager_device_list);
    }

    @Override
    protected void iniData() {
        setTitle(R.string.mananger_device);
        DeviceInfo workOne = new DeviceInfo("12315","炒菜设备","工作中","炒菜设备","操作人 王志远","正常");
        DeviceInfo workTwo = new DeviceInfo("12335","消毒设备","工作中","消毒设备","操作人 王志远","正常");
        DeviceInfo workThree = new DeviceInfo("12215","冷藏设备","工作中","冷藏设备","操作人 王志远","异常");
        DeviceInfo workFour = new DeviceInfo("12313","温湿度仪","工作中","温湿度仪","操作人 王志远","正常");
        mDeviceInfoList = new ArrayList<>();
        mDeviceInfoList.add(workOne);
        mDeviceInfoList.add(workTwo);
        mDeviceInfoList.add(workThree);
        mDeviceInfoList.add(workFour);

        allKindDeviceAdapter = new AllKindDeviceAdapter(ManagerDeviceActivity.this,mDeviceInfoList);
        vDeviceKindsListView.setAdapter(allKindDeviceAdapter);
    }

    @Override
    protected void setListener() {
        vDeviceKindsListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ManagerDeviceActivity.this,SingleKindDeviceListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 设备型号列表
     */
    private void getDeviceTypeList(String treeGradeId) {

        HttpManager.getDeviceTypeInfo(treeGradeId, new AbstractResponseListener<ResultInfo<DeviceTypeInfo>>() {
            @Override
            public void onResponseStart() {
            }

            @Override
            public void onResponseSuccess(ResultInfo<DeviceTypeInfo> message) {
                super.onResponseSuccess(message);

            }

            @Override
            public void onResponseFailed(String reason) {
                Toast.makeText(ManagerDeviceActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
