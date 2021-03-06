package ar.edu.unlam.analisissoftware.testool.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.edu.unlam.analisissoftware.testool.dao.ProjectIterator;
import ar.edu.unlam.analisissoftware.testool.dao.TestTool;
import ar.edu.unlam.analisissoftware.testool.metrics.CCMetric;
import ar.edu.unlam.analisissoftware.testool.metrics.CommentMetric;
import ar.edu.unlam.analisissoftware.testool.metrics.CommentPercentMetric;
import ar.edu.unlam.analisissoftware.testool.metrics.FanInMetric;
import ar.edu.unlam.analisissoftware.testool.metrics.FanOutMetric;
import ar.edu.unlam.analisissoftware.testool.metrics.HalsteadLengthMetric;
import ar.edu.unlam.analisissoftware.testool.metrics.HalsteadVolumeMetric;
import ar.edu.unlam.analisissoftware.testool.metrics.LocMetric;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.testool.parsing.ClassParserFactory;
import ar.edu.unlam.analisissoftware.testool.parsing.japaparser.JapaClassParserFactory;

@Configuration
public class ConfigService {
	//Services
	@Bean ConfigService configService(){ return new ConfigService(); }
	@Bean VelocityReportingService velocityReportingService(){ return new VelocityReportingService(velocityEngine(), configService()); }
	@Bean ClassParserFactory classParserFactory(){ return new JapaClassParserFactory(); }
	@Bean ParserService parserService(){ return new ParserService(classParserFactory()); }
	@Bean TestTool testTool(){ return new TestTool(velocityReportingService(),parserService(),metrics()); }
	@Bean ProjectIterator projectIterator(){ return new ProjectIterator(testTool(),velocityReportingService(), parserService()); }
	@Bean FanInMetric fanInMetric() { return new FanInMetric(); }
	@Bean FanOutMetric fanOutMetric() { return new FanOutMetric(); }
	@Bean CCMetric ccMetric() { return new CCMetric(); }

	//Velocity engine
	@Bean VelocityEngine velocityEngine(){ 
		VelocityEngine ve=new VelocityEngine();
		ve.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
		return ve; 
	}
	
	//Metrics	
	@Bean LocMetric locMetric(){ return new LocMetric();}
	@Bean CommentMetric commentMetric(){ return new CommentMetric(); }
	@Bean CommentPercentMetric commentPercentMetric(){ return new CommentPercentMetric(locMetric(), commentMetric()); }
	@Bean HalsteadLengthMetric lenMetric() { return new HalsteadLengthMetric();}
	@Bean HalsteadVolumeMetric volumenMetric() { return new HalsteadVolumeMetric();}
	@Bean List<Metric> metrics() { 
		List<Metric> metrics = new ArrayList<Metric>();
		metrics.add(locMetric());
		metrics.add(commentMetric());
		metrics.add(commentPercentMetric());
		metrics.add(lenMetric());
		metrics.add(volumenMetric());
		metrics.add(fanInMetric());
		metrics.add(fanOutMetric());
		metrics.add(ccMetric());
		return metrics;
	}

	private String methodTemplateName="methodReport.vm";
	private String classTemplateName="classReport.vm";
	private String projectTemplateName="projectReport.vm";

	public String getClassTemplateName() {
		return classTemplateName;
	}
	public void setClassTemplateName(String classTemplateName) {
		this.classTemplateName = classTemplateName;
	}
	
	public String getMethodTemplateName() {
		return methodTemplateName;
	}
	public void setMethodTemplateName(String methodTemplateName) {
		this.methodTemplateName = methodTemplateName;
	}
	
	public String getProjectTemplateName() {
		return projectTemplateName;
	}
	public void setProjectTemplateName(String projectTemplateName) {
		this.projectTemplateName = projectTemplateName;
	}
	
	
}
