import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class RDF {

	public static JSONArray jsonListe;

	public static void main(String[] args) {

		jsonListe = new JSONArray();

		JSONObject json1 = new JSONObject("{\"sujet\":\"Donald Trump\",\"verbe\":\"président\",\"complément\": \"Etats-Unis\"}");
		JSONObject json2 = new JSONObject("{\"sujet\":\"Donald Trump\",\"verbe\":\"habite\",\"complément\":\"New York\"}");
		JSONObject json3 = new JSONObject("{\"sujet\":\"John Doe\",\"verbe\":\"habite\",\"complément\":\"New York\"}");
		JSONObject json4 = new JSONObject("{\"sujet\":\"John Doe\",\"verbe\":\"déteste\",\"complément\":\"Donald Trump\"}");
		JSONObject json5 = new JSONObject("{\"sujet\":\"John Doe\",\"verbe\":\"citoyen\",\"complément\":\"Etats-Unis\"}");

		jsonListe.put(json1);
		jsonListe.put(json2);
		jsonListe.put(json3);
		jsonListe.put(json4);
		jsonListe.put(json5);

	}

	public static void createRDF(JSONArray jsonListe) {

	}


}