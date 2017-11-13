import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TextExtractor {

	private static final Pattern REGEX_P = Pattern.compile("<p.*?>(.+?)</p>");
	private static final Pattern REGEX_NO_BRACKETS = Pattern.compile("(.*?)<.+?>(.*)");

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader("test.html"));

		String line = reader.readLine();

		//String full = new String(line);

		while (line != null) {
			//full.concat(line);
			list.add(line);
			line = reader.readLine();
		}
		String full = String.join("\n", list);
		reader.close();
		getTextFromHTMLV2(full);
	}

	private static String getTextFromHTMLV2(final String str) {
		 Document doc = Jsoup.parse(str);
		 String text = doc.text();
		 System.out.println(text);
		 return text;
	}
	
	private static String getTextFromHTML(final String str) {
		if(str != null) {
			List<String> tagValues = new ArrayList<String>();
			Matcher matcher = REGEX_P.matcher(str);
			while (matcher.find()) {
				tagValues.add(matcher.group(1));
				System.out.println(matcher.group(1));
			}
			String text = String.join("", tagValues);

			tagValues.clear();

			Matcher matcher2 = REGEX_NO_BRACKETS.matcher(text);
			while(matcher2.find()) {
				//tagValues.add(matcher.group(1));
				System.out.println(matcher2.group(2));
			}

			return text;
		} else {
			return null;
		}
=======

	public static String getTextFromHTML(final String str) {
	    final List<String> tagValues = new ArrayList<String>();
	    final Matcher matcher = REGEX_P.matcher(str);
	    while (matcher.find()) {
	        tagValues.add(matcher.group(1));
	        System.out.println(matcher.group(1));
	    }
	    
	    String text = String.join("", tagValues);
	    
	    return text;
>>>>>>> cb0f6599ead9a5283f053b25ec82355cc4a97c2d
	}
}
