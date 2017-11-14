import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.resultset.RDFOutput;

public class RDF {
	
	private final static String OUTPUT_DIRECTORY = "output/";

	public static void createRDF(ResultSet res, String query) throws FileNotFoundException {
		
		ResultSet restxt = res;
		
		FileOutputStream file = new FileOutputStream(OUTPUT_DIRECTORY + "output_" + query + ".xml");
		
		try(  PrintWriter out = new PrintWriter( OUTPUT_DIRECTORY + "output_" + query + ".txt" )  ){
		    out.println( ResultSetFormatter.asText(restxt) );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		RDFOutput.outputAsRDF(file, "RDF/XML", res);
		
	}

}