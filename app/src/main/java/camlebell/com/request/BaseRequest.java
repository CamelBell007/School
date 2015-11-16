package camlebell.com.request;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 谢晓祥
 */
public abstract class BaseRequest implements Request {

    private String mService;

    private boolean timeOut;

    private JSONObject mRequest;

    private JSONObject mResponse;

    private String mResponseString;

    private List<NameValuePair> paramsList;

    protected abstract String initService();

    protected abstract Object initContent()
            throws JSONException;

//    protected abstract String getUrl();

    private void initRequest() {
        try {
            JSONObject jsonObject = (JSONObject) initContent();
            setContent(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setService(String service) {
        mService = service;

    }

    @Override
    public void setContent(Object content)
            throws Exception {
        mRequest = (JSONObject) content;
        if (mRequest == null) {
            mRequest = new JSONObject();
        }
//        mRequest.put("platform", 4 + "");
//        mRequest.put("locale", YohoBoyApplcation.LOCALE);
//        mRequest.put("language", YohoBoyApplcation.LANGUAGE);
//        mRequest.put("udid", YohoBoyApplcation.DEVICEID);
//        JSONObject authInfo = new JSONObject();
//        UserInfo userInfo = ConfigManager.getUserInfo();
//        if (userInfo != null) {
//            authInfo.put("sessionCode", userInfo.getSessionCode());
//            authInfo.put("uid", userInfo.getUserID());
//        }
//        authInfo.put("udid", YohoBoyApplcation.DEVICEID);
//        mRequest.put("authInfo", authInfo);
    }

    /**
     * 获取post请求相关参数
     *
     * @return
     */
    public List<NameValuePair> getParamsList() {
        initRequest();
        paramsList = new ArrayList<NameValuePair>();

        paramsList.add(new BasicNameValuePair("params", mRequest.toString()));
        return paramsList;
    }

    /**
     * 获取post请求相关参数
     * @return
     */
    public Map<String,String> getParamsMap(){
        initRequest();
        Map<String,String> params = new HashMap<>();
        params.put("parameters",mRequest.toString());
        return params;
    }

    public String getmService() {
        setService(initService());
        return mService;
    }

    @Override
    public void setResponse(String response)
            throws Exception {
        if (response != null && !"".equals(response)) {
            mResponse = new JSONObject(response);
        }
    }

    @Override
    public void setResponseString(String response)
            throws Exception {
        mResponseString = response;
    }

    public String getResponseString()
            throws Exception {
        return mResponseString;
    }

    @Override
    public int getStatus() {
        int status = -1;
        if (mResponse != null) {
            String statusStr = mResponse.optString("code");
            if (statusStr != null && "0".equals(statusStr)) {
                status = 0;
            }
        }
        return status;
    }

    @Override
    public JSONObject getData() {
        return mResponse == null ? null : mResponse.optJSONObject("data");
    }

    public String getDataString() {
        return mResponse == null ? null : mResponse.optString("data");
    }

    public JSONArray getDataArray() {
        return mResponse == null ? null : mResponse.optJSONArray("data");
    }

    @Override
    public boolean success() {
        return RequestStatus.SUCCESS.is(getStatus());
    }

    @Override
    public int getCode() {
        return mResponse == null ? 0 : mResponse.optInt("code");
    }

    @Override
    public String getMessage() {
        return mResponse == null ? null : mResponse.optString("message");
    }

    @Override
    public String toString() {
        initRequest();
        return mRequest.toString();
    }

    public boolean isTimeOut() {
        return timeOut;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }

}
