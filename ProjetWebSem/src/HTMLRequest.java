import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLRequest {
	public static void main(String[] args) throws IOException {
		StringBuilder html = new StringBuilder();
		java.net.URL url = new URL("http://www.espn.com/nba/");
		
		BufferedReader input = null;
		try {
			input = new BufferedReader(
					new InputStreamReader(url.openStream()));

			String htmlLine;
			while ((htmlLine=input.readLine())!=null) {
				html.append(htmlLine);
				System.out.println(htmlLine);
			}
		}
		finally {
			input.close();
		}
	}
}
