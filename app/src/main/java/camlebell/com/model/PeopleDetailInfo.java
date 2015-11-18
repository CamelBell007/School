package camlebell.com.model;


import java.util.ArrayList;

import camlebell.com.base.BaseBean;

/**
 * Created by Administrator on 2015/11/10.
 * 设备型号列表
 */
public class PeopleDetailInfo extends BaseBean {
    public int  totalRecordNum;
    public PeopleDetailList detail;
    public class PeopleDetailList{
        public ArrayList<PeopleDetail> dataList;
    }
    public class PeopleDetail{
        public String id;
        public String name;//
        public String position;//
        public String address;//
        public String certificatetime;//
    }


}
