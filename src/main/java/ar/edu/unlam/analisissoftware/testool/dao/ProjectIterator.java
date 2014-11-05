package ar.edu.unlam.analisissoftware.testool.dao;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.analisissoftware.testool.reports.ClassReport;
import ar.edu.unlam.analisissoftware.testool.reports.ProjectReport;

public class ProjectIterator {
	
	
	final Logger logger = LoggerFactory.getLogger(ProjectIterator.class);

	private TestTool testTool;
	private ProjectReport projectReport;
	
	@Autowired
	public ProjectIterator(TestTool testTool) {
		super();
		this.testTool = testTool;
	}
	
	public ProjectReport analyzeProject(File projectDir, File outputDir){		
		projectReport = new ProjectReport(outputDir.getAbsolutePath()+"/");
		visitDirectory(projectDir,outputDir.getAbsolutePath()+"/");
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
				ClassReport cr=testTool.generateReportForClass(file,relativePath);
				if(cr!=null){
					
					projectReport.addClass(cr);
				}
			}
		}
	}
	
	
}
