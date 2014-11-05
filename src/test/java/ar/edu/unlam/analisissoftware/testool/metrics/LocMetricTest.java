package ar.edu.unlam.analisissoftware.testool.metrics;

import junit.framework.TestCase;
import ar.edu.unlam.analisissoftware.testool.metrics.LocMetric;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class LocMetricTest extends TestCase {
	
	
	
	public void testLocMetric(){
		Metric metric=new LocMetric();
		metric.calculate(getMethod());
		assertEquals("10 Lineas",metric.getValue());
	}

	private Method getMethod() {
		return new Method("testMethod","1"
				+ "\n 2"
				+ "\n 3"
				+ "\n 4"
				+ "\n 5"
				+ "\n 6"
				+ "\n 7"
				+ "\n 8"
				+ "\n 9"
				+ "\n 10");
	}
}
