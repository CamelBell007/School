package camlebell.com.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import camlebell.com.Adapter.WeakMenusAdapter;
import camlebell.com.MyApplcation;
import camlebell.com.Utils.Constants;
import camlebell.com.base.BaseBean;
import camlebell.com.base.BaseFragment;
import camlebell.com.model.WeekDayDishInfo;
import camlebell.com.myapplication.R;
import camlebell.com.net.BaseAsyncHttp;
import camlebell.com.net.HttpResponseHandler;
import camlebell.com.net.PackagePostData;

/**
 * @author sunyan
 * 本周菜单
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeakMenusFragment.OnLeftFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeakMenusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeakMenusFragment extends BaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private WeakMenusAdapter weakMenusAdapter;
    private ListView vWeakMenuListView;
    private ArrayList<WeekDayDishInfo.WeekDayDishListInfo> mMenuInfoList;

    private String mParam1;
    private String mParam2;

    private OnLeftFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DrawLeftFragment.
     */
    public static WeakMenusFragment newInstance(String param1, String param2) {
        WeakMenusFragment fragment = new WeakMenusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public WeakMenusFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onLeftFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnLeftFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
//        mMenuInfoList = new ArrayList<WeekDayDishInfo.WeekDayDishListInfo>();

        weakMenusAdapter = new WeakMenusAdapter(getActivity());
        vWeakMenuListView.setAdapter(weakMenusAdapter);
        getWeekMenuList(MyApplcation.CURRENT_SCHOOL_ID);
    }

    @Override
    protected void initView() {


        vWeakMenuListView = findView(R.id.weak_menu_listview);
    }

    @Override
    protected int getFragmentViewId() {
        return R.layout.fragment_weak_menus;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnLeftFragmentInteractionListener {
        public void onLeftFragmentInteraction(Uri uri);
    }
    /**
     * 获取一周菜单列表
     */
    public void getWeekMenuList(final String treeGradeId) {
        String json = PackagePostData.weekMenus(treeGradeId);

        BaseAsyncHttp.postUrlEntity(Constants.BASE_URL, "",
                json, new HttpResponseHandler(
                        WeekDayDishInfo.class, getActivity()) {
                    @Override
                    public void uiSuccess(BaseBean resp) {
                        WeekDayDishInfo bean = (WeekDayDishInfo) resp;
                        mMenuInfoList = bean.detail.arraylist;
                        weakMenusAdapter.setDataChange(mMenuInfoList);
                    }

                    @Override
                    public void uiFail(BaseBean resp) {
                        Toast.makeText(getActivity(), resp.resultNote, Toast.LENGTH_SHORT)
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
