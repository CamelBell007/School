package camlebell.com.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import camlebell.com.model.ChannelInfo;
import camlebell.com.model.ResultInfo;


/**
 * 获取频道
 * 
 * @author 谢晓祥
 * 
 */
public class GetChannelListRequest extends BaseRequest {

	private String mapTime;//map本地最新时间字符串，取map
	
	private String zineString;//本地保存的杂志封面标题
	private String curVersion;//当前版本号
	public GetChannelListRequest() {

	}

	public GetChannelListRequest(String time, String zineString, String curVersion) {
		this.mapTime = time;
		this.zineString = zineString;
		this.curVersion = curVersion;
	}
	//TODO 没用了
	@Override
	protected String initService() {
		return "get/channel";
	}

	@Override
	protected Object initContent() throws JSONException {
		JSONObject request = new JSONObject();
		try {
//			request.put("mapTime", mapTime);
//			request.put("zineString", zineString);
//			request.put("curVersion", curVersion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request;
	}

	public String getUrl() {
		return "url";
	}

	@Override
	public String toString() {
		try {
			return initContent().toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return super.toString();
	}

	/**
	 * 获取数据
	 * 
	 * @return
	 */
	public ResultInfo<ChannelInfo> getChannelList() {
		ResultInfo<ChannelInfo> result = new ResultInfo<ChannelInfo>();

		boolean isSuccess = super.success();
		result.setSuccess(isSuccess);
		if (!isSuccess) {
			result.setrTimeOut(super.isTimeOut());
			result.setFailInfo(super.getMessage());
			return result;
		}
		JSONArray data = getDataArray();
		if (data == null || data.length() == 0) {
			return result;
		}
		List<ChannelInfo> list = new ArrayList<ChannelInfo>();

		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = data.optJSONObject(i);
			ChannelInfo info = new ChannelInfo(jo);
			if (info != null) {
				list.add(info);
			}
		}
		result.setListInfo(list);

		return result;
	}

}
