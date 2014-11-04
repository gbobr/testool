package ar.edu.unlam.analisissoftware.testool.metrics;

import junit.framework.TestCase;
import ar.edu.unlam.analisissoftware.testool.metrics.LocMetric;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class CommentMetricTest extends TestCase {
	
	
	
	public void testCommentMetric(){
		Metric metric=new CommentMetric();
		metric.calculate(getMethod());
		assertEquals("4",metric.getValue());
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
