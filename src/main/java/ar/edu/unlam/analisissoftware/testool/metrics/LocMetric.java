package ar.edu.unlam.analisissoftware.testool.metrics;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class LocMetric extends Metric {
	private Integer totalLocs;
	
	@Override
	public String getName() {
		return "Lineas de código totales: ";
	}

	@Override
	public String internalGetValue() {
		return totalLocs.toString() + " Lineas";
	}
	
	@Override
	public void internalCalculate(Method method) {
		totalLocs = method.getCode().split("\n").length;
	}

	public Integer getRawValue(){
		failIfNotCalculated();
		return totalLocs;
	}
}
