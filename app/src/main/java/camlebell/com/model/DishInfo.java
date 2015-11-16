package camlebell.com.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/10.
 */
public class DishInfo {
    //这3个没用了
    public String menuId;
    public String menuName;//设备名称
    public String teamName;//归属类型


    public String dishId;//4
    public String imgFile;//"4.jpg",
    public String dishName;
    public String goodId;//"汤",


    public DishInfo(String menuId, String menuName, String teamName) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.teamName = teamName;
    }

    public DishInfo(JSONObject jo) {
        if (jo == null) {
            return;
        }
        this.dishId = jo.optString("dishId");
        this.imgFile = jo.optString("imgFile");
        this.dishName = jo.optString("dishName");
		this.goodId = jo.optString("goodId");

    }
}
