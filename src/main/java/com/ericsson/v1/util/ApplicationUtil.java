package com.ericsson.v1.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
		for(String val : eripro_sub_cd_types) {
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
	
	public String getFileName() {
		String excelFilePath = PropertyUtil.getInstance().getValue("excelFilePath");
				//"C:\\Apps\\ericssonInternalApps\\utilizationApp\\src\\main\\resources\\Utilization Analysis.xls";
		System.out.println("excelFilePath :"+excelFilePath);
		return excelFilePath;
	}
}
