
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleExample {
    public static void main(String[] args) {
        // Set the desired locale (for example, French)
        Locale locale = new Locale("fr", "FR");
        
        // Load the resource bundle
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", locale);

        // Get the current date and time
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String formattedDate = now.format(formatter);

        // Display localized messages
        System.out.println(messages.getString("greeting"));
        System.out.println(messages.getString("date_time_message").replace("{0}", formattedDate));
    }
}

