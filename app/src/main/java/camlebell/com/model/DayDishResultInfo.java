package camlebell.com.model;

import org.json.JSONObject;

import java.util.ArrayList;

import camlebell.com.base.BaseBean;

/**
 * Created by Administrator on 2015/11/10.
 */
public class DayDishResultInfo extends BaseBean {

    public int totalRecordNum;
    public DayDishList detail;

    public class DayDishList{
        public ArrayList<DayDishInfo> dataList;
    }

    public class DayDishInfo {
        public String dishId;
        public String imgFile;//设备名称
        public String dishClassId;//归属类型
        public String dishClassName;//4
        public String goodId;//"4.jpg",
        public String good;
    }


//    public DayDishResultInfo(JSONObject jo) {
//        if (jo == null) {
//            return;
//        }
//        this.dishId = jo.optString("dishId");
//        this.imgFile = jo.optString("imgFile");
//        this.dishName = jo.optString("dishName");
//		this.goodId = jo.optString("goodId");
//
//    }
}
