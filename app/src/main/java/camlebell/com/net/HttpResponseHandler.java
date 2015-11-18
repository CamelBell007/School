package camlebell.com.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONObject;

import java.net.ConnectException;

import camlebell.com.base.BaseBean;

/**
 */
public class HttpResponseHandler extends JsonHttpResponseHandler {

	public Class<?> classBean;
    public Context mContext;

	public HttpResponseHandler(Class<?> beanType,Context context) {
		this.classBean = beanType;
        this.mContext = context;
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
		super.onSuccess(statusCode, headers, response);
		Log.i("response", response.toString());
		Object oBean = JSONBeanUtil.getObjectFromJson(response.toString(),
				classBean);
		BaseBean bean = (BaseBean) oBean;
		if (bean != null) {
			if (bean.result == 0) {
				uiSuccess(bean);
			} else {
				uiFail(bean);
			}
		} else {
			uiSuccess(bean);
		}
	}

	public void uiStart() {

	}

	public void uiFinish() {

	}

	public void uiSuccess(BaseBean bean) {

	}

	public void uiFail(BaseBean bean) {

	}

    public void onFailure(String failure){
        Toast.makeText(mContext,failure,Toast.LENGTH_SHORT).show();

    }

	@Override
	public void onFailure(int statusCode, Header[] headers,
			Throwable throwable, JSONObject errorResponse) {
		// super.onFailure(statusCode, headers, throwable, errorResponse);
		Log.i("response", "statusCode:" + statusCode);
		if (throwable instanceof ConnectTimeoutException) {
            onFailure("网络异常,请检查设置");
		} else if (throwable.getCause() instanceof HttpHostConnectException) {
            onFailure("网络异常,请检查设置");
		} else if (throwable.getCause() instanceof ConnectException) {
            onFailure("网络异常,请检查设置");
		} else {
            onFailure("数据读取异常,请检查网络设置");
		}

	}

    @Override
	public void onFailure(int statusCode, Header[] headers,
			String responseString, Throwable throwable) {
		Log.i("response", "statusCode:" + responseString);
        onFailure("数据出错啦，程序猿正在抢修中");
	}

	@Override
	public void onStart() {
		super.onStart();
		uiStart();
	}

	@Override
	public void onFinish() {
		super.onFinish();
		uiFinish();
	}

}
