package ar.edu.unlam.analisissoftware.testool;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ar.edu.unlam.analisissoftware.testool.dao.ProjectIterator;
import ar.edu.unlam.analisissoftware.testool.service.ConfigService;

public class Main 
{		
	static final Logger logger = LoggerFactory.getLogger(Main.class);
	
    @SuppressWarnings("resource")
	public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigService.class);
        File chosenDirectory=new File(args[0]);
        File outputDir=new File(args[1]);
        
        ProjectIterator pi=context.getBean(ProjectIterator.class);
        logger.info("Comenzando análisis del proyecto con path '" + chosenDirectory.getAbsolutePath() + "'");
        
        if(chosenDirectory.isDirectory()){
			try {		    	
		    	pi.analyzeProject(chosenDirectory,outputDir);				
			} catch(Exception ex) {
				logger.error("No se puede analizar el proyecto.", ex);
			}
        } else {
        	logger.error("El directorio elegido no existe o es incorrecto.");
        }
        
        logger.info("Análisis completado");
    }
}
