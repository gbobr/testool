package ar.edu.unlam.analisissoftware.testool.parsing.japaparser;

import japa.parser.ast.body.MethodDeclaration;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.parsing.MethodParser;

public class JapaMethodParser implements MethodParser {
	MethodDeclaration declaration;

	public JapaMethodParser(MethodDeclaration declaration){
		this.declaration=declaration;
	}
	
	public Method getMethod() {
		return new Method(declaration.getName(),declaration.getBody().toString(),this);
	}
}
