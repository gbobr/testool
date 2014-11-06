package ar.edu.unlam.analisissoftware.testool.metrics;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
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
			
			for( Method classMethod : method.getMethodClass().getMethods() ){
			
				if( classMethod == method )
					continue;
				
				mFanOut = StringTools.countOccurences( line, classMethod.getName() );
			}
		}
	}

}
