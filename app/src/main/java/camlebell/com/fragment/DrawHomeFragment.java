package camlebell.com.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import camlebell.com.activity.ExceptionMessageActivity;
import camlebell.com.activity.ManagerDeviceActivity;
import camlebell.com.activity.ManagerMenuActivity;
import camlebell.com.activity.ManagerMonitorActivity;
import camlebell.com.activity.ManagerPeopleActivity;
import camlebell.com.base.BaseFragment;
import camlebell.com.manager.HttpManager;
import camlebell.com.model.KitchenStatusInfo;
import camlebell.com.model.DishInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.myapplication.R;
import cn.yoho.yohobase.net.AbstractResponseListener;

/**
 * @author sunyan
 * 侧边栏框架中的主界面
 */
public class DrawHomeFragment extends BaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private LinearLayout vManagerPeople;
    private LinearLayout vManagerDevice;
    private LinearLayout vManagerMeun;
    private LinearLayout vManagerMonitor;
    private LinearLayout vManagerOrder;


    private String mParam1;
    private String mParam2;

    private OnHomeFragmentInteractionListener mListener;

    private MyClickListener myClickListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawHomeFragment.
     */
    public static DrawHomeFragment newInstance(String param1, String param2) {
        DrawHomeFragment fragment = new DrawHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DrawHomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int getFragmentViewId() {
        return R.layout.fragment_draw_home;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnHomeFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void setListener() {
        vManagerPeople.setOnClickListener(myClickListener);
        vManagerDevice.setOnClickListener(myClickListener);
        vManagerMeun.setOnClickListener(myClickListener);
        vManagerMonitor.setOnClickListener(myClickListener);
        vManagerOrder.setOnClickListener(myClickListener);
    }

    @Override
    protected void initData() {
        myClickListener = new MyClickListener();
    }

    @Override
    protected void initView() {

        vManagerPeople = findView(R.id.manager_people_layout);
        vManagerDevice = findView(R.id.manager_device_layout);
        vManagerMeun = findView(R.id.manager_menu_layout);
        vManagerMonitor = findView(R.id.manager_monitor_layout);
        vManagerOrder = findView(R.id.manager_order_layout);
    }


    /**
     * 人员、设备状态接口
     */
    private void getKitchenStatus(String treeGradeId) {

        HttpManager.getKitchenStatusRequest(treeGradeId, new AbstractResponseListener<ResultInfo<KitchenStatusInfo>>() {

            @Override
            public void onResponseStart() {

            }

            @Override
            public void onResponseSuccess(ResultInfo<KitchenStatusInfo> model) {
                super.onResponseSuccess(model);
                KitchenStatusInfo kitchenStatusInfo = (KitchenStatusInfo) model.getInfo();
            }

            @Override
            public void onResponseFailed(String reason) {

            }
        });
    }

    /**
     * 每日菜单请求数据
     */
    private void getDayMenuRequest(String day,String treeGradeId) {

        HttpManager.dayMenuRequest(day, treeGradeId,  new AbstractResponseListener<ResultInfo<DishInfo>>() {

            @Override
            public void onResponseStart() {

            }

            @Override
            public void onResponseSuccess(ResultInfo<DishInfo> model) {
                super.onResponseSuccess(model);
                ArrayList<DishInfo> dishInfos = (ArrayList<DishInfo>)model.getListInfo();
            }

            @Override
            public void onResponseFailed(String reason) {

            }
        });
    }


    /**
     */
    public interface OnHomeFragmentInteractionListener {
        public void onHomeFragmentInteraction(Uri uri);
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.manager_people_layout:
                    intent.setClass(getActivity(), ManagerPeopleActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case R.id.manager_device_layout:
                    intent.setClass(getActivity(), ManagerDeviceActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case R.id.manager_menu_layout:
                    intent.setClass(getActivity(), ManagerMenuActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case R.id.manager_monitor_layout:
                    intent.setClass(getActivity(), ManagerMonitorActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case R.id.manager_order_layout:
                    intent.setClass(getActivity(), ExceptionMessageActivity.class);
                    getActivity().startActivity(intent);
                    break;

                default:
                    break;
            }
        }
    }
}
