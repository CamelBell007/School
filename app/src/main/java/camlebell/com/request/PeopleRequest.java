package camlebell.com.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import camlebell.com.Utils.Constants;
import camlebell.com.model.PeopleTypeInfo;
import camlebell.com.model.PeopleInfo;
import camlebell.com.model.ResultInfo;


/**
 * 人员列表
 * 
 * @author sunyan
 * 
 */
public class PeopleRequest extends BaseRequest {

	private String treeGradeId;//学校id，目前固定为5，赤壁路(山西路)小学S
	private String peopleTypeId;//必填，组别id，1厨师 2清洁 3配送
	public PeopleRequest() {

	}

	public PeopleRequest(String treeGradeId,String peopleTypeId) {
		this.treeGradeId = treeGradeId;
		this.peopleTypeId = peopleTypeId;
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
			request.put("peopleTypeId", peopleTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	public String getUrl() {
		return Constants.BASE_URL+Constants.REQUEST_NAME.PEOPLE_LIST;
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
	public ResultInfo<PeopleInfo> getPeopleInfo() {
		ResultInfo<PeopleInfo> result = new ResultInfo<PeopleInfo>();

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
		List<PeopleInfo> list = new ArrayList<PeopleInfo>();

		for (int i = 0; i < data.length(); i++) {
			JSONObject jo = data.optJSONObject(i);
			PeopleInfo info = new PeopleInfo(jo);
			if (info != null) {
				list.add(info);
			}
		}
		result.setListInfo(list);

		return result;
	}

}
