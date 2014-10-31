package ar.edu.unlam.analisissoftware.testool.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.testool.reports.ClassReport;
import ar.edu.unlam.analisissoftware.testool.reports.MethodReport;
import ar.edu.unlam.analisissoftware.testool.service.ConfigService;
import ar.edu.unlam.analisissoftware.testool.service.ParserService;
import ar.edu.unlam.analisissoftware.testool.service.VelocityReportingService;

@Component
public class TestTool {
	VelocityReportingService velocityReportingService;
	ParserService parserService;
	List<Metric> metrics;
	ConfigService configService;
		
	@Autowired
	public TestTool(VelocityReportingService velocityReportingService,
			ParserService parserService, List<Metric> metrics, ConfigService configService) {
		super();
		this.velocityReportingService = velocityReportingService;
		this.parserService = parserService;
		this.metrics = metrics;
		this.configService=configService;
	}

	public ClassReport generateReportForClass(File javaFile,String relativePath){		
		String outPath=getDestinationPath(javaFile.getName(), relativePath);
		Class myClass=parserService.parse(javaFile);
		List<MethodReport> reports = new ArrayList<MethodReport>();
		
		for(Method method: myClass.getMethods()){
			MethodReport currentReport=new MethodReport(myClass,method,metrics,outPath+method.getName()+".html");
			currentReport.calculateAllMetrics();
			velocityReportingService.generateReport(currentReport,outPath);
			reports.add(currentReport);
		}
		
		ClassReport cr=new ClassReport(reports,outPath+myClass.getName()+".html");
		
		return cr;
	}
	
	private String getDestinationPath(String filename,String relativePath){
		String outputPath=configService.getBaseOutputDir()+relativePath+filename+"/";
		File directory=new File(outputPath);
		if(!directory.exists())
			directory.mkdirs();
		
		return directory.getAbsolutePath();
	}
	
}
