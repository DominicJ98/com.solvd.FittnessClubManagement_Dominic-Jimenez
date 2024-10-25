package CRUD;

import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteOperations {
    public static void deleteMembers() {
        String[] deleteStatements = {
                "DELETE FROM Member WHERE email_address = 'alice.johnson@example.com';",
                "DELETE FROM Member WHERE email_address = 'bob.smith@example.com';",
                "DELETE FROM Member WHERE email_address = 'charlie.brown@example.com';"
        };

        try (Connection connection = DatabaseConnection.getConnection()) {
            for (String deleteSQL : deleteStatements) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Delete successful! Rows affected: " + rowsAffected);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
