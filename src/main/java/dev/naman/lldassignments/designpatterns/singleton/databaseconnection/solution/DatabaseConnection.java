package dev.naman.lldassignments.designpatterns.singleton.databaseconnection.solution;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connected to the database.");
    }

    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }
}