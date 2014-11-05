package ar.edu.unlam.analisissoftware.testool.reports;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class ClassReport extends Report {
	private Class _class;
	private List<MethodReport> methodReports;
	
	public ClassReport(Class _class,List<MethodReport> methodReports,String reportPath) {
		super(reportPath);
		this.methodReports=methodReports;
		this._class=_class;
	}

	public ClassReport(Class _class, String reportPath) {
		this(_class,new ArrayList<MethodReport>(), reportPath);		
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
	
}
