package ar.edu.unlam.analisissoftware.utils;

public final class StringTools {

	//
	// Count the number of occurrences of substring on string while it is not in a comment
	//
	public static int countOccurences(final String string, final String substring) {
		int count = 0;
		int idx = 0;
		
		int commentStart = string.indexOf("//");
		
		if( commentStart == -1 )
			commentStart = string.indexOf( "/*" );
		
		if( commentStart == -1 && string.indexOf( "*/" ) == -1 )
			commentStart = string.length();
	
		while ( ( idx = string.indexOf( substring, idx ) ) != -1 && idx <= commentStart ){
			idx++;
			count++;
		}
	
		return count;
	}

}
