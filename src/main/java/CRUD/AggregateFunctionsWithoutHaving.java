package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AggregateFunctionsWithoutHaving {

    public static void countMembersByMembershipType() {
        String sql = "SELECT membership_type, COUNT(*) AS member_count FROM Membership mem " +
                "JOIN Member m ON m.membership_id = mem.membership_id GROUP BY membership_type";
        executeAndPrint(sql);
    }

    public static void averageClassSize() {
        String sql = "SELECT class_name, COUNT(b.booking_id) AS number_of_bookings " +
                "FROM Class c LEFT JOIN Booking b ON c.class_id = b.class_id GROUP BY class_name";
        executeAndPrint(sql);
    }

    public static void sumOfFeesByMembershipType() {
        String sql = "SELECT membership_type, SUM(fee) AS total_fees FROM Membership GROUP BY membership_type";
        executeAndPrint(sql);
    }

    public static void minAndMaxFeesByMembershipType() {
        String sql = "SELECT membership_type, MIN(fee) AS min_fee, MAX(fee) AS max_fee FROM Membership GROUP BY membership_type";
        executeAndPrint(sql);
    }

    public static void averageRatingByTrainer() {
        String sql = "SELECT t.first_name, t.last_name, AVG(fb.rating) AS average_rating " +
                "FROM Trainer t JOIN Feedback fb ON t.trainer_id = fb.trainer_id GROUP BY t.trainer_id";
        executeAndPrint(sql);
    }

    public static void countClassesByTrainer() {
        String sql = "SELECT t.first_name, t.last_name, COUNT(c.class_id) AS class_count " +
                "FROM Trainer t LEFT JOIN Class c ON t.trainer_id = c.trainer_id GROUP BY t.trainer_id";
        executeAndPrint(sql);
    }

    public static void countBookingsPerClass() {
        String sql = "SELECT class_name, COUNT(b.booking_id) AS total_bookings " +
                "FROM Class c LEFT JOIN Booking b ON c.class_id = b.class_id GROUP BY c.class_id";
        executeAndPrint(sql);
    }

    // Helper method to execute the SQL statement and print results
    private static void executeAndPrint(String sql) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            // Print column headers
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-25s", resultSet.getMetaData().getColumnName(i));
            }
            System.out.println();

            // Print each row
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-25s", resultSet.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
