package camlebell.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.AllKindPeopleAdapter;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.DeviceTypeInfo;
import camlebell.com.model.PeopleTypeInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.model.WorkInfo;
import camlebell.com.myapplication.R;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 人员管理
 */
public class ManagerPeopleActivity extends ToolbarBaseActivity {
    private ListView vAllKindPeoppleList;
    private AllKindPeopleAdapter allKindPeopleAdapter;
    private ArrayList<WorkInfo> mWorkInfoList;
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
        WorkInfo workOne = new WorkInfo("总负责","王志远","工作中");
        WorkInfo workTwo = new WorkInfo("厨师组长","刘强","工作中");
        WorkInfo workThree = new WorkInfo("清洁组长","张敏","工作中");
        WorkInfo workFour = new WorkInfo("配送组长","高远","工作中");
        mWorkInfoList = new ArrayList<WorkInfo>();
        mWorkInfoList.add(workOne);
        mWorkInfoList.add(workTwo);
        mWorkInfoList.add(workThree);
        mWorkInfoList.add(workFour);

        allKindPeopleAdapter = new AllKindPeopleAdapter(ManagerPeopleActivity.this,mWorkInfoList);
        vAllKindPeoppleList.setAdapter(allKindPeopleAdapter);
    }

    @Override
    protected void setListener() {
        vAllKindPeoppleList.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ManagerPeopleActivity.this,SingleKindPeopleListActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * 人员分类列表
     */
    private void getPeopleTypeList(String treeGradeId) {

        HttpManager.getPeopleTypeInfo(treeGradeId, new AbstractResponseListener<ResultInfo<PeopleTypeInfo>>() {
            @Override
            public void onResponseStart() {
            }

            @Override
            public void onResponseSuccess(ResultInfo<PeopleTypeInfo> message) {
                super.onResponseSuccess(message);

            }

            @Override
            public void onResponseFailed(String reason) {
                Toast.makeText(ManagerPeopleActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
