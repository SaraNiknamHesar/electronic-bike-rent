package wascoot.database;
import wascoot.resource.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;


/**
 * Reads the map of average ages by postal code of customers.
 */
public class CustomerAvgAgeDAO extends AbstractDAO<Map<String, Double>>{

    /**
     * Creates a new map for listing avg age customers by postal code.
     *
     * @param con
     *            the connection to the database.
     */
    public CustomerAvgAgeDAO(final Connection con) {
        super(con);
    }

    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        Map<String, List<Integer>> postalCodeAges = new HashMap<>();

        try {
            String statement = "SELECT postalCode, birthdate FROM public.Customer";
            pstmt = con.prepareStatement(statement);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String postalCode = rs.getString("postalCode");
                String birthdate = rs.getString("birthdate");
                LocalDate customerBirthdate = LocalDate.parse(birthdate);
                LocalDate now = LocalDate.now();
                Period period = Period.between(customerBirthdate, now);
                int age = period.getYears();

                postalCodeAges.computeIfAbsent(postalCode, k -> new ArrayList<>()).add(age);
            }

            LOGGER.info("Customer(s) successfully listed.");
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        outputParam = calculateAverageAges(postalCodeAges);
    }

    /**
     * Calculates the average age of customers for each postal code.
     *
     * @return a map containing postal code as key and average age as value.
     * @throws SQLException if any error occurs while searching for customer ages.
     */
    public Map<String, Double> getAverageCustomerAgeByPostalCode() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<String, List<Integer>> postalCodeAges = new HashMap<>();

        try {
            String statement = "SELECT postalCode, birthdate FROM public.Customer";
            pstmt = con.prepareStatement(statement);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String postalCode = rs.getString("postalCode");
                String birthdate = rs.getString("birthdate");
                LocalDate customerBirthdate = LocalDate.parse(birthdate);
                LocalDate now = LocalDate.now();
                Period period = Period.between(customerBirthdate, now);
                int age = period.getYears();

                postalCodeAges.computeIfAbsent(postalCode, k -> new ArrayList<>()).add(age);
            }

            LOGGER.info("Customer ages by postal code successfully retrieved.");
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return calculateAverageAges(postalCodeAges);
    }

    /**
     * Calculates the average age from the list of ages.
     *
     * @param postalCodeAges a map containing postal code as key and list of ages as value.
     * @return a map containing postal code as key and average age as value.
     */
    public Map<String, Double> calculateAverageAges(Map<String, List<Integer>> postalCodeAges) {
        Map<String, Double> averageAges = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : postalCodeAges.entrySet()) {
            String postalCode = entry.getKey();
            List<Integer> ages = entry.getValue();

            int sum = 0;
            for (int age : ages) {
                sum += age;
            }

            double averageAge = (double) sum / ages.size();
            averageAges.put(postalCode, averageAge);
        }

        return averageAges;
    }
}
