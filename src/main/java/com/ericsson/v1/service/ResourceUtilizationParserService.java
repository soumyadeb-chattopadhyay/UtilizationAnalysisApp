package com.ericsson.v1.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ericsson.v1.model.JobStageDTO;
import com.ericsson.v1.model.MonthCdKey;
import com.ericsson.v1.model.MonthSubCdKey;
import com.ericsson.v1.model.MonthSubCdTypeDTO;
import com.ericsson.v1.model.ResourceUtilizationBaseData;
import com.ericsson.v1.repository.ResourceUtilizationParserServiceRepository;
import com.ericsson.v1.util.ApplicationUtil;
import com.ericsson.v1.util.PropertyUtil;

@Service
public class ResourceUtilizationParserService {
	
	 //private static final Logger log = Logger.getLogger(ResourceUtilizationParserService.class);
	
	//@Autowired
	private ResourceUtilizationParserServiceRepository utilizationParserServiceRepository;
	
	public void persist(List<ResourceUtilizationBaseData> baseDatas) {
		for(ResourceUtilizationBaseData baseData : baseDatas) {
			if(baseData != null && baseData.getCorporateID() !=null && !"".equals(baseData.getCorporateID().toString().trim())) {
				//System.out.println(baseData.getCorporateID());
				//utilizationParserServiceRepository.save(baseData);
			}
			
		}
		
	}
	/* returns a list of ResourceUtilizationBaseData objects returned by readResourceUtilizationBaseDataFromExcelFile() after reading from the excel file in the given path  */
	public List<ResourceUtilizationBaseData> parse(String excelFilePath) throws Exception {
		return readResourceUtilizationBaseDataFromExcelFile(excelFilePath);
	}
	/* We are checking from the list of ResourceUtilizationBaseData obtained from the excel sheet whether Pool Name is "RNAM_CAC".If yes change eri pro subCD for that ResourceUtilizationBaseData object to "RNAM_CAC".Add these object to the list of filtered base data and return the list */
	public List<ResourceUtilizationBaseData> filterDataRNAMCAC(List<ResourceUtilizationBaseData> baseDatas) {
		 List<ResourceUtilizationBaseData> filteredBaseDatas = new ArrayList<ResourceUtilizationBaseData>();
		 for(ResourceUtilizationBaseData data : baseDatas) {
			 Object poolName = data.getPoolName();
			
			 if(poolName != null) {
				 String cdPoolName = String.valueOf(data.getPoolName());
				 if(StringUtils.isNotBlank(cdPoolName) && cdPoolName.equalsIgnoreCase("RNAM_CAC")) {
					 data.setEriproSubCD("RNAM_CAC");
					 //System.out.println("poolName "+poolName);
				 }
			 }
			 filteredBaseDatas.add(data);
		 }
		 return filteredBaseDatas;
	}
	
	/*  Grouping data by mapping list of ResourceUtilizationBaseData objects against any of the 5 SubCD types.We are fetching the eriproSubCd from the base data 
	 *list and mapping it against the sub cd type from the map generated through another function gropuByEriproSubCDAndSubCdTypeMap()
	 * Once we are getting the epri pro sub cd type for an entity we are grouping base data as per the subCD type    */
	public Map<String, List<ResourceUtilizationBaseData>> groupResourceUtilizationBaseDataByEriproSubCD(List<ResourceUtilizationBaseData> baseDatas) {
		Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap = new LinkedHashMap<String, List<ResourceUtilizationBaseData>>();
		Map<String, String> eriproSubCDTypeMap = gropuByEriproSubCDAndSubCdTypeMap();
		System.out.println(eriproSubCDTypeMap);
		for(ResourceUtilizationBaseData data : baseDatas) {
			String eriproSubCD = String.valueOf(data.getEriproSubCD());
			//System.out.println(eriproSubCD);
			if(StringUtils.isNotBlank(eriproSubCD)) {
				String eriproSubCdType = eriproSubCDTypeMap.get(eriproSubCD);
				if(StringUtils.isNotBlank(eriproSubCdType)) {
					if(eriproSubCDGroupMap.get(eriproSubCdType) != null) {
						eriproSubCDGroupMap.get(eriproSubCdType).add(data);
					} else {
						List<ResourceUtilizationBaseData> list = new ArrayList<ResourceUtilizationBaseData>();
						list.add(data);
						eriproSubCDGroupMap.put(eriproSubCdType, list);
					}
				}
			}
		}
		//System.out.println("eriproSubCDGroupMap : "+eriproSubCDGroupMap);
		ApplicationUtil applicationUtil = new ApplicationUtil();
		Map<String, List<ResourceUtilizationBaseData>> eriproSubCDSortedGroupMap = applicationUtil.sortedData(eriproSubCDGroupMap);
		return eriproSubCDSortedGroupMap;
	}
	
