package com.ericsson.v1.util;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.ericsson.v1.model.MonthSubCdKey;
import com.ericsson.v1.model.MonthSubCdTypeDTO;
import com.ericsson.v1.model.ResourceUtilizationBaseData;
import com.ericsson.v1.service.ResourceUtilizationParserService;

public class Application {

	public static void main(String[] args) throws Exception
	{
		ApplicationUtil applicationUtil = new ApplicationUtil();
		 // https://msr1980.wordpress.com/2015/01/02/happy-42005/
		String excelFilePath = applicationUtil.getFileName();//"C:\\Apps\\ericssonInternalApps\\utilizationApp\\src\\main\\resources\\Utilization Analysis.xls";
		
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("spring-config.xml").getPath());
			//ApplicationContext context = new FileSystemXmlApplicationContext("C:\\Apps\\ericssonInternalApps\\utilizationApp\\src\\resources\\spring-config.xml");
			//ResourceUtilizationParserServiceRepository utilizationParserServiceRepository = context.getBean(ResourceUtilizationParserServiceRepository.class);
			
			ResourceUtilizationParserService resourceUtilizationParserService = context.getBean(ResourceUtilizationParserService.class);
			List<ResourceUtilizationBaseData> baseDatas = resourceUtilizationParserService.parse(excelFilePath);
			System.out.println(baseDatas.size());
			
			//Map<String, String> map =  resourceUtilizationParserService.gropuByEriproSubCDAndSubCdTypeMap();
			//System.out.println(map);
			
			List<ResourceUtilizationBaseData> filteredBaseDatas = resourceUtilizationParserService.filterDataRNAMCAC(baseDatas);
			Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByEriproSubCD(filteredBaseDatas);
			resourceUtilizationParserService.groupResourceUtilizationBaseDataByEriproCDMonthJs(eriproSubCDGroupMap, "Job stage 4");
			//System.out.println(eriproSubCDGroupMap.keySet());
			//resourceUtilizationParserService.persist(baseDatas);
			//System.out.println(context);
			//System.out.println(utilizationParserServiceRepository.findAll());
			
			//System.out.println(PropertyUtil.getInstance().getValue("eripro.sub.cd.SDP"));
			
			//Map<String, Map<String, Double>>  map = resourceUtilizationParserService.groupResourceUtilizationBaseDataByYear(eriproSubCDGroupMap);
			
			//Map<MonthSubCdKey, Map<String, Double>>  monthSubCdSubCdTypeKeyMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByMonth(eriproSubCDGroupMap);
			//System.out.println(monthSubCdSubCdTypeKeyMap);
			
			//Map<String, Map<String, Double>> monthAndSubCdTypeMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByMonthAndSubCdType(monthSubCdSubCdTypeKeyMap, "TV");
			//System.out.println(monthAndSubCdTypeMap);
			//Map<String, Double> monthSubCdTypeMap = resourceUtilizationParserService.groupPercentageResourceUtilizationBaseDataByMonthAndSubCdType(monthAndSubCdTypeMap);
			//System.out.println(monthSubCdTypeMap);
			
			//List<MonthSubCdTypeDTO> cdTypeDTOs = resourceUtilizationParserService.getUtilizationDetails(monthSubCdSubCdTypeKeyMap);
			//System.out.println(cdTypeDTOs);
			//42005;
			
			/*Calendar calendar = new GregorianCalendar();

			calendar.set(Calendar.YEAR, 1900 );
			calendar.set(Calendar.MONTH, 0); // 11 = december
			calendar.set(Calendar.DAY_OF_MONTH, 1); // christmas eve
			
			System.out.println(calendar.getTime());
			
			int i = 42003;
			int r = i-1900;
			System.out.println(r);
			int j = r/12;
			calendar.add(Calendar.DATE, r);
			
			System.out.println(calendar.getTime());*/
			
	}
	
}
