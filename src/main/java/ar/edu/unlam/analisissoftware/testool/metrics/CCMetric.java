package ar.edu.unlam.analisissoftware.testool.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.unlam.analisissoftware.testool.Main;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.utils.StringTools;

public class CCMetric extends Metric {
	
	static final Logger logger = LoggerFactory.getLogger(CCMetric.class);
	
	private int mCC = 0;
	private int mIf = 0;
	private int mWhile = 0;
	private int mFor = 0;
	private int mCase = 0;
	private int mCatch = 0;
	private int mAnd = 0;
	private int mOr = 0;
	private int mTernary = 0;
	
	@Override
	public String getName() {
		return "Complejidad Ciclomatica: ";
	}

	@Override
	public String internalGetValue() {
		return String.format( "%d If: %d, While: %d, For: %d, Case: %d, Catch: %d, And: %d, Or: %d, Ternario: %d" , 
				mCC,
				mIf,
				mWhile,
				mFor,
				mCase,
				mCatch,
				mAnd,
				mOr,
				mTernary);
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
		
		mCC = 0;
		mIf = 0;
		mWhile = 0;
		mFor = 0;
		mCase = 0;
		mCatch = 0;
		mAnd = 0;
		mOr = 0;
		mTernary = 0;
		
		String[] lines = method.getCode().split( "\n" );
		
		for( String line : lines ){	
			line = line.replace(" ", "");
			
			mIf 		+= StringTools.countOccurences( line, "if(" );
			mWhile 		+= StringTools.countOccurences( line, "while(" );
			mFor		+= StringTools.countOccurences( line, "for(" );
			mCase		+= StringTools.countOccurences( line, "case" );
			mCatch		+= StringTools.countOccurences( line, "catch(" );
			mAnd		+= StringTools.countOccurences( line, "&&" );
			mOr			+= StringTools.countOccurences( line, "||" );
			mTernary	+= StringTools.countOccurences( line, "?" );
				
		}
		
		mCC = mIf + mWhile + mFor + mCase + mCatch + mAnd + mOr + mTernary;
		mCC = mCC > 1 ? mCC : mCC + 1;
	}
}
