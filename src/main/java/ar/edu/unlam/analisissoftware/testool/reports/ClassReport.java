package ar.edu.unlam.analisissoftware.testool.reports;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class ClassReport extends Report {
	private Class _class;
	private List<MethodReport> methodReports;
	private String basePath;
	
	public ClassReport(Class _class,List<MethodReport> methodReports,String reportPath, String basePath) {
		super(reportPath);
		this.methodReports=methodReports;
		this._class=_class;
		this.basePath=basePath;
	}

	public ClassReport(Class _class, String reportPath, String basePath) {
		this(_class,new ArrayList<MethodReport>(), reportPath, basePath);		
	}

	public List<MethodReport> getMethodReports() {
		return methodReports;
	}
	

	public Class get_class() {
		return _class;
	}

	public void addMethodReport(MethodReport methodReport) {
		this.methodReports.add(methodReport);
	}

	public String getBasePath() {
		return "file:/"+basePath.replace("\\", "/");
	}
	
	
	
}
