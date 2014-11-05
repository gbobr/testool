package ar.edu.unlam.analisissoftware.testool.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.unlam.analisissoftware.testool.dao.ProjectIterator;

public abstract class Metric {
	final Logger logger = LoggerFactory.getLogger(ProjectIterator.class);
	private Boolean pHasBeenCalculated=false;
	
	public abstract String getName();
	public abstract String internalGetValue();
	public abstract void internalCalculate(Method method);
	
	public void calculate(Method method){
		pHasBeenCalculated=true;
		logger.info("Procesando métrica {} para el método {}",this.getName(),method.getName());
		internalCalculate(method);
	}
	
	public String getValue(){
		failIfNotCalculated();
		return internalGetValue();
	}
	
	public Boolean hasBeenCalculated(){
		return pHasBeenCalculated;
	}
	
	public void failIfNotCalculated(){
		if(!pHasBeenCalculated) throw(new IllegalStateException("Asking for metric value but it's not yet calculated"));
	}
}
