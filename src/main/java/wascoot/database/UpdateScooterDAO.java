package wascoot.database;

import wascoot.resource.Scooter;
import wascoot.resource.Scooterrack;

import java.sql.*;
/**
 * Updates a scooter in the database
 */
public final class UpdateScooterDAO extends AbstractDAO<Scooter>{

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE public.scooter SET id=?, date_of_purchase=?, km_traveled=?, model=?, remaining_battery_life=?, id_scooter_rack=? WHERE id=? RETURNING *";

    /**
     * Scooter object with information for the update
     */
    private final Scooter scooter;

    /**
     * Creates the object to update a scooter
     * @param con   connection to DB
     * @param s  object with information for the update
     */
    public UpdateScooterDAO(final Connection con, final Scooter s) {
        super(con);

        if (s == null) {
            LOGGER.error("The scooter cannot be null.");
            throw new NullPointerException("The scooter cannot be null.");
        }

        this.scooter = s;
    }

    protected void doAccess() throws SQLException{

        PreparedStatement pstmt = null;


        try {
            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setInt(1, scooter.getId());
            pstmt.setDate(2, scooter.getDateOfPurchase());
            pstmt.setDouble(3, scooter.getKmTraveled());
            pstmt.setString(4, scooter.getModel());
            pstmt.setDouble(5, scooter.getRemainingBatteryLife());
            pstmt.setInt(6, scooter.getIdScooterrack());
            pstmt.setInt(7, scooter.getId());

            Boolean result = pstmt.execute();
            if (result) {
                LOGGER.info("Scooter %s successfully stored in the database.", scooter.getId());
            }
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
