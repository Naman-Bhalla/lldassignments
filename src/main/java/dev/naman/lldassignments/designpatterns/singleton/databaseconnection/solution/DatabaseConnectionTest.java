package dev.naman.lldassignments.designpatterns.singleton.databaseconnection.solution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class DatabaseConnectionTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Singleton Instance Test")
    void testSingletonInstance() {
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();

        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    @DisplayName("Connect and Execute Query Test")
    void testConnectAndExecuteQuery() {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();

        dbConnection.connect();
        assertEquals("Connected to the database.\n", outputStreamCaptor.toString());

        outputStreamCaptor.reset();
        dbConnection.executeQuery("SELECT * FROM users");
        assertEquals("Executing query: SELECT * FROM users\n", outputStreamCaptor.toString());
    }
}
