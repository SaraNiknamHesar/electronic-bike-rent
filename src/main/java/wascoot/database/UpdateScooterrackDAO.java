package wascoot.database;

import wascoot.resource.Scooterrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Updates a scooterrack in the database
 */
public final class UpdateScooterrackDAO extends AbstractDAO<Scooterrack>{

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE public.scooterracks SET id=?, total_parking_spots=?, available_parking_spots=?, postalcode=?, address=? WHERE id=? RETURNING *";

    /**
     * Scooter object with information for the update
     */
    private final Scooterrack scooterrack;

    /**
     * Creates the object to update a scooterrack
     * @param con   connection to DB
     * @param s  object with information for the update
     */
    public UpdateScooterrackDAO(final Connection con, final Scooterrack s) {
        super(con);

        if (s == null) {
            LOGGER.error("The scooterrack cannot be null.");
            throw new NullPointerException("The scooterrack cannot be null.");
        }

        this.scooterrack = s;
    }

    protected void doAccess() throws SQLException{

        PreparedStatement pstmt = null;


        try {
            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setInt(1, scooterrack.getId());
            pstmt.setInt(2, scooterrack.getTotalParkingSpots());
            pstmt.setInt(3, scooterrack.getAvailableParkingSpots());
            pstmt.setString(4, scooterrack.getPostalCode());
            pstmt.setString(5, scooterrack.getAddress());
            pstmt.setInt(6, scooterrack.getId());

            Boolean result = pstmt.execute();
            if (result) {
                LOGGER.info("Scooterrack %s successfully stored in the database.", scooterrack.getId());
            }
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
