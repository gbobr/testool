package ar.edu.unlam.analisissoftware.testool.parsing.japaparser;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.parsing.ClassParser;
import ar.edu.unlam.analisissoftware.testool.parsing.ParserException;

public class JapaClassParser implements ClassParser {

	@SuppressWarnings("unchecked")
	public Class getClass(File file) throws ParserException {
			try{
				CompilationUnit cu=JavaParser.parse(file);				
				JapaMethodVisitor jmv=new JapaMethodVisitor();
				jmv.visit(cu,null);
				return new Class(cu.getClass().getName(),jmv.getMethods(),this);
			} catch (Exception e){
				e.printStackTrace();
				throw new ParserException(e);
			}
		
	}
	
	@SuppressWarnings("rawtypes")
	private class JapaMethodVisitor extends VoidVisitorAdapter {
		List<Method> methods=new ArrayList<Method>();
		
	    @Override
	    public void visit(MethodDeclaration n, Object arg) {
	        System.out.println(n.getName());
	        methods.add(new JapaMethodParser(n).getMethod());
	    }
	    
	    public List<Method> getMethods(){
	    	return methods;
	    }
	}

}
