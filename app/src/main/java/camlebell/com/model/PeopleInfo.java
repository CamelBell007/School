package camlebell.com.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/10.
 * 设备型号列表
 */
public class PeopleInfo {
    public String id;///用户id
    public String name;//姓名
    public String position;//职位
    public String address;//地址
    public String certificatetime;//证件有效时间


    public PeopleInfo(){};
    public PeopleInfo(String id, String name, String position, String address, String certificatetime){
        this.id = id;
        this.name = name;
        this.position = position;
        this.address = address;
        this.certificatetime = certificatetime;
    }

    public PeopleInfo(JSONObject jo) {
        if (jo == null) {
            return;
        }
        this.id = jo.optString("id");
        this.name = jo.optString("name");
        this.position = jo.optString("position");
        this.address = jo.optString("address");
        this.certificatetime = jo.optString("certificatetime");

    }
}
