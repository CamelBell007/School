package camlebell.com.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/10.
 * 设备型号列表
 */
public class DeviceTypeInfo {
    public String deviceTypeId;//设备种类id
    public String status;//当前状态
    public String deviceTypeName;//设备类型名称


    public DeviceTypeInfo(){};
    public DeviceTypeInfo(String deviceTypeId, String status,String deviceTypeName){
        this.deviceTypeId = deviceTypeId;
        this.status = status;
        this.deviceTypeName = deviceTypeName;
    }

    public DeviceTypeInfo(JSONObject jo) {
        if (jo == null) {
            return;
        }
        this.deviceTypeId = jo.optString("deviceTypeId");
        this.status = jo.optString("status");
        this.deviceTypeName = jo.optString("deviceTypeName");

    }
}
