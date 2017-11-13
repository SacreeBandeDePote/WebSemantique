import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class RDF {

	public static Model model;

	public static void main(String[] args) {

		model = ModelFactory.createDefaultModel();

	}

	public static void createRDF(ResultSet res) {
		
		OutputStream resultats = new OutputStream();
		ResultSetFormatter.outputAsXML(resultats, res);
		try(  PrintWriter out = new PrintWriter( "filename.txt" )  ){
		    out.println( resultats.toString() );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			/*String resultats = ResultSetFormatter.asText(res);
			try(  PrintWriter out = new PrintWriter( "filename.txt" )  ){
			    out.println( resultats );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}*/
	}

}