import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

	public static String simpleRequest(String urlToRequest) {
		try {
			URL urll = new URL(urlToRequest);
			
			URLConnection co = urll.openConnection();
			Scanner scanner = new Scanner(co.getInputStream());
			scanner.useDelimiter("\\Z");
			String string = scanner.next();
				
			return string;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
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
			input = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			while ((inputLine = input.readLine()) != null) {
				content.append(inputLine);
				// System.out.println(inputLine);
			}
		} finally {
			if (input != null) {
				input.close();
			}
			con.disconnect();
		}

		return content.toString();
	}

	public static void main(String[] args) throws IOException {
	}
}
