package eu.uplinkgroup.qDispatcher.console;
import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class Helper {
		
	
	public static void main(String[] args) throws Exception {
		//Insert test here.
	}
}

class URLHelper {
	private String url = null;
	private String user = null;
	private String passwd = null;
	
	URLHelper(String gurl, String u, String pw){
		url = gurl;
		user = u;
		passwd = pw;
	}
	
	private String buildAddress(){
		String finalUrl = url + "?u=" + user + "&p=" + passwd;
		return finalUrl;
	}
	
	
	private String openAndCheck(String gurl) throws Exception {
		URL db = new URL(gurl);
		URLConnection con = db.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		String output;
		while ((inputLine = in.readLine()) != null) {
			output += inputLine;
		}
		in.close();
		return output;
	}
	
	public int chk(){
		String finalUrl = buildAddress();
		String output = openAndCheck(finalUrl);
		if (output == "OK"){
			return 0;
		} else if (output == "NO"){
			return -1;
		} else {
			return -2;
		}
		
	}
}