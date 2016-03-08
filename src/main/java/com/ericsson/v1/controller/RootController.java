package com.ericsson.v1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {
	
	/*@Autowired
	private ResourceUtilizationParserService resourceUtilizationParserService;*/

	 @RequestMapping(value = "/", method = RequestMethod.GET)
	    public String root(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 
		 /// TEST////
		/* String excelFilePath = "C:\\Apps\\ericssonInternalApps\\utilizationApp\\src\\main\\resources\\Utilization Analysis.xls";
		 List<ResourceUtilizationBaseData> baseDatas = resourceUtilizationParserService.parse(excelFilePath);
		 List<ResourceUtilizationBaseData> filteredBaseDatas = resourceUtilizationParserService.filterDataRNAMCAC(baseDatas);
		 Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByEriproSubCD(filteredBaseDatas);
			
		 Map<MonthSubCdKey, Map<String, Double>>  monthSubCdSubCdTypeKeyMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByMonth(eriproSubCDGroupMap);
		 List<MonthSubCdTypeDTO> cdTypeDTOs = resourceUtilizationParserService.getUtilizationDetails(monthSubCdSubCdTypeKeyMap);
		 model.addAttribute("cdTypeDTOs", cdTypeDTOs);
		 
		 ApplicationUtil applicationUtil = new ApplicationUtil();
		 List<SubCdTypeMonthPercentageDTO> cdTypeMonthPercentageDTOs = applicationUtil.groupPercentageResourceUtilizationBaseDataByMonth(monthSubCdSubCdTypeKeyMap, resourceUtilizationParserService);
		 model.addAttribute("cdTypeMonthPercentageDTOs", cdTypeMonthPercentageDTOs);
		 */
	     //return "index";
	     
	     return "welcome";
	    }

	/*public void setResourceUtilizationParserService(ResourceUtilizationParserService resourceUtilizationParserService) {
		this.resourceUtilizationParserService = resourceUtilizationParserService;
	}*/
	 
	 
}
