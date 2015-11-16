package camlebell.com.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/10.
 */
public class DeviceInfo {
    public String deviceId;
    public String deviceName;//设备名称
    public String workStatus;//工作状态
    public String opreationPeople;//操作人员
    public String heathStatus;//设备是否正常
    public String teamName;//归属类型

    public String status;//正常


    public DeviceInfo(String deviceId, String deviceName, String workStatus, String teamName, String opreationPeople, String heathStatus){
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.workStatus = workStatus;
        this.teamName = teamName;
        this.opreationPeople = opreationPeople;
        this.heathStatus = heathStatus;
    }
    public DeviceInfo(String deviceId, String deviceName, String status){
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.status = status;
    }

    public DeviceInfo(JSONObject jo) {
        if (jo == null) {
            return;
        }
        this.deviceId = jo.optString("deviceId");
        this.deviceName = jo.optString("deviceName");
        this.status = jo.optString("status");

    }
}
