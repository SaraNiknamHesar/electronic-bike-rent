package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.CreateSubscriptionDAO;
import wascoot.database.ListSubscriptionDAO;
import wascoot.database.UpdateSubscriptionDAO;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;
import wascoot.resource.Subscription;
import wascoot.utils.ErrorCode;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * Subscription servlet
 */
public class SubscriptionServlet extends AbstractDatabaseServlet{

    /**
     * doGet method
     * @param req   HTTP request from client
     * @param res  HTTP response from server
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("subscription") + 13);

        switch (op){
            case "list/":
                List<Subscription> el = null;
                Message m = null;

                try {

                    el = new ListSubscriptionDAO(getConnection()).access().getOutputParam();

                    m = new Message("Subscriptions successfully searched.");

                } catch (NumberFormatException ex) {
                    m = new Message("Cannot search for subscriptions.",
                            "E100", ex.getMessage());
                } catch (SQLException ex) {
                    m = new Message("Cannot search for subscriptions: unexpected error while accessing the database.",
                            "E200", ex.getMessage());
                }

                req.setAttribute("subscriptionList", el);
                req.setAttribute("message", m);

                req.getRequestDispatcher("/jsp/manage-pages/subscription-list.jsp").forward(req, res);
                break;
            default:
                writeError(res, ErrorCode.OPERATION_UNKNOWN);
                LOGGER.warn("requested op "+op);

        }
    }

    /**
     * doPost method
     * @param req   HTTP request from client
     * @param res  HTTP response from server
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("subscription") + 13);

        switch (op){

            case "insert/":
                insertOperation(req, res);
                break;
            case "update/":
                updateOperation(req, res);
                break;
            default:
                writeError(res, ErrorCode.OPERATION_UNKNOWN);
                LOGGER.warn("requested op" + op);
        }

    }

    private void insertOperation(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.CREATE_SUBSCRIPTION);


        String type = null;
        int dailyUnlocks = -1;
        String validityPerDay = null;
        double fixedPrice = -1;


        Subscription e = null;
        Message m = null;

        try {

            type = req.getParameter("type");
            dailyUnlocks =Integer.parseInt(req.getParameter("daily_unlocks"));
            validityPerDay = req.getParameter("validity_per_day");
            fixedPrice = Double.parseDouble(req.getParameter("fixed_price"));

            LogContext.setResource(req.getParameter("type"));


            e = new Subscription(type, dailyUnlocks, Time.valueOf(validityPerDay), fixedPrice);


            new CreateSubscriptionDAO(getConnection(), e).access();

            m = new Message(String.format("Subscription %s successfully created.", type));

            LOGGER.info("Subscription %s successfully created in the database.", type);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the subscription. Invalid input parameters: daily unlocks must be integer.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the subscription. Invalid input parameters: daily unlocks must be integer.",
                    ex);
        } catch (SQLException ex) {

                m = new Message("Cannot create the subscription: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot create the subscription: unexpected error while accessing the database.", ex);

        }

        try {
            // stores the employee and the message as a request attribute
            req.setAttribute("newSubscription", e);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/manage-pages/error-subscription.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating subscription %s.", type), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }

    private void updateOperation(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_SUBSCRIPTION);

        int id = -1;
        String type = null;
        int dailyUnlocks = -1;
        String validityPerDay = null;
        double fixedPrice = -1;


        Subscription e = null;
        Message m = null;

        try {

            id = Integer.parseInt(req.getParameter("id"));
            type = req.getParameter("type");
            dailyUnlocks = Integer.parseInt(req.getParameter("daily_unlocks"));
            validityPerDay = req.getParameter("validity_per_day");
            fixedPrice = Double.parseDouble(req.getParameter("fixed_price"));


            LogContext.setResource(req.getParameter("id"));

            e = new Subscription(id,type, dailyUnlocks, Time.valueOf(validityPerDay), fixedPrice);

            new UpdateSubscriptionDAO(getConnection(), e).access();

            m = new Message(String.format("Subscription with id %d successfully updated.", id));

            LOGGER.info("Subscription with id %d successfully updated in the database.", id);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot update the subscription. Invalid input parameters: id, daily unlocks must be integer and fixed price should be double.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot update the subscription. Invalid input parameters: id, daily unlocks must be integer and fixed price should be double.",
                    ex);
        } catch (SQLException ex) {

                m = new Message("Cannot update the subscription: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot update the subscription: unexpected error while accessing the database.", ex);

        }

        try {

            req.setAttribute("updateSubscription", e);
            req.setAttribute("message", m);

            req.getRequestDispatcher("/jsp/manage-pages/error-subscription.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when updating subscription %d.", id), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }
}
