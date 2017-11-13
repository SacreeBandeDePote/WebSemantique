import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TextExtractor {

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
		System.out.println(getTextFromHTMLV2(full));
		
	}

	public static String getTextFromHTMLV2(final String str) {
		 Document doc = Jsoup.parse(str);
		 String text = doc.text();
		 return text;
	}
}


