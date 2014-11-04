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
		String[] lines=method.getCode().split("\n");
		Boolean multiline=false;
		Integer count=0;
		for(String line:lines){			
			if(line.contains("/*")){
				count++;
				multiline=true;
			} else if(line.contains("*/")){
				count++;
				multiline=false;			
			}else if(line.contains("//") || multiline) {
					count++;
			}
		}
		
		commentCount=count;
	}
	
	public Integer getRawValue(){
		failIfNotCalculated();
		return commentCount;
	}

}
