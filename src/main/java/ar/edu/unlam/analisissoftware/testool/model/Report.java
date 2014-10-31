package ar.edu.unlam.analisissoftware.testool.model;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private Class _class;
	private Method method;
	private List<Metric> metrics;
	
	public Report(Class _class, Method method, List<Metric> metrics) {
		super();
		this._class = _class;
		this.method = method;
		this.metrics = metrics;
	}

	public Report(Class _class, Method method) {
		this(_class,method,new ArrayList<Metric>());		
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
