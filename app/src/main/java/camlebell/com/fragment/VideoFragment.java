package camlebell.com.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wmclient.clientsdk.WMDeviceInfo;

import java.util.ArrayList;
import java.util.List;

import camlebell.com.MyApplcation;
import camlebell.com.myapplication.R;
import camlebell.com.wmclient.RealplayActivity;

/**
 * Created by mac on 15/11/5.
 */
public class VideoFragment extends Fragment implements
        View.OnClickListener {
    private List<WMDeviceInfo> deviceList = new ArrayList<WMDeviceInfo>();
    private ExpandableListView mList;
    private int expandedGroup = -1;
    private ListView mListView;
    private ExpandableListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//强制刷新获取新的设备列表，设备列表中包含有设备基本信息以及设备是否在线信息
        if (MyApplcation.getInstance().hasLogin()) {
            if (MyApplcation.getInstance().GetSdkInterface().getDeviceList(deviceList, true) != 0) {
                //log
            }

            mList = new ExpandableListView(getActivity());
            if (null != adapter) {
                mList.setAdapter(adapter);
            } else {
                adapter = new MyExpandableListAdapter(deviceList);
                mList.setAdapter(adapter);
            }
            mList.setCacheColorHint(0x00000000);
            mList.setGroupIndicator(null);
            mList.setBackgroundColor(Color.parseColor("#FFFFFF"));
            return mList;
        } else {
            View view = inflater.inflate(R.layout.message, container, false);
            TextView textView = (TextView) view.findViewById(R.id.message_info);
            textView.setText("对不起,监控视频连接失败,请稍后在试!");
            return view;
        }
//        setContentView(mList);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        mListView = (ListView) view.findViewById(R.id.list_chef);

    }


    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    private class MyExpandableListAdapter extends BaseExpandableListAdapter {

        private List<WMDeviceInfo> deviceList;
        private ArrayList<String> groupList = new ArrayList<String>();
        private ArrayList<ArrayList<String>> childList = new ArrayList<ArrayList<String>>();

        private int selectedGroupPosition = -1;
        private int selectedChildPosition = -1;

        MyExpandableListAdapter(List<WMDeviceInfo> list) {
            deviceList = list;

            for (int i = 0; i < deviceList.size(); i++) {
                groupList.add(deviceList.get(i).getDevName());
                ArrayList<String> itemList = new ArrayList<String>();

                //如果通道名称不为空串，则直接显示通道名，如果为空串，则通道名=“通道”+通道ID
                for (int j = 0; j < deviceList.get(i).getChannelArr().length; j++) {
                    if (deviceList.get(i).getChannelArr()[j].getChannelName().length() <= 0) {
                        itemList.add("通道" + deviceList.get(i).getChannelArr()[j].getChannelId());
                    } else {
                        itemList.add(deviceList.get(i).getChannelArr()[j].getChannelName());
                    }
                }

                childList.add(itemList);
            }
        }

        public Object getChild(int groupPosition, int childPosition) {
            return childList.get(groupPosition).get(childPosition);
        }

        public void setSelectedPosition(int selectedGroupPosition, int selectedChildPosition) {
            this.selectedGroupPosition = selectedGroupPosition;
            this.selectedChildPosition = selectedChildPosition;
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size();
        }

        ////////////////////////////////////childview
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            LinearLayout inside = new LinearLayout(getActivity());
            inside.setBackgroundColor(0xffeef9ff);
            inside.setPadding(0, 10, 0, 10);
            inside.setGravity(Gravity.CENTER_VERTICAL);

            ImageView imgIndicator0 = new ImageView(getActivity());
            TextView textView = new TextView(getActivity());
            textView.setText(getChild(groupPosition, childPosition).toString());
            textView.setTextColor(Color.rgb(44, 127, 167));
            textView.setTextSize(16);
            textView.setPadding(15, 0, 0, 0);

            imgIndicator0.setBackgroundResource(R.mipmap.main_img_cameral);

            if (groupPosition == selectedGroupPosition) {
                if (childPosition == selectedChildPosition) {
                    inside.setBackgroundColor(0xffb6ddee);
                    Intent intent = new Intent(getActivity(), RealplayActivity.class);
                    intent.putExtra("deviceId", deviceList.get(groupPosition).getDevId());
                    intent.putExtra("deviceName", deviceList.get(groupPosition).getDevName());
                    intent.putExtra("deviceType", deviceList.get(groupPosition).getDevType());
                    intent.putExtra("channelId", deviceList.get(groupPosition).getChannelArr()[childPosition].getChannelId());    //channel 娴狅拷瀵拷顫�

                    startActivity(intent);
                } else {
                    //inside.setBackgroundColor(Color.TRANSPARENT);
                }
            }

            inside.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setSelectedPosition(groupPosition, childPosition);
                    notifyDataSetChanged();
                }
            });

            inside.addView(imgIndicator0);
            inside.addView(textView);

            return inside;
        }

        public Object getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        public int getGroupCount() {
            return groupList.size();
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            LinearLayout cotain = new LinearLayout(getActivity());
            cotain.setBackgroundColor(0xffb6ddee);
            cotain.setPadding(0, 10, 0, 10);
            cotain.setGravity(Gravity.CENTER_VERTICAL);

            ImageView imgIndicator = new ImageView(getActivity());
            TextView textView = new TextView(getActivity());
            textView.setTextColor(Color.rgb(44, 127, 167));
            textView.setTextSize(24);
            textView.setText(getGroup(groupPosition).toString());
            textView.setPadding(5, 0, 0, 0);

            if (isExpanded) {
                imgIndicator.setBackgroundResource(R.mipmap.main_img_retract);
            } else {
                imgIndicator.setBackgroundResource(R.mipmap.main_img_spread);
            }

            cotain.addView(imgIndicator);
            cotain.addView(textView);

            return cotain;
        }

        public boolean hasStableIds() {
            return true;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }


    @Override
    public void onClick(View view) {

    }


}
