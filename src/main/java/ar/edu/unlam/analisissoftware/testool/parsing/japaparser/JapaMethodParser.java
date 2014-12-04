package ar.edu.unlam.analisissoftware.testool.parsing.japaparser;

import java.util.Collection;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.comments.Comment;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.parsing.MethodParser;

public class JapaMethodParser implements MethodParser {
	MethodDeclaration declaration;

	public JapaMethodParser(MethodDeclaration declaration){
		this.declaration=declaration;
	}
	
	public Method getMethod() {
		Method m = new Method(declaration.getName(),declaration.getBody().toString(),this);
		
		for(Comment c : declaration.getAllContainedComments()){
			m.addComment(c.toString());
		}
		
		return m;
	}

}
