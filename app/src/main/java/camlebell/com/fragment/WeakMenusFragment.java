package camlebell.com.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import java.util.ArrayList;

import camlebell.com.Adapter.WeakMenusAdapter;
import camlebell.com.base.BaseFragment;
import camlebell.com.model.DishInfo;
import camlebell.com.myapplication.R;

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
    private ArrayList<ArrayList<DishInfo>> mMenuInfoList;

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
        mMenuInfoList = new ArrayList<ArrayList<DishInfo>>();
        ArrayList<DishInfo> one = new ArrayList<>();
        DishInfo one1 = new DishInfo("111","酸汤肥牛","大荤");
        DishInfo one2 = new DishInfo("112","藕片炒肉","小荤");
        DishInfo one3 = new DishInfo("113","炒空心菜","素菜");
        DishInfo one4 = new DishInfo("114","苹果","水果");
        one.add(one1);one.add(one2);one.add(one3);one.add(one4);
        ArrayList<DishInfo> two = new ArrayList<>();
        DishInfo two1 = new DishInfo("121","酸汤肥牛","大荤");
        DishInfo two2 = new DishInfo("122","藕片炒肉","小荤");
        DishInfo two3 = new DishInfo("123","炒空心菜","素菜");
        DishInfo two4 = new DishInfo("124","苹果","水果");
        two.add(two1);two.add(two2);two.add(two3);two.add(two4);
        ArrayList<DishInfo> three = new ArrayList<>();
        DishInfo three1 = new DishInfo("111","酸汤肥牛","大荤");
        DishInfo three2 = new DishInfo("112","藕片炒肉","小荤");
        DishInfo three3 = new DishInfo("113","炒空心菜","素菜");
        DishInfo three4 = new DishInfo("114","苹果","水果");
        three.add(three1);three.add(three2);three.add(three3);three.add(three4);
        ArrayList<DishInfo> four = new ArrayList<>();
        DishInfo four1 = new DishInfo("111","酸汤肥牛","大荤");
        DishInfo four2 = new DishInfo("112","藕片炒肉","小荤");
        DishInfo four3 = new DishInfo("113","炒空心菜","素菜");
        DishInfo four4 = new DishInfo("114","苹果","水果");
        four.add(one1);four.add(one2);four.add(one3);four.add(one4);
        ArrayList<DishInfo> five = new ArrayList<>();
        DishInfo five1 = new DishInfo("111","酸汤肥牛","大荤");
        DishInfo five2 = new DishInfo("112","藕片炒肉","小荤");
        DishInfo five3 = new DishInfo("113","炒空心菜","素菜");
        DishInfo five4 = new DishInfo("114","苹果","水果");
        five.add(five1);five.add(five2);five.add(five3);five.add(five4);
        ArrayList<DishInfo> six = new ArrayList<>();
        DishInfo six1 = new DishInfo("111","酸汤肥牛","大荤");
        DishInfo six2 = new DishInfo("112","藕片炒肉","小荤");
        DishInfo six3 = new DishInfo("113","炒空心菜","素菜");
        DishInfo six4 = new DishInfo("114","苹果","水果");
        six.add(six1);six.add(six2);six.add(six3);six.add(six4);
        ArrayList<DishInfo> seven = new ArrayList<>();
        DishInfo seven1 = new DishInfo("111","酸汤肥牛","大荤");
        DishInfo seven2 = new DishInfo("112","藕片炒肉","小荤");
        DishInfo seven3 = new DishInfo("113","炒空心菜","素菜");
        DishInfo seven4 = new DishInfo("114","苹果","水果");
        seven.add(seven1);seven.add(seven2);seven.add(seven3);seven.add(seven4);

        mMenuInfoList.add(one);
        mMenuInfoList.add(two);
        mMenuInfoList.add(three);
        mMenuInfoList.add(four);
        mMenuInfoList.add(five);
        mMenuInfoList.add(six);
        mMenuInfoList.add(seven);

        weakMenusAdapter = new WeakMenusAdapter(getActivity(),mMenuInfoList);
        vWeakMenuListView.setAdapter(weakMenusAdapter);
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

}
