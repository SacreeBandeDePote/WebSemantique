import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextExtractor {

	private static final Pattern REGEX_P = Pattern.compile("<p.*?>(.+?)</p>");
	private static final Pattern REGEX_NO_BRACKETS = Pattern.compile("");
	
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
		getTextFromHTML(full);
	}


	public static String getTextFromHTML(final String str) {
	    final List<String> tagValues = new ArrayList<String>();
	    final Matcher matcher = REGEX_P.matcher(str);
	    while (matcher.find()) {
	        tagValues.add(matcher.group(1));
	        System.out.println(matcher.group(1));
	    }
	    
	    String text = String.join("", tagValues);
	    
	    return text;
	}
}
