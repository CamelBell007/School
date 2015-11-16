package camlebell.com.request;

import org.json.JSONException;
import org.json.JSONObject;

import camlebell.com.Utils.Constants;
import camlebell.com.model.KitchenStatusInfo;
import camlebell.com.model.ResultInfo;


/**
 * 人员、设备状态接口
 * 
 * @author sunyan
 * 
 */
public class KitchenStatusRequest extends BaseRequest {

	private String treeGradeId;//学校id，目前固定为5，赤壁路(山西路)小学

	public KitchenStatusRequest() {

	}

	public KitchenStatusRequest( String treeGradeId) {
		this.treeGradeId = treeGradeId;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	public String getUrl() {
		return Constants.BASE_URL+Constants.REQUEST_NAME.KITCHEN_STATUS;
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
	public ResultInfo<KitchenStatusInfo> getKitchenStatus() {
		ResultInfo<KitchenStatusInfo> result = new ResultInfo<KitchenStatusInfo>();

		boolean isSuccess = super.success();
		result.setSuccess(isSuccess);
		if (!isSuccess) {
			result.setrTimeOut(super.isTimeOut());
			result.setFailInfo(super.getMessage());
			return result;
		}
		JSONObject data = getData();
		if (data == null || data.length() == 0) {
			return result;
		}
		KitchenStatusInfo kitchenStatusInfo = new KitchenStatusInfo();
		try {
			kitchenStatusInfo.peopleStatus = data.getString("peopleStatus");
			kitchenStatusInfo.deviceStatus = data.getString("deviceStatus");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		result.setInfo(kitchenStatusInfo);

		return result;
	}

}
