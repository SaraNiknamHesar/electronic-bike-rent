package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.CreateScooterDAO;
import wascoot.database.ListScootersDAO;
import wascoot.database.UpdateScooterDAO;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;
import wascoot.resource.Scooter;
import wascoot.utils.ErrorCode;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Servlet that provides features for scooter resource : add, edit and list
 */
public class ScooterServlet extends AbstractDatabaseServlet{

    /**
     * List scooters if url is correct
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("scooter") + 8);

        switch (op){

            case "list/":

                LogContext.setIPAddress(req.getRemoteAddr());
                LogContext.setAction(Actions.LIST_SCOOTERS);

                List<Scooter> sl = null;
                Message m = null;

                try {

                    // creates a new object for accessing the database and searching the scooters
                    sl = new ListScootersDAO(getDataSource().getConnection()).getScootersList();

                    m = new Message("Scooters successfully searched.");

                } catch (NumberFormatException ex) {
                    m = new Message("Cannot search for the scooters in the db.",
                            "E100", ex.getMessage());
                } catch (SQLException ex) {
                    m = new Message("Cannot search for the scooters: unexpected error while accessing the database.",
                            "E200", ex.getMessage());
                }

                // stores the lists and the message as a request attribute
                req.setAttribute("scootersList", sl);
                req.setAttribute("message", m);

                // forwards the control to the scooter.jsp
                req.getRequestDispatcher("/jsp/manage-pages/scooter-list.jsp").forward(req, res);
                break;
            default:
                writeError(res, ErrorCode.OPERATION_UNKNOWN);
                LOGGER.warn("requested op "+op);

        }

    }

    /**
     * Provides features of add and edit for scooters
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("scooter") + 8);

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

    /**
     * Adds a scooter in the database.
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    private void insertOperation(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.INSERT_NEW_SCOOTER);

        java.util.Date dateOfPurchase = null;
        Double kmTraveled = null;
        String model = null;
        Double remainingBatteryLife = null;
        Integer idScooterrack = null;

        Scooter s = null;
        Message m = null;

        try {
            // retrieves the request parameters

            dateOfPurchase = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("date_of_purchase"));
            kmTraveled = Double.parseDouble(req.getParameter("km_traveled"));
            model = req.getParameter("model");
            remainingBatteryLife = Double.parseDouble(req.getParameter("remaining_battery_life"));
            idScooterrack = Integer.parseInt(req.getParameter("id_scooterrack"));

            java.sql.Date dateOfPurchase_sql = new java.sql.Date(dateOfPurchase.getTime());

            LogContext.setResource(req.getParameter("id"));

            s = new Scooter(-1, dateOfPurchase_sql, kmTraveled, model, remainingBatteryLife, idScooterrack);

            new CreateScooterDAO(getConnection(), s).access();

            m = new Message("New scooter successfully created.");

            LOGGER.info("New scooter successfully created in the database.");

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the scooter. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the scooter. Invalid input parameters.",
                    ex);
        } catch (SQLException ex) {
            m = new Message("Cannot create the scooter: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

            LOGGER.error("Cannot create the scooter: unexpected error while accessing the database.", ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {

            req.setAttribute("newScooter", s);
            req.setAttribute("message", m);


            req.getRequestDispatcher("/jsp/manage-pages/error-scooter.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating scooter."), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }
    }

    /**
     * Updates a scooter
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    private void updateOperation(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_SCOOTER);

        Integer id = null;
        java.util.Date dateOfPurchase = null;
        Double kmTraveled = null;
        String model = null;
        Double remainingBatteryLife = null;
        Integer idScooterrack = null;

        Scooter s = null;
        Message m = null;

        try {
            // retrieves the request parameters

            id = Integer.parseInt(req.getParameter("id"));
            dateOfPurchase = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("date_of_purchase"));
            kmTraveled = Double.parseDouble(req.getParameter("km_traveled"));
            model = req.getParameter("model");
            remainingBatteryLife = Double.parseDouble(req.getParameter("remaining_battery_life"));
            idScooterrack = Integer.parseInt(req.getParameter("id_scooterrack"));

            java.sql.Date dateOfPurchase_sql = new java.sql.Date(dateOfPurchase.getTime());

            LogContext.setResource(req.getParameter("id"));

            // creates a new scooter from the request parameters
            s = new Scooter(id, dateOfPurchase_sql, kmTraveled, model, remainingBatteryLife, idScooterrack);


            new UpdateScooterDAO(getConnection(), s).access();

            m = new Message(String.format("Scooter %d successfully updated.", id));

            LOGGER.info("Scooter %d successfully updated in the database.", id);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the scooter. Invalid input parameters",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the scooter. Invalid input parameters",
                    ex);
        } catch (SQLException ex) {
                m = new Message("Cannot create the scooter: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot create the scooter: unexpected error while accessing the database.", ex);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            // stores the scooter and the message as a request attribute
            req.setAttribute("updateScooter", s);
            req.setAttribute("message", m);

            req.getRequestDispatcher("/jsp/manage-pages/error-scooter.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when updating scooter %d.", id), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }

}
