import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * an example of how to open a url and read from it
 * exercise: open a remote xml file and parse it using the parsing code we did last week!
 * @author gares
 *
 */
public class TestURLConnection {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		URL myURL = new URL("https://en.wikipedia.org/wiki/Java_(programming_language)");
		System.out.println("Connecting to: " + myURL);
		URLConnection uc = myURL.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		String inputLine;
		while((inputLine = in.readLine()) != null){
			System.out.println(inputLine);
		}
		in.close();
		
	}

}