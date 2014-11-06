package ar.edu.unlam.analisissoftware.testool.reports;

public abstract class Report {
	private String reportPath;

	public Report(String reportPath) {
		super();
		this.reportPath = reportPath;
	}
	
	public String getReportPath() {
		return "file:/"+reportPath.replace("\\", "/");
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
}
