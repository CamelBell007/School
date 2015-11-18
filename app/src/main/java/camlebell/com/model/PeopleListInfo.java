package camlebell.com.model;


import java.util.ArrayList;

import camlebell.com.base.BaseBean;

/**
 * Created by Administrator on 2015/11/10.
 * 设备型号列表
 */
public class PeopleListInfo extends BaseBean {
    public int  totalRecordNum;
    public PeopleList detail;
    public class PeopleList{
        public ArrayList<PeopleInfo> dataList;
    }
    public class PeopleInfo{
        public String peopleTypeId;//设备名称
        public String groupLeader;
        public String peopleTypeName;//正常

    }


}
