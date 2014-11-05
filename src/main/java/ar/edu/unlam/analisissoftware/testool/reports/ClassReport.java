package ar.edu.unlam.analisissoftware.testool.reports;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class ClassReport extends Report {
	private List<MethodReport> methodReports;
	
	public ClassReport(List<MethodReport> methodReports,String reportPath) {
		super(reportPath);
		this.methodReports=methodReports;
	}

	public ClassReport(String reportPath) {
		this(new ArrayList<MethodReport>(), reportPath);		
	}

	public List<MethodReport> getMethodReports() {
		return methodReports;
	}

	public void addMethodReport(MethodReport methodReport) {
		this.methodReports.add(methodReport);
	}
	
}
