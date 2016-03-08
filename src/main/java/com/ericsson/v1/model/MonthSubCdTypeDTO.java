package com.ericsson.v1.model;

public class MonthSubCdTypeDTO {

	 private String month;
	 private Double targetHours;
	 private Double recordedHours;
	 
	 private String subCdType;
	 private String subCd;
	 
	public MonthSubCdTypeDTO(String month, Double targetHours, Double recordedHours) {
		super();
		this.month = month;
		this.targetHours = targetHours;
		this.recordedHours = recordedHours;
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

	public String getSubCdType() {
		return subCdType;
	}

	public void setSubCdType(String subCdType) {
		this.subCdType = subCdType;
	}

	public String getSubCd() {
		return subCd;
	}

	public void setSubCd(String subCd) {
		this.subCd = subCd;
	}

	@Override
	public String toString() {
		return "MonthSubCdTypeDTO [month=" + month + ", targetHours=" + targetHours + ", recordedHours=" + recordedHours
				+ ", subCdType=" + subCdType + ", subCd=" + subCd + "]";
	}
	 
	
}
