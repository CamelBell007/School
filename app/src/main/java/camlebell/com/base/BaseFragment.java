package camlebell.com.base;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import camlebell.com.myapplication.R;

/**
 * @author sunyan
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int subViewId;
    private View subView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        subViewId = getFragmentViewId();
        subView = inflater.inflate(subViewId, container, false);
        initView();
        initData();
        setListener();
        return subView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    /**
     * 初始化控件
     * @param ViewId viewId
     * @param <T> sub View type
     * @return subView
     */
    protected <T extends View> T findView(int ViewId){
        return (T)subView.findViewById(ViewId);
    }

    protected abstract void setListener();
    protected abstract void initData();
    protected abstract void initView();

    /**
     * 获取当前Fragment-xml的id
     * @return
     */
    protected abstract int getFragmentViewId();
}
