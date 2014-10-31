package ar.edu.unlam.analisissoftware.testool.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.unlam.analisissoftware.testool.parsing.ClassParserFactory;
import ar.edu.unlam.analisissoftware.testool.model.Class;

public class ParserService {
	ClassParserFactory parserFactory;
	
	@Autowired
	public ParserService(ClassParserFactory parserFactory) {
		super();
		this.parserFactory = parserFactory;
	}

	public Class parse(File file){
		return parserFactory.getClassParser().getClass(file);
	}
}