	public Map<String, Map<String, Double>> groupResourceUtilizationBaseDataByYear(Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap) {
		Map<String, Map<String, Double>> eriproSubCDGroupTypeMap = new HashMap<String, Map<String, Double>>();
		Set<String> list = eriproSubCDGroupMap.keySet();
		for(String key : list) {
			if(StringUtils.isNotBlank(key)) {
				List<ResourceUtilizationBaseData> datas = eriproSubCDGroupMap.get(key);
				for(ResourceUtilizationBaseData data : datas) {
					Double targetHours = (Double)data.getTargetHours();
					Double recordedHours = (Double)data.getTotalBillableHours();
					if(eriproSubCDGroupTypeMap.get(key) != null) {
						Map<String, Double> targetRecordedHoursMap = eriproSubCDGroupTypeMap.get(key);
						Double targetHoursD = targetRecordedHoursMap.get("targetHours") + targetHours;
						Double recordedHoursD = targetRecordedHoursMap.get("recordedHours") + recordedHours;
						targetRecordedHoursMap.put("targetHours", targetHoursD);
						targetRecordedHoursMap.put("recordedHours", recordedHoursD);
					} else {
						Map<String, Double> map = new HashMap<String, Double>();
						map.put("targetHours", targetHours);
						map.put("recordedHours", recordedHours);
						eriproSubCDGroupTypeMap.put(key, map);
					}
				}
			}
		}
		return eriproSubCDGroupTypeMap;
	}
	
	
	public Map<MonthSubCdKey, Map<String, Double>> groupResourceUtilizationBaseDataByMonth(Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap) {
		
		Map<MonthSubCdKey, Map<String, Double>> monthSubCdSubCdTypeKeyMap = new LinkedHashMap<MonthSubCdKey, Map<String, Double>>();
		Map<String, String> eriproSubCDAndSubCdTypeMap =  gropuByEriproSubCDAndSubCdTypeMap();
		List<String> eriproSubCDList = new ArrayList<String>(eriproSubCDGroupMap.keySet());
		Collections.sort(eriproSubCDList);
		for(String subCd : eriproSubCDList) {
			if(StringUtils.isNotBlank(subCd)) {
				List<ResourceUtilizationBaseData> list = eriproSubCDGroupMap.get(subCd);
				for(ResourceUtilizationBaseData data : list) {
					if(data.getEriproSubCD() != null && String.valueOf(data.getEriproSubCD()) != null && !"".equals(String.valueOf(data.getEriproSubCD()).trim())) {
						MonthSubCdKey key = new MonthSubCdKey(eriproSubCDAndSubCdTypeMap.get(String.valueOf(data.getEriproSubCD())), data.getMonth(), String.valueOf(data.getEriproSubCD()));
						Double targetHours = (Double)data.getTargetHours();
						Double recordedHours = (Double)data.getTotalBillableHours();
						if(monthSubCdSubCdTypeKeyMap.get(key) != null) {
							Map<String, Double> targetRecordedHoursMap = monthSubCdSubCdTypeKeyMap.get(key);
							Double targetHoursD = targetRecordedHoursMap.get("targetHours") + targetHours;
							Double recordedHoursD = targetRecordedHoursMap.get("recordedHours") + recordedHours;
							targetRecordedHoursMap.put("targetHours", targetHoursD);
							targetRecordedHoursMap.put("recordedHours", recordedHoursD);
						} else {
							Map<String, Double> map = new HashMap<String, Double>();
							map.put("targetHours", targetHours);
							map.put("recordedHours", recordedHours);
							monthSubCdSubCdTypeKeyMap.put(key, map);
						}
					}
				}
			}
		}
		return monthSubCdSubCdTypeKeyMap;
	}
	
