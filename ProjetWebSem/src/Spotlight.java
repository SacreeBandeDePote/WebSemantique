import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.json.*;

public class Spotlight {
	public static JSONObject SpotlightText (String text) throws IOException {
		String url = "http://model.dbpedia-spotlight.org/fr/annotate";
		HTTPRequest httpReq = new HTTPRequest(url);
		httpReq.setHeader("Accept", "application/json");
		httpReq.addParam("text", URLEncoder.encode(text, "utf-8"));
		httpReq.addParam("confidence", "0.1");
		String res = httpReq.doRequest();
		System.out.println(res);
		JSONObject json = new JSONObject(res);
		System.out.println(json);
		return json;
	}
		public static void main(String[] args) throws IOException {
			JSONObject jsontest;
			jsontest = SpotlightText("Donal Trump Barrack Obama George Bush");
		
		}
}