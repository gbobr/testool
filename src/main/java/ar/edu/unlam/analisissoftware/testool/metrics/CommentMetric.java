package ar.edu.unlam.analisissoftware.testool.metrics;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class CommentMetric extends Metric {

	private Integer commentCount;
	
	@Override
	public String getName() {
		return "Lineas comentadas: ";
	}

	@Override
	public String internalGetValue() {
		return commentCount.toString();
	}

	@Override
	public void internalCalculate(Method method) {
		commentCount=0;
		for(String comment : method.getComments()){
			commentCount+=comment.split("\n").length;
		}
	}
	
	public Integer getRawValue(){
		failIfNotCalculated();
		return commentCount;
	}

}
