package camlebell.com.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import camlebell.com.Utils.Constants;
import camlebell.com.model.DishInfo;
import camlebell.com.model.ResultInfo;


/**
 * 每日菜单接口
 * 
 * @author sunyan
 * 
 */
public class DayMenuRequest extends BaseRequest {

	private String day;//"2015-11-10"必填，
	private String treeGradeId;//学校id，目前固定为5，赤壁路(山西路)小学

	public DayMenuRequest() {

	}

	public DayMenuRequest(String day, String treeGradeId) {
		this.day = day;
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
			request.put("day", day);
			request.put("treeGradeId", treeGradeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	public String getUrl() {
		return Constants.BASE_URL+Constants.REQUEST_NAME.DAY_MENU;
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
	public ResultInfo<DishInfo> getDayMenuList() {
		ResultInfo<DishInfo> result = new ResultInfo<DishInfo>();

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
		List<DishInfo> list = new ArrayList<DishInfo>();

		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = data.optJSONObject(i);
			DishInfo info = new DishInfo(jo);
			if (info != null) {
				list.add(info);
			}
		}
		result.setListInfo(list);

		return result;
	}

}
