package ar.edu.unlam.analisissoftware.testool.model;

import ar.edu.unlam.analisissoftware.testool.parsing.MethodParser;

public class Method {
	private String name;
	private String reportUrl;
	private String code;
	private MethodParser parser;
	
	public Method(String name, String code, MethodParser parser) {
		this(name,code);
		this.parser=parser;
	}
	
	public Method(String name, String reportUrl, String code) {
		this(name,code);
		this.name = name;
		this.reportUrl = reportUrl;
		this.code=code;
	}
	
	public Method(String name, String code) {
		super();
		this.name=name;
		this.code=code;
		this.reportUrl = name + ".html";
	}

	public String getName() {
		return name;
	}
	public String getReportUrl() {
		return reportUrl;
	}
	public String getCode() {
		return code;
	}
	
	public MethodParser getParser(){
		return parser;
	}
}
