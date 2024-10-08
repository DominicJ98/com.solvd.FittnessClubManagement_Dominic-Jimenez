import CRUD.*;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){
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
