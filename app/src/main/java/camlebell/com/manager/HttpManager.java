package camlebell.com.manager;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

import camlebell.com.base.net.Decoder;
import camlebell.com.base.net.YohoRequest;
import camlebell.com.model.BaseModel;
import camlebell.com.model.ChannelInfo;
import camlebell.com.model.DeviceInfo;
import camlebell.com.model.DeviceTypeInfo;
import camlebell.com.model.DishTypeInfo;
import camlebell.com.model.GoodNutritionInfo;
import camlebell.com.model.KitchenStatusInfo;
import camlebell.com.model.DishInfo;
import camlebell.com.model.PeopleInfo;
import camlebell.com.model.PeopleTypeInfo;
import camlebell.com.model.ResultInfo;
import camlebell.com.model.WeekDishInfo;
import camlebell.com.request.ChangePasswordRequest;
import camlebell.com.request.DayMenuRequest;
import camlebell.com.request.DeviceListRequest;
import camlebell.com.request.DeviceTypeRequest;
import camlebell.com.request.DishRequest;
import camlebell.com.request.DishTypeRequest;
import camlebell.com.request.GetChannelListRequest;
import camlebell.com.request.GoodNutritionRequest;
import camlebell.com.request.KitchenStatusRequest;
import camlebell.com.request.LoginRequest;
import camlebell.com.request.PeopleRequest;
import camlebell.com.request.PeopleTypeRequest;
import camlebell.com.request.WeekDishRequest;
import cn.yoho.yohobase.net.HttpService;
import cn.yoho.yohobase.net.IResponseListener;

/**
 * Created by sunyan on 2015/11/12.
 * 管理主界面中的一些接口请求（更新，获取一二级频道，设置信息，下载等等）
 */
public class HttpManager {
//    /**
//     * 获取一二级频道
//     * @param listener
//     */
//    public static void GetChannelList(final IResponseListener<ResultInfo<ChannelInfo>> listener){
//        GetChannelListRequest request = new GetChannelListRequest();
//        Map<String,String> params = request.getParamsMap();
//
//        YohoRequest yohoRequest = new YohoRequest(Decoder.API_GET_CHANNEL_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
//            @Override
//            public void onResponse(BaseModel response) {
//                listener.onResponseSuccess((ResultInfo<ChannelInfo>)response);
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                listener.onResponseFailed("网络错误!");
//            }
//
//            @Override
//            public void onStart() {
//                listener.onResponseStart();
//            }
//        });
//         HttpService.INSTANCE.startRequest(yohoRequest);
//    }

    /**
     * 登陆请求
     * @param listener
     */
    public static void loginRequest(String userName,String passwd,String appName,final IResponseListener<ResultInfo<String>> listener){
        LoginRequest request = new LoginRequest(userName,passwd,appName);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.LOGIN, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<String>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }

    /**
     * 修改密码
     * @param listener
     */
    public static void changePassWordRequest(String userName,String oldPassword,
                                             String newPassword,String appName,final IResponseListener<ResultInfo<String>> listener){
        ChangePasswordRequest request = new ChangePasswordRequest(userName,oldPassword,
                newPassword,appName);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.LOGIN, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<String>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }
    /**
     * 每日菜单
     * @param listener
     */
    public static void dayMenuRequest(String day,String treeGradeId,final IResponseListener<ResultInfo<DishInfo>> listener){
        DayMenuRequest request = new DayMenuRequest(day,treeGradeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.DAY_MENU_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<DishInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }
    /**
     * 人员、设备状态接口
     * @param listener
     */
    public static void getKitchenStatusRequest(String treeGradeId,final IResponseListener<ResultInfo<KitchenStatusInfo>> listener){
        KitchenStatusRequest request = new KitchenStatusRequest(treeGradeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.KITCHEN_STATUS, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<KitchenStatusInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }

    /**
     * 设备型号列表
     * @param listener
     */
    public static void getDeviceTypeInfo(String treeGradeId,final IResponseListener<ResultInfo<DeviceTypeInfo>> listener){
        DeviceTypeRequest request = new DeviceTypeRequest(treeGradeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.DEVICE_TYPE_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<DeviceTypeInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }
    /**
     * 设备列表
     * @param listener
     */
    public static void getDeviceListInfo(String treeGradeId,String deviceTypeId,final IResponseListener<ResultInfo<DeviceInfo
            >> listener){
        DeviceListRequest request = new DeviceListRequest(treeGradeId,deviceTypeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.DEVICE_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<DeviceInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }

    /**
     * 人员分类列表
     * @param listener
     */
    public static void getPeopleTypeInfo(String treeGradeId,final IResponseListener<ResultInfo<PeopleTypeInfo>> listener){
        PeopleTypeRequest request = new PeopleTypeRequest(treeGradeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.PEOPLE_TYPE_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<PeopleTypeInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }

    /**
     * 人员列表
     * @param listener
     */
    public static void getPeopleListInfo(String treeGradeId,String peopleTypeId,final IResponseListener<ResultInfo<PeopleInfo>> listener){
        PeopleRequest request = new PeopleRequest(treeGradeId,peopleTypeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.PEOPLE_TYPE_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<PeopleInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }
    /**
     * 菜肴类别接口
     * @param listener
     */
    public static void getDishTypeInfo(String treeGradeId,final IResponseListener<ResultInfo<DishTypeInfo>> listener){
        DishTypeRequest request = new DishTypeRequest(treeGradeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.DISH_TYPE_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<DishTypeInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }

    /**
     * 菜肴列表
     * @param listener
     */
    public static void getDishInfo(String dishTypeId,final IResponseListener<ResultInfo<DishInfo>> listener){
        DishRequest request = new DishRequest(dishTypeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.DISH_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<DishInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }

    /**
     * 菜肴营养详情接口
     * @param listener
     */
    public static void getGoodNutritionInfo(String dishId,final IResponseListener<ResultInfo<GoodNutritionInfo>> listener){
        GoodNutritionRequest request = new GoodNutritionRequest(dishId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.WEEK_DISH_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<GoodNutritionInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }
    /**
     * 一周菜单接口
     * @param listener
     */
    public static void getWeekDishInfo(String treeGradeId,final IResponseListener<ResultInfo<WeekDishInfo>> listener){
        WeekDishRequest request = new WeekDishRequest(treeGradeId);
        Map<String,String> params = request.getParamsMap();

        YohoRequest yohoRequest = new YohoRequest(Decoder.WEEK_DISH_LIST, request.getUrl(), params, new Response.Listener<BaseModel>() {
            @Override
            public void onResponse(BaseModel response) {
                listener.onResponseSuccess((ResultInfo<WeekDishInfo>)response);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponseFailed("网络错误!");
            }

            @Override
            public void onStart() {
                listener.onResponseStart();
            }
        });
        HttpService.INSTANCE.startRequest(yohoRequest);
    }
}
