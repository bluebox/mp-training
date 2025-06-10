import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementChallenge {
	 static String URL = "jdbc:mysql://localhost:3306/surya_medplus";
     static String USERNAME = "Surya";
     static String PASSWORD = "Surya525";
     static String ORDERS_CSV_FILE = "orders1.csv";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            modifyOrderDetailsTable(connection);

            List<Order> orders = ReadCSV(connection);

            insertOrdersAndLineItems(connection, orders);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void modifyOrderDetailsTable(Connection connection) throws SQLException {
        String alterTableSQL = "ALTER TABLE order_details ADD COLUMN quantity INT";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(alterTableSQL);
            System.out.println("order_details table modified successfully.");
        }
    }

    private static List<Order> ReadCSV(Connection connection) throws IOException, SQLException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_CSV_FILE))) {
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int orderId = Integer.parseInt(parts[0]);
                String customerName = parts[1];
                double total = Double.parseDouble(parts[2]);
                Order order = new Order(orderId, customerName, total);
                orders.add(order);
                insertOrder(connection, order);
            }
        }
        return orders;
    }

    private static void insertOrder(Connection connection, Order order) throws SQLException {
        String insertOrderSQL = "INSERT INTO orders (id, customer_name, total) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSQL)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getCustomerName());
            preparedStatement.setDouble(3, order.getTotal());
            preparedStatement.executeUpdate();
        }
    }

    private static void insertOrdersAndLineItems(Connection connection, List<Order> orders) throws SQLException {
        for (Order order : orders) {
            try {
                connection.setAutoCommit(false);
                insertOrderLineItems(connection, order);
                connection.commit();
                System.out.println("Order " + order.getId() + " inserted successfully.");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Error inserting order " + order.getId() + ". Rolling back.");
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    private static void insertOrderLineItems(Connection connection, Order order) throws SQLException {
        String insertLineItemSQL = "INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertLineItemSQL)) {
            for (LineItem lineItem : order.getLineItems()) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, lineItem.getProductId());
                preparedStatement.setInt(3, lineItem.getQuantity());
                preparedStatement.setDouble(4, lineItem.getPrice());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private static class Order {
        private int id;
        private String customerName;
        private double total;
        private List<LineItem> lineItems;

        public Order(int id, String customerName, double total) {
            this.id = id;
            this.customerName = customerName;
            this.total = total;
            this.lineItems = new ArrayList<>();
        }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

		public List<LineItem> getLineItems() {
			return lineItems;
		}

		public void setLineItems(List<LineItem> lineItems) {
			this.lineItems = lineItems;
		}

    }

    private static class LineItem {
        private int productId;
        private int quantity;
        private double price;

        public LineItem(int productId, int quantity, double price) {
            this.productId = productId;
            this.quantity = quantity;
            this.price = price;
        }

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

    }
}
