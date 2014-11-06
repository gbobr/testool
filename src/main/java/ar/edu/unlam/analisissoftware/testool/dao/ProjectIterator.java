package ar.edu.unlam.analisissoftware.testool.dao;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.analisissoftware.testool.reports.ClassReport;
import ar.edu.unlam.analisissoftware.testool.reports.ProjectReport;
import ar.edu.unlam.analisissoftware.testool.service.VelocityReportingService;

public class ProjectIterator {
	
	
	final Logger logger = LoggerFactory.getLogger(ProjectIterator.class);

	private TestTool testTool;
	private ProjectReport projectReport;
	private VelocityReportingService velocityReportingService;
	private String basePath;
	
	@Autowired
	public ProjectIterator(TestTool testTool, VelocityReportingService velocityReportingService) {
		super();
		this.testTool = testTool;
		this.velocityReportingService=velocityReportingService;
	}
	
	public ProjectReport analyzeProject(File projectDir, File outputDir){		
		basePath=outputDir.getAbsolutePath()+"/";
		projectReport = new ProjectReport(basePath);
		visitDirectory(projectDir,basePath);
		
		velocityReportingService.generateProjectReport(projectReport, basePath);
		return projectReport;
	}
	
	private void visitDirectory(File directory,String relativePath){
		//TODO: Refactorizar sobre un threadPool
		if( !directory.isDirectory() )
			throw new IllegalArgumentException( "Not a directory!" );
		
		logger.info("Analizando directorio '" + directory.getAbsolutePath() + "'");
		File[] files = directory.listFiles();
		
		for( File file : files ){
			if(file.isDirectory())
				visitDirectory( file,relativePath+file.getName()+"/" );
			else if(file.getName().endsWith(".java")){
				logger.info("Procesando archivo '" + file.getName() + "'");
				ClassReport cr=testTool.generateReportForClass(file,relativePath,basePath);
				if(cr!=null){
					
					projectReport.addClass(cr);
				}
			}
		}
	}
	
	
}
