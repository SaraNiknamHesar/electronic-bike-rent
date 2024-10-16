package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.CustomerDAO;
import wascoot.resource.Customer;
import wascoot.resource.Message;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to get the list of all customers
 */
public class ListCustomerServlet extends AbstractDatabaseServlet{

    /**
     * get the customers' list and print them into a jsp
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        // model
        List<Customer> el = null;
        Message m = null;

        try {

            // creates a new object for accessing the database and searching the customers
            el = new CustomerDAO(getConnection()).access().getOutputParam();

            m = new Message("Customers successfully searched.");

        } catch (NumberFormatException ex) {
            m = new Message("Cannot search for customers.",
                    "E100", ex.getMessage());
        } catch (SQLException ex) {
            m = new Message("Cannot search for customers: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("customerList", el);
        req.setAttribute("message", m);

        req.getRequestDispatcher("/jsp/manage-pages/customer-list.jsp").forward(req, res);

    }
}
