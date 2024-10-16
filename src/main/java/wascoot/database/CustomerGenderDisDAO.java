package wascoot.database;

import wascoot.resource.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Return gender distribution in the customer table from the database
 */
public class CustomerGenderDisDAO extends AbstractDAO<int[]> {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT COUNT(*) FILTER (WHERE sex = 'M') AS male_count, " +
            "COUNT(*) FILTER (WHERE sex = 'F') AS female_count " +
            "FROM public.Customer";

    /**
     * Creates a new object for retrieving the gender distribution of customers.
     *
     * @param con the connection to the database
     */
    public CustomerGenderDisDAO(Connection con) {
        super(con);
    }

    protected void doAccess() throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int[] genderCounts = new int[2]; // Index 0: male count, Index 1: female count

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                genderCounts[0] = rs.getInt("male_count");
                genderCounts[1] = rs.getInt("female_count");

                LOGGER.info("Gender distribution of customers successfully retrieved.");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }
        }

        outputParam = genderCounts;
    }

    /**
     * Retrieves the gender distribution of customers.
     *
     * @return an array of two integers representing the number of male and female persons respectively
     * @throws SQLException if any error occurs while retrieving the gender distribution
     */
    public int[] getCustomerGenderDis() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int[] genderCounts = new int[2]; // Index 0: male count, Index 1: female count

        try {
            pstmt = con.prepareStatement(STATEMENT);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                genderCounts[0] = rs.getInt("male_count");
                genderCounts[1] = rs.getInt("female_count");

                LOGGER.info("Gender distribution of customers successfully retrieved.");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return genderCounts;
    }
}