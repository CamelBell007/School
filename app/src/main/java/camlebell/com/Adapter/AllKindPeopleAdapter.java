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

import camlebell.com.model.WorkInfo;
import camlebell.com.myapplication.R;


/**
 * rules适配
 *
 * @author wangchuanjian
 * @version C01 2014-12-10
 * @since PhonePlus
 */
public class AllKindPeopleAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<WorkInfo> mWorkInfoList;

    public AllKindPeopleAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public AllKindPeopleAdapter(Context context, ArrayList<WorkInfo> mWorkInfoList) {
        super();
        this.mContext = context;
        this.mWorkInfoList = mWorkInfoList;

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
            convertView = mInflater.inflate(R.layout.adapter_all_kind_people_item, null);
            holder.vJobName = (TextView) convertView
                    .findViewById(R.id.job_name);
            holder.vWorkerName = (TextView) convertView
                    .findViewById(R.id.worker_name);
            holder.vWorkStatus = (TextView) convertView
                    .findViewById(R.id.work_status);
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
        holder.vJobName.setText(mWorkInfoList.get(position).jobName);
        holder.vWorkerName.setText(mWorkInfoList.get(position).workName);
        holder.vWorkStatus.setText(mWorkInfoList.get(position).workStatus);
    }

    /**
     * Adapter中的控件
     */
    public final class ViewHolder {
        public TextView vJobName;//职位
        public TextView vWorkerName;//职位
        public TextView vWorkStatus;//职位
    }

}
