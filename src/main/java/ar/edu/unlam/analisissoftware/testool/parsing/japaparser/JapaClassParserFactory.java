package ar.edu.unlam.analisissoftware.testool.parsing.japaparser;

import ar.edu.unlam.analisissoftware.testool.parsing.ClassParser;
import ar.edu.unlam.analisissoftware.testool.parsing.ClassParserFactory;

public class JapaClassParserFactory implements ClassParserFactory {

	public ClassParser getClassParser() {
		return new JapaClassParser();
	}

}
