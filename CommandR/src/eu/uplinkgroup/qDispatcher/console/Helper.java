package eu.uplinkgroup.qDispatcher.console;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class Helper {
		
	
	public static void main(String[] args) throws Exception {
		System.out.println("oasch");
		URLHelper uhelp = new URLHelper("http://danube.uplinkgroup.eu/index.php");
		uhelp.openConnection("http://danube.uplinkgroup.eu/index.php?test=shit");
		
	}
}

class URLHelper {
	private String url = null;
	
	URLHelper(String gurl){
		url = gurl;
	}
	
	public void openConnection(String gurl) throws Exception {
		URL db = new URL(gurl);
		URLConnection con = db.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		in.close();
	}
}