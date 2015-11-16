package camlebell.com.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import camlebell.com.Adapter.AllKindMenusAdapter;
import camlebell.com.activity.SingleKindBigMenuListActivity;
import camlebell.com.base.BaseFragment;
import camlebell.com.model.DishInfo;
import camlebell.com.myapplication.R;

/**
 * @author sunyan
 * 所有菜肴
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllMenusFragment.OnLeftFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllMenusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllMenusFragment extends BaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private AllKindMenusAdapter allKindMenusAdapter;
    private ListView vMenuKindsListView;
    private ArrayList<DishInfo> mDishInfoList;
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
    public static AllMenusFragment newInstance(String param1, String param2) {
        AllMenusFragment fragment = new AllMenusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AllMenusFragment() {

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
        vMenuKindsListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),SingleKindBigMenuListActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        DishInfo workOne = new DishInfo("12315","大荤","大荤");
        DishInfo workTwo = new DishInfo("12315","小荤","小荤");
        DishInfo workThree = new DishInfo("12315","水果","水果");
        DishInfo workFour = new DishInfo("12315","蔬菜","蔬菜");
        DishInfo workFive = new DishInfo("12315","汤","汤");
        DishInfo workSix = new DishInfo("12315","饭","饭");
        mDishInfoList = new ArrayList<>();
        mDishInfoList.add(workOne);
        mDishInfoList.add(workTwo);
        mDishInfoList.add(workThree);
        mDishInfoList.add(workFour);
        mDishInfoList.add(workFive);
        mDishInfoList.add(workSix);

        allKindMenusAdapter = new AllKindMenusAdapter(getActivity(), mDishInfoList);
        vMenuKindsListView.setAdapter(allKindMenusAdapter);
    }

    @Override
    protected void initView() {
        vMenuKindsListView = findView(R.id.all_menu_listview);
    }

    @Override
    protected int getFragmentViewId() {
        return R.layout.fragment_all_menus;
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
