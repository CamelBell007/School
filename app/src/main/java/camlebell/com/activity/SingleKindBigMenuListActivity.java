package camlebell.com.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.SingleKindMenusAdapter;
import camlebell.com.Utils.Constants;
import camlebell.com.base.BaseBean;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.fragment.DrawHomeFragment;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.DishDetailInfo;
import camlebell.com.model.DishInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 单一种类菜单详细列表
 */
public class SingleKindBigMenuListActivity extends ToolbarBaseActivity implements DrawHomeFragment.OnHomeFragmentInteractionListener {
    public DisplayMetrics dm;
    private SingleKindMenusAdapter singleKindMenusAdapter;
    private ListView vSingleMenuListView;
    private ArrayList<DishDetailInfo.DishDetail> mDishInfoList;
    private String mDishTypeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_single_big_menus_list;
    }

    @Override
    protected void findViews() {
        vSingleMenuListView = findView(R.id.single_menus_list_layout);
    }

    @Override
    protected void iniData() {
        setTitle("大荤");
        mDishTypeId = getIntent().getStringExtra("dishTypeId");


        singleKindMenusAdapter = new SingleKindMenusAdapter(SingleKindBigMenuListActivity.this);
        vSingleMenuListView.setAdapter(singleKindMenusAdapter);

        getDishTypeList(mDishTypeId);
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
    private void getDishList(String dishTypeId) {

        HttpManager.getDishInfo(dishTypeId, new AbstractResponseListener<ResultInfo<DishInfo>>() {
            @Override
            public void onResponseStart() {
            }

            @Override
            public void onResponseSuccess(ResultInfo<DishInfo> message) {
                super.onResponseSuccess(message);

            }

            @Override
            public void onResponseFailed(String reason) {
                Toast.makeText(SingleKindBigMenuListActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }



    /**
     * 菜肴类型列表
     */
    public void getDishTypeList(final String dishTypeId) {
        String json = PackagePostData.dishList(dishTypeId);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        DishDetailInfo.class, SingleKindBigMenuListActivity.this) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                        DishDetailInfo bean = (DishDetailInfo) resp;
                        mDishInfoList = bean.detail.dataList;
                        singleKindMenusAdapter.setDataChange(mDishInfoList);
                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(SingleKindBigMenuListActivity.this, resp.resultNote, Toast.LENGTH_SHORT)
                                .show();
//                mMainHandler.sendEmptyMessageDelayed(GOTO_SELECTLOGIN, 2000);
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
