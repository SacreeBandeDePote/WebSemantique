import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetRewindable;
import org.json.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir votre requête :");
		String query =  sc.nextLine();
		sc.close();
		ArrayList<String> urls = ExtractURL.extractURLFromRequest(query);
		ArrayList<JSONArray> uris = new ArrayList<JSONArray>();
		JSONArray uri = new JSONArray();
		int compt = 1;
		for(String url : urls) {
			String htmlText = HTTPRequest.simpleRequest(url);
			String onlyText = "";
			if (htmlText.length() != 0) {
			onlyText = TextExtractor.getTextFromHTMLV2(htmlText);
			}
			//System.out.println(onlyText);
			if (onlyText.length() != 0) {
				uri = Spotlight.spotlightText(onlyText);
				uris.add(uri);
			}
			
			ResultSet res = Sparql.performeSparql(uri);
			RDF.createRDF(res);
			while(res.hasNext()) {
				QuerySolution sol = res.nextSolution();
				//System.out.println(sol);
			}

			//System.out.println(compt);
			//System.out.println(uris);

			compt++;
		}
		//System.out.println(uris);
	}

}
