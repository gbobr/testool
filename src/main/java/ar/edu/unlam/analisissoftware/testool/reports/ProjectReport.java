package ar.edu.unlam.analisissoftware.testool.reports;

import java.util.ArrayList;
import java.util.List;

public class ProjectReport extends Report {
	private List<ClassReport> classes;

	public ProjectReport(String reportPath) { this( new ArrayList<ClassReport>(), reportPath ); }
	public ProjectReport(List<ClassReport> classes,String reportPath) {
		super (reportPath);
		this.classes = classes;
	}

	public void addClass(ClassReport classReport){
		classes.add(classReport);
	}
	
	public List<ClassReport> getClasses() {
		return classes;
	}


/*	public void calculateAllMetrics() {
		for(Metric metric:metrics){
			metric.calculate(method);
		}
	}*/
}
