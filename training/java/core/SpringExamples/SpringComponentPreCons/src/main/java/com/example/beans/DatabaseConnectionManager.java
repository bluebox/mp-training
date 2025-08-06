package com.example.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionManager {
	
	
	

    private Connection dbConnection; 

    @PostConstruct
    public void initializeConnection() {
        System.out.println("initializing database connection");
        try {
  
            this.dbConnection = new Connection("jdbc:mysql://localhost:3306/library", "root", "adheesh@1234");
            System.out.println("Database connection established: " + dbConnection);
        } catch (Exception e) {
            System.err.println("error " + e.getMessage());
        }
    }

    public void executeQuery(String query) {
        if (dbConnection != null && dbConnection.isOpen()) {
            System.out.println("Executing query: " + query + " using connection: " + dbConnection);
        } else {
            System.out.println("connection null.");
        }
    }

    @PreDestroy
    public void closeConnection() {
        System.out.println("Closing database connection...");
        if (dbConnection != null) {
            try {
                dbConnection.close(); 
                System.out.println("Database connection closed.");
            } catch (Exception e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }

    private static class Connection {
        private String url;
        private String user;
        private String password;
        private boolean open;

        public Connection(String url, String user, String password) {
            this.url = url;
            this.user = user;
            this.password = password;
            this.open = true; 
        }

        public boolean isOpen() {
            return open;
        }

        public void close() {
            this.open = false;
        }

        @Override
        public String toString() {
            return "Connection{" +
                   "url='" + url + '\'' +
                   ", user='" + user + '\'' +
                   '}';
        }
    }
}