package ar.edu.unlam.analisissoftware.testool.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	final Logger logger = LoggerFactory.getLogger(TestTool.class);
	
	VelocityReportingService velocityReportingService;
	ParserService parserService;
	List<Metric> metrics;
		
	@Autowired
	public TestTool(VelocityReportingService velocityReportingService,
			ParserService parserService, List<Metric> metrics) {
		super();
		this.velocityReportingService = velocityReportingService;
		this.parserService = parserService;
		this.metrics = metrics;
	}

	public ClassReport generateReportForClass(File javaFile,String relativePath){		
		String outPath=getDestinationPath(javaFile.getName(), relativePath);
		Class myClass;
		try{
			myClass=parserService.parse(javaFile);
		
			List<MethodReport> reports = new ArrayList<MethodReport>();
			
			for(Method method: myClass.getMethods()){
				MethodReport currentReport=new MethodReport(myClass,method,metrics,outPath+method.getName()+".html");
				currentReport.calculateAllMetrics();
				velocityReportingService.generateMethodReport(currentReport,outPath);
				reports.add(currentReport);
			}
			
			ClassReport cr=new ClassReport(myClass,reports,outPath+myClass.getName()+".html");
			velocityReportingService.generateClassReport(cr, outPath);
			
			return cr;
		} catch(Exception e) {
			logger.error("Ocurrio un error procesando la clase " + javaFile.getName(),e);
			return null;
		}
	}
	
	private String getDestinationPath(String filename,String relativePath){
		String outputPath=relativePath+filename+"/";
		File directory=new File(outputPath);
		if(!directory.exists())
			directory.mkdirs();
		
		return directory.getAbsolutePath();
	}
	
}