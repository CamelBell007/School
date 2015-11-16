package camlebell.com.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.AllKindPeopleAdapter;
import camlebell.com.Adapter.MenuViewPagerAdapter;
import camlebell.com.Adapter.PeopleDetailInfoAdapter;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.fragment.DrawHomeFragment;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.DeviceTypeInfo;
import camlebell.com.model.PeopleInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.model.WorkInfo;
import camlebell.com.myapplication.R;
import camlebell.com.widget.PagerSlidingTabStrip;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 单一职责人员列表
 */
public class SingleKindPeopleListActivity extends ToolbarBaseActivity implements DrawHomeFragment.OnHomeFragmentInteractionListener {
    public DisplayMetrics dm;
    private PeopleDetailInfoAdapter peopleDetailInfoAdapter;
    private ListView vSinglePeopleList;
    private ArrayList<WorkInfo> mWorkInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_single_people_list;
    }

    @Override
    protected void findViews() {
        vSinglePeopleList = findView(R.id.single_people_list);
    }

    @Override
    protected void iniData() {
        setTitle("厨师组");
        WorkInfo workOne = new WorkInfo("厨师长","刘强","工作中","厨师组","正常","7:20","南京市鼓楼区","2018.12.30","324122","13401566259");
        WorkInfo workTwo = new WorkInfo("厨师","张丽","工作中","厨师组","正常","7:20","南京市鼓楼区","2018.12.30","324123","13401566259");
        WorkInfo workThree = new WorkInfo("厨师","赵阳","工作中","厨师组","正常","7:20","南京市鼓楼区","2018.12.30","324124","13401566259");
        WorkInfo workFour = new WorkInfo("厨师","赵东","工作中","厨师组","正常","7:20","南京市鼓楼区","2018.12.30","324125","13401566259");
        mWorkInfoList = new ArrayList<WorkInfo>();
        mWorkInfoList.add(workOne);
        mWorkInfoList.add(workTwo);
        mWorkInfoList.add(workThree);
        mWorkInfoList.add(workFour);

        peopleDetailInfoAdapter = new PeopleDetailInfoAdapter(SingleKindPeopleListActivity.this,mWorkInfoList);
        vSinglePeopleList.setAdapter(peopleDetailInfoAdapter);
    }

    @Override
    protected void setListener() {

    }


    @Override
    public void onHomeFragmentInteraction(Uri uri) {

    }

    /**
     * 人员列表
     */
    private void getPeopleList(String treeGradeId,String peopleTypeId) {

        HttpManager.getPeopleListInfo(treeGradeId, peopleTypeId, new AbstractResponseListener<ResultInfo<PeopleInfo>>() {
            @Override
            public void onResponseStart() {
            }

            @Override
            public void onResponseSuccess(ResultInfo<PeopleInfo> message) {
                super.onResponseSuccess(message);

            }

            @Override
            public void onResponseFailed(String reason) {
                Toast.makeText(SingleKindPeopleListActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
