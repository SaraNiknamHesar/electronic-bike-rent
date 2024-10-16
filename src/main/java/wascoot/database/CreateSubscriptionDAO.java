package wascoot.database;

import wascoot.resource.Subscription;

import java.sql.*;

/**
 * Inserts a new subscription in the database
 */
public final class CreateSubscriptionDAO extends AbstractDAO{

    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "INSERT INTO public.Subscription (type, daily_unlocks, validity_per_day, fixed_price) VALUES (?, ?, ?, ?)";

    /**
     * The subscription to be inserted
     */
    private final Subscription subscription;

    /**
     * Creates a new object for inserting the subscription
     * @param con   connection to DB
     * @param subscription  the object of the subcription to be added
     */
    public CreateSubscriptionDAO(final Connection con, final Subscription subscription){

        super(con);

        if (subscription == null) {
            LOGGER.error("The subscription cannot be null.");
            throw new NullPointerException("The subscription cannot be null.");
        }
        this.subscription = subscription;

    }

    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, subscription.getType());
            pstmt.setInt(2, subscription.getDailyUnlocks());
            pstmt.setTime(3, subscription.getValidityPerDay());
            pstmt.setDouble(4, subscription.getFixedPrice());

            pstmt.execute();

            LOGGER.info("subscription %s successfully stored in the database.", subscription.getType());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
