package ar.edu.unlam.analisissoftware.testool;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ar.edu.unlam.analisissoftware.testool.dao.ProjectIterator;
import ar.edu.unlam.analisissoftware.testool.service.ConfigService;

public class Main 
{		
    @SuppressWarnings("resource")
	public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigService.class);
        File chosenDirectory=new File(args[0]);
        ProjectIterator pi=context.getBean(ProjectIterator.class);
        if(chosenDirectory.isDirectory());
        	pi.visitDirectory(chosenDirectory);       
    }
}
