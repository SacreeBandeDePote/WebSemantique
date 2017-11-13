import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir votre requête :");
		String query =  sc.nextLine();
		sc.close();
		ArrayList<String> urls = ExtractURL.extractURLFromRequest(query);
		ArrayList<JSONArray> uris = new ArrayList<JSONArray>();
		for(String url : urls) {
			System.out.println(url);
			String htmlText = HTTPRequest.simpleRequest(url);
			String onlyText = TextExtractor.getTextFromHTML(htmlText);
			uris.add(Spotlight.spotlightText(onlyText));
			System.out.println(uris);
		}
		System.out.println(uris);
	}

}
