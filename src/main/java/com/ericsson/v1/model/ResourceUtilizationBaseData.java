package com.ericsson.v1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection="resourceUtilizationData")
public class ResourceUtilizationBaseData {
	
	//@Id
	private String id;
	private String month;
	private Object quarter;
	private Object persNo;
	private Object empId;
	private Object empName;
	private Object employeeStatus;
	private Object cATCTargetHours;
	private Object cATCRecordedHours;
	private Object targetHours;
	private Object recordedHours;
	private Object totalBillableHours;
	private Object nonRecordedHours;
	private Object pendingHours;
	private Object billableNonIOHours;
	private Object gTTIOHours;
	private Object nonGTTIOHours;
	private Object utilization;
	private Object utilizationNonIO;
	private Object utilizationSlab;
	private Object technicalDateOfEntry;
	private Object monthOfJoining;
	private Object ageInOrg;
	private Object personnelSubarea;
	private Object costctr;
	private Object orgUnit;
	private Object organizationalUnitDesc;
	private Object orgUnitShort;
	private Object nameOfSuperiorOM;
	private Object supervisorPersonalNo;
	private Object positionId;
	private Object positionName;
	private Object jobOM;
	private Object jobNameOM;
	private Object jobAreaAbbreviationOM;
	private Object jobFamilyNameOM;
	private Object jobFamilyAbbreviationOM;
	private Object genderKey;
	private Object corporateID;
	private Object ericssonEmailAddress;
	private Object pERSONID_EXT;
	private Object payScaleType;
	private Object jobAreaNameOM;
	private Object globalJobStage;
	private Object employeeSubgroup;
	private Object billableNonBillable;
	private Object eriproCD;
	private Object eriproCDId;
	private Object eriproSubCD;
	private Object eriproAccount;
	private Object logicalGrouping;
	private Object eriproNonDeployableRole;
	private Object isLineManager;
	private Object isProjectManager;
	private Object isHosted;
	private Object lMSpan;
	private Object nonDeplStartDate;
	private Object nonDeplEndDate;
	private Object pasPns;
	private Object poolName;
	private Object nonDeployableFlag;
	private Object fAFuncArea;
	private Object employeeGroup;
	private Object utilizationreviousMonth1;
	private Object utilizationreviousMonth2;
	private Object utilizationreviousMonth3;
	private Object utilizationreviousMonth4;
	private Object utilizationreviousMonth5;
	private Object utilizationreviousMonth6;
	private Object benchClassification;
	private Object billabilityHours;


