package wascoot.servlet;

import wascoot.database.PaymentWithSubscriptionDatabase;
import wascoot.database.ScooterRackDatabase;
import wascoot.database.RentalDatabase;
import wascoot.resource.Revenue;
import wascoot.resource.Scooterrack;

import wascoot.database.PaymentWithoutSubscriptionDatabase;
import wascoot.resource.PaymentWithoutSubscription;

import wascoot.resource.Message;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Dashboard Servlet
 *
 * @version 1.00
 * @since 1.00
 */
public final class DashboardServlet extends AbstractDatabaseServlet {

    /**
     * Dashboard servlet
     *
     * @param req
     *            the HTTP request from the client.
     * @param res
     *            the HTTP response from the server.
     *
     * @throws ServletException
     *             if any error occurs while executing the servlet.
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        List<Scooterrack> el0 = null;
        List<PaymentWithoutSubscription> el1 = null;
        List<Integer> topLocation = null;
        List<Revenue> revenueList = null;

        Message m = null;

        try {

            // creates a new object for accessing the database and searching the scooterracks and paymentwithoutsubscription
            el0 = new ScooterRackDatabase(getDataSource().getConnection()).getScooterRackList();
            el1 = new PaymentWithoutSubscriptionDatabase(getDataSource().getConnection()).getPaymentWithoutSubscriptionList();
            topLocation = new RentalDatabase(getDataSource().getConnection()).getTopLocation();
            revenueList = new PaymentWithSubscriptionDatabase(getDataSource().getConnection()).getRevenueInfo();

            m = new Message("Successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for the ressources in the db.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for the ressources: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the lists and the message as a request attribute
        req.setAttribute("scooterRackList", el0);
        req.setAttribute("paymentWithoutSubscriptionList", el1);
        req.setAttribute("topLocation", topLocation);
        req.setAttribute("revenueList", revenueList);
        req.setAttribute("message", m);

        // forwards the control to the dashboard.jsp
        req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req, res);

    }
}
// code