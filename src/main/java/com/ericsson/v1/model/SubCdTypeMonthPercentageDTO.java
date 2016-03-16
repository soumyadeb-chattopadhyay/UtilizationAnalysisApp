package com.ericsson.v1.model;

import java.io.Serializable;

public class SubCdTypeMonthPercentageDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String subCdType;
	private String month;
	private Double percentage;
	 private Double targetHours;
	 private Double recordedHours;
	
	
	public String getSubCdType() {
		return subCdType;
	}
	public void setSubCdType(String subCdType) {
		this.subCdType = subCdType;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
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
	
}
