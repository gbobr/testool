package ar.edu.unlam.analisissoftware.testool.service;

import java.io.FileWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.analisissoftware.testool.reports.MethodReport;

public class VelocityReportingService {
	private VelocityEngine velocityEngine;
	private ConfigService configService;
	
	@Autowired
	public VelocityReportingService(VelocityEngine velocityEngine, ConfigService configService) {
		super();
		this.velocityEngine = velocityEngine;
		this.configService=configService;
	}

	public String generateReport(MethodReport report,String outPath){
		String filepath=outPath+report.getMethod().getName()+".html";
		try {
			FileWriter fileWriter=new FileWriter(filepath,false);
			Template tpl=velocityEngine.getTemplate(configService.getTemplateName());
			tpl.merge(generateContext(report), fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filepath;
	}
	
	private VelocityContext generateContext(MethodReport report){
		VelocityContext ctx = new VelocityContext();
		ctx.put("class", report.get_class());
		ctx.put("method", report.getMethod());
		ctx.put("metrics", report.getMetrics().iterator());
		return ctx;
	}
	
}
