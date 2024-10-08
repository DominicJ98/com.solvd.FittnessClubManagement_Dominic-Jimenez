package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AggregateFunctionsWithHaving {

    public static void classesWithMoreThanTwoBookings() {
        String sql = "SELECT class_name, COUNT(b.booking_id) AS total_bookings " +
                "FROM Class c LEFT JOIN Booking b ON c.class_id = b.class_id " +
                "GROUP BY c.class_id HAVING COUNT(b.booking_id) > 2";
        executeAndPrint(sql);
    }

    public static void trainersWithHighAverageRating() {
        String sql = "SELECT t.first_name, t.last_name, AVG(fb.rating) AS average_rating " +
                "FROM Trainer t JOIN Feedback fb ON t.trainer_id = fb.trainer_id " +
                "GROUP BY t.trainer_id HAVING AVG(fb.rating) > 4";
        executeAndPrint(sql);
    }

    public static void membershipsWithHighTotalFees() {
        String sql = "SELECT membership_type, SUM(fee) AS total_fees " +
                "FROM Membership GROUP BY membership_type HAVING SUM(fee) > 500";
        executeAndPrint(sql);
    }

    public static void trainersWithMoreThanThreeClasses() {
        String sql = "SELECT t.first_name, t.last_name, COUNT(c.class_id) AS class_count " +
                "FROM Trainer t LEFT JOIN Class c ON t.trainer_id = c.trainer_id " +
                "GROUP BY t.trainer_id HAVING COUNT(c.class_id) > 3";
        executeAndPrint(sql);
    }

    public static void classesWithAverageRatingAboveThreshold() {
        String sql = "SELECT c.class_name, AVG(fb.rating) AS average_rating " +
                "FROM Class c JOIN Feedback fb ON c.class_id = fb.class_id " +
                "GROUP BY c.class_id HAVING AVG(fb.rating) > 4";
        executeAndPrint(sql);
    }

    public static void facilitiesWithMoreThanTwoEquipments() {
        String sql = "SELECT f.facility_name, COUNT(e.equipment_id) AS equipment_count " +
                "FROM Facility f LEFT JOIN Equipment e ON f.facility_id = e.facility_id " +
                "GROUP BY f.facility_id HAVING COUNT(e.equipment_id) > 2";
        executeAndPrint(sql);
    }

    public static void membersWithMultipleBookings() {
        String sql = "SELECT m.first_name, m.last_name, COUNT(b.booking_id) AS booking_count " +
                "FROM Member m LEFT JOIN Booking b ON m.member_id = b.member_id " +
                "GROUP BY m.member_id HAVING COUNT(b.booking_id) > 1";
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
