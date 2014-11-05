package ar.edu.unlam.analisissoftware.testool.metrics;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class CommentPercentMetric extends Metric {
	private LocMetric locMetric;
	private CommentMetric commentMetric;
	
	private Double commentPercent;
	
	@Autowired
	public CommentPercentMetric(LocMetric locMetric, CommentMetric commentMetric){
		this.locMetric=locMetric;
		this.commentMetric=commentMetric;
	}
	
	@Override
	public String getName() {
		return "Lineas de código comentadas: ";
	}

	@Override
	public String internalGetValue() {
		return commentPercent.toString() + "%";
	}

	@Override
	public void internalCalculate(Method method) {
		commentPercent=commentMetric.getRawValue() * 100.0D / locMetric.getRawValue();
	}

}
