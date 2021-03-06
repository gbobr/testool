package ar.edu.unlam.analisissoftware.testool.metrics;

import java.util.ArrayList;

import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;

public class HalsteadVolumeMetric extends Metric {

	private ArrayList<String> operadores = new ArrayList<String>();
	private ArrayList<String> operandosEncontrados = new ArrayList<String>();
	private int N1=0;
	private int N2=0;
	private int n1=0;
	private int n2=0;
	@Override
	public String getName() {
		return "Volumen de Halstead: ";
	}

	@Override
	public String internalGetValue() {
	Integer longitud = N1+N2;
	Integer vocabulario = n1+n2;
	Double volumen = longitud * Math.log(vocabulario)/Math.log(2);
	volumen = (double) Math.round(volumen);
	
	
	
	return volumen.toString();
		
	}

	@Override
	public void internalCalculate(Method method) {
		if(operadores.size() == 0)
			incluirOperadores();
		  N1=0;
		  N2=0;
		  n1=0;
		  n2=0;
		for (String operador : operadores){  		      
		      int cant=0;
		      if(method.getCode().contains(operador)){
		    	  n1++;
		    	  String codigo = method.getCode();		    	  
		    	  while (codigo.indexOf(operador) > -1)
		    	   {
		    		 codigo = codigo.substring(codigo.indexOf(operador)+operador.length(),codigo.length());
		    	     cant++;		    	    
		    	   }		
		    	  if(operador.contains("{") && cant > 0)
		    	    	 cant--;
		      }	
		      N1+=cant;
		} 
		
		 
		String[] lines = method.getCode().split("\n");
		for( String line : lines ){	
			String Linea = line.toString().trim();
			if( Linea.indexOf(" ") > -1){
				String primer_palabra = Linea.substring(0, Linea.indexOf(" "));
				if(!primer_palabra.contains(".") && !primer_palabra.contains("return")){
					Linea = Linea.substring(Linea.indexOf(primer_palabra)+primer_palabra.length(),Linea.length()).trim();
				
					if(Linea.contains("=") && Linea.contains("new") && Linea.contains(primer_palabra+"("))
						operandosEncontrados.add(Linea.substring(0, Linea.indexOf("=")));
					else
						if(Linea.contains(";") && !Linea.contains (",") && !Linea.contains("(")&& !Linea.contains(" "))
							operandosEncontrados.add(Linea.substring(0, Linea.indexOf(";")));	
						else
							if(Linea.contains(";") && Linea.contains(",") && !Linea.contains("(")){
								
								String conComa = Linea.substring(0, Linea.indexOf(","));
								operandosEncontrados.add(conComa);
								Linea = Linea.substring(Linea.indexOf(",")+1, Linea.indexOf(";")).trim();
								while(Linea.indexOf(",") > -1){
									conComa = Linea.substring(0, Linea.indexOf(","));
									operandosEncontrados.add(conComa);
									Linea = Linea.substring(Linea.indexOf(",")+1, Linea.indexOf(";"));
									
								}
								operandosEncontrados.add(Linea);								
							}											
				}
			
			}
		}
		
		for (String operando : operandosEncontrados){  	
			int cant=0;
		      if(method.getCode().contains(operando)){
		    	  n2++;
		    	  String codigo = method.getCode();		    	  
		    	  while (codigo.indexOf(operando) > -1)
		    	   {
		    		 codigo = codigo.substring(codigo.indexOf(operando)+operando.length(),codigo.length());
		    	     cant++;
		    	   }		    	 
		      }	
		      N2+=cant;
		}
		

	}
	private void incluirOperadores(){
		operadores.add("if");
		operadores.add("for");
		operadores.add("while");
		operadores.add("else");
		operadores.add("{");
		operadores.add("=");
		operadores.add("<");		
		operadores.add("<=");
		operadores.add("==");
		operadores.add(">");
		operadores.add("[");
		operadores.add(";\n");
		operadores.add("++");
		operadores.add("--");
		
		
		
	}
}
