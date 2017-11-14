import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.json.JSONArray;

import java.io.IOException;

public class Sparql {

	public static String createFilter(JSONArray uris) {
		String filter = "";
		
		//int i = 0;
		
		for (int i = 0; i < uris.length()/100; i++) {
			String uri = (String) uris.get(i);
			filter = filter + "<"
					+ uri
					+ ">,\n";
		}
		
		String uri = (String) uris.get(uris.length()/100);
		filter = filter + "<"
				+ uri.toString()
				+ ">";
		
		//System.out.println(filter);
		
		return filter;
	}
	
	public static Query createQuery(JSONArray uris) {
		
		String filter = createFilter(uris);
		
		String query = "select * where {"
				+ "FILTER (?s IN("
				+ filter
				+ "))."
				/*+ "FILTER (?c IN("
				+ filter
				+ "))."*/
				+ "?s ?v ?c.}";
		Query q = QueryFactory.create(query);
		
		
		return q;
	}
	
	public static ResultSet performeSparql(JSONArray uris) {
		Query q = createQuery(uris);
		QueryEngineHTTP queryEngine = QueryExecutionFactory.createServiceRequest("http://dbpedia.org/sparql", q);
		
		ResultSet res = queryEngine.execSelect();
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		ResultSet res = performeSparql(Spotlight.spotlightText("La ville de Berlin se situe dans le nord-est de l'Allemagne, dans la plaine germano-polonaise, à 33 m d'altitude, au confluent de la Spree et de la Havel. Une particularité de la ville est la présence de nombreux lacs et rivières, le long des cours d'eau. On en trouve plusieurs à l'ouest, mais aussi à l'est avec le Müggelsee. Berlin est égayée par plusieurs rivières, canaux, parcs et lacs (Havel, Wannsee, Müggelsee, Spree, Dahme, Landwehrkanal). Elle possède en outre une architecture ancienne et classique très riche."));
		
		while(res.hasNext()) {
			System.out.println("hello");
			QuerySolution sol = res.nextSolution();
			System.out.println(sol);
		}
	}
}
