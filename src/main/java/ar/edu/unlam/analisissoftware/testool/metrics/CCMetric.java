package ar.edu.unlam.analisissoftware.testool.metrics;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

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
			mCC += countOccurences( line, "if" );
			mCC += countOccurences( line, "while" );
			mCC += countOccurences( line, "for" );
			mCC += countOccurences( line, "case" );
			mCC += countOccurences( line, "catch" );
			mCC += countOccurences( line, "&&" );
			mCC += countOccurences( line, "||" );
			mCC += countOccurences( line, "?" );
		}
	}
	
	//
	// Count the number of occurrences of substring on string while it is not in a comment
	//
	public static int countOccurences(final String string, final String substring) {
		int count = 0;
		int idx = 0;
		
		int commentStart = string.indexOf("//");
		
		if( commentStart == -1 )
			commentStart = string.indexOf( "/*" );
		
		if( commentStart == -1 && string.indexOf( "*/" ) == -1 )
			commentStart = string.length();
	
		while ( ( idx = string.indexOf( substring, idx ) ) != -1 && idx <= commentStart ){
			idx++;
			count++;
		}
	
		return count;
	}

}
