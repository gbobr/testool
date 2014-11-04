package ar.edu.unlam.analisissoftware.testool.metrics;

import junit.framework.TestCase;
import ar.edu.unlam.analisissoftware.testool.metrics.LocMetric;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class CommentPercentMetricTest extends TestCase {
	
	
	
	public void testCommentPercentMetric(){
		LocMetric locMetric=new LocMetric();
		CommentMetric commentMetric=new CommentMetric();
		CommentPercentMetric commentPercentMetric=new CommentPercentMetric(locMetric,commentMetric);
		
		locMetric.calculate(getMethod());
		commentMetric.calculate(getMethod());
		commentPercentMetric.calculate(getMethod());
		assertEquals("10 Lineas",locMetric.getValue());
		assertEquals("4",commentMetric.getValue());
		assertEquals("40.0%",commentPercentMetric.getValue());
	}

	private Method getMethod() {
		return new Method("testMethod","1"
				+ "\n 2"
				+ "\n //3"
				+ "\n 4"
				+ "\n 5"
				+ "\n /*6"
				+ "\n 7"
				+ "\n 8*/"
				+ "\n 9"
				+ "\n 10");
	}
}
