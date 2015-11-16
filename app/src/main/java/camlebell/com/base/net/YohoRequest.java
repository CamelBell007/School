package camlebell.com.base.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import camlebell.com.model.BaseModel;


/**
 * Created by Administrator on 2015/9/11.
 */
public class YohoRequest extends Request<BaseModel> {

    protected static final String PROTOCOL_CHARSET = "utf-8";
    private Map<String, String> mParams;
    /**
     * api type
     */
    private final int mApiType;

    public YohoRequest(int apitype, String url, Map<String, String> params, Response.Listener<BaseModel> listener) {
        super(Method.POST, url, listener);
        mApiType = apitype;
        mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return super.getHeaders();
    }

    @Override
    protected Response<BaseModel> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            BaseModel result = Decoder.newInstance().doDecode(mApiType, jsonString);
            if (result == null) {
                return Response.error(new ParseError("unknown api type"));
            } else {
                return Response.success(result,
                        HttpHeaderParser.parseCacheHeaders(response));
            }

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(BaseModel response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }
}
