package camlebell.com.model;

import camlebell.com.base.BaseBean;

/**
 * Created by Administrator on 2015/11/10.
 * 人员、设备状态接口
 */
public class KitchenStatusInfo extends BaseBean{

    public int totalRecordNum ;
    public KitchenStatus detail;//设备名称

    public class KitchenStatus{
        public String peopleStatus;
        public String deviceStatus;
    }

}
