package camlebell.com.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/10.
 * 设备型号列表
 */
public class DishTypeInfo {
    public String dishTypeId;//设备种类id
    public String dishTypeName;//设备类型名称


    public DishTypeInfo(){};
    public DishTypeInfo(String dishTypeId,String dishTypeName){
        this.dishTypeId = dishTypeId;
        this.dishTypeName = dishTypeName;
    }

    public DishTypeInfo(JSONObject jo) {
        if (jo == null) {
            return;
        }
        this.dishTypeId = jo.optString("dishTypeId");
        this.dishTypeName = jo.optString("dishTypeName");

    }
}
