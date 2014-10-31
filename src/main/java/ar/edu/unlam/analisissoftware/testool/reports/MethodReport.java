package ar.edu.unlam.analisissoftware.testool.reports;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class MethodReport extends Report {
	private Class _class;
	private Method method;
	private List<Metric> metrics;
	
	public MethodReport(Class _class, Method method, List<Metric> metrics, String reportPath) {
		super(reportPath);
		this._class = _class;
		this.method = method;
		this.metrics = metrics;
	}

	public MethodReport(Class _class, Method method, String reportPath) {
		this(_class,method,new ArrayList<Metric>(), reportPath);		
	}
	
	public void addMetric(Metric metric){
		metrics.add(metric);
	}
	
	public Class get_class() {
		return _class;
	}

	public Method getMethod() {
		return method;
	}

	public List<Metric> getMetrics() {
		return metrics;
	}

	public void calculateAllMetrics() {
		for(Metric metric:metrics){
			metric.calculate(method);
		}
	}
}
