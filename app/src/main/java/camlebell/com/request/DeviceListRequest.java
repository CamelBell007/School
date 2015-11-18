package camlebell.com.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import camlebell.com.Utils.Constants;
import camlebell.com.model.DeviceInfo;
import camlebell.com.model.ResultInfo;


/**
 * 设备列表
 * 
 * @author sunyan
 * 
 */
public class DeviceListRequest extends BaseRequest {

	private String treeGradeId;//学校id，目前固定为5，赤壁路(山西路)小学
	private String deviceTypeId;//必填，设备型号id

	public DeviceListRequest() {

	}

	public DeviceListRequest(String treeGradeId,String deviceTypeId) {
		this.treeGradeId = treeGradeId;
		this.deviceTypeId = deviceTypeId;
	}

	@Override
	protected String initService() {
		return "";
	}

	@Override
	protected Object initContent() throws JSONException {
		JSONObject request = new JSONObject();
		try {
			request.put("treeGradeId", treeGradeId);
			request.put("deviceTypeId", deviceTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	public String getUrl() {
		return Constants.BASE_URL+Constants.REQUEST_NAME.DEVICE_TYPE;
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
	public ResultInfo<DeviceInfo> getDeviceInfo() {
		ResultInfo<DeviceInfo> result = new ResultInfo<DeviceInfo>();

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
		List<DeviceInfo> list = new ArrayList<DeviceInfo>();

		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = data.optJSONObject(i);
			DeviceInfo info = new DeviceInfo(jo);
			if (info != null) {
				list.add(info);
			}
		}
		result.setListInfo(list);

		return result;
	}

}
