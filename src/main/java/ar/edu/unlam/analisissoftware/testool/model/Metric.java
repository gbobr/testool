package ar.edu.unlam.analisissoftware.testool.model;

public abstract class Metric {
	private Boolean pHasBeenCalculated=false;
	
	public abstract String getName();
	public abstract String internalGetValue();
	public abstract void internalCalculate(Method method);
	
	public void calculate(Method method){
		pHasBeenCalculated=true;
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
