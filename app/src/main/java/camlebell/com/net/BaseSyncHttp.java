package camlebell.com.net;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;

import camlebell.com.MyApplcation;
import camlebell.com.Utils.CommonUtils;
import camlebell.com.Utils.Constants;

/**
 * 同步
 */
public class BaseSyncHttp extends AsyncHttpClient {

	private static String ENCODING = "UTF-8";
	private static String CONTENTTYPE = "application/json;UTF-8";
	// 同步
	private static AsyncHttpClient syncClient = new SyncHttpClient();
	private static Context context = MyApplcation.getInstance();
	static {
		syncClient.setTimeout(20000);
	}

	public static void postReq(String host, String url, RequestParams params,
			JsonHttpResponseHandler hander) {
		syncClient.post(host + url, params, hander);
	}

	public static void postReq(String url, RequestParams params,
			JsonHttpResponseHandler hander) {
		Log.i("request", Constants.BASE_URL + url);
		syncClient.post(Constants.BASE_URL + url, params, hander);
	}

	public static void getReq(String host, String url, RequestParams params,
			JsonHttpResponseHandler hander) {
		syncClient.get(host + url, params, hander);
	}

	public static void getReq(String url, RequestParams params,
			JsonHttpResponseHandler hander) {
		Log.i("request",
				Constants.BASE_URL + url + " requestJson =" + params.toString());
		syncClient.get(Constants.BASE_URL + url, params, hander);
	}

	public static void postEntity(String cmd, String entity,
			JsonHttpResponseHandler responseHandler) {
		Log.d("PortalClient", entity);
		StringEntity se = null;
		try {
			se = new StringEntity(CommonUtils.urlEncode(entity), ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, CONTENTTYPE));
		syncClient.post(context, Constants.BASE_URL + cmd, se, CONTENTTYPE,
				responseHandler);
	}

}
