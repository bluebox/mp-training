
package Challange;


import java.io.IOException;
import java.nio.file.Path;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

record OrderDetails(int detailid, String description, int qty) {
    OrderDetails(String description, int qty) {
        this(-1, description, qty);
    }
    public String toJSON() {
        return new StringJoiner(", ", "{", "}")
                .add("\"itemDescription\":\"" + description + "\"")
                .add("\"qty\":" + qty)
                .toString();
    }
}

record Order(int orderid, String date, List<OrderDetails> list) {
    Order(String date) {
        this(-1, date, new ArrayList<>());
    }

    public void addDetail(String itemDescription, int qty) {
        list.add(new OrderDetails(itemDescription, qty));
    }
    
    public String getDetailsJson() {
        StringJoiner jsonString = new StringJoiner(",", "[", "]");
        list.forEach((d) -> jsonString.add(d.toJSON()));
        return jsonString.toString();
    }
}

public class Main {
	private static Connection conn;

    public static void main(String[] args) {

        List<Order> orders = readData();

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/", "devuser", "Vardhan@123");
            System.out.println("Connection established.");
        	CallableStatement cs = conn.prepareCall(
                    "{ CALL storefront.addOrder(?, ?, ?, ?) }");

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("G yyyy-MM-dd HH:mm:ss")
                                    .withResolverStyle(ResolverStyle.STRICT);

            orders.forEach((o) -> {
                try {
                    LocalDateTime localDateTime =
                            LocalDateTime.parse("AD " + o.date(), formatter);
                    Timestamp timestamp = Timestamp.valueOf(localDateTime);
                    cs.setTimestamp(1, timestamp);
                    cs.setString(2, o.getDetailsJson());
                    cs.registerOutParameter(3, Types.INTEGER);
                    cs.registerOutParameter(4, Types.INTEGER);
                    cs.execute();
                    System.out.printf("%d records inserted for %d (%s)%n",
                            cs.getInt(4),
                            cs.getInt(3),
                            o.date());
                } catch (Exception e) {
                    System.out.printf("Problem with %s : %s%n", o.date(),
                            e.getMessage());
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    private static List<Order> readData() {
        List<Order> orderList = new ArrayList<>();
        try (Scanner scanner = new Scanner(Path.of("/home/vardhan/eclipse-workspace/Day13/src/Orders.csv"))) {
            scanner.useDelimiter("[,\\n]");
            List<String> tokens = scanner.tokens().map(String::trim).toList();

            for (int i = 0; i < tokens.size(); i++) {
                String token = tokens.get(i);
                if (token.equalsIgnoreCase("order")) {
                    String date = tokens.get(++i);
                    orderList.add(new Order(date));
                } else if (token.equalsIgnoreCase("item")) {
                    int qty = Integer.parseInt(tokens.get(++i));
                    String description = tokens.get(++i);
                    Order currentOrder = orderList.get(orderList.size() - 1);
                    currentOrder.addDetail(description, qty);
                }
            }

            orderList.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orderList;
    }
    }