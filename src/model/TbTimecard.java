package model;

import java.util.Date;

public class TbTimecard {
	private int timecardId;
	private String personId;
	private Date timecardDate;
	private String ratifierId;
	private String explains;
	
	public int getTimecardId() {
		return timecardId;
	}
	public void setTimecardId(int timecardId) {
		this.timecardId = timecardId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public Date getTimecardDate() {
		return timecardDate;
	}
	public void setTimecardDate(Date timecardDate) {
		this.timecardDate = timecardDate;
	}
	public String getRatifierId() {
		return ratifierId;
	}
	public void setRatifierId(String ratifierId) {
		this.ratifierId = ratifierId;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	
	public TbTimecard(int timecardId, String personId,
			Date timecardDate, String ratifierId, String explains) {
		super();
		this.timecardId = timecardId;
		this.personId = personId;
		this.timecardDate = timecardDate;
		this.ratifierId = ratifierId;
		this.explains = explains;
	}
	@Override
	public String toString() {
		return "TbTimecard [timecardId=" + timecardId
				+ ", personId=" + personId + ", timecardDate=" + timecardDate
				+ ", ratifierId=" + ratifierId + ", explains=" + explains + "]";
	}

	
}
