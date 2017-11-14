import java.io.IOException;
import java.net.URLEncoder;
import org.json.*;

public class Spotlight {
	public static JSONArray spotlightText(String text) throws IOException, JSONException {

		final String url = "http://model.dbpedia-spotlight.org/fr/annotate";

		String conf = "0.3";

		HTTPRequest httpReq = new HTTPRequest(url);
		httpReq.setHeader("Accept", "application/json");
		httpReq.addParam("text", URLEncoder.encode(text, "utf-8"));
		httpReq.addParam("confidence", conf);

		String res = httpReq.doRequest();

		JSONObject json = new JSONObject(res);
		JSONArray resources = (JSONArray) json.get("Resources");
		JSONArray URIs = new JSONArray();

		for (int i = 0; i < resources.length(); i++) {
			JSONObject tmp = resources.getJSONObject(i);
			URIs.put(tmp.get("@URI"));
		}
		return URIs;
	}

	/*MAIN FOR TESTING CLASS
	public static void main(String[] args) throws IOException {
		String text = "La ville de Berlin se situe dans le nord-est de l'Allemagne, dans la plaine germano-polonaise, à 33 m d'altitude, au confluent de la Spree et de la Havel. Une particularité de la ville est la présence de nombreux lacs et rivières, le long des cours d'eau. On en trouve plusieurs à l'ouest, mais aussi à l'est avec le Müggelsee. Berlin est égayée par plusieurs rivières, canaux, parcs et lacs (Havel, Wannsee, Müggelsee, Spree, Dahme, Landwehrkanal). Elle possède en outre une architecture ancienne et classique très riche.";
		JSONArray jsontest = spotlightText(text);
		System.out.println(jsontest);
		
		System.out.println(jsontest.get(0));
	}*/
}