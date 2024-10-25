package CRUD;

import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateOperations {
    public static void updateMembers() {
        String[] updateStatements = {
                "UPDATE Member SET phone_number = '1111111111' WHERE email_address = 'alice.johnson@example.com';",
                "UPDATE Member SET address = 'New Address' WHERE email_address = 'bob.smith@example.com';",
                "UPDATE Member SET membership_id = 2 WHERE email_address = 'charlie.brown@example.com';"
        };

        try (Connection connection = DatabaseConnection.getConnection()) {
            for (String updateSQL : updateStatements) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Update successful! Rows affected: " + rowsAffected);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
