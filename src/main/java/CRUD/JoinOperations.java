package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinOperations {
    public static void performInnerJoin() {
        String innerJoinSQL = "SELECT m.first_name, m.last_name, mem.membership_type " +
                "FROM Member m INNER JOIN Membership mem ON m.membership_id = mem.membership_id";

        executeAndPrint(innerJoinSQL);
    }

    public static void performLeftJoin() {
        String leftJoinSQL = "SELECT m.first_name, m.last_name, c.class_name, b.booking_date " +
                "FROM Member m LEFT JOIN Booking b ON m.member_id = b.member_id " +
                "LEFT JOIN Class c ON b.class_id = c.class_id";

        executeAndPrint(leftJoinSQL);
    }

    public static void performRightJoin() {
        String rightJoinSQL = "SELECT t.first_name, t.last_name, c.class_name " +
                "FROM Trainer t RIGHT JOIN Class c ON t.trainer_id = c.trainer_id";

        executeAndPrint(rightJoinSQL);
    }

    public static void performFullOuterJoin() {
        String fullOuterJoinSQL = "SELECT m.first_name, m.last_name, a.attendance_date, a.status " +
                "FROM Member m LEFT JOIN Attendance a ON m.member_id = a.member_id " +
                "UNION " +
                "SELECT m.first_name, m.last_name, a.attendance_date, a.status " +
                "FROM Member m RIGHT JOIN Attendance a ON m.member_id = a.member_id";

        executeAndPrint(fullOuterJoinSQL);
    }

    public static void performCrossJoin() {
        String crossJoinSQL = "SELECT m.first_name, m.last_name, t.first_name AS trainer_first_name, t.last_name AS trainer_last_name " +
                "FROM Member m CROSS JOIN Trainer t";

        executeAndPrint(crossJoinSQL);
    }

    // Helper method to execute and print the results
    private static void executeAndPrint(String sql) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            // Print column names
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