	public Map<String, Map<String, Double>> groupResourceUtilizationBaseDataByMonthAndSubCdType(Map<MonthSubCdKey, Map<String, Double>> monthSubCdSubCdTypeKeyMap, String subCd) {
		
		Map<String, Map<String, Double>> monthAndSubCdTypeMap = new LinkedHashMap<String, Map<String, Double>>();
		ApplicationUtil applicationUtil = new ApplicationUtil();
		List<MonthSubCdKey> monthSubCdSubCdTypeSortedKeyList = applicationUtil.sortedByMonthData(monthSubCdSubCdTypeKeyMap.keySet());
		for(MonthSubCdKey MonthSubCdKey : monthSubCdSubCdTypeSortedKeyList) {
			if(subCd.equalsIgnoreCase(MonthSubCdKey.getSubCdType())) {
				//MonthSubCdTypeKey key = new MonthSubCdTypeKey(MonthSubCdKey.getMonth(), MonthSubCdKey.getSubCd());
				Map<String, Double> targetRecordedHoursSubCdMap = monthSubCdSubCdTypeKeyMap.get(MonthSubCdKey);
				if(monthAndSubCdTypeMap.get(MonthSubCdKey.getMonth()) != null) { 
					Map<String, Double> targetRecordedHoursMap = monthAndSubCdTypeMap.get(MonthSubCdKey.getMonth());
					Double targetHoursD = targetRecordedHoursMap.get("targetHours") + targetRecordedHoursSubCdMap.get("targetHours");
					Double recordedHoursD = targetRecordedHoursMap.get("recordedHours") + targetRecordedHoursSubCdMap.get("recordedHours");
					targetRecordedHoursMap.put("targetHours", targetHoursD);
					targetRecordedHoursMap.put("recordedHours", recordedHoursD);
				} else {
					Map<String, Double> map = new HashMap<String, Double>();
					map.put("targetHours", targetRecordedHoursSubCdMap.get("targetHours"));
					map.put("recordedHours", targetRecordedHoursSubCdMap.get("recordedHours"));
					monthAndSubCdTypeMap.put(MonthSubCdKey.getMonth(), map);
				}
			}
			
		}
		
		return monthAndSubCdTypeMap;
	}
	
