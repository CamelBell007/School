package camlebell.com.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.PeopleDetailInfoAdapter;
import camlebell.com.MyApplcation;
import camlebell.com.Utils.Constants;
import camlebell.com.base.BaseBean;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.fragment.DrawHomeFragment;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.PeopleDetailInfo;
import camlebell.com.model.PeopleInfo;
import camlebell.com.model.PeopleListInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.model.WorkInfo;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 单一职责人员列表
 */
public class SingleKindPeopleListActivity extends ToolbarBaseActivity implements DrawHomeFragment.OnHomeFragmentInteractionListener {
    public DisplayMetrics dm;
    private PeopleDetailInfoAdapter peopleDetailInfoAdapter;
    private ListView vSinglePeopleList;
    private ArrayList<PeopleDetailInfo.PeopleDetail> mWorkInfoList;

    private String mPeopleTypeId;
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
        mPeopleTypeId = getIntent().getStringExtra("peopleTypeId");
//        WorkInfo workOne = new WorkInfo("厨师长","刘强","工作中","厨师组","正常","7:20","南京市鼓楼区","2018.12.30","324122","13401566259");
//        WorkInfo workTwo = new WorkInfo("厨师","张丽","工作中","厨师组","正常","7:20","南京市鼓楼区","2018.12.30","324123","13401566259");
//        WorkInfo workThree = new WorkInfo("厨师","赵阳","工作中","厨师组","正常","7:20","南京市鼓楼区","2018.12.30","324124","13401566259");
//        WorkInfo workFour = new WorkInfo("厨师","赵东","工作中","厨师组","正常","7:20","南京市鼓楼区","2018.12.30","324125","13401566259");
//        mWorkInfoList = new ArrayList<WorkInfo>();
//        mWorkInfoList.add(workOne);
//        mWorkInfoList.add(workTwo);
//        mWorkInfoList.add(workThree);
//        mWorkInfoList.add(workFour);

        peopleDetailInfoAdapter = new PeopleDetailInfoAdapter(SingleKindPeopleListActivity.this);
        vSinglePeopleList.setAdapter(peopleDetailInfoAdapter);
        getPeopleList(MyApplcation.CURRENT_SCHOOL_ID,mPeopleTypeId);
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


    public void getPeopleList(final String treeGradeId,String peopleTypeId) {
        String json = PackagePostData.peopleList(treeGradeId, peopleTypeId);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        PeopleDetailInfo.class, this) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                        PeopleDetailInfo bean = (PeopleDetailInfo) resp;
                        mWorkInfoList = bean.detail.dataList;
                        peopleDetailInfoAdapter.setDataChange(mWorkInfoList);
                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(SingleKindPeopleListActivity.this, resp.resultNote, Toast.LENGTH_SHORT)
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
