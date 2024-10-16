package wascoot.database;

import wascoot.resource.Rental;
import wascoot.resource.Scooterrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads all rentals in the database.
 */
public final class ListRentalsDAO extends AbstractDAO<List<Rental>> {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = " SELECT id, date_hour_delivery, date_hour_collection, id_scooter, " +
            "scooterrack_delivery, scooterrack_collection, customer, km_traveled FROM public.rental";

    /**
     * Creates a new object for listing all the rentals.
     *
     * @param con
     *            the connection to the database.
     */
    public ListRentalsDAO(final Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Rental> rentals = new ArrayList<Rental>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                rentals.add(new Rental(rs.getInt("id"), rs.getDate("date_hour_delivery"),
                        rs.getDate("date_hour_collection"), rs.getInt("id_scooter"),
                        rs.getInt("scooterrack_delivery"), rs.getInt("scooterrack_collection"),
                        rs.getString("customer"), rs.getDouble("km_traveled")));
            }

            LOGGER.info("Rental(s) successfully listed.");
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        outputParam = rentals;
    }

    /**
     * Lists all the rentals in the database.
     *
     * @return a list of {@code rentals} object.
     *
     * @throws SQLException
     *             if any error occurs while searching for rentals.
     */
    public List<Rental> getRentalsList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Rental> rentals = new ArrayList<Rental>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                rentals.add(new Rental(rs.getInt("id"), rs.getDate("date_hour_delivery"),
                        rs.getDate("date_hour_collection"), rs.getInt("id_scooter"),
                        rs.getInt("scooterrack_delivery"), rs.getInt("scooterrack_collection"),
                        rs.getString("customer"), rs.getDouble("km_traveled")));
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

        return rentals;
    }
}

