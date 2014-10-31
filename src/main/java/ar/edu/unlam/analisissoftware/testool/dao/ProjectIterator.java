package ar.edu.unlam.analisissoftware.testool.dao;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

public class ProjectIterator {
	private TestTool testTool;

	@Autowired
	public ProjectIterator(TestTool testTool) {
		super();
		this.testTool = testTool;
	}
	
	public void visitDirectory(File directory){
		visitDirectory(directory,"./");
	}
	
	public void visitDirectory(File directory,String relativePath){
		//TODO: Refactorizar sobre un threadPool
		if( !directory.isDirectory() )
			throw new IllegalArgumentException( "Not a directory!" );
			
		File[] files = directory.listFiles();
		
		for( File file : files ){
			if(file.isDirectory())
				visitDirectory( file,relativePath+file.getName()+"/" );
			else if(file.getName().endsWith(".java")){
				testTool.generateReportForClass(file,relativePath);
			}
		}
	}
	
	
}