	public Map<String, Double> groupPercentageResourceUtilizationBaseDataByMonthAndSubCdType(Map<String, Map<String, Double>> monthAndSubCdTypeMap) {
		Map<String, Double> monthSubCdTypeMap = new HashMap<String, Double>();
		
		for(String month : monthAndSubCdTypeMap.keySet()) {
			Map<String, Double> map = monthAndSubCdTypeMap.get(month);
			//System.out.println(map.get("recordedHours"));
			//System.out.println(map.get("targetHours"));
			Double percentage = ((map.get("recordedHours"))/(map.get("targetHours")))*100;
			//System.out.println("result : "+percentage);
			monthSubCdTypeMap.put(month, percentage);
		}
		return monthSubCdTypeMap;
	}
	
	
	public List<MonthSubCdTypeDTO> getMonthTargetRecordedHoursSubCdMapWiseBySubCd(Map<MonthSubCdKey, Map<String, Double>> monthSubCdSubCdTypeKeyMap, String subCd) {
		List<MonthSubCdTypeDTO> cdTypeDTOs = new ArrayList<MonthSubCdTypeDTO>();
		Map<String, Map<String, Double>> monthAndSubCdTypeMap = groupResourceUtilizationBaseDataByMonthAndSubCdType(monthSubCdSubCdTypeKeyMap, subCd);
		for(String month : monthAndSubCdTypeMap.keySet()) {
			Map<String, Double> targetRecordedHoursMap = monthAndSubCdTypeMap.get(month);
			MonthSubCdTypeDTO cdTypeDTO = new MonthSubCdTypeDTO(month, targetRecordedHoursMap.get("targetHours"), targetRecordedHoursMap.get("recordedHours"));
			cdTypeDTOs.add(cdTypeDTO);
		}
		return cdTypeDTOs;
	}
	
	public List<MonthSubCdTypeDTO> getUtilizationDetails(Map<MonthSubCdKey, Map<String, Double>> monthSubCdSubCdTypeKeyMap) {
		List<MonthSubCdTypeDTO> cdTypeDTOs = new ArrayList<MonthSubCdTypeDTO>();
		for(MonthSubCdKey monthSubCdKey : monthSubCdSubCdTypeKeyMap.keySet()) {
			Map<String, Double> targetRecordedHoursMap = monthSubCdSubCdTypeKeyMap.get(monthSubCdKey);
			MonthSubCdTypeDTO cdTypeDTO = new MonthSubCdTypeDTO(monthSubCdKey.getMonth(), targetRecordedHoursMap.get("targetHours"), targetRecordedHoursMap.get("recordedHours"));
			cdTypeDTO.setSubCd(monthSubCdKey.getSubCd());
			cdTypeDTO.setSubCdType(monthSubCdKey.getSubCdType());
			cdTypeDTOs.add(cdTypeDTO);
		}
		return cdTypeDTOs;
	}
	
	
	
	/*  Here we are grouping different eripro subCDs of m&a into 
	 any of the 5 subCD types */

