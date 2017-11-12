import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class GoogleRequest {

	private final static String API_KEY = "AIzaSyBspyc1fD-X92bkL7RdKoiI55c2n3Hr_vE";
	private final static String CACHE_DIRECTORY = "cache/";

	public ArrayList<String> getRequestResult(String search) {

		ArrayList<String> list = new ArrayList<String>();

		URL url;
		try {
			url = new URL("https://www.googleapis.com/customsearch/v1?key=" + API_KEY
					+ "&cx=013036536707430787589:_pqjad5hr1a&q=" + search + "&alt=json");

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");

			PrintWriter writer = new PrintWriter(CACHE_DIRECTORY + search + ".txt");

			while ((output = br.readLine()) != null) {
				if (output.contains("\"link\": \"")) {
					String link = output.substring(output.indexOf("\"link\": \"") + ("\"link\": \"").length(),
							output.indexOf("\","));
					System.out.println(link); // Will print the google search links
					list.add(link);
					writer.println(link);
				}
			}
			writer.close();
			urlConnection.disconnect();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}