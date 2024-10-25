package CRUD;

import connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlterTableOperations {
    public static void alterTables() {
        String[] alterStatements = {
//                "ALTER TABLE Member ADD COLUMN middle_name VARCHAR(50);",
                "ALTER TABLE Equipment ADD COLUMN `condition` VARCHAR(50);",
                "ALTER TABLE Member MODIFY COLUMN phone_number VARCHAR(20);",
                "ALTER TABLE Member MODIFY COLUMN address TEXT NOT NULL;",
                "ALTER TABLE Member CHANGE COLUMN email email_address VARCHAR(100);",
                "ALTER TABLE Member ADD CONSTRAINT unique_email UNIQUE (email_address);",
                "ALTER TABLE Equipment ADD COLUMN `condition` VARCHAR(50);"
        };

        try (Connection connection = DatabaseConnection.getConnection()) {
            for (String alterSQL : alterStatements) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(alterSQL)) {
                    preparedStatement.execute();
                    System.out.println("Successfully executed: " + alterSQL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
