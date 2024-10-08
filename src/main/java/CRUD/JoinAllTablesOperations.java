package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinAllTablesOperations {
    public static void joinAllTables() {
        String joinSQL = "SELECT " +
                "m.member_id, m.first_name, m.last_name, m.date_of_birth, m.email_address, m.phone_number, " +
                "mem.membership_type, mem.start_date AS membership_start_date, mem.end_date AS membership_end_date, " +
                "c.class_id, c.class_name, c.description AS class_description, " +
                "t.trainer_id, t.first_name AS trainer_first_name, t.last_name AS trainer_last_name, t.specialty, " +
                "b.booking_date, a.attendance_date, a.status AS attendance_status, " +
                "f.facility_name, e.equipment_name, e.condition AS equipment_condition, " +
                "maint.maintenance_date, maint.description AS maintenance_description, " +
                "fb.feedback_text, fb.rating " +
                "FROM Member m " +
                "LEFT JOIN Membership mem ON m.membership_id = mem.membership_id " +
                "LEFT JOIN Booking b ON m.member_id = b.member_id " +
                "LEFT JOIN Class c ON b.class_id = c.class_id " +
                "LEFT JOIN Trainer t ON c.trainer_id = t.trainer_id " +
                "LEFT JOIN Attendance a ON m.member_id = a.member_id AND c.class_id = a.class_id " +
                "LEFT JOIN Facility f ON c.class_id = f.facility_id " +
                "LEFT JOIN Equipment e ON f.facility_id = e.facility_id " +
                "LEFT JOIN Maintenance maint ON e.equipment_id = maint.equipment_id " +
                "LEFT JOIN Feedback fb ON m.member_id = fb.member_id AND c.class_id = fb.class_id AND t.trainer_id = fb.trainer_id;";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(joinSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Print the column headers
            System.out.printf("%-10s %-15s %-15s %-12s %-25s %-15s %-15s %-15s %-15s %-10s %-15s %-25s %-10s %-15s %-15s %-20s %-15s %-15s %-25s %-25s %-15s %-25s %-25s %-25s\n",
                    "member_id", "first_name", "last_name", "dob", "email", "phone", "membership_type", "mem_start", "mem_end",
                    "class_id", "class_name", "class_desc", "trainer_id", "trainer_fname", "trainer_lname", "specialty",
                    "booking_date", "attendance_date", "attendance_status", "facility_name", "equipment_name", "equipment_condition",
                    "maint_date", "maint_desc", "feedback_text", "rating");

            // Iterate through the result set and print each row
            while (resultSet.next()) {
                System.out.printf("%-10d %-15s %-15s %-12s %-25s %-15s %-15s %-15s %-15s %-10d %-15s %-25s %-10d %-15s %-15s %-20s %-15s %-15s %-25s %-25s %-15s %-25s %-25s %-25s %-25s %-5d\n",
                        resultSet.getInt("member_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("email_address"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("membership_type"),
                        resultSet.getDate("membership_start_date"),
                        resultSet.getDate("membership_end_date"),
                        resultSet.getInt("class_id"),
                        resultSet.getString("class_name"),
                        resultSet.getString("class_description"),
                        resultSet.getInt("trainer_id"),
                        resultSet.getString("trainer_first_name"),
                        resultSet.getString("trainer_last_name"),
                        resultSet.getString("specialty"),
                        resultSet.getDate("booking_date"),
                        resultSet.getDate("attendance_date"),
                        resultSet.getString("attendance_status"),
                        resultSet.getString("facility_name"),
                        resultSet.getString("equipment_name"),
                        resultSet.getString("equipment_condition"),
                        resultSet.getDate("maintenance_date"),
                        resultSet.getString("maintenance_description"),
                        resultSet.getString("feedback_text"),
                        resultSet.getInt("rating"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
