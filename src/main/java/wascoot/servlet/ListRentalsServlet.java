package wascoot.servlet;

import wascoot.database.ListRentalsDAO;
import wascoot.database.ListScootersDAO;
import wascoot.database.ScooterRackDatabase;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * List rentals Servlet
 *  get the list of all rentals
 *
 * @version 1.00
 * @since 1.00
 */
public final class ListRentalsServlet extends AbstractDatabaseServlet {

    /**
     * List Rentals servlet
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

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.LIST_RENTALS);

        List<Rental> rl = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the rentals
            rl = new ListRentalsDAO(getDataSource().getConnection()).getRentalsList();

            m = new Message("Rentals successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for the rentals in the db.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for the rentals: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        // stores the lists and the message as a request attribute
        req.setAttribute("rentalsList", rl);
        req.setAttribute("message", m);

        // forwards the control to the rentals.jsp
        req.getRequestDispatcher("/jsp/manage-pages/rental-list.jsp").forward(req, res);

    }
}
