package com.ericsson.v1.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ericsson.v1.model.MonthCdKey;
import com.ericsson.v1.model.MonthSubCdKey;
import com.ericsson.v1.model.ResourceUtilizationBaseData;
import com.ericsson.v1.model.SubCdTypeMonthPercentageDTO;
import com.ericsson.v1.service.ResourceUtilizationParserService;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;

public class ApplicationUtil {

	
	public DataTable generateColumnChartData(String subCdType, ResourceUtilizationParserService resourceUtilizationParserService) throws Exception {
			
		DataTable data = new DataTable();
		ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
		
		cd.add(new ColumnDescription("Month/Year", ValueType.TEXT, "Month/Year"));
		cd.add(new ColumnDescription("targetHours", ValueType.NUMBER, "Target Hours"));
		cd.add(new ColumnDescription("recordedHours", ValueType.NUMBER, "Recorded Hours"));
		
		data.addColumns(cd);
		
		Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap = 
				resourceUtilizationParserService.groupResourceUtilizationBaseDataByEriproSubCD(getFilteredBaseData(resourceUtilizationParserService));
		
		Map<MonthSubCdKey, Map<String, Double>>  monthSubCdSubCdTypeKeyMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByMonth(eriproSubCDGroupMap);
		
		Map<String, Map<String, Double>> monthAndSubCdTypeMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByMonthAndSubCdType(monthSubCdSubCdTypeKeyMap, subCdType);

		System.out.println("monthAndSubCdTypeMap : "+monthAndSubCdTypeMap);
		
		List<String> list = new ArrayList<String>(monthAndSubCdTypeMap.keySet());
		
		Collections.sort(list);
		for(String month : list) {
			Map<String, Double> targetRecordedHoursMap = monthAndSubCdTypeMap.get(month);
			data.addRowFromValues(month, targetRecordedHoursMap.get("targetHours"), targetRecordedHoursMap.get("recordedHours"));
		}
		
		return data;
	}
	
	
	public List<SubCdTypeMonthPercentageDTO> groupPercentageResourceUtilizationBaseDataByMonth(Map<MonthSubCdKey, Map<String, Double>> monthSubCdSubCdTypeKeyMap,
			ResourceUtilizationParserService resourceUtilizationParserService) {
		List<SubCdTypeMonthPercentageDTO> cdTypeMonthPercentageDTOs = new ArrayList<SubCdTypeMonthPercentageDTO>();
		String value = PropertyUtil.getInstance().getValue("eripro.sub.cd.types");
		String[] eripro_sub_cd_types= value.split(",");
		List<String> eripro_sub_cd_typesList = Arrays.asList(eripro_sub_cd_types);
		Collections.sort(eripro_sub_cd_typesList);
		for(String val : eripro_sub_cd_typesList) {
			Map<String, Map<String, Double>> monthAndSubCdTypeMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByMonthAndSubCdType(monthSubCdSubCdTypeKeyMap, val);
			Map<String, Double> monthSubCdTypeMap = resourceUtilizationParserService.groupPercentageResourceUtilizationBaseDataByMonthAndSubCdType(monthAndSubCdTypeMap);
			List<String> monthList = new ArrayList<String>(monthSubCdTypeMap.keySet());
			Collections.sort(monthList);
			for(String month : monthList) {
				Double percentageVal = monthSubCdTypeMap.get(month);
				SubCdTypeMonthPercentageDTO cdTypeDTO = new SubCdTypeMonthPercentageDTO();
				cdTypeDTO.setMonth(month);
				cdTypeDTO.setPercentage(percentageVal);
				cdTypeDTO.setSubCdType(val);
				cdTypeDTO.setTargetHours(monthAndSubCdTypeMap.get(month).get("targetHours"));
				cdTypeDTO.setRecordedHours(monthAndSubCdTypeMap.get(month).get("recordedHours"));
				cdTypeMonthPercentageDTOs.add(cdTypeDTO);
			}
		}
		return cdTypeMonthPercentageDTOs;
	}
	
	
	private List<ResourceUtilizationBaseData> getFilteredBaseData(ResourceUtilizationParserService resourceUtilizationParserService) throws Exception { 
		String excelFilePath = getFileName();
		List<ResourceUtilizationBaseData> baseDatas = resourceUtilizationParserService.parse(excelFilePath);
		
		List<ResourceUtilizationBaseData> filteredBaseDatas = resourceUtilizationParserService.filterDataRNAMCAC(baseDatas);
		
		return filteredBaseDatas;
	}
	
	
	public Map<String, List<ResourceUtilizationBaseData>> sortedData(Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap) {
		Map<String, List<ResourceUtilizationBaseData>> eriproSubCDSortedGroupMap = new LinkedHashMap<String, List<ResourceUtilizationBaseData>>();
		Set<String> eriproSubCDs =  eriproSubCDGroupMap.keySet();
		
		List<String> eriproSubCDList = new ArrayList<String>(eriproSubCDs);
		Collections.sort(eriproSubCDList);
		
		
		for(String eriproSubCD : eriproSubCDList) {
			if(StringUtils.isNotBlank(eriproSubCD)) {
				List<ResourceUtilizationBaseData> baseDatas = eriproSubCDGroupMap.get(eriproSubCD);
				
				Comparator<ResourceUtilizationBaseData> comparator = new Comparator<ResourceUtilizationBaseData>() {
					public int compare(ResourceUtilizationBaseData o1, ResourceUtilizationBaseData o2) {
						return o1.getMonth().compareTo(o2.getMonth());
					}
				};
				
				Collections.sort(baseDatas, comparator);
				eriproSubCDSortedGroupMap.put(eriproSubCD, baseDatas);
			}
		}
		return eriproSubCDSortedGroupMap;
	}
	
