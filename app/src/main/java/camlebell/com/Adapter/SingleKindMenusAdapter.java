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

import camlebell.com.model.DishDetailInfo;
import camlebell.com.model.DishInfo;
import camlebell.com.model.DishListInfo;
import camlebell.com.myapplication.R;


/**
 * rules适配
 *
 * @author wangchuanjian
 * @version C01 2014-12-10
 * @since PhonePlus
 */
public class SingleKindMenusAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<DishDetailInfo.DishDetail> mContentInfoList;

//    private DisplayImageOptions options;

    //    private ImageLoader imageLoader;
    public SingleKindMenusAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public SingleKindMenusAdapter(Context context, ArrayList<DishDetailInfo.DishDetail> mContentInfoList) {
        super();
        this.mContext = context;
        this.mContentInfoList = mContentInfoList;

    }

    @Override
    public int getCount() {
        if (mContentInfoList != null) {
            return mContentInfoList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mContentInfoList.get(position);
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
            convertView = mInflater.inflate(R.layout.adapter_single_kind_menus_item, null);
            holder.vMenuName = (TextView) convertView
                    .findViewById(R.id.menu_name);
            holder.imgRules = (ImageView) convertView
                    .findViewById(R.id.menu_menu);
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
        holder.vMenuName.setText(mContentInfoList.get(position).dishName);
//        holder.imgRules.setScaleType(ScaleType.CENTER_CROP);
//        holder.imgRules.setVisibility(View.VISIBLE);
    }

    /**
     * Adapter中的控件
     */
    public final class ViewHolder {
        public TextView vMenuName;
        public ImageView imgRules;
    }

    /**
     * 修改数据
     * @param mContentInfoList
     */
    public void setDataChange(ArrayList<DishDetailInfo.DishDetail> mContentInfoList){
        this.mContentInfoList = mContentInfoList;
        notifyDataSetChanged();
    }

}
