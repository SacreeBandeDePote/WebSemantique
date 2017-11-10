import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.json.*;

public class Spotlight {
	public static JSONArray SpotlightText (String text) throws IOException {
		
		final String url = "http://model.dbpedia-spotlight.org/fr/annotate";
		
		String conf = "0.3";
		
		HTTPRequest httpReq = new HTTPRequest(url);
		httpReq.setHeader("Accept", "application/json");
		httpReq.addParam("text", URLEncoder.encode(text, "utf-8"));
		httpReq.addParam("confidence", conf);
		
		String res = httpReq.doRequest();
		
		JSONObject json = new JSONObject(res);
		JSONArray test = (JSONArray) json.get("Resources");
		JSONArray URIs = new JSONArray();
		
		for(int i =0; i<test.length(); i++) {
			JSONObject tmp = test.getJSONObject(i);
			URIs.put(tmp.get("@URI"));
		}
			return URIs;
	}
		public static void main(String[] args) throws IOException {
			String text = "La ville de Berlin se situe dans le nord-est de l'Allemagne, dans la plaine germano-polonaise, à 33 m d'altitude, au confluent de la Spree et de la Havel. Une particularité de la ville est la présence de nombreux lacs et rivières, le long des cours d'eau. On en trouve plusieurs à l'ouest, mais aussi à l'est avec le Müggelsee. Berlin est égayée par plusieurs rivières, canaux, parcs et lacs (Havel, Wannsee, Müggelsee, Spree, Dahme, Landwehrkanal). Elle possède en outre une architecture ancienne et classique très riche.";
			JSONArray jsontest = SpotlightText(text);
			System.out.println(jsontest);
		}
}