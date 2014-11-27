package ar.edu.unlam.analisissoftware.testool.metrics;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.utils.StringTools;

public class FanOutMetric extends Metric {

	private int mFanOut;
	
	@Override
	public String getName() {
		return "Fan Out: ";
	}

	@Override
	public String internalGetValue() {
		return String.format( "%d", mFanOut );
	}

	@Override
	public void internalCalculate(Method method) {
		mFanOut = 0;
		
		String[] lines = method.getCode().split( "\n" );
		
		for( String line : lines ){
			
			for( Class projectClass : method.getMethodClass().getProject().getClasses() ){
				for( Method classMethod : projectClass.getMethods() ){
				
					if( classMethod.getName().equals(method.getName()) )
						continue;
					
					mFanOut += StringTools.countOccurences( line, classMethod.getName() );
				}
			}
		}
	}

}
