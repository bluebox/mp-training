package networking;
import java.net.*;
import java.io.*;

public class HttpGetExample {
  public static void main(String[] args) {
    try {
      URL url = new URL("https://www.google.com");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");

      int status = con.getResponseCode();
      System.out.println("Status: " + status);

      try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        String input;
        while ((input = in.readLine()) != null) {
          System.out.println(input);
        }
      }
      con.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
