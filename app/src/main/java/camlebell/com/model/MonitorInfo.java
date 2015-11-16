package camlebell.com.model;

/**
 * Created by Administrator on 2015/11/10.
 */
public class MonitorInfo {
    public String monitorId;
    public String monitorName;//设备名称
    public String monitorUrl;//工作状态


    public MonitorInfo(String monitorId, String monitorName, String monitorUrl){
        this.monitorId = monitorId;
        this.monitorName = monitorName;
        this.monitorUrl = monitorUrl;
    }
}