	public List<MonthSubCdKey> sortedByMonthData(Set<MonthSubCdKey> monthSubCdKeys) {
		List<MonthSubCdKey> eriproSortedMonthList = new ArrayList<MonthSubCdKey>(monthSubCdKeys);
		
		Comparator<MonthSubCdKey> comparator = new Comparator<MonthSubCdKey>() {
			public int compare(MonthSubCdKey o1, MonthSubCdKey o2) {
				return o1.getMonth().compareTo(o2.getMonth());
			}
		};
		Collections.sort(eriproSortedMonthList, comparator);
		return eriproSortedMonthList;
	}
	
	
	public Map<MonthCdKey, Map<String, Double>> getSubCdWiseTargetRecortedHours(Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap) {
		
		Map<MonthCdKey, Map<String, Double>> monthCdKeyKeyMap = new LinkedHashMap<MonthCdKey, Map<String, Double>>();
		List<String> eriproSubCDList = new ArrayList<String>(eriproSubCDGroupMap.keySet());
		Collections.sort(eriproSubCDList);
		for(String subCd : eriproSubCDList) {
			if(StringUtils.isNotBlank(subCd)) {
				List<ResourceUtilizationBaseData> list = eriproSubCDGroupMap.get(subCd);
				for(ResourceUtilizationBaseData data : list) {
					if(data.getEriproSubCD() != null && String.valueOf(data.getEriproSubCD()) != null && !"".equals(String.valueOf(data.getEriproSubCD()).trim())) {
						MonthCdKey key = new MonthCdKey();
						key.setDomain(subCd);
						key.setMonth(data.getMonth());
						Double targetHours = (Double)data.getTargetHours();
						Double recordedHours = (Double)data.getTotalBillableHours();
						if(monthCdKeyKeyMap.get(key) != null) {
							Map<String, Double> targetRecordedHoursMap = monthCdKeyKeyMap.get(key);
							Double targetHoursD = targetRecordedHoursMap.get("targetHours") + targetHours;
							Double recordedHoursD = targetRecordedHoursMap.get("recordedHours") + recordedHours;
							targetRecordedHoursMap.put("targetHours", targetHoursD);
							targetRecordedHoursMap.put("recordedHours", recordedHoursD);
						} else {
							Map<String, Double> map = new HashMap<String, Double>();
							map.put("targetHours", targetHours);
							map.put("recordedHours", recordedHours);
							monthCdKeyKeyMap.put(key, map);
						}
					}
				}
			}
		}
		return monthCdKeyKeyMap;
		
		
	}
	
	public String getFileName() {
		String excelFilePath = PropertyUtil.getInstance().getValue("excelFilePath");
				//"C:\\Apps\\ericssonInternalApps\\utilizationApp\\src\\main\\resources\\Utilization Analysis.xls";
		System.out.println("excelFilePath :"+excelFilePath);
		return excelFilePath;
	}
}
