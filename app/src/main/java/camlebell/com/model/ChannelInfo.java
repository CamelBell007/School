/**
 * User.java
 * project_name: cn.yoho.yohoevogue.Activity.StartActivity
 *
 * Created by sunny on 2013-2-16 ����2:50:16
 * Copyright (c) 2013�� NewPower Co. All rights reserved.
 *
 */
package camlebell.com.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 频道
 * 
 * @author 谢晓祥
 * 
 */
public class ChannelInfo implements Serializable {

	private static final long serialVersionUID = 8968662043406742742L;
	/**
	 * id
	 */
	private String channelID;

	/**
	 * name
	 */
	private String channelName;
	/**
	 * 英文名
	 */
	private String channelEnName;
	/**
	 * 一级频道标题图标
	 */
	private int channelTitleImg;
	/**
	 * 一级频道显示的图片
	 */
	private int channelImg;
	/**
	 * 子类别
	 */
	private List<ChannelInfo> subChannelList;
	
	/**
	 * 是否有更新
	 */
	private String isUpdate ;
	
	/**
	 * 最新杂志期刊号
	 */
	private String magazineJournal;

	public String getMagazineJournal() {
		return magazineJournal;
	}

	public void setMagazineJournal(String magazineJournal) {
		this.magazineJournal = magazineJournal;
	}

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelEnName() {
		return channelEnName;
	}

	public void setChannelEnName(String channelEnName) {
		this.channelEnName = channelEnName;
	}

	public List<ChannelInfo> getSubChannelList() {
		return subChannelList;
	}

	public void setSubChannelList(List<ChannelInfo> subChannelList) {
		this.subChannelList = subChannelList;
	}

	public int getChannelTitleImg() {
		return channelTitleImg;
	}

	public void setChannelTitleImg(int channelTitleImg) {
		this.channelTitleImg = channelTitleImg;
	}

	public int getChannelImg() {
		return channelImg;
	}

	public void setChannelImg(int channelImg) {
		this.channelImg = channelImg;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public ChannelInfo(String channelID, String channelName, String channelEnName) {
		this.channelID = channelID;
		this.channelName = channelName;
		this.channelEnName = channelEnName;
	}
	
	public ChannelInfo(String channelID, String channelName, String channelEnName, int channelTitleImg, int channelImg) {
		this.channelID = channelID;
		this.channelName = channelName;
		this.channelEnName = channelEnName;
		this.channelTitleImg = channelTitleImg;
		this.channelImg = channelImg;
	}

	public ChannelInfo(JSONObject jo) {
		if (jo == null) {
			return;
		}
		this.channelID = jo.optString("id");
		this.channelName = jo.optString("channel_name_cn");
		this.channelEnName = jo.optString("channel_name_en");
//		this.isUpdate = jo.optString("isUpdate");
//		this.magazineJournal = jo.optString("zineString");
		this.subChannelList = new ArrayList<ChannelInfo>();
		JSONArray subList = jo.optJSONArray("subNav");
		if (subList != null && subList.length() > 0) {
			for (int i = 0; i < subList.length(); i++) {
				JSONObject subChannel = subList.optJSONObject(i);
				if (subChannel == null) {
					continue;
				}
				ChannelInfo info = new ChannelInfo(subChannel.optString("id"), subChannel.optString("channel_name_cn"), subChannel.optString("channel_name_en"));
				subChannelList.add(info);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channelID == null) ? 0 : channelID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelInfo other = (ChannelInfo) obj;
		if (channelID == null) {
			if (other.channelID != null)
				return false;
		} else if (!channelID.equals(other.channelID))
			return false;
		return true;
	}
}
