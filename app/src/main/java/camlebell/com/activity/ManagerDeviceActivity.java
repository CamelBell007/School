package camlebell.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.AllKindDeviceAdapter;
import camlebell.com.MyApplcation;
import camlebell.com.Utils.Constants;
import camlebell.com.base.BaseBean;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.model.DeviceListInfo;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;

/**
 * @author sunyan
 * 设备管理
 */
public class ManagerDeviceActivity extends ToolbarBaseActivity {
    private ListView vDeviceKindsListView;
    private AllKindDeviceAdapter allKindDeviceAdapter;
    private ArrayList<DeviceListInfo.DeviceInfo> mDeviceInfoList;
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
        allKindDeviceAdapter = new AllKindDeviceAdapter(ManagerDeviceActivity.this);
        vDeviceKindsListView.setAdapter(allKindDeviceAdapter);
        getDeviceTypeList(MyApplcation.CURRENT_SCHOOL_ID);
    }

    @Override
    protected void setListener() {
        vDeviceKindsListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ManagerDeviceActivity.this, SingleKindDeviceListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 设备型号列表
     */

    public void getDeviceTypeList(final String treeGradeId) {
        String json = PackagePostData.deviceTypeList(treeGradeId);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        DeviceListInfo.class, this) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                DeviceListInfo bean = (DeviceListInfo) resp;
                        mDeviceInfoList = bean.detail.dataList;
                        allKindDeviceAdapter.setDataChange(mDeviceInfoList);
                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(ManagerDeviceActivity.this, resp.resultNote, Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void uiStart() {
                    }

                    @Override
                    public void uiFinish() {
                    }

                });
    }
}
