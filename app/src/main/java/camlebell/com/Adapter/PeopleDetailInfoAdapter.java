package camlebell.com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class PeopleDetailInfoAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<WorkInfo> mWorkInfoList;

    public PeopleDetailInfoAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public PeopleDetailInfoAdapter(Context context, ArrayList<WorkInfo> mWorkInfoList) {
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
            convertView = mInflater.inflate(R.layout.adapter_single_kind_people_item, null);
            holder.vJobName = (TextView) convertView
                    .findViewById(R.id.job_name);
            holder.vWorkerName = (TextView) convertView
                    .findViewById(R.id.worker_name);
            holder.vWorkStartTime = (TextView) convertView
                    .findViewById(R.id.work_start_time);
            holder.vBodyTem = (TextView) convertView
                    .findViewById(R.id.work_body_tempture);
            holder.vShowIcon = (ImageView) convertView
                    .findViewById(R.id.show_icon);
            holder.vSlideLayout = (LinearLayout) convertView
                    .findViewById(R.id.people_detail_slide_layout);

            holder.vWorkId = (TextView) convertView
                    .findViewById(R.id.work_id);
            holder.vWorkTele = (TextView) convertView
                    .findViewById(R.id.worker_tele);
            holder.vWorkAddress = (TextView) convertView
                    .findViewById(R.id.worker_address);
            holder.vWorkCardData = (TextView) convertView
                    .findViewById(R.id.worker_card_data);
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
    public void bindView(final ViewHolder holder,int position) {
        holder.vJobName.setText(mWorkInfoList.get(position).jobName);
        holder.vWorkerName.setText(mWorkInfoList.get(position).workName);
        holder.vWorkStartTime.setText(mWorkInfoList.get(position).earlyWorkTime);
        holder.vBodyTem.setText(mWorkInfoList.get(position).boyTem);

        holder.vWorkId.setText(mWorkInfoList.get(position).workId);
        holder.vWorkTele.setText(mWorkInfoList.get(position).telePhone);
        holder.vWorkAddress.setText(mWorkInfoList.get(position).address);
        holder.vWorkCardData.setText(mWorkInfoList.get(position).cardUsedData);


        holder.vShowIcon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(holder.vSlideLayout.getVisibility() == View.VISIBLE){
                    holder.vSlideLayout.setVisibility(View.GONE);
                }else{
                    holder.vSlideLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * Adapter中的控件
     */
    public final class ViewHolder {
        public TextView vJobName;//职位
        public TextView vWorkerName;//职位
        public TextView vWorkStartTime;//职位
        public TextView vBodyTem;//职位
        public ImageView vShowIcon;//职位

        public LinearLayout vSlideLayout;//隐藏的区域
        public TextView vWorkId;//职位
        public TextView vWorkTele;//职位
        public TextView vWorkAddress;//职位
        public TextView vWorkCardData;//职位

    }

}
