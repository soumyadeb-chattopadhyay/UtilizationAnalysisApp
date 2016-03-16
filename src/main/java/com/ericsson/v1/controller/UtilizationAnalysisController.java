package com.ericsson.v1.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ericsson.v1.model.JobStageDTO;
import com.ericsson.v1.model.MonthSubCdKey;
import com.ericsson.v1.model.MonthSubCdTypeDTO;
import com.ericsson.v1.model.ResourceUtilizationBaseData;
import com.ericsson.v1.model.SubCdTypeMonthPercentageDTO;
import com.ericsson.v1.service.ResourceUtilizationParserService;
import com.ericsson.v1.util.ApplicationUtil;
import com.google.visualization.datasource.DataSourceHelper;
import com.google.visualization.datasource.DataSourceRequest;
import com.google.visualization.datasource.base.ReasonType;
import com.google.visualization.datasource.base.ResponseStatus;
import com.google.visualization.datasource.base.StatusType;
import com.google.visualization.datasource.datatable.DataTable;

@Controller
public class UtilizationAnalysisController {

	
	@Autowired
	private ResourceUtilizationParserService resourceUtilizationParserService;
	
	@RequestMapping(value="/subCdColumnChartChart", method=RequestMethod.GET)
	public void getSubCdChartDetail(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ApplicationUtil applicationUtil = new ApplicationUtil();
		String subCdType = request.getParameter("subCdType");
		System.out.println("subCdType : "+subCdType);
		DataTable data = applicationUtil.generateColumnChartData(subCdType, resourceUtilizationParserService);
		DataSourceRequest dsRequest = null;
		
		try {
			// Extract the datasource request parameters.
			dsRequest = new DataSourceRequest(request);
			DataSourceHelper.setServletResponse(data, dsRequest, response);
		} catch (Exception rte) {
			ResponseStatus status = new ResponseStatus(StatusType.ERROR,
					ReasonType.INTERNAL_ERROR, rte.getMessage());
			System.out.println("status :"+status);
			if (dsRequest == null) {
				dsRequest = DataSourceRequest.getDefaultDataSourceRequest(request);
			}
		} 
		return ;
	}
	
	
	 @RequestMapping(value = "/eriproSubCdTypePercentageReportUrl", method = RequestMethod.GET)
	 public String getEriproSubCdTypePercentageReport(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 ApplicationUtil applicationUtil = new ApplicationUtil();
		 
		 /// TEST////
		 String excelFilePath = applicationUtil.getFileName();
		 List<ResourceUtilizationBaseData> baseDatas = resourceUtilizationParserService.parse(excelFilePath);
		 List<ResourceUtilizationBaseData> filteredBaseDatas = resourceUtilizationParserService.filterDataRNAMCAC(baseDatas);
		 Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByEriproSubCD(filteredBaseDatas);
			
		 Map<MonthSubCdKey, Map<String, Double>>  monthSubCdSubCdTypeKeyMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByMonth(eriproSubCDGroupMap);
		 
		 List<SubCdTypeMonthPercentageDTO> cdTypeMonthPercentageDTOs = applicationUtil.groupPercentageResourceUtilizationBaseDataByMonth(monthSubCdSubCdTypeKeyMap, resourceUtilizationParserService);
		 model.addAttribute("cdTypeMonthPercentageDTOs", cdTypeMonthPercentageDTOs);
		 
	     return "eriproSubCdTypePercentage";
	    }
	 
	 
	 
	 @RequestMapping(value = "/eriproSubDomainReportUrl", method = RequestMethod.GET)
	 public String getEriproSubDomainReportUrl(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 ApplicationUtil applicationUtil = new ApplicationUtil();
		 
		 /// TEST////
		 String excelFilePath = applicationUtil.getFileName();
		 List<ResourceUtilizationBaseData> baseDatas = resourceUtilizationParserService.parse(excelFilePath);
		 List<ResourceUtilizationBaseData> filteredBaseDatas = resourceUtilizationParserService.filterDataRNAMCAC(baseDatas);
		 Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByEriproSubCD(filteredBaseDatas);
			
		 Map<MonthSubCdKey, Map<String, Double>>  monthSubCdSubCdTypeKeyMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByMonth(eriproSubCDGroupMap);
		 
		 List<MonthSubCdTypeDTO> cdTypeDTOs = resourceUtilizationParserService.getUtilizationDetails(monthSubCdSubCdTypeKeyMap);
		 model.addAttribute("cdTypeDTOs", cdTypeDTOs);
		 
	     return "eriproSubDomainReport";
	    }
	 
	 
	 @RequestMapping(value = "/monthlyReportUrl", method = RequestMethod.GET)
	    public String root(HttpServletRequest request, HttpServletResponse response) throws Exception {
	     return "monthlyReport";
	 }
	 
	 
	 @RequestMapping(value = "/jobStageReportUrl", method = RequestMethod.GET)
	    public String getJobStageReport(Model model, HttpServletRequest request, HttpServletResponse response) throws Throwable {
	 		String jobStage = request.getParameter("jobStage");
			System.out.println("jobStage : "+jobStage);
			
			ApplicationUtil applicationUtil = new ApplicationUtil();
			String excelFilePath = applicationUtil.getFileName();
			
			List<ResourceUtilizationBaseData> baseDatas = resourceUtilizationParserService.parse(excelFilePath);
			
			List<ResourceUtilizationBaseData> filteredBaseDatas = resourceUtilizationParserService.filterDataRNAMCAC(baseDatas);
			Map<String, List<ResourceUtilizationBaseData>> eriproSubCDGroupMap = resourceUtilizationParserService.groupResourceUtilizationBaseDataByEriproSubCD(filteredBaseDatas);
			
			 List<JobStageDTO> jobStageDTOs = resourceUtilizationParserService.getJobStageWiseHoursCalculation(eriproSubCDGroupMap, jobStage);
			 model.addAttribute("jobStageDTOs", jobStageDTOs);
		 
	     return "jobStageReport";
	 }
	 
	 
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
        	System.out.println("name : "+name);
        	System.out.println("getContentType : "+file.getContentType());
        	System.out.println("getName: "+file.getName());
        	System.out.println("getOriginalFilename : "+file.getOriginalFilename());
        	System.out.println("getName: "+file.getSize());
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
	
	

	public void setResourceUtilizationParserService(ResourceUtilizationParserService resourceUtilizationParserService) {
		this.resourceUtilizationParserService = resourceUtilizationParserService;
	}
	
	
}
