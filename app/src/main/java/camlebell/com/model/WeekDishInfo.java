package camlebell.com.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/10.
 */
public class WeekDishInfo {


    public String dishId;//4
    public String imgFile;//"4.jpg",
    public String dishClassId;//5
    public String dishClassName;//"主食水果",
    public String goodId;
    public String good;//"汤",

    public WeekDishInfo(){}
    public WeekDishInfo(String dishId, String imgFile, String dishClassId,
                        String dishClassName, String goodId,String good) {
        this.dishId = dishId;
        this.imgFile = imgFile;
        this.dishClassId = dishClassId;
        this.dishClassName = dishClassName;
        this.goodId = goodId;
        this.good = good;
    }

    public WeekDishInfo(JSONObject jo) {
        if (jo == null) {
            return;
        }
        this.dishId = jo.optString("dishId");
        this.imgFile = jo.optString("imgFile");
        this.dishClassId = jo.optString("dishClassId");
		this.dishClassName = jo.optString("dishClassName");
        this.goodId = jo.optString("goodId");
        this.good = jo.optString("good");

    }
}
