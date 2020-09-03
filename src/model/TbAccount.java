package model;

public class TbAccount {
	private int timecardId;
	private String name;
	private String type;
	private String money;
	public int getTimecardId() {
		return timecardId;
	}
	public void setTimecardId(int timecardId) {
		this.timecardId = timecardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "TbAccount [timecardId=" + timecardId + ", name=" + name
				+ ", type=" + type + ", money=" + money + "]";
	}
	
	
}
