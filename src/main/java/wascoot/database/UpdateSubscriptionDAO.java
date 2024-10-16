package wascoot.database;

import wascoot.resource.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Updates a subscription in the database.
 */
public final class UpdateSubscriptionDAO extends AbstractDAO{

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE public.Subscription SET type=?, daily_unlocks=?, validity_per_day=?, fixed_price=? WHERE ID=? RETURNING *";

    /**
     * Scooter object with information for the update
     */
    private final Subscription subscription;

    /**
     * Creates the object to update a scooter
     * @param con   connection to DB
     * @param subscription  object with information for the update
     */
    public UpdateSubscriptionDAO(final Connection con, final Subscription subscription) {
        super(con);

        if (subscription == null) {
            LOGGER.error("The subscription cannot be null.");
            throw new NullPointerException("The subscription cannot be null.");
        }

        this.subscription = subscription;
    }

    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1,subscription.getType());
            pstmt.setInt(2,subscription.getDailyUnlocks());
            pstmt.setTime(3, subscription.getValidityPerDay());
            pstmt.setDouble(4, subscription.getFixedPrice());
            pstmt.setInt(5,subscription.getId());

            pstmt.execute();

            LOGGER.info("Subscription %d successfully stored in the database.", subscription.getId());
        }finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
