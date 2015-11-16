package camlebell.com.request;

import org.json.JSONException;
import org.json.JSONObject;

import camlebell.com.Utils.Constants;
import camlebell.com.model.GoodNutritionInfo;
import camlebell.com.model.KitchenStatusInfo;
import camlebell.com.model.ResultInfo;


/**
 * 人员、设备状态接口
 * 
 * @author sunyan
 * 
 */
public class GoodNutritionRequest extends BaseRequest {

	private String dishId;//学校id，目前固定为5，赤壁路(山西路)小学

	public GoodNutritionRequest() {

	}

	public GoodNutritionRequest(String dishId) {
		this.dishId = dishId;
	}

	@Override
	protected String initService() {
		return "";
	}

	@Override
	protected Object initContent() throws JSONException {
		JSONObject request = new JSONObject();
		try {
			request.put("dishId", dishId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	public String getUrl() {
		return Constants.BASE_URL+Constants.REQUEST_NAME.GOOD_NUTRITION;
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
	public ResultInfo<GoodNutritionInfo> getGoodNutrition() {
		ResultInfo<GoodNutritionInfo> result = new ResultInfo<GoodNutritionInfo>();

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
		GoodNutritionInfo kitchenStatusInfo = new GoodNutritionInfo(data);
		result.setInfo(kitchenStatusInfo);

		return result;
	}

}
