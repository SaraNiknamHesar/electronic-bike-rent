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
 * Reads the list of customers from the database
 */
public class CustomerDAO extends AbstractDAO<List<Customer>>{
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT cf, name, surname, email, sex, birthdate, postalCode, paymentType FROM public.Customer";

    /**
     * Creates a new object for listing all the customers.
     *
     * @param con
     *            the connection to the database.
     */
    public CustomerDAO(final Connection con) {
        super(con);
    }

    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Customer> customers = new ArrayList<Customer>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(rs.getString("cf"), rs.getString("name"), rs.getString("surname"),
                        rs.getString("email"), rs.getString("sex"),
                        rs.getString("birthdate"), rs.getString("postalCode"),
                        rs.getString("paymentType")));
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

        outputParam = customers;
    }


    /**
     * Lists all the customers in the database.
     *
     * @return a list of {@code customer} object.
     *
     * @throws SQLException
     *             if any error occurs while searching for employees.
     */
    public List<Customer> getCustomerList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Customer> customers = new ArrayList<Customer>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(rs.getString("cf"), rs.getString("name"), rs.getString("surname"),
                        rs.getString("email"), rs.getString("sex"),
                        rs.getString("birthdate"), rs.getString("postalCode"),
                        rs.getString("paymentType")));
            }
            LOGGER.info("Customer(s) successfully listed.");
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return customers;
    }
}
