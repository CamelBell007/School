package camlebell.com.net;



import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 2015/9/11.
 */
public class PackagePostData {




//    private static String addToken(JSONObject root) {
//        try {
//            String token = PreferencesHelper.getSharedPreferences(
//                    Myapplication.getInstance(),
//                    PreferencesContact.LOGIN_TOKEN, "HXDP#@$@#%!PSDUI@#$@#");
//            root.put("token", token);
//            return root.toString();
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return root.toString();
//        }
//    }


    private static String setPages(JSONObject root, int pages, int pageNo) {
        try {
            root.put("pageNo", 1);
            root.put("onePageNum", 10);
            root.put("version", "1");

            return root.toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return root.toString();
        }
    }


    /**
     * 检查APP更新接口
     */
    public static String checkAppUpdate() {
        JSONObject root = new JSONObject();
        JSONObject parameters = new JSONObject();
        try {
            root.put("cmd", "checkAppUpdate");
            root.put("parameters", parameters);
//            parameters.put("version", version);
            return setPages(root, 10, 1);
        } catch (JSONException e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     * 系统账号登录接口
     */
    public static String signin(String userName, String password) {
        JSONObject root = new JSONObject();
        JSONObject parameters = new JSONObject();
        try {
            root.put("cmd", "signin");
            root.put("params", parameters);
            parameters.put("userName", userName);
            parameters.put("password", password);
            parameters.put("appName", "wisdomKitchen_SCHOOL");
            return setPages(root, 10, 1);
        } catch (JSONException e) {

            e.printStackTrace();
            return null;
        }
    }





}
