package ar.edu.unlam.analisissoftware.testool.metrics;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.utils.StringTools;

public class FanInMetric extends Metric {

	int mFanIn;
	
	@Override
	public String getName() {
		return "Fan In: ";
	}

	@Override
	public String internalGetValue() {
		return String.format("%d", mFanIn);
	}

	@Override
	public void internalCalculate(Method method) {
		mFanIn = 0;
		
		for( Class projectClass : method.getMethodClass().getProject().getClasses() ){
			for( Method classMethod : projectClass.getMethods() ){
				
				if( classMethod.getName().equals(method.getName()))
					continue;
				
				String[] lines = classMethod.getCode().split( "\n" );
				
				for( String line : lines ){			
					mFanIn += StringTools.countOccurences( line, method.getName() );
				}
			}
		}
		
	}

}
