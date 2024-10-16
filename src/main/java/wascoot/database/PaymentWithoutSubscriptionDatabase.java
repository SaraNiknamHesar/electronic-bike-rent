package wascoot.database;

import wascoot.resource.PaymentWithoutSubscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads all payments without subscription in database.
 */
public final class PaymentWithoutSubscriptionDatabase extends AbstractDAO<List<PaymentWithoutSubscription>> {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT id, price, date_hour, order_id  FROM public.paymentwithoutsubscription";

    /**
     * Creates a new object for listing all the paymentwithoutsubscription.
     *
     * @param con
     *            the connection to the database.
     */
    public PaymentWithoutSubscriptionDatabase(final Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<PaymentWithoutSubscription> paymentWithoutSubscriptionList = new ArrayList<PaymentWithoutSubscription>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                paymentWithoutSubscriptionList.add(new PaymentWithoutSubscription(rs.getInt("id"), rs
                        .getInt("price"), rs.getString("date_hour"),
                        rs.getInt("order_id") ));
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

        outputParam = paymentWithoutSubscriptionList;
    }

    /**
     * Lists all the scooter racks in the database.
     *
     * @return a list of {@code scooterrack} object.
     *
     * @throws SQLException
     *             if any error occurs while searching for scooterracks.
     */
    public List<PaymentWithoutSubscription> getPaymentWithoutSubscriptionList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<PaymentWithoutSubscription> paymentWithoutSubscriptionList = new ArrayList<PaymentWithoutSubscription>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                paymentWithoutSubscriptionList.add(new PaymentWithoutSubscription(rs.getInt("id"), rs
                        .getInt("price"), rs.getString("date_hour"),
                        rs.getInt("order_id") ));
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

        return paymentWithoutSubscriptionList;
    }
}
