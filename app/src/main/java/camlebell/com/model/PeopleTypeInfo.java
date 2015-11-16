package camlebell.com.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/10.
 * 设备型号列表
 */
public class PeopleTypeInfo {
    public String peopleTypeId;//当前状态
    public String groupLeader;//设备种类id
    public String peopleTypeName;//设备类型名称


    public PeopleTypeInfo(){};
    public PeopleTypeInfo(String peopleTypeId, String groupLeader, String peopleTypeName){
        this.peopleTypeId = peopleTypeId;
        this.groupLeader = groupLeader;
        this.peopleTypeName = peopleTypeName;
    }

    public PeopleTypeInfo(JSONObject jo) {
        if (jo == null) {
            return;
        }
        this.peopleTypeId = jo.optString("peopleTypeId");
        this.groupLeader = jo.optString("groupLeader");
        this.peopleTypeName = jo.optString("peopleTypeName");

    }
}