	public void setId(String id) {
		this.id = id;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setQuarter(Object quarter) {
		this.quarter = quarter;
	}

	public void setPersNo(Object persNo) {
		this.persNo = persNo;
	}

	public void setEmpId(Object empId) {
		this.empId = empId;
	}

	public void setEmpName(Object empName) {
		this.empName = empName;
	}

	public void setEmployeeStatus(Object employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public void setcATCTargetHours(Object cATCTargetHours) {
		this.cATCTargetHours = cATCTargetHours;
	}

	public void setcATCRecordedHours(Object cATCRecordedHours) {
		this.cATCRecordedHours = cATCRecordedHours;
	}

	public void setTargetHours(Object targetHours) {
		this.targetHours = targetHours;
	}

	public void setRecordedHours(Object recordedHours) {
		this.recordedHours = recordedHours;
	}

	public void setTotalBillableHours(Object totalBillableHours) {
		this.totalBillableHours = totalBillableHours;
	}

	public void setNonRecordedHours(Object nonRecordedHours) {
		this.nonRecordedHours = nonRecordedHours;
	}

	public void setPendingHours(Object pendingHours) {
		this.pendingHours = pendingHours;
	}

	public void setBillableNonIOHours(Object billableNonIOHours) {
		this.billableNonIOHours = billableNonIOHours;
	}

	public void setgTTIOHours(Object gTTIOHours) {
		this.gTTIOHours = gTTIOHours;
	}

	public void setNonGTTIOHours(Object nonGTTIOHours) {
		this.nonGTTIOHours = nonGTTIOHours;
	}

	public void setUtilization(Object utilization) {
		this.utilization = utilization;
	}

	public void setUtilizationNonIO(Object utilizationNonIO) {
		this.utilizationNonIO = utilizationNonIO;
	}

	public void setUtilizationSlab(Object utilizationSlab) {
		this.utilizationSlab = utilizationSlab;
	}

	public void setTechnicalDateOfEntry(Object technicalDateOfEntry) {
		this.technicalDateOfEntry = technicalDateOfEntry;
	}

	public void setMonthOfJoining(Object monthOfJoining) {
		this.monthOfJoining = monthOfJoining;
	}

	public void setAgeInOrg(Object ageInOrg) {
		this.ageInOrg = ageInOrg;
	}

	public void setPersonnelSubarea(Object personnelSubarea) {
		this.personnelSubarea = personnelSubarea;
	}

	public void setCostctr(Object costctr) {
		this.costctr = costctr;
	}

	public void setOrgUnit(Object orgUnit) {
		this.orgUnit = orgUnit;
	}

	public void setOrganizationalUnitDesc(Object organizationalUnitDesc) {
		this.organizationalUnitDesc = organizationalUnitDesc;
	}

	public void setOrgUnitShort(Object orgUnitShort) {
		this.orgUnitShort = orgUnitShort;
	}

	public void setNameOfSuperiorOM(Object nameOfSuperiorOM) {
		this.nameOfSuperiorOM = nameOfSuperiorOM;
	}

	public void setSupervisorPersonalNo(Object supervisorPersonalNo) {
		this.supervisorPersonalNo = supervisorPersonalNo;
	}

	public void setPositionId(Object positionId) {
		this.positionId = positionId;
	}

	public void setPositionName(Object positionName) {
		this.positionName = positionName;
	}

	public void setJobOM(Object jobOM) {
		this.jobOM = jobOM;
	}

	public void setJobNameOM(Object jobNameOM) {
		this.jobNameOM = jobNameOM;
	}

	public void setJobAreaAbbreviationOM(Object jobAreaAbbreviationOM) {
		this.jobAreaAbbreviationOM = jobAreaAbbreviationOM;
	}

	public void setJobFamilyNameOM(Object jobFamilyNameOM) {
		this.jobFamilyNameOM = jobFamilyNameOM;
	}

	public void setJobFamilyAbbreviationOM(Object jobFamilyAbbreviationOM) {
		this.jobFamilyAbbreviationOM = jobFamilyAbbreviationOM;
	}

	public void setGenderKey(Object genderKey) {
		this.genderKey = genderKey;
	}

	public void setCorporateID(Object corporateID) {
		this.corporateID = corporateID;
	}

	public void setEricssonEmailAddress(Object ericssonEmailAddress) {
		this.ericssonEmailAddress = ericssonEmailAddress;
	}

	public void setpERSONID_EXT(Object pERSONID_EXT) {
		this.pERSONID_EXT = pERSONID_EXT;
	}

	public void setPayScaleType(Object payScaleType) {
		this.payScaleType = payScaleType;
	}

	public void setJobAreaNameOM(Object jobAreaNameOM) {
		this.jobAreaNameOM = jobAreaNameOM;
	}

	public void setGlobalJobStage(Object globalJobStage) {
		this.globalJobStage = globalJobStage;
	}

	public void setEmployeeSubgroup(Object employeeSubgroup) {
		this.employeeSubgroup = employeeSubgroup;
	}

	public void setBillableNonBillable(Object billableNonBillable) {
		this.billableNonBillable = billableNonBillable;
	}

	public void setEriproCD(Object eriproCD) {
		this.eriproCD = eriproCD;
	}

	public void setEriproCDId(Object eriproCDId) {
		this.eriproCDId = eriproCDId;
	}

	public void setEriproSubCD(Object eriproSubCD) {
		this.eriproSubCD = eriproSubCD;
	}

	public void setEriproAccount(Object eriproAccount) {
		this.eriproAccount = eriproAccount;
	}

	public void setLogicalGrouping(Object logicalGrouping) {
		this.logicalGrouping = logicalGrouping;
	}

	public void setEriproNonDeployableRole(Object eriproNonDeployableRole) {
		this.eriproNonDeployableRole = eriproNonDeployableRole;
	}

	public void setIsLineManager(Object isLineManager) {
		this.isLineManager = isLineManager;
	}

	public void setIsProjectManager(Object isProjectManager) {
		this.isProjectManager = isProjectManager;
	}

	public void setIsHosted(Object isHosted) {
		this.isHosted = isHosted;
	}

	public void setlMSpan(Object lMSpan) {
		this.lMSpan = lMSpan;
	}

	public void setNonDeplStartDate(Object nonDeplStartDate) {
		this.nonDeplStartDate = nonDeplStartDate;
	}

	public void setNonDeplEndDate(Object nonDeplEndDate) {
		this.nonDeplEndDate = nonDeplEndDate;
	}

	public void setPasPns(Object pasPns) {
		this.pasPns = pasPns;
	}

	public void setPoolName(Object poolName) {
		this.poolName = poolName;
	}

	public void setNonDeployableFlag(Object nonDeployableFlag) {
		this.nonDeployableFlag = nonDeployableFlag;
	}

	public void setfAFuncArea(Object fAFuncArea) {
		this.fAFuncArea = fAFuncArea;
	}

	public void setEmployeeGroup(Object employeeGroup) {
		this.employeeGroup = employeeGroup;
	}

	public void setUtilizationreviousMonth1(Object utilizationreviousMonth1) {
		this.utilizationreviousMonth1 = utilizationreviousMonth1;
	}

	public void setUtilizationreviousMonth2(Object utilizationreviousMonth2) {
		this.utilizationreviousMonth2 = utilizationreviousMonth2;
	}

	public void setUtilizationreviousMonth3(Object utilizationreviousMonth3) {
		this.utilizationreviousMonth3 = utilizationreviousMonth3;
	}

	public void setUtilizationreviousMonth4(Object utilizationreviousMonth4) {
		this.utilizationreviousMonth4 = utilizationreviousMonth4;
	}

	public void setUtilizationreviousMonth5(Object utilizationreviousMonth5) {
		this.utilizationreviousMonth5 = utilizationreviousMonth5;
	}

	public void setUtilizationreviousMonth6(Object utilizationreviousMonth6) {
		this.utilizationreviousMonth6 = utilizationreviousMonth6;
	}

	public void setBenchClassification(Object benchClassification) {
		this.benchClassification = benchClassification;
	}

	public void setBillabilityHours(Object billabilityHours) {
		this.billabilityHours = billabilityHours;
	}
	
	
	
	

	@Override
	public String toString() {
		return "ResourceUtilizationBaseData [month=" + month + ", quarter=" + quarter + ", persNo=" + persNo
				+ ", empId=" + empId + ", empName=" + empName + ", employeeStatus=" + employeeStatus
				+ ", cATCTargetHours=" + cATCTargetHours + ", cATCRecordedHours=" + cATCRecordedHours + ", targetHours="
				+ targetHours + ", recordedHours=" + recordedHours + ", totalBillableHours=" + totalBillableHours
				+ ", nonRecordedHours=" + nonRecordedHours + ", pendingHours=" + pendingHours + ", billableNonIOHours="
				+ billableNonIOHours + ", gTTIOHours=" + gTTIOHours + ", nonGTTIOHours=" + nonGTTIOHours
				+ ", utilization=" + utilization + ", utilizationNonIO=" + utilizationNonIO + ", utilizationSlab="
				+ utilizationSlab + ", technicalDateOfEntry=" + technicalDateOfEntry + ", monthOfJoining="
				+ monthOfJoining + ", ageInOrg=" + ageInOrg + ", personnelSubarea=" + personnelSubarea + ", costctr="
				+ costctr + ", orgUnit=" + orgUnit + ", organizationalUnitDesc=" + organizationalUnitDesc
				+ ", orgUnitShort=" + orgUnitShort + ", nameOfSuperiorOM=" + nameOfSuperiorOM
				+ ", supervisorPersonalNo=" + supervisorPersonalNo + ", positionId=" + positionId + ", positionName="
				+ positionName + ", jobOM=" + jobOM + ", jobNameOM=" + jobNameOM + ", jobAreaAbbreviationOM="
				+ jobAreaAbbreviationOM + ", jobFamilyNameOM=" + jobFamilyNameOM + ", jobFamilyAbbreviationOM="
				+ jobFamilyAbbreviationOM + ", genderKey=" + genderKey + ", corporateID=" + corporateID
				+ ", ericssonEmailAddress=" + ericssonEmailAddress + ", pERSONID_EXT=" + pERSONID_EXT
				+ ", payScaleType=" + payScaleType + ", jobAreaNameOM=" + jobAreaNameOM + ", globalJobStage="
				+ globalJobStage + ", employeeSubgroup=" + employeeSubgroup + ", billableNonBillable="
				+ billableNonBillable + ", eriproCD=" + eriproCD + ", eriproCDId=" + eriproCDId + ", eriproSubCD="
				+ eriproSubCD + ", eriproAccount=" + eriproAccount + ", logicalGrouping=" + logicalGrouping
				+ ", eriproNonDeployableRole=" + eriproNonDeployableRole + ", isLineManager=" + isLineManager
				+ ", isProjectManager=" + isProjectManager + ", isHosted=" + isHosted + ", lMSpan=" + lMSpan
				+ ", nonDeplStartDate=" + nonDeplStartDate + ", nonDeplEndDate=" + nonDeplEndDate + ", pasPns=" + pasPns
				+ ", poolName=" + poolName + ", nonDeployableFlag=" + nonDeployableFlag + ", fAFuncArea=" + fAFuncArea
				+ ", employeeGroup=" + employeeGroup + ", utilizationreviousMonth1=" + utilizationreviousMonth1
				+ ", utilizationreviousMonth2=" + utilizationreviousMonth2 + ", utilizationreviousMonth3="
				+ utilizationreviousMonth3 + ", utilizationreviousMonth4=" + utilizationreviousMonth4
				+ ", utilizationreviousMonth5=" + utilizationreviousMonth5 + ", utilizationreviousMonth6="
				+ utilizationreviousMonth6 + ", benchClassification=" + benchClassification + ", billabilityHours="
				+ billabilityHours + "]";
	}

	
	// getter 
	public String getId() {
		return id;
	}

	public String getMonth() {
		return month;
	}

	public Object getQuarter() {
		return quarter;
	}

	public Object getPersNo() {
		return persNo;
	}

	public Object getEmpId() {
		return empId;
	}

	public Object getEmpName() {
		return empName;
	}

	public Object getEmployeeStatus() {
		return employeeStatus;
	}

	public Object getcATCTargetHours() {
		return cATCTargetHours;
	}

	public Object getcATCRecordedHours() {
		return cATCRecordedHours;
	}

	public Object getTargetHours() {
		return targetHours;
	}

	public Object getRecordedHours() {
		return recordedHours;
	}

	public Object getTotalBillableHours() {
		return totalBillableHours;
	}

	public Object getNonRecordedHours() {
		return nonRecordedHours;
	}

	public Object getPendingHours() {
		return pendingHours;
	}

	public Object getBillableNonIOHours() {
		return billableNonIOHours;
	}

	public Object getgTTIOHours() {
		return gTTIOHours;
	}

	public Object getNonGTTIOHours() {
		return nonGTTIOHours;
	}

	public Object getUtilization() {
		return utilization;
	}

	public Object getUtilizationNonIO() {
		return utilizationNonIO;
	}

	public Object getUtilizationSlab() {
		return utilizationSlab;
	}

	public Object getTechnicalDateOfEntry() {
		return technicalDateOfEntry;
	}

	public Object getMonthOfJoining() {
		return monthOfJoining;
	}

	public Object getAgeInOrg() {
		return ageInOrg;
	}

	public Object getPersonnelSubarea() {
		return personnelSubarea;
	}

	public Object getCostctr() {
		return costctr;
	}

	public Object getOrgUnit() {
		return orgUnit;
	}

	public Object getOrganizationalUnitDesc() {
		return organizationalUnitDesc;
	}

	public Object getOrgUnitShort() {
		return orgUnitShort;
	}

	public Object getNameOfSuperiorOM() {
		return nameOfSuperiorOM;
	}

	public Object getSupervisorPersonalNo() {
		return supervisorPersonalNo;
	}

	public Object getPositionId() {
		return positionId;
	}

	public Object getPositionName() {
		return positionName;
	}

	public Object getJobOM() {
		return jobOM;
	}

	public Object getJobNameOM() {
		return jobNameOM;
	}

	public Object getJobAreaAbbreviationOM() {
		return jobAreaAbbreviationOM;
	}

	public Object getJobFamilyNameOM() {
		return jobFamilyNameOM;
	}

	public Object getJobFamilyAbbreviationOM() {
		return jobFamilyAbbreviationOM;
	}

	public Object getGenderKey() {
		return genderKey;
	}

	public Object getCorporateID() {
		return corporateID;
	}

	public Object getEricssonEmailAddress() {
		return ericssonEmailAddress;
	}

	public Object getpERSONID_EXT() {
		return pERSONID_EXT;
	}

	public Object getPayScaleType() {
		return payScaleType;
	}

	public Object getJobAreaNameOM() {
		return jobAreaNameOM;
	}

	public Object getGlobalJobStage() {
		return globalJobStage;
	}

	public Object getEmployeeSubgroup() {
		return employeeSubgroup;
	}

	public Object getBillableNonBillable() {
		return billableNonBillable;
	}

	public Object getEriproCD() {
		return eriproCD;
	}

	public Object getEriproCDId() {
		return eriproCDId;
	}

	public Object getEriproSubCD() {
		return eriproSubCD;
	}

	public Object getEriproAccount() {
		return eriproAccount;
	}

	public Object getLogicalGrouping() {
		return logicalGrouping;
	}

	public Object getEriproNonDeployableRole() {
		return eriproNonDeployableRole;
	}

	public Object getIsLineManager() {
		return isLineManager;
	}

	public Object getIsProjectManager() {
		return isProjectManager;
	}

	public Object getIsHosted() {
		return isHosted;
	}

	public Object getlMSpan() {
		return lMSpan;
	}

	public Object getNonDeplStartDate() {
		return nonDeplStartDate;
	}

	public Object getNonDeplEndDate() {
		return nonDeplEndDate;
	}

	public Object getPasPns() {
		return pasPns;
	}

	public Object getPoolName() {
		return poolName;
	}

	public Object getNonDeployableFlag() {
		return nonDeployableFlag;
	}

	public Object getfAFuncArea() {
		return fAFuncArea;
	}

	public Object getEmployeeGroup() {
		return employeeGroup;
	}

	public Object getUtilizationreviousMonth1() {
		return utilizationreviousMonth1;
	}

	public Object getUtilizationreviousMonth2() {
		return utilizationreviousMonth2;
	}

	public Object getUtilizationreviousMonth3() {
		return utilizationreviousMonth3;
	}

	public Object getUtilizationreviousMonth4() {
		return utilizationreviousMonth4;
	}

	public Object getUtilizationreviousMonth5() {
		return utilizationreviousMonth5;
	}

	public Object getUtilizationreviousMonth6() {
		return utilizationreviousMonth6;
	}

	public Object getBenchClassification() {
		return benchClassification;
	}

	public Object getBillabilityHours() {
		return billabilityHours;
	}
	
	
	
}
