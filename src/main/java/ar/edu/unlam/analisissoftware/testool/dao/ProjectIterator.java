package ar.edu.unlam.analisissoftware.testool.dao;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.analisissoftware.testool.reports.ProjectReport;

public class ProjectIterator {
	private TestTool testTool;
	private ProjectReport projectReport;
	
	@Autowired
	public ProjectIterator(TestTool testTool) {
		super();
		this.testTool = testTool;
	}
	
	public ProjectReport analyzeProject(File directory){
		projectReport = new ProjectReport(directory.getAbsolutePath());
		visitDirectory(directory,"./");
		return projectReport;
	}
	
	private void visitDirectory(File directory,String relativePath){
		//TODO: Refactorizar sobre un threadPool
		if( !directory.isDirectory() )
			throw new IllegalArgumentException( "Not a directory!" );
			
		File[] files = directory.listFiles();
		
		for( File file : files ){
			if(file.isDirectory())
				visitDirectory( file,relativePath+file.getName()+"/" );
			else if(file.getName().endsWith(".java")){
				projectReport.addClass(testTool.generateReportForClass(file,relativePath));
			}
		}
	}
	
	
}
