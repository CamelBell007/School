package camlebell.com.model;

/**
 * Created by Administrator on 2015/11/10.
 * 人员、设备状态接口
 */
public class KitchenStatusInfo {
    public String peopleStatus;
    public String deviceStatus;//设备名称

    public KitchenStatusInfo(){};
    public KitchenStatusInfo(String peopleStatus, String deviceStatus){
        this.peopleStatus = peopleStatus;
        this.deviceStatus = deviceStatus;
    }
}
