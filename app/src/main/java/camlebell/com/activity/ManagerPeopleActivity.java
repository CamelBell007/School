package camlebell.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.AllKindPeopleAdapter;
import camlebell.com.MyApplcation;
import camlebell.com.Utils.Constants;
import camlebell.com.base.BaseBean;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.DeviceListInfo;
import camlebell.com.model.PeopleListInfo;
import camlebell.com.model.PeopleTypeInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.model.WorkInfo;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 人员管理
 */
public class ManagerPeopleActivity extends ToolbarBaseActivity {
    private ListView vAllKindPeoppleList;
    private AllKindPeopleAdapter allKindPeopleAdapter;
    private ArrayList<PeopleListInfo.PeopleInfo> mPeopleInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manager_people;
    }

    @Override
    protected void findViews() {
        vAllKindPeoppleList = findView(R.id.manager_people_list);
    }

    @Override
    protected void iniData() {
        setTitle("人员管理");
//        WorkInfo workOne = new WorkInfo("总负责","王志远","工作中");
//        WorkInfo workTwo = new WorkInfo("厨师组长","刘强","工作中");
//        WorkInfo workThree = new WorkInfo("清洁组长","张敏","工作中");
//        WorkInfo workFour = new WorkInfo("配送组长","高远","工作中");
//        mWorkInfoList = new ArrayList<WorkInfo>();
//        mWorkInfoList.add(workOne);
//        mWorkInfoList.add(workTwo);
//        mWorkInfoList.add(workThree);
//        mWorkInfoList.add(workFour);

        allKindPeopleAdapter = new AllKindPeopleAdapter(ManagerPeopleActivity.this);
        vAllKindPeoppleList.setAdapter(allKindPeopleAdapter);

        getPeopleTypeList(MyApplcation.CURRENT_SCHOOL_ID);
    }

    @Override
    protected void setListener() {
        vAllKindPeoppleList.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PeopleListInfo.PeopleInfo peopleInfo = (PeopleListInfo.PeopleInfo)allKindPeopleAdapter.getItem(position);
                Intent intent = new Intent();
                intent.setClass(ManagerPeopleActivity.this, SingleKindPeopleListActivity.class);
                intent.putExtra("peopleTypeId",peopleInfo.peopleTypeId);
                startActivity(intent);
            }
        });
    }


    /**
     * 人员分类列表
     */

    public void getPeopleTypeList(final String treeGradeId) {
        String json = PackagePostData.peopleTypeList(treeGradeId);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        PeopleListInfo.class, this) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                        PeopleListInfo bean = (PeopleListInfo) resp;
                        mPeopleInfoList = bean.detail.dataList;
                        allKindPeopleAdapter.setDataChange(mPeopleInfoList);
                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(ManagerPeopleActivity.this, resp.resultNote, Toast.LENGTH_SHORT)
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
