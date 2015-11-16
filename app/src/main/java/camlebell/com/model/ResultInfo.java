package camlebell.com.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 谢晓祥
 *
 * @param <T>
 */


public class ResultInfo<T> extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 是否成功
	 */
	private boolean isSuccess;
	/**
	 * 失败是否因为超时
	 */
	private boolean rTimeOut;
	/**
	 * 失败原因
	 */
	private String FailInfo;
	/**
	 * 返回信息
	 */
	private T Info;
	private List<T> ListInfo;
	private Map<String,T> MapInfo;
	/**
	 * 总数量
	 */
	private int TotalCount;
	private int haveNextPage ;
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getFailInfo() {
		return FailInfo;
	}
	public void setFailInfo(String failInfo) {
		FailInfo = failInfo;
	}
	public T getInfo() {
		return Info;
	}
	public void setInfo(T info) {
		Info = info;
	}
	public List<T> getListInfo() {
		return ListInfo;
	}
	public void setListInfo(List<T> listInfo) {
		ListInfo = listInfo;
	}
	
	public Map<String,T> getMapInfo() {
		return MapInfo;
	}
	public void setMapInfo(Map<String, T> mapInfo) {
		MapInfo = mapInfo;
	}
	public int getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}
	public int getHaveNextPage() {
		return haveNextPage;
	}
	public void setHaveNextPage(int haveNextPage) {
		this.haveNextPage = haveNextPage;
	}
	public boolean isrTimeOut() {
		return rTimeOut;
	}
	public void setrTimeOut(boolean rTimeOut) {
		this.rTimeOut = rTimeOut;
	}
}