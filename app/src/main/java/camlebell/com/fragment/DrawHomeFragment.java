package camlebell.com.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import camlebell.com.MyApplcation;
import camlebell.com.Utils.Constants;
import camlebell.com.Utils.TimeUtils;
import camlebell.com.activity.ExceptionMessageActivity;
import camlebell.com.activity.ManagerDeviceActivity;
import camlebell.com.activity.ManagerMenuActivity;
import camlebell.com.activity.ManagerMonitorActivity;
import camlebell.com.activity.ManagerPeopleActivity;
import camlebell.com.base.BaseBean;
import camlebell.com.base.BaseFragment;
import camlebell.com.model.DayDishResultInfo;
import camlebell.com.model.KitchenStatusInfo;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;

/**
 * @author sunyan
 *         侧边栏框架中的主界面
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

    private ImageView vLastDayImage;
    private ImageView vNextDayImage;
    private TextView tvDate;
    private TextView vYearText;
    private TextView vMonthText;

    private TextView vBigMeatText;
    private TextView vSmallMeatText;
    private TextView vVegeText;
    private TextView vFruitText;
    private TextView vSoupText;
    private TextView vRiceText;

    private TextView vDeviceWorkStatusText;
    private TextView vDeviceStatusText;
    private TextView vDevicePeoPleText;
    private TextView vPeoPleWorkStatusText;
    private TextView vPeoPleStatusText;
    private TextView vPeoPlePeoPleText;


    private String mParam1;
    private String mParam2;

    private OnHomeFragmentInteractionListener mListener;

    private MyClickListener myClickListener;

    private Calendar calendar;

    private SimpleDateFormat formater;

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
        getDayMenuRequest(TimeUtils.getNowDate(), MyApplcation.CURRENT_SCHOOL_ID);
        getKitchenStatus(MyApplcation.CURRENT_SCHOOL_ID);
    }

    @Override
    protected void initView() {
        calendar = Calendar.getInstance();
        formater = new SimpleDateFormat("yyyy-MM-dd");
        vManagerPeople = findView(R.id.manager_people_layout);
        vManagerDevice = findView(R.id.manager_device_layout);
        vManagerMeun = findView(R.id.manager_menu_layout);
        vManagerMonitor = findView(R.id.manager_monitor_layout);
        vManagerOrder = findView(R.id.manager_order_layout);

        vLastDayImage = findView(R.id.last_day_image);
        tvDate= findView(R.id.tv_date);
        tvDate.setText(TimeUtils.getNowDate());
        vNextDayImage = findView(R.id.next_day_image);

        vLastDayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    calendar.setTime(formater.parse(tvDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.add(Calendar.DAY_OF_MONTH, -1); // 得到后一月

                String preMonth = formater.format(calendar.getTime());
//                today = preMonth;
                tvDate.setText(preMonth);

                getDayMenuRequest(tvDate.getText().toString(), MyApplcation.CURRENT_SCHOOL_ID);
            }
        });

        vNextDayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    calendar.setTime(formater.parse(tvDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.add(Calendar.DAY_OF_MONTH, 1); // 得到后一月
                String nextMonth = formater.format(calendar.getTime());
                tvDate.setText(nextMonth);
                getDayMenuRequest(tvDate.getText().toString(), MyApplcation.CURRENT_SCHOOL_ID);
            }
        });

        vBigMeatText = findView(R.id.day_menu_big_meat_text);
        vSmallMeatText = findView(R.id.day_menu_small_meat_text);
        vVegeText = findView(R.id.day_menu_vege_meat_text);
        vFruitText = findView(R.id.day_menu_fruit_text);
        vSoupText = findView(R.id.day_menu_soup_text);
        vRiceText = findView(R.id.day_menu_rice_text);

        vDeviceWorkStatusText = findView(R.id.device_work_status_text);
        vDeviceStatusText = findView(R.id.device_status_text);
        vDevicePeoPleText = findView(R.id.device_people_text);
        vPeoPleWorkStatusText = findView(R.id.people_work_status_text);
        vPeoPleStatusText = findView(R.id.people_status_text);
        vPeoPlePeoPleText = findView(R.id.people_people_text);
    }


    /**
     * 人员、设备状态接口
     */

    private void getKitchenStatus(String treeGradeId) {
        String json = PackagePostData.kitchenStatus(treeGradeId);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        KitchenStatusInfo.class, getActivity()) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                        KitchenStatusInfo info = (KitchenStatusInfo)resp;
                        if(info!=null){
                            if("0".equals(info.detail.deviceStatus)){
                                vDeviceStatusText.setText("不正常");
                            }else{
                                vDeviceStatusText.setText("正常");
                            }
                            if("0".equals(info.detail.peopleStatus)){
                                vPeoPleStatusText.setText("不正常");
                            }else{
                                vPeoPleStatusText.setText("正常");
                            }
                        }

                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(getActivity(), resp.resultNote, Toast.LENGTH_SHORT)
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

    /**
     * 每日菜单请求数据
     */
    private void getDayMenuRequest(String day, String treeGradeId) {
        String json = PackagePostData.dayMenu(day, treeGradeId);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        DayDishResultInfo.class, getActivity()) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                        DayDishResultInfo info = (DayDishResultInfo)resp;
                        if(info!=null){
                            ArrayList<DayDishResultInfo.DayDishInfo> dishs = info.detail.dataList;
                            for(int i=0;i<dishs.size();i++){
                                DayDishResultInfo.DayDishInfo dayDishInfo = dishs.get(i);
                                String id = dayDishInfo.dishClassId;
                                if("1".equals(id)){
                                    vBigMeatText.setText(dishs.get(i).good);
                                }
//                                else if("2".equals(id)){
//                                    vBigMeatText.setText(dishs.get(i).good);
//                                }
                                if("2".equals(id)){
                                    //小荤
                                    vSmallMeatText.setText(dishs.get(i).good);
                                }
                                if("3".equals(id)){
                                    //蔬菜
                                    vVegeText.setText(dishs.get(i).good);
                                }
                                if("4".equals(id)){
                                    //汤
                                    vSoupText.setText(dishs.get(i).good);
                                }
                                if("5".equals(id)){
//饭                                 //米饭
                                    vRiceText.setText(dishs.get(i).good);
                                }
                            }
                            //大荤
//                            vBigMeatText.setText(dishs.get(0).good);
                            //小荤
//                            vSmallMeatText.setText(dishs.get(1).good);
//                            vVegeText.setText(dishs.get(2).good);
//                         vFruitText.setText(dishs.get(3).good);


                        }else{
                            Toast.makeText(getActivity(), resp.resultNote, Toast.LENGTH_SHORT)
                                    .show();
                        }

                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(getActivity(), resp.resultNote, Toast.LENGTH_SHORT)
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
