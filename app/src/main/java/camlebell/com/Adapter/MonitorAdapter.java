package camlebell.com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import camlebell.com.model.DeviceInfo;
import camlebell.com.model.MonitorInfo;
import camlebell.com.myapplication.R;


/**
 * rules适配
 *
 * @author wangchuanjian
 * @version C01 2014-12-10
 * @since PhonePlus
 */
public class MonitorAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<MonitorInfo> mMonitorInfos;

    public MonitorAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public MonitorAdapter(Context context, ArrayList<MonitorInfo> mMonitorInfos) {
        super();
        this.mContext = context;
        this.mMonitorInfos = mMonitorInfos;

    }

    @Override
    public int getCount() {
        if (mMonitorInfos != null) {
            return mMonitorInfos.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mMonitorInfos.get(position);
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
            convertView = mInflater.inflate(R.layout.adapter_monitor_item, null);
            holder.vMonitorName = (TextView) convertView
                    .findViewById(R.id.monitor_area_name);
            holder.vMonitorAreaImage = (ImageView) convertView
                    .findViewById(R.id.show_icon);
            holder.vPlayIcon = (ImageView) convertView
                    .findViewById(R.id.play_icon);
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
        holder.vMonitorName.setText(mMonitorInfos.get(position).monitorName);
    }

    /**
     * Adapter中的控件
     */
    public final class ViewHolder {
        public TextView vMonitorName;//职位
        public ImageView vMonitorAreaImage;
        public ImageView vPlayIcon;//职位
    }

}
