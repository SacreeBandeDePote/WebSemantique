import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ExtractHTML {

	public static final String CACHE_DIRECTORY = "cache/";
	
	public static void main(String[] args) throws IOException {
		String key = "AIzaSyBspyc1fD-X92bkL7RdKoiI55c2n3Hr_vE";
		String qry="Toz";

		ArrayList<String> list = new ArrayList<String>();

		File cacheFile = new File(CACHE_DIRECTORY + qry +".txt");
		if(!cacheFile.exists()) {

			URL url = new URL(
					"https://www.googleapis.com/customsearch/v1?key="+key+ "&cx=013036536707430787589:_pqjad5hr1a&q="+ qry + "&alt=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			
			PrintWriter writer = new PrintWriter(CACHE_DIRECTORY + qry+".txt", "UTF-8");
			
			while ((output = br.readLine()) != null) {
				if(output.contains("\"link\": \"")){                
					String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
					System.out.println(link);       //Will print the google search links
					list.add(link);
					writer.println(link);
				}     
			}
			writer.close();
			conn.disconnect();
		} else {
			BufferedReader reader = null;
			reader = new BufferedReader(new FileReader(CACHE_DIRECTORY + qry + ".txt"));
			
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while( line != null ) {
				System.out.println(line);
			}
		}
	}   
}