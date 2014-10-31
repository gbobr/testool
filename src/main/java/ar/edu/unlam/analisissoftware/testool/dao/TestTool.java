package ar.edu.unlam.analisissoftware.testool.dao;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.testool.model.Report;
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

	public String generateReportForClass(File javaFile,String relativePath){		
		String outPath=getDestinationPath(javaFile.getName(), relativePath);
		Class myClass=parserService.parse(javaFile);
		
		for(Method method: myClass.getMethods()){
			Report currentReport=new Report(myClass,method,metrics);
			currentReport.calculateAllMetrics();
			velocityReportingService.generateReport(currentReport,outPath);
		}
		
		return outPath;
	}
	
	private String getDestinationPath(String filename,String relativePath){
		String outputPath=configService.getBaseOutputDir()+relativePath+filename+"/";
		File directory=new File(outputPath);
		if(!directory.exists())
			directory.mkdirs();
		
		return directory.getAbsolutePath();
	}
	
}
