import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.resultset.RDFOutput;

public class RDF {
	
	private final static String OUTPUT_DIRECTORY = "output/";

	public static void main(String[] args) {

	}

	public static void createRDF(ResultSet res, String query) throws FileNotFoundException {
		
		FileOutputStream file = new FileOutputStream(OUTPUT_DIRECTORY+"output_"+query+".xml");
		
		/*Model model = RDFOutput.encodeAsModel(res);
		
		model.write(file, "RDF/XML");*/
		
		RDFOutput.outputAsRDF(file, "RDF/XML", res);
		//ResultSetFormatter.outputAsXML(file, res);
	}

}