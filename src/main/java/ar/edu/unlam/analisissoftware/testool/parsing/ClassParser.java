package ar.edu.unlam.analisissoftware.testool.parsing;

import java.io.File;
import ar.edu.unlam.analisissoftware.testool.model.Class;

public interface ClassParser {
	public Class getClass(File file) throws ParserException;
}
