import CRUD.*;
import connection.DatabaseConnection;
import dao.MemberDAO;
import model.Member;
import service.MemberService;

import java.sql.Connection;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        MemberService memberService = new MemberService(new MemberDAO());

        try {
            // Create a new member (you can add this if needed)
            Member newMember = new Member(0, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", "123456789", "123 Elm St", 1);
            memberService.addMember(newMember);

            // Get a member by ID
            Member member = memberService.getMemberById(1);
            if (member != null) {
                System.out.println("Retrieved member: " + member.getFirstName());
            } else {
                System.out.println("Member with ID 1 does not exist.");
            }

            // Update member
            member.setPhoneNumber("9999999999");
            memberService.updateMember(member);

            // Delete a member
            memberService.deleteMember(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection Successful");
        } else {
            System.out.println("Connection Failed");
        }
        //10 insertion statements
        InsertionOperations.insertMemberships();
        InsertionOperations.insertMembers();

        //10 statements for updating
        UpdateOperations.updateMembers();

        //10 statements for deletions.
        DeleteOperations.deleteMembers();

        //5 alter table.
        AlterTableOperations.alterTables();

        //Join all Tables in Database
        JoinAllTablesOperations.joinAllTables();

        //5 statements with left, right, inner, outer joins
        JoinAllTablesOperations.joinAllTables();

        // Perform different JOIN operations
        JoinOperations.performInnerJoin();
        JoinOperations.performLeftJoin();
        JoinOperations.performRightJoin();
        JoinOperations.performFullOuterJoin();
        JoinOperations.performCrossJoin();

        // Aggregate Functions without HAVING
        AggregateFunctionsWithoutHaving.countMembersByMembershipType();
        AggregateFunctionsWithoutHaving.averageClassSize();
        AggregateFunctionsWithoutHaving.sumOfFeesByMembershipType();
        AggregateFunctionsWithoutHaving.minAndMaxFeesByMembershipType();
        AggregateFunctionsWithoutHaving.averageRatingByTrainer();
        AggregateFunctionsWithoutHaving.countClassesByTrainer();
        AggregateFunctionsWithoutHaving.countBookingsPerClass();

        // Aggregate Functions with HAVING
        AggregateFunctionsWithHaving.classesWithMoreThanTwoBookings();
        AggregateFunctionsWithHaving.trainersWithHighAverageRating();
        AggregateFunctionsWithHaving.membershipsWithHighTotalFees();
        AggregateFunctionsWithHaving.trainersWithMoreThanThreeClasses();
        AggregateFunctionsWithHaving.classesWithAverageRatingAboveThreshold();
        AggregateFunctionsWithHaving.facilitiesWithMoreThanTwoEquipments();
        AggregateFunctionsWithHaving.membersWithMultipleBookings();
    }
}
