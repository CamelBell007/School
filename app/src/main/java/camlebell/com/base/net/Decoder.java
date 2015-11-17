package camlebell.com.base.net;

import camlebell.com.model.BaseModel;
import camlebell.com.request.ChangePasswordRequest;
import camlebell.com.request.DayMenuRequest;
import camlebell.com.request.DeviceListRequest;
import camlebell.com.request.DeviceTypeRequest;
import camlebell.com.request.DishRequest;
import camlebell.com.request.DishTypeRequest;
import camlebell.com.request.GetChannelListRequest;
import camlebell.com.request.GoodNutritionRequest;
import camlebell.com.request.LoginRequest;
import camlebell.com.request.PeopleRequest;
import camlebell.com.request.PeopleTypeRequest;
import camlebell.com.request.WeekDishRequest;
import cn.yoho.yohobase.net.IDecoder;

/**
 * Created by sunyan on 2015/9/11.
 * 将所有的数据转化成对应的对象
 */
public class Decoder implements IDecoder<BaseModel> {

    public static final int LOGIN = 0x1000;
    public static final int CHAGE_PASSWD = 0x1010;
    public static final int DAY_MENU_LIST = 0x1001;
    public static final int KITCHEN_STATUS = 0x1002;
    public static final int API_GET_CHANNEL_LIST = 0x2000;
    public static final int DEVICE_TYPE_LIST = 0x3001;
    public static final int DEVICE_LIST = 0x3002;
    public static final int PEOPLE_TYPE_LIST = 0x4001;
    public static final int PEOPLE_LIST = 0x4002;
    public static final int DISH_TYPE_LIST = 0x5001;
    public static final int DISH_LIST = 0x5002;
    public static final int WEEK_DISH_LIST = 0x6001;
    public static final int GOOD_NUTRITION = 0x6002;

    public static Decoder newInstance() {
        return new Decoder();
    }

    @Override
    public BaseModel doDecode(int type, String jsonString) {
        try {
            switch (type) {
                case API_GET_CHANNEL_LIST: {
                    GetChannelListRequest request = new GetChannelListRequest();
                    request.setResponse(jsonString);
                    return request.getChannelList();

                }
                case LOGIN: {
                    LoginRequest request = new LoginRequest();
                    request.setResponse(jsonString);
                    return request.getLoginMessage();

                }
                case CHAGE_PASSWD: {
                    ChangePasswordRequest request = new ChangePasswordRequest();
                    request.setResponse(jsonString);
                    return request.getChangePasswdMessage();

                }
                case DAY_MENU_LIST:{
                    DayMenuRequest request = new DayMenuRequest();
                    request.setResponse(jsonString);
                    return request.getDayMenuList();
                }

                case DEVICE_TYPE_LIST:{
                    DeviceTypeRequest request = new DeviceTypeRequest();
                    request.setResponse(jsonString);
                    return request.getDeviceTypeInfo();
                }
                case DEVICE_LIST:{
                    DeviceListRequest request = new DeviceListRequest();
                    request.setResponse(jsonString);
                    return request.getDeviceInfo();
                }
                case PEOPLE_TYPE_LIST:{
                    PeopleTypeRequest request = new PeopleTypeRequest();
                    request.setResponse(jsonString);
                    return request.getPeopleTypeInfo();
                }
                case PEOPLE_LIST:{
                    PeopleRequest request = new PeopleRequest();
                    request.setResponse(jsonString);
                    return request.getPeopleInfo();
                }

                case DISH_TYPE_LIST:{
                    DishTypeRequest request = new DishTypeRequest();
                    request.setResponse(jsonString);
                    return request.getDishTypeInfo();
                }
                case DISH_LIST:{
                    DishRequest request = new DishRequest();
                    request.setResponse(jsonString);
                    return request.getDishInfo();
                }
                case WEEK_DISH_LIST:{
                    WeekDishRequest request = new WeekDishRequest();
                    request.setResponse(jsonString);
                    return request.getWeekDishList();
                }
                case GOOD_NUTRITION:{
                    GoodNutritionRequest request = new GoodNutritionRequest();
                    request.setResponse(jsonString);
                    return request.getGoodNutrition();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