	public Map<String, String> gropuByEriproSubCDAndSubCdTypeMap() {
		Map<String, String> map = new HashMap<String, String>();
		String[] eripro_sub_cd_keys= {"eripro.sub.cd.SDP", "eripro.sub.cd.ECE-NGIN", "eripro.sub.cd.TV", "eripro.sub.cd.MSP", "eripro.sub.cd.RNAM", "eripro.sub.cd.MA"};
		for(String key : eripro_sub_cd_keys) {
			String value = PropertyUtil.getInstance().getValue(key);
			if(value.contains(",")) {
				String[] eripro_sub_cd_values = value.split(",");
				for(String val : eripro_sub_cd_values) {
					//System.out.println(val);
					String level = key+".label";
					//System.out.println(level);
					map.put(val, PropertyUtil.getInstance().getValue(level));
				}
			} else {
				map.put(value, PropertyUtil.getInstance().getValue(key+".label"));
			}
		}
		return map;
	}
	/* reading from excel file using apache poi Api */
	private List<ResourceUtilizationBaseData> readResourceUtilizationBaseDataFromExcelFile(String excelFilePath) throws IOException {
	    List<ResourceUtilizationBaseData> baseDatas = new ArrayList<ResourceUtilizationBaseData>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = getWorkbook(inputStream, excelFilePath);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	    //Header header = firstSheet.getHeader();
	    //System.out.println(header);
	    /*for(int i=0; i<70; i++) {
	    	 String cell = firstSheet.getRow(0).getCell(i).getRichStringCellValue().toString();
	    	 System.out.println("private String "+cell+";");
	    }*/
	   
	    while (iterator.hasNext()) {
	    	ResourceUtilizationBaseData baseData = new ResourceUtilizationBaseData();
	        Row nextRow = iterator.next();
	        int rowNo = nextRow.getRowNum();
	        if(rowNo == 0) {
            	System.out.println("rowNo  "+rowNo);
                continue; //just skip the rows if row number is 0 or 1
               }
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	       // ResourceUtilizationBaseData baseData = new ResourceUtilizationBaseData();
	 
	        while (cellIterator.hasNext()) {
	            Cell nextCell = cellIterator.next();
	            //int rowIndex = nextCell.getRow().getRowNum();
	            //System.out.println("rowIndex  "+rowIndex);
	           /* if(rowIndex == 0) {
	            	System.out.println("rowIndex  "+rowIndex);
	                continue; //just skip the rows if row number is 0 or 1
	               }*/
	            int columnIndex = nextCell.getColumnIndex();
	 
	            switch (columnIndex) {
	            case 0:
	            	//System.out.println("0 : "+getCellValue(nextCell));
	                //baseData.setMonth(getCellValue(nextCell));
	                if(DateUtil.isCellDateFormatted(nextCell)) {
	                	//System.out.println("----> "+nextCell.getDateCellValue());
	                	Calendar calendar =  DateUtil.getJavaCalendar((Double)getCellValue(nextCell), false);
	                	//System.out.println("----> "+calendar.get(Calendar.MONTH));
	                	baseData.setMonth(getMonthWithPrefix((calendar.get(Calendar.MONTH)+1))+ "/" +(calendar.get(Calendar.YEAR)));
	                }
	                
	                break;
	            case 1:
	            	//System.out.println("1 : "+getCellValue(nextCell));
	                baseData.setQuarter(getCellValue(nextCell));
	                break;
	            case 2:
	            	//System.out.println("2 : "+getCellValue(nextCell));
	                baseData.setPersNo((Double) getCellValue(nextCell));
	                break;
	            case 3:
	            	//System.out.println("3 : "+getCellValue(nextCell));
	                baseData.setEmpId((String) getCellValue(nextCell));
	                break;
	            case 4:
	            	//
	                baseData.setEmpName((String) getCellValue(nextCell));
	                break;
	            case 5:
	            	
	            	baseData.setEmployeeStatus((String) getCellValue(nextCell));
	            	break;
	            case 6:
	            	
	            	baseData.setcATCTargetHours((Double) getCellValue(nextCell));
	            	break;
	            case 7:
	            	
	            	baseData.setcATCRecordedHours((Double) getCellValue(nextCell));
	            	break;
	            case 8:
	            	
	            	baseData.setTargetHours((Double) getCellValue(nextCell));
	            	break;
	            case 9:
	            	
	            	baseData.setRecordedHours((Double) getCellValue(nextCell));
	            	break;
	            case 10:
	            	
	            	baseData.setTotalBillableHours((Double) getCellValue(nextCell));
	            	break;
	            case 11:
	            	
	            	baseData.setNonRecordedHours((Double) getCellValue(nextCell));
	            	break;
    
	            case 12:
	            	
	            	baseData.setPendingHours((Double) getCellValue(nextCell));
	            	break;
	            case 13:
	            	
	            	baseData.setBillableNonIOHours((Double) getCellValue(nextCell));
	            	break;
	            case 14:
	            	
	            	baseData.setgTTIOHours((Double) getCellValue(nextCell));
	            	break;
	            case 15:
	            	
	            	baseData.setNonGTTIOHours((Double) getCellValue(nextCell));
	            	break;
	            case 16:
	            	
	            	baseData.setUtilization((Double) getCellValue(nextCell));
	            	break;
	            case 17:
	            	
	            	baseData.setUtilizationNonIO((Double) getCellValue(nextCell));
	            	break;
	            case 18:
	            	baseData.setUtilizationSlab((Object) getCellValue(nextCell));
	            	break;
	            case 19:
	            	baseData.setTechnicalDateOfEntry((Object) getCellValue(nextCell));
	            	break;	
	            case 20:
	            	baseData.setMonthOfJoining((Object) getCellValue(nextCell));
	            	break;
	            case 21:
	            	baseData.setAgeInOrg((Object) getCellValue(nextCell));
	            	break;
	            case 22:
	            	baseData.setPersonnelSubarea((Object) getCellValue(nextCell));
	            	break;
	            case 23:
	            	baseData.setCostctr((Object) getCellValue(nextCell));
	            	break;
	            case 24:
	            	baseData.setOrgUnit((Object) getCellValue(nextCell));
	            	break;
	            case 25:
	            	baseData.setOrganizationalUnitDesc((Object) getCellValue(nextCell));
	            	break;
	            case 26:
	            	baseData.setOrgUnitShort((Object) getCellValue(nextCell));
	            	break;
	            case 27:
	            	baseData.setNameOfSuperiorOM((Object) getCellValue(nextCell));
	            	break;
	            case 28:
	            	baseData.setSupervisorPersonalNo((Object) getCellValue(nextCell));
	            	break;
	            case 29:
	            	baseData.setPositionId((Object) getCellValue(nextCell));
	            	break;
	            case 30:
	            	baseData.setPositionName((Object) getCellValue(nextCell));
	            	break;
	            case 31:
	            	baseData.setJobOM((Object) getCellValue(nextCell));
	            	break;
	            case 32:
	            	baseData.setJobNameOM((Object) getCellValue(nextCell));
	            	break;
	            case 33:
	            	baseData.setJobAreaAbbreviationOM((Object) getCellValue(nextCell));
	            	break;
	            case 34:
	            	baseData.setJobFamilyNameOM((Object) getCellValue(nextCell));
	            	break;
	            case 35:
	            	baseData.setJobFamilyAbbreviationOM((Object) getCellValue(nextCell));
	            	break;
	            case 36:
	            	baseData.setGenderKey((Object) getCellValue(nextCell));
	            	break;
	            case 37:
	            	baseData.setCorporateID((Object) getCellValue(nextCell));
	            	break;
	            case 38:
	            	baseData.setEricssonEmailAddress((Object) getCellValue(nextCell));
	            	break;
	            case 39:
	            	baseData.setpERSONID_EXT((Object) getCellValue(nextCell));
	            	break;
	            case 40:
	            	baseData.setPayScaleType((Object) getCellValue(nextCell));
	            	break;
	            case 41:
	            	baseData.setJobAreaNameOM((Object) getCellValue(nextCell));
	            	break;
	            case 42:
	            	baseData.setGlobalJobStage((Object) getCellValue(nextCell));
	            	break;
	            case 43:
	            	baseData.setEmployeeSubgroup((Object) getCellValue(nextCell));
	            	break;
	            case 44:
	            	baseData.setBillableNonBillable((Object) getCellValue(nextCell));
	            	break;
	            case 45:
	            	baseData.setEriproCD((Object) getCellValue(nextCell));
	            	break;
	            case 46:
	            	baseData.setEriproCDId((Object) getCellValue(nextCell));
	            	break;
	            case 47:
	            	baseData.setEriproSubCD((Object) getCellValue(nextCell));
	            	break;
	            case 48:
	            	baseData.setEriproAccount((Object) getCellValue(nextCell));
	            	break;
	            case 49:
	            	baseData.setLogicalGrouping((Object) getCellValue(nextCell));
	            	break;
	            case 50:
	            	baseData.setEriproNonDeployableRole((Object) getCellValue(nextCell));
	            	break;
	            case 51:
	            	baseData.setIsLineManager((Object) getCellValue(nextCell));
	            	break;
	            case 52:
	            	baseData.setIsProjectManager((Object) getCellValue(nextCell));
	            	break;
	            case 53:
	            	baseData.setIsHosted((Object) getCellValue(nextCell));
	            	break;
	            case 54:
	            	baseData.setlMSpan((Object) getCellValue(nextCell));
	            	break;
	            case 55:
	            	baseData.setNonDeplStartDate((Object) getCellValue(nextCell));
	            	break;
	            case 56:
	            	baseData.setNonDeplEndDate((Object) getCellValue(nextCell));
	            	break;
	            case 57:
	            	baseData.setPasPns((Object) getCellValue(nextCell));
	            	break;
	            case 58:
	            	baseData.setPoolName((Object) getCellValue(nextCell));
	            	break;
	            case 59:
	            	baseData.setNonDeployableFlag((Object) getCellValue(nextCell));
	            	break;	
	            case 60:
	            	baseData.setfAFuncArea((Object) getCellValue(nextCell));
	            	break;
	            case 61:
	            	baseData.setEmployeeGroup((Object) getCellValue(nextCell));
	            	break;
	            case 62:
	            	baseData.setUtilizationreviousMonth1((Object) getCellValue(nextCell));
	            	break;
	            case 63:
	            	baseData.setUtilizationreviousMonth2((Object) getCellValue(nextCell));
	            	break;
	            case 64:
	            	baseData.setUtilizationreviousMonth3((Object) getCellValue(nextCell));
	            	break;
	            case 65:
	            	baseData.setUtilizationreviousMonth4((Object) getCellValue(nextCell));
	            	break;
	            case 66:
	            	baseData.setUtilizationreviousMonth5((Object) getCellValue(nextCell));
	            	break;
	            case 67:
	            	baseData.setUtilizationreviousMonth6((Object) getCellValue(nextCell));
	            	break;
	            case 68:
	            	baseData.setBenchClassification((Object) getCellValue(nextCell));
	            	break;
	            case 69:
	            	baseData.setBillabilityHours((Object) getCellValue(nextCell));
	            	break;	            	
	            }
	 
	 
	        }
	        if(baseData != null) {
	        	//System.out.println(baseData);
		        baseDatas.add(baseData);
	        }
	    }
	 
	    //workbook.close();
	    inputStream.close();
	 
	    //System.out.println(baseDatas.size());
	    return baseDatas;
	}
	  /* checking if its type is that of a proper excel file  */
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath)
	        throws IOException {
	    Workbook workbook = null;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook(inputStream);
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook(inputStream);
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
	/* getting the cell value of the cell passed as 
	 * argument as per the datatype */
	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	    }
	 
	    return null;
	}
	/* returns month;if its a single digit 0 is appended in the beginning  */
	private String getMonthWithPrefix(int month) {
		if(month < 10) {
			return "0"+month;
		} else {
			return String.valueOf(month);
		}
	}
	
	/*public int getNextSequence(String collectionName) {
    Counter counter = mongo.findAndModify(
      query(where("_id").is(collectionName)), 
      new Update().inc("seq", 1),
      options().returnNew(true),
      Counter.class);
       
    return counter.getSeq();
  }*/
	
	/*    */
	
	
	public List<JobStageDTO> getJobStageWiseHoursCalculation(Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap, String jobStage) throws Throwable {
		List<JobStageDTO> dtos = new ArrayList<JobStageDTO>();
		
		ApplicationUtil applicationUtil = new ApplicationUtil();
		Map<MonthCdKey, Map<String, Double>> map = groupResourceUtilizationBaseDataByEriproCDMonthJs(eriproSubCDGroupMap, jobStage);
		Map<MonthCdKey, Map<String, Double>> subCdWiseTargetRecortedHoursMap = applicationUtil.getSubCdWiseTargetRecortedHours(eriproSubCDGroupMap);

		for(MonthCdKey  monthCdKey : map.keySet()) { 
			JobStageDTO jobStageDTO = new JobStageDTO();  
			jobStageDTO.setCd(monthCdKey.getDomain());
			jobStageDTO.setMonth(monthCdKey.getMonth());
			
			Map<String, Double> targetRecordedHoursMap = map.get(monthCdKey);
			
			jobStageDTO.setTargetHours(targetRecordedHoursMap.get("targetHours"));
			jobStageDTO.setRecordedHours(targetRecordedHoursMap.get("recordedHours"));
			
			
			MonthCdKey key = new MonthCdKey();
			key.setDomain(monthCdKey.getDomain());
			key.setMonth(monthCdKey.getMonth());
			Map<String, Double> totalTargetRecordedHoursMap = subCdWiseTargetRecortedHoursMap.get(key);
			jobStageDTO.setTargetHours(totalTargetRecordedHoursMap.get("targetHours"));
			
			Double percentage = ( (targetRecordedHoursMap.get("recordedHours"))/(totalTargetRecordedHoursMap.get("targetHours")) )*100;
			jobStageDTO.setPercentage(percentage);
			dtos.add(jobStageDTO);
		}
		return dtos;
	}
	
	
	
	
	/* ***************Newly Written Code ****************** */
	// key as CD and is vales list of ResourceUtilizationBaseData
	
	public Map<MonthCdKey, Map<String, Double>> groupResourceUtilizationBaseDataByEriproCDMonthJs(Map<String,List<ResourceUtilizationBaseData>> cdMap, 
			String jobStage)
	{
		Map<MonthCdKey, Map<String, Double>> cdMonthTargetHoursMap = new LinkedHashMap<MonthCdKey, Map<String, Double>>();
		Set<String> cdGroups= cdMap.keySet();
		List<String> cdGroupsList = new ArrayList<String>(cdGroups);
		Collections.sort(cdGroupsList);
		if(cdGroupsList != null && !cdGroupsList.isEmpty())
		{
			for(String cdGroup :cdGroupsList)
			{
				List<ResourceUtilizationBaseData> domainValuesList=cdMap.get(cdGroup);
				
				if(domainValuesList!=null && !domainValuesList.isEmpty())
				{
					for(ResourceUtilizationBaseData domainValue :domainValuesList)
					{
						if(jobStage.equalsIgnoreCase(String.valueOf(domainValue.getGlobalJobStage()))) {
						MonthCdKey month_cd_key=new MonthCdKey();
						month_cd_key.setDomain(cdGroup);
						month_cd_key.setMonth(domainValue.getMonth());
						if(cdMonthTargetHoursMap.get(month_cd_key) != null) {
							Map<String, Double> targetRecordedHoursMap = cdMonthTargetHoursMap.get(month_cd_key);
							Double targetHoursD = targetRecordedHoursMap.get("targetHours") + (Double)domainValue.getTargetHours();
							Double recordedHoursD = targetRecordedHoursMap.get("recordedHours") + (Double)domainValue.getTotalBillableHours();
							targetRecordedHoursMap.put("targetHours", targetHoursD);
							targetRecordedHoursMap.put("recordedHours", recordedHoursD);
						} else {
							Map<String, Double> map = new HashMap<String, Double>();
							map.put("targetHours", (Double)domainValue.getTargetHours());
							map.put("recordedHours", (Double)domainValue.getTotalBillableHours());
							cdMonthTargetHoursMap.put(month_cd_key, map);
							}
						}
					}
				}
			}
			
		
		}
		return cdMonthTargetHoursMap;
	}
	
	
	public static void main(String[] args) throws Exception {
		ApplicationUtil applicationUtil = new ApplicationUtil();
		 
		String excelFilePath = applicationUtil.getFileName();
		ResourceUtilizationParserService baseData = new ResourceUtilizationParserService();
		baseData.readResourceUtilizationBaseDataFromExcelFile(excelFilePath);
	}



	public ResourceUtilizationParserServiceRepository getUtilizationParserServiceRepository() {
		return utilizationParserServiceRepository;
	}
}
