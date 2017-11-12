import java.util.List;
import java.util.ArrayList;

public class Sparql {
	public static void main(String[] args) {
		List<String> url = new ArrayList<String>();
		url.add("test");
		url.add("tamere");
		String test = createQuery(url);
		System.out.println(test);
	}

	public static String createQuery(List<String> urls) {
		String query = "SELECT * WHERE";
		for (int i = 0; i < urls.size(); i++) {
			if (i == 0) {
				query += "{ FILTER(?s IN (<" + urls.get(i) + ">";
			} else {
				query += ", <" + urls.get(i) + ">";
			}
		}
		query += ")). ?s ?p ?o. }";
		return query;
	}

	public void executeQuery(String query) {

	}
}
