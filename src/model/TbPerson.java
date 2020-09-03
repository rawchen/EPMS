package model;

import java.util.Date;

public class TbPerson {

	// Fields

	private int id;

	private String recordNumber;

	private int nativePlaceId;
	
	private int deptId;
	
	private int dutyId;

	private String name;	

	private String sex;

	private Date birthday;

	private String photo;

	private String idCard;

	private String marriaged;

	private String partyMember;

	private String schoolAge;

	private String specialty;

	private String foreignLanguage;

	private String grade;

	private String password;

	private String state;

	private int roleId;

	
	
	public TbPerson(String recordNumber, String password) {
		super();
		this.recordNumber = recordNumber;
		this.password = password;
	}

	public TbPerson() {
	}

	public int getDutyId() {
		return dutyId;
	}

	public void setDutyId(int dutyId) {
		this.dutyId = dutyId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public int getNativePlaceId() {
		return nativePlaceId;
	}

	public void setNativePlaceId(int nativePlaceId) {
		this.nativePlaceId = nativePlaceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMarriaged() {
		return marriaged;
	}

	public void setMarriaged(String marriaged) {
		this.marriaged = marriaged;
	}

	public String getPartyMember() {
		return partyMember;
	}

	public void setPartyMember(String partyMember) {
		this.partyMember = partyMember;
	}

	public String getSchoolAge() {
		return schoolAge;
	}

	public void setSchoolAge(String schoolAge) {
		this.schoolAge = schoolAge;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getForeignLanguage() {
		return foreignLanguage;
	}

	public void setForeignLanguage(String foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "TbPerson [id=" + id + ", recordNumber=" + recordNumber
				+ ", nativePlaceId=" + nativePlaceId + ", deptId=" + deptId
				+ ", dutyId=" + dutyId + ", name=" + name + ", sex=" + sex
				+ ", birthday=" + birthday + ", photo=" + photo + ", idCard="
				+ idCard + ", marriaged=" + marriaged + ", partyMember="
				+ partyMember + ", schoolAge=" + schoolAge + ", specialty="
				+ specialty + ", foreignLanguage=" + foreignLanguage
				+ ", grade=" + grade + ", password=" + password + ", state="
				+ state + ", roleId=" + roleId + "]";
	}
	
	

}