import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ExtractHTML {

	private static final String CACHE_DIRECTORY = "cache/";

	public static void main(String args[])
	{
		String qry = "";

		extractURLFromRequest(qry);
	}
	public static ArrayList<String> extractURLFromRequest(String qry){

		ArrayList<String> list = new ArrayList<String>();

		File cacheFile = new File(CACHE_DIRECTORY + qry +".txt");
		if(!cacheFile.exists()) {
			GoogleRequest gr = new GoogleRequest();
			list = gr.getRequestResult(qry);
		} else {
			try {
				System.out.println("Output from cache file .... \n");
				BufferedReader reader = null;
				reader = new BufferedReader(new FileReader(CACHE_DIRECTORY + qry + ".txt"));

				String line = reader.readLine();

				while( line != null ) {
					System.out.println(line);
					list.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch( Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}