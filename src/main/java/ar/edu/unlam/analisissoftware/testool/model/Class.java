package ar.edu.unlam.analisissoftware.testool.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.analisissoftware.testool.parsing.ClassParser;

public class Class {
	private List<Method> methods;
	private String name;
	private ClassParser parser;
	
	public Class(String name){
		this.name=name;
		methods=new ArrayList<Method>();
	}
	
	public Class(String name, List<Method> methods, ClassParser parser){
		this.name=name;
		this.methods=methods;
		this.parser=parser;
	}

	public List<Method> getMethods() {
		return methods;
	}
	
	public void addMethod(Method method){
		methods.add(method);
	}

	public String getName() {
		return name;
	}
	
	public ClassParser getParser(){
		return parser;
	}
	
}
