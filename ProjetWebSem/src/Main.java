import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir votre requête :");
		String query =  sc.nextLine();
		sc.close();
		ArrayList<String> urls = GoogleRequest.getRequestResult(query);

	}

}
