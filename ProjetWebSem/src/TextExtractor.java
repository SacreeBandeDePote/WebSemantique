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

		while (line != null) {
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

}
