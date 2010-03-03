/**
 * CombineQueryAndCasesMethod.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 05/01/2007
 */
package jcolibri.method.reuse;


import java.util.*;
import jcolibri.cbrcore.*;
import static jcolibri.util.CopyUtils.*;
/**
 * Method to combine the description of a query with the other components of a case: solution, result and justification of solution.
 * @author Juan A. Recio-Garcia
 * @version 2.0
 */
public class CombineQueryAndCasesMethod {

	/**
	 * Combienes some cases with a query. 
	 * This method creates a new copy for each case and overwrites their description with the description of the query.
	 */
	public static Collection combine(CBRQuery query, Collection cases)
	{
		ArrayList res = new ArrayList();
		
		for(Object on: cases)
		{
			CBRCase orig = (CBRCase)on;
			try {
				CBRCase copy = (CBRCase)orig.getClass().newInstance();
				
				copy.setDescription(copyCaseComponent(query.getDescription()));
				copy.setSolution(copyCaseComponent(orig.getSolution()));
				copy.setJustificationOfSolution(copyCaseComponent(orig.getJustificationOfSolution()));
				copy.setResult(copyCaseComponent(orig.getResult()));
				
				res.add(copy);
				
				
			} catch (Exception e) {
				org.apache.commons.logging.LogFactory.getLog(CombineQueryAndCasesMethod.class).error("Error combining cases and query", e);
			} 
		}	
		return res;
	}
}
