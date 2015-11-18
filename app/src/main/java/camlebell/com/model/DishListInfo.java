package camlebell.com.model;


import java.util.ArrayList;

import camlebell.com.base.BaseBean;

/**
 * Created by Administrator on 2015/11/10.
 * 菜肴型号列表
 */
public class DishListInfo extends BaseBean {
    public int  totalRecordNum;
    public DishList detail;
    public class DishList{
        public ArrayList<DishInfo> dataList;
    }
    public class DishInfo{
        public String dishTypeId;
        public String dishTypeName;//设备名称


        public String workStatus;//工作状态
        public String opreationPeople;//操作人员
        public String heathStatus;//设备是否正常
        public String teamName;//归属类型
    }


}
