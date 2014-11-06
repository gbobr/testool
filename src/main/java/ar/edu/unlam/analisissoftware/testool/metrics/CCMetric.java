package ar.edu.unlam.analisissoftware.testool.metrics;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.utils.StringTools;

public class CCMetric extends Metric {
	
	private int mCC;
	
	@Override
	public String getName() {
		return "Complejidad Ciclomatica: ";
	}

	@Override
	public String internalGetValue() {
		return String.format( "%d" , mCC );
	}

	@Override
	public void internalCalculate(Method method) {
		// We will use this method to calculate Cyclomatic Complexity
		// http://gmetrics.sourceforge.net/gmetrics-CyclomaticComplexityMetric.html
		
//		Start with a initial (default) value of one (1). Add one (1) for each occurrence of each of the following:
//			if statement
//			while statement
//			for statement
//			case statement
//			catch statement
//			&& and || boolean operations
//			?: ternary operator and ?: Elvis operator.
//			?. null-check operator
		
		mCC = 1;
		String[] lines = method.getCode().split( "\n" );
		
		for( String line : lines ){	
			mCC += StringTools.countOccurences( line, "if" );
			mCC += StringTools.countOccurences( line, "while" );
			mCC += StringTools.countOccurences( line, "for" );
			mCC += StringTools.countOccurences( line, "case" );
			mCC += StringTools.countOccurences( line, "catch" );
			mCC += StringTools.countOccurences( line, "&&" );
			mCC += StringTools.countOccurences( line, "||" );
			mCC += StringTools.countOccurences( line, "?" );
		}
	}
}
