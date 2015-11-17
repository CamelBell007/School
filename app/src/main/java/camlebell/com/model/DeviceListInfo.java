package camlebell.com.model;


import java.util.ArrayList;

import camlebell.com.base.BaseBean;

/**
 * Created by Administrator on 2015/11/10.
 * 设备型号列表
 */
public class DeviceListInfo extends BaseBean {
    public int  totalRecordNum;
    public DeviceList detail;
    public class DeviceList{
        public ArrayList<DeviceInfo> dataList;
    }
    public class DeviceInfo{
        public String deviceTypeId;
        public String deviceTypeName;//设备名称
        public String status;//正常



        public String workStatus;//工作状态
        public String opreationPeople;//操作人员
        public String heathStatus;//设备是否正常
        public String teamName;//归属类型
    }


}
