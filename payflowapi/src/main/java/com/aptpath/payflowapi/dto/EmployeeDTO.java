package com.aptpath.payflowapi.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}
	public String getPastExperience() {
		return pastExperience;
	}
	public void setPastExperience(String pastExperience) {
		this.pastExperience = pastExperience;
	}
	public Integer getCreatedByUserId() {
		return createdByUserId;
	}
	public void setCreatedByUserId(Integer createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	private String fullName;
    private int age;
    private String totalExperience;
    private String pastExperience;
    private Integer createdByUserId; 
}

