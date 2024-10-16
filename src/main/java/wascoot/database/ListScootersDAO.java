package wascoot.database;

import wascoot.resource.Scooter;
import wascoot.resource.Scooterrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reads all scooters in the database.
 */
public final class ListScootersDAO extends AbstractDAO<List<Scooter>> {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT id, date_of_purchase, km_traveled, model, remaining_battery_life, id_scooter_rack FROM public.scooter";

    /**
     * The connection to the database
     */


    /**
     * Creates a new object for listing all the scooter.
     *
     * @param con
     *            the connection to the database.
     */
    public ListScootersDAO(final Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Scooter> scooters = new ArrayList<Scooter>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                scooters.add(new Scooter(rs.getInt("id"), rs.getDate("date_of_purchase"), rs.getDouble("km_traveled"),
                        rs.getString("model"), rs.getDouble("remaining_battery_life"), rs.getInt("id_scooter_rack")));
            }

            LOGGER.info("Scooter(s) successfully listed.");

            Collections.sort(scooters);

            LOGGER.info("Scooter(s) successfully sorted.");

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        outputParam = scooters;
    }

    /**
     * Lists all the scooter in the database.
     *
     * @return a list of {@code scooter} object.
     *
     * @throws SQLException
     *             if any error occurs while searching for scooterracks.
     */
    public List<Scooter> getScootersList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Scooter> scooters = new ArrayList<Scooter>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                scooters.add(new Scooter(rs.getInt("id"), rs.getDate("date_of_purchase"), rs.getDouble("km_traveled"),
                        rs.getString("model"), rs.getDouble("remaining_battery_life"), rs.getInt("id_scooter_rack")));
            }

            Collections.sort(scooters);

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return scooters;
    }
}
