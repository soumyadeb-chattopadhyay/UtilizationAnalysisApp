package com.ericsson.v1.model;

public class MonthSubCdKey {

	private String subCd;
	private String month;
	private String subCdType;
	
	public MonthSubCdKey(String subCdType, String month, String subCd) {
		super();
		this.subCd = subCd;
		this.month = month;
		this.subCdType = subCdType;
	}

	public String getSubCd() {
		return subCd;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getSubCdType() {
		return subCdType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((subCd == null) ? 0 : subCd.hashCode());
		result = prime * result + ((subCdType == null) ? 0 : subCdType.hashCode());
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
		MonthSubCdKey other = (MonthSubCdKey) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (subCd == null) {
			if (other.subCd != null)
				return false;
		} else if (!subCd.equals(other.subCd))
			return false;
		if (subCdType == null) {
			if (other.subCdType != null)
				return false;
		} else if (!subCdType.equals(other.subCdType))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MonthSubCdKey [subCd=" + subCd + ", month=" + month + ", subCdType=" + subCdType + "]";
	}
	
	
	
	
}
