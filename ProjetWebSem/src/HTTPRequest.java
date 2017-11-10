import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class HTTPRequest {
	
	private URL url;
	private Map<String, String> parameters;
	private HttpURLConnection con;
	
	public HTTPRequest(String url) throws IOException {
		this.url = new URL(url);
		this.con = (HttpURLConnection) this.url.openConnection();
		this.parameters = new HashMap<>();
	}
	
	public void setHeader(String key, String val) {
		con.setRequestProperty(key, val);
	}
	
	public void addParam(String key, String val) {
		parameters.put(key, val);
	}

	public String doRequest() throws IOException {
		StringBuffer content = new StringBuffer();
		
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();
		
		BufferedReader input = null;
		try {
			input = new BufferedReader(
					new InputStreamReader(con.getInputStream()));

			String inputLine;
			while ((inputLine=input.readLine())!=null) {
				content.append(inputLine);
				System.out.println(inputLine);
			}
		}
		finally {
			input.close();
			con.disconnect();
		}
		
		return content.toString();
	}


	public static void main(String[] args) throws IOException {
		URL url = new URL("http://model.dbpedia-spotlight.org/fr/annotate");
		StringBuffer content = new StringBuffer();
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put("text", URLEncoder.encode("Donald Trump, né le 14 juin 1946 à New York, est un homme d'affaires américain. Magnat milliardaire de l'immobilier, il est également animateur de télévision et homme politique", "utf-8"));
		parameters.put("confidence", "0.35");
		 
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
		out.flush();
		out.close();
		
		BufferedReader input = null;
		try {
			input = new BufferedReader(
					new InputStreamReader(con.getInputStream()));

			String inputLine;
			while ((inputLine=input.readLine())!=null) {
				content.append(inputLine);
				System.out.println(inputLine);
			}
		}
		finally {
			input.close();
			
			con.disconnect();
		}
	}
}
