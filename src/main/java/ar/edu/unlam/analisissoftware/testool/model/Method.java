package ar.edu.unlam.analisissoftware.testool.model;

import java.util.ArrayList;

import ar.edu.unlam.analisissoftware.testool.parsing.MethodParser;

public class Method {
	private String name;
	private String reportUrl;
	private String code;
	private MethodParser parser;
	private Class mClass;
	private ArrayList<String> comments;
	
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
		this.comments=new ArrayList<String>();
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

	public Class getMethodClass() {
		return mClass;
	}

	public void setMethodClass(Class methodClass) {
		this.mClass = methodClass;
	}

	public ArrayList<String> getComments() {
		return comments;
	}
	
	public void addComment(String comment){
		this.comments.add(comment);
	}
}
