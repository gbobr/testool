package ar.edu.unlam.analisissoftware.testool;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import ar.edu.unlam.analisissoftware.testool.model.Class;
import ar.edu.unlam.analisissoftware.testool.model.Method;
import ar.edu.unlam.analisissoftware.testool.model.Metric;
import ar.edu.unlam.analisissoftware.testool.model.Report;
import ar.edu.unlam.analisissoftware.testool.service.ConfigService;
import ar.edu.unlam.analisissoftware.testool.service.VelocityReportingService;

/**
 * Unit test for simple App.
 */
public class VelocityReportingServiceTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public VelocityReportingServiceTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( VelocityReportingServiceTest.class );
    }

    private Report buildReport(){
    	Class myClass=new Class("ClaseDePrueba");
    	
    	Method myMethod=new Method("testMethod","#","public void testMethod(){"
    			+ "\n"
    			+ "\t//TODO: Hacer algo"
    			+ "\n}");
    	
    	myClass.addMethod(myMethod);
    	
    	Metric metric=new Metric(){
			@Override public String getName() { return "Mock metric"; }
			@Override public String internalGetValue() { return "10"; }
			@Override public void internalCalculate(Method method) {}
			@Override public Boolean hasBeenCalculated(){ return true; }
			@Override public String getValue() { return internalGetValue(); }
    	};
    	
    	Report report=new Report(myClass,myMethod);
    	report.addMetric(metric);
    	return report;
    }
    
    public void testReport()
    {
    	VelocityEngine ve=new VelocityEngine();
    	ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "src/main/resources/");
        VelocityReportingService vrs=new VelocityReportingService(ve, new ConfigService());
        String result=vrs.generateReport(buildReport(),"target/");
        assertNotNull(result);
        System.out.println(result);
    }
}

