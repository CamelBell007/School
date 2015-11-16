package camlebell.com.model;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/11/10.
 * 菜肴营养详情
 */
public class GoodNutritionInfo {
    public String each_dish_weight;// "80",
    public String state;// "3",
    public String energy;//"44.0",
    public String protein;// "5.0",
    public String fat;// "1.0",
    public String carbohydrate;// "2.0",
    public String ca;// "15.0",
    public String fe;// "1.0",
    public String zn;// "0.0",
    public String na;// "256.0",
    public String vita;// "65.0",
    public String vitb1;// "0.0",
    public String vitb2;// "0.0",
    public String vitc;// "5.0"

    public GoodNutritionInfo(){};

    public GoodNutritionInfo(JSONObject jo) {
        if (jo == null) {
            return;
        }
        this.each_dish_weight = jo.optString("each_dish_weight");
        this.state = jo.optString("state");
        this.energy = jo.optString("energy");
        this.protein = jo.optString("protein");
        this.fat = jo.optString("fat");
        this.carbohydrate = jo.optString("carbohydrate");
        this.ca = jo.optString("ca");
        this.fe = jo.optString("fe");
        this.zn = jo.optString("zn");
        this.na = jo.optString("na");
        this.vita = jo.optString("vita");
        this.vitb1 = jo.optString("vitb1");
        this.vitb2 = jo.optString("vitb2");
        this.vitc = jo.optString("vitc");

    }
}
