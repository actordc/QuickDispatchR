package eu.uplinkgroup.qDispatcher.console;
//import java.util.ArrayList;
import java.net.*;
import java.io.*;

public class Helper {
		
	
	public static void main(String[] args) throws Exception {
		//Nothing in here...
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
		String output = "";
		while ((inputLine = in.readLine()) != null) {
			output += inputLine;
		}
		in.close();
		return output;
	}
	
	public int chk() throws Exception{
		String finalUrl = buildAddress();
		String output = openAndCheck(finalUrl);
		if (output.matches("OK")){
			return 0;
		} else if (output.matches("NO")){
			return -1;
		} else {
			return -2;
		}
		
	}
}

class PasswordHelper {
	private char[] pass;
	private String converted = "";
	
	PasswordHelper(char[] password){
		pass = password;
		
	}
	
	public String parse(){
		for (char e : pass){
			converted += e;
		}
		
		return converted;
	}
	
}

class CBoxHelper {
	private String content;
	private String host;
	
	CBoxHelper(String c){
		content = c;
	}
	
	public String parse(){
		if (content.equals("UplinkGroup.eu")) {
			host = "http://danube.uplinkgroup.eu/index.php";
		} else if (content.equals("CaptainCode.net")) {
			host = "http://danube.captaincode.eu/index.php";
		} else {
			host = "ERROR";
		}
		
		return host;
	}
}