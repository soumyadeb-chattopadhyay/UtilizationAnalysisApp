package com.ericsson.v1.model;

public class JobStageDTO {

	 private String cd;
	 private String month;
	 private Double targetHours;
	 private Double recordedHours;
	 private Double percentage;
	 
	 
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getTargetHours() {
		return targetHours;
	}
	public void setTargetHours(Double targetHours) {
		this.targetHours = targetHours;
	}
	public Double getRecordedHours() {
		return recordedHours;
	}
	public void setRecordedHours(Double recordedHours) {
		this.recordedHours = recordedHours;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
		
}
