package camlebell.com.model;


import java.util.ArrayList;

import camlebell.com.base.BaseBean;

/**
 * Created by Administrator on 2015/11/10.
 * 菜肴型号列表
 */
public class DishDetailInfo extends BaseBean {
    public int  totalRecordNum;
    public DishDetailList detail;
    public class DishDetailList{
        public ArrayList<DishDetail> dataList;
    }
    public class DishDetail{
        public String dishId; //菜肴种类的id
        public String imgFile;//图片
        public String goodId;//食物id
        public String dishName;//食物名称
    }


}
