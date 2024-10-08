package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertionOperations {

    public static void insertMemberships() {
        String insertMembershipSQL = "INSERT INTO Membership (membership_type, start_date, end_date, fee) VALUES (?, ?, ?, ?)";

        String[][] membershipData = {
                {"Standard", "2024-01-01", "2024-12-31", "100.00"},
                {"Premium", "2024-01-01", "2024-12-31", "200.00"},
                {"VIP", "2024-01-01", "2024-12-31", "300.00"}
        };

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertMembershipSQL)) {

            for (String[] membership : membershipData) {
                preparedStatement.setString(1, membership[0]);  // membership_type
                preparedStatement.setString(2, membership[1]);  // start_date
                preparedStatement.setString(3, membership[2]);  // end_date
                preparedStatement.setBigDecimal(4, new java.math.BigDecimal(membership[3]));  // fee

                preparedStatement.executeUpdate();
            }

            System.out.println("Successfully inserted memberships into the database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertMembers() {
        String insertSQL = "INSERT INTO Member (first_name, last_name, date_of_birth, email_address, phone_number, address, membership_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String[][] memberData = {
                {"Alice", "Johnson", "1990-01-01", "alice.johnson@example.com", "1234567890", "123 Elm St", "1"},
                {"Bob", "Smith", "1985-03-20", "bob.smith@example.com", "0987654321", "456 Maple St", "2"},
                {"Charlie", "Brown", "1975-07-14", "charlie.brown@example.com", "5678901234", "789 Oak St", "1"},
                {"David", "Wilson", "1988-11-11", "david.wilson@example.com", "3456789012", "321 Pine St", "3"},
                {"Eva", "Taylor", "1992-09-23", "eva.taylor@example.com", "4567890123", "654 Cedar St", "1"},
                {"Frank", "Garcia", "1980-12-15", "frank.garcia@example.com", "6789012345", "987 Birch St", "2"},
                {"Grace", "Martinez", "1995-05-05", "grace.martinez@example.com", "7890123456", "741 Walnut St", "3"},
                {"Henry", "Lee", "1999-04-28", "henry.lee@example.com", "8901234567", "852 Willow St", "1"},
                {"Isabella", "Walker", "2000-06-18", "isabella.walker@example.com", "9012345678", "963 Fir St", "2"},
                {"Jack", "Hall", "1983-08-10", "jack.hall@example.com", "1234567890", "147 Spruce St", "3"}
        };

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            for (String[] member : memberData) {
                preparedStatement.setString(1, member[0]);
                preparedStatement.setString(2, member[1]);
                preparedStatement.setString(3, member[2]);
                preparedStatement.setString(4, member[3]);
                preparedStatement.setString(5, member[4]);
                preparedStatement.setString(6, member[5]);
                preparedStatement.setInt(7, Integer.parseInt(member[6]));

                preparedStatement.executeUpdate();
            }

            System.out.println("Successfully inserted 10 members into the database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
