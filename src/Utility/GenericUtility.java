package Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenericUtility {
	public static int hitAnyUrlUsingGetMethod(String url) throws Exception {
		final String USER_AGENT = "Mozilla/5.0";
		int status=0;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return status=1;

	}

	public static void callGetOfMail() throws Exception {

		String url = "http://localhost:8080/MailSender/controller?mode=sendMail";
		int status=hitAnyUrlUsingGetMethod(url);
		if(status==1){
			shutdownServer();
		}
		
		
	}

	public static void callGetOfCronJobHitter() throws Exception {
		System.out.println("In callget cjh");
		String url = "http://localhost:8081/Cronjob/CronJobHitter";
		hitAnyUrlUsingGetMethod(url);
		
	}
	
	public static void startServer() throws IOException {
		System.out.println("in start server");
		String path = System.getProperty("user.home")
				+ "/Desktop/startServer.bat";
		String[] command = { "cmd.exe", "/C", "Start", path };
		Process run = Runtime.getRuntime().exec(command);
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				System.out.println("in start server run");
				try {
					GenericUtility.callGetOfMail();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 5000);
	}
	
	public static void  shutdownServer() throws IOException {
		System.out.println("in start server");
		String path = System.getProperty("user.home")
				+ "/Desktop/shutdownServer.bat";
		String[] command = { "cmd.exe", "/C", "Start", path };
		Process run = Runtime.getRuntime().exec(command);

	}
}
