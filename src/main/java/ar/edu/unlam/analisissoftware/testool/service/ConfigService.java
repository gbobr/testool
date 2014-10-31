package ar.edu.unlam.analisissoftware.testool.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.edu.unlam.analisissoftware.testool.dao.ProjectIterator;
import ar.edu.unlam.analisissoftware.testool.dao.TestTool;
import ar.edu.unlam.analisissoftware.testool.metrics.LocMetric;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.testool.parsing.ClassParserFactory;
import ar.edu.unlam.analisissoftware.testool.parsing.japaparser.JapaClassParserFactory;

@Configuration
public class ConfigService {
	@Bean ConfigService configService(){ return new ConfigService(); }
	@Bean VelocityReportingService velocityReportingService(){ return new VelocityReportingService(velocityEngine(), configService()); }
	@Bean ClassParserFactory classParserFactory(){ return new JapaClassParserFactory(); }
	@Bean ParserService parserService(){ return new ParserService(classParserFactory()); }
	@Bean TestTool testTool(){ return new TestTool(velocityReportingService(),parserService(),metrics(),configService()); }
	@Bean ProjectIterator projectIterator(){ return new ProjectIterator(testTool()); }

	@Bean VelocityEngine velocityEngine(){ 
		VelocityEngine ve=new VelocityEngine();
		ve.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
		return ve; 
	}
	
	@Bean List<Metric> metrics() { 
		List<Metric> metrics = new ArrayList<Metric>();
		metrics.add(new LocMetric());
		return metrics;
	}
	
	private String baseOutputDir="target/testtool/";
	private String templateName="report.vm";
	
	public String getBaseOutputDir() {
		return baseOutputDir;
	}

	public void setBaseOutputDir(String baseOutputDir) {
		this.baseOutputDir=baseOutputDir;
		if(!this.baseOutputDir.endsWith("/")) baseOutputDir=baseOutputDir+"/";
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}
