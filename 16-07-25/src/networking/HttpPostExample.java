package networking;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class HttpPostExample {
  public static void main(String[] args) {
    try {
      URL url = new URL("https://localhost:8080");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setDoOutput(true);
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      String params = "username=user&password=pass";
      try(OutputStream os = con.getOutputStream()) {
        os.write(params.getBytes(StandardCharsets.UTF_8));
      }

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
