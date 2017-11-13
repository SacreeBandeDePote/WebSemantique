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

public class TextExtractor {

	private static final Pattern REGEX = Pattern.compile("<p.*?>(.+?)</p>");
	
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
		getTagValues(full);
	}

	private static List<String> getTagValues(final String str) {
	    final List<String> tagValues = new ArrayList<String>();
	    final Matcher matcher = REGEX.matcher(str);
	    while (matcher.find()) {
	        tagValues.add(matcher.group(1));
	        System.out.println(matcher.group(1));
	    }
	    return tagValues;
	}
}
