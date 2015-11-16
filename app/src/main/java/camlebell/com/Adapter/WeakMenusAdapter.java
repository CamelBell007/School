package camlebell.com.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import camlebell.com.model.DishInfo;
import camlebell.com.myapplication.R;


/**
 * 一周菜单的适配器
 *
 * @author wangchuanjian
 * @version C01 2014-12-10
 * @since PhonePlus
 */
public class WeakMenusAdapter extends BaseAdapter {
    private Context mContext;
    private String[] days;
    private ArrayList<ArrayList<DishInfo>> mMenuInfos;

    public WeakMenusAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public WeakMenusAdapter(Context context, ArrayList<ArrayList<DishInfo>> mMenuInfos) {
        super();
        this.mContext = context;
        this.mMenuInfos = mMenuInfos;
        days = new String[]{"一","二","三","四","五","六","日",};
    }

    @Override
    public int getCount() {
        if (mMenuInfos != null) {
            return mMenuInfos.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mMenuInfos.get(position);
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
            convertView = mInflater.inflate(R.layout.adapter_week_menus_item, null);
            holder.vWeekDay = (TextView) convertView
                    .findViewById(R.id.week_day);
            holder.vBigMenu = (TextView) convertView
                    .findViewById(R.id.big_menu);
            holder.vSmallMenu = (TextView) convertView
                    .findViewById(R.id.small_menu);
            holder.vVegetable = (TextView) convertView
                    .findViewById(R.id.vegetalble);
            holder.vFruit = (TextView) convertView
                    .findViewById(R.id.rice);
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
        ArrayList<DishInfo> dayMenusInfo = mMenuInfos.get(position);
        holder.vWeekDay.setText(days[position]);
        holder.vBigMenu.setText(dayMenusInfo.get(0).menuName);
        holder.vSmallMenu.setText(dayMenusInfo.get(1).menuName);
        holder.vVegetable.setText(dayMenusInfo.get(2).menuName);
        holder.vFruit.setText(dayMenusInfo.get(3).menuName);
    }

    /**
     * Adapter中的控件
     */
    public final class ViewHolder {
        public TextView vWeekDay;//
        public TextView vBigMenu;//
        public TextView vSmallMenu;//
        public TextView vVegetable;//
        public TextView vFruit;//
    }

}
