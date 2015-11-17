package camlebell.com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import java.util.ArrayList;

import camlebell.com.model.DeviceDetailInfo;
import camlebell.com.model.DeviceInfo;
import camlebell.com.myapplication.R;


/**
 * rules适配
 *
 * @author wangchuanjian
 * @version C01 2014-12-10
 * @since PhonePlus
 */
public class SingleKindDeviceAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<DeviceDetailInfo.DeviceDetail> mWorkInfoList;

    public SingleKindDeviceAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public SingleKindDeviceAdapter(Context context, ArrayList<DeviceDetailInfo.DeviceDetail> mContentInfoList) {
        super();
        this.mContext = context;
        this.mWorkInfoList = mContentInfoList;

    }

    @Override
    public int getCount() {
        if (mWorkInfoList != null) {
            return mWorkInfoList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mWorkInfoList.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.adapter_single_kind_device_item, null);
            holder.vDeviceName = (TextView) convertView
                    .findViewById(R.id.device_name);
            holder.vDeviceWorkerStatus = (TextView) convertView
                    .findViewById(R.id.device_worker_status);
            holder.vDeviceOpreationPeople = (TextView) convertView
                    .findViewById(R.id.device_opreation_people);
            holder.vDeviceStatus = (TextView) convertView
                    .findViewById(R.id.work_status);
            convertView.setTag(holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        bindView(holder,position);
        return convertView;
    }

    /**
     * 控件填充数据
     *
     * @param holder
     */
    public void bindView(ViewHolder holder,int position) {
        holder.vDeviceName.setText(mWorkInfoList.get(position).deviceName);
//        holder.vDeviceWorkerStatus.setText(mWorkInfoList.get(position).workStatus);
//        holder.vDeviceOpreationPeople.setText(mWorkInfoList.get(position).opreationPeople);
        holder.vDeviceStatus.setText(mWorkInfoList.get(position).status);
    }

    /**
     * Adapter中的控件
     */
    public final class ViewHolder {
        public TextView vDeviceName;//职位
        public TextView vDeviceWorkerStatus;
        public TextView vDeviceOpreationPeople;//职位
        public TextView vDeviceStatus;//职位
    }

    /**
     * data change
     * @param mWorkInfoList
     */
    public void setDataChange(ArrayList<DeviceDetailInfo.DeviceDetail> mWorkInfoList){
        this.mWorkInfoList = mWorkInfoList;
        notifyDataSetChanged();
    }

}
