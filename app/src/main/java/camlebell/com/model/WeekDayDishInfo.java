package camlebell.com.model;

import java.util.ArrayList;

import camlebell.com.base.BaseBean;

/**
 * Created by Administrator on 2015/11/10.
 */
public class WeekDayDishInfo extends BaseBean{
    //这3个没用了
    public int  totalRecordNum;
    public WeekDayDishListMoreInfo detail;

    public class WeekDayDishListMoreInfo{
        public ArrayList<WeekDayDishListInfo> arraylist;
    }
    public class WeekDayDishListInfo{
        public ArrayList<WeekDishInfo> dataList;
        public int totalRecordNum;
        public int result;
    }


}
