package camlebell.com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import camlebell.com.model.DishListInfo;
import camlebell.com.myapplication.R;


/**
 * rules适配
 *
 * @author wangchuanjian
 * @version C01 2014-12-10
 * @since PhonePlus
 */
public class AllKindMenusAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<DishListInfo.DishInfo> mDishInfo;

    public AllKindMenusAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public AllKindMenusAdapter(Context context, ArrayList<DishListInfo.DishInfo> mDishInfo) {
        super();
        this.mContext = context;
        this.mDishInfo = mDishInfo;

    }

    @Override
    public int getCount() {
        if (mDishInfo != null) {
            return mDishInfo.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mDishInfo.get(position);
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
            convertView = mInflater.inflate(R.layout.adapter_all_menu_item, null);
            holder.vMenuName = (TextView) convertView
                    .findViewById(R.id.menu_name);
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
        holder.vMenuName.setText(mDishInfo.get(position).dishTypeName);
    }

    /**
     * Adapter中的控件
     */
    public final class ViewHolder {
        public TextView vMenuName;//职位
    }

    /**
     * 修改数据
     * @param mDishInfo
     */
    public void setDataChange(ArrayList<DishListInfo.DishInfo> mDishInfo){
        this.mDishInfo = mDishInfo;
        notifyDataSetChanged();
    }

}
