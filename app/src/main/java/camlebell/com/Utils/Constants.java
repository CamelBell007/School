package camlebell.com.Utils;

/**
 * Created by Administrator on 2015/11/15.
 * 所有的静态变量
 */
public class Constants {
    public static final String BASE_URL = "http://nj.pansum.net/pansum/api";

    /**
     * 接口名称
     */
    public interface REQUEST_NAME{
        //用户登陆接口
        public static final String LOGIN = "signin";
        //每日菜单接口
        public static final String DAY_MENU = "dayMenu";
        //人员、设备状态接口
        public static final String KITCHEN_STATUS = "kitchenStatus";
        //设备型号列表
        public static final String DEVICE_TYPE_LIST = "deviceTypeList";
        //设备列表
        public static final String DEVICE_TYPE = "deviceList";
        //人员分类列表
        public static final String PEOPLE_TYPE_LIST = "peopleTypeList";
        //人员列表(单一种类)
        public static final String PEOPLE_LIST = "peopleList";
        //菜肴类别接口
        public static final String DISH_TYPE_LIST = "dishTypeList";
        //菜肴列表(单一种类)
        public static final String DISH_LIST = "dishList";
        //菜肴营养详情接口
        public static final String GOOD_NUTRITION = "goodNutrition";
        //修改密码接口
        public static final String CHANGE_PASSWORD = "changePassword";
        //一周菜单接口
        public static final String WEEK_MENU = "weekMenu";
    }

    public static final String PROJECT_FILE_NAME = "school";
    public static final String CACHE_FILE_NAME = "cache";
}
