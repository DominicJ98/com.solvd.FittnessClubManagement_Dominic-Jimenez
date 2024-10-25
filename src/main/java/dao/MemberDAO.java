package dao;

import model.Member;
import connection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO implements GenericDAO<Member, Integer> {

    @Override
    public void insert(Member member) throws SQLException {
        String sql = "INSERT INTO Member (first_name, last_name, date_of_birth, email_address, phone_number, address, membership_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setDate(3, Date.valueOf(member.getDateOfBirth()));
            statement.setString(4, member.getEmailAddress());
            statement.setString(5, member.getPhoneNumber());
            statement.setString(6, member.getAddress());
            statement.setInt(7, member.getMembershipId());

            statement.executeUpdate();
        }
    }

    @Override
    public Member getById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Member WHERE member_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("first_name") != null ? resultSet.getString("first_name") : "", // Handle null values
                        resultSet.getString("last_name") != null ? resultSet.getString("last_name") : "",
                        resultSet.getDate("date_of_birth") != null ? resultSet.getDate("date_of_birth").toLocalDate() : null,
                        resultSet.getString("email_address") != null ? resultSet.getString("email_address") : "",
                        resultSet.getString("phone_number") != null ? resultSet.getString("phone_number") : "",
                        resultSet.getString("address") != null ? resultSet.getString("address") : "",
                        resultSet.getInt("membership_id")
                );
            }
        }
        return null;
    }


    @Override
    public List<Member> getAll() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Member";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                members.add(new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth").toLocalDate(),
                        resultSet.getString("email_address"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getInt("membership_id")
                ));
            }
        }
        return members;
    }

    @Override
    public void update(Member member) throws SQLException {
        String sql = "UPDATE Member SET first_name = ?, last_name = ?, date_of_birth = ?, email_address = ?, phone_number = ?, address = ?, membership_id = ? WHERE member_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setDate(3, Date.valueOf(member.getDateOfBirth()));
            statement.setString(4, member.getEmailAddress());
            statement.setString(5, member.getPhoneNumber());
            statement.setString(6, member.getAddress());
            statement.setInt(7, member.getMembershipId());
            statement.setInt(8, member.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Member WHERE member_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
