package networks;

import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WriteToConnection {
    public static void main(String[] args) {
        try {
            
            URL url = new URL("https://postman-echo.com/post"); 

            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            
            connection.setRequestMethod("POST");

            
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8"); 
            connection.setRequestProperty("Accept", "*/*"); 

            
            String data = "message=HelloWorld";

            
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes("utf-8"); // Convert string to bytes
                os.write(input, 0, input.length); // Write data to output stream
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }
                System.out.println("Response: " + response.toString());
            }
            
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
