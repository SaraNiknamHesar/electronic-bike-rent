package wascoot.database;

import wascoot.resource.Model;
import wascoot.resource.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reads all payment methods from database.
 */
public final class ListPaymentMethodDAO extends AbstractDAO<List<PaymentMethod>> {
    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "SELECT ID, type, Activation FROM public.paymentmethod";

    /**
     * Creates a new object to read all payment methods.
     * @param con connection to DB
     */
    public ListPaymentMethodDAO(final Connection con){
        super(con);
    }

    public final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                paymentMethods.add(new PaymentMethod(rs.getInt("ID"),
                        rs.getString("type"), rs.getString("Activation")));
            }

            LOGGER.info("payment method(s) successfully listed.");

            Collections.sort(paymentMethods);

            LOGGER.info("payment method(s) successfully sorted.");

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        this.outputParam = paymentMethods;
    }
}
