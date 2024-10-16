package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.CreateScooterrackDAO;
import wascoot.database.ScooterRackDatabase;
import wascoot.database.UpdateScooterrackDAO;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;
import wascoot.resource.Scooterrack;
import wascoot.utils.ErrorCode;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet that provides features for scooterracks: add, edit and list
 */
public class ScooterrackServlet extends AbstractDatabaseServlet{

    /**
     * Provides list scooterrack feature
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("scooterrack") + 12);

        switch (op){

            case "list/":
                LogContext.setIPAddress(req.getRemoteAddr());
                LogContext.setAction(Actions.LIST_SCOOTERRACKS);

                List<Scooterrack> el = null;
                Message m = null;

                try {

                    el = new ScooterRackDatabase(getDataSource().getConnection()).getScooterRackList();

                    m = new Message("Scooter Racks successfully searched.");

                } catch (NumberFormatException ex) {
                    m = new Message("Cannot search for the scooter racks in the db.",
                            "E100", ex.getMessage());
                } catch (SQLException ex) {
                    m = new Message("Cannot search for the scooter racks: unexpected error while accessing the database.",
                            "E200", ex.getMessage());
                }

                // stores the lists and the message as a request attribute
                req.setAttribute("scooterrackList", el);
                req.setAttribute("message", m);

                // forwards the control to the scooterracks.jsp
                req.getRequestDispatcher("/jsp/manage-pages/scooterrack-list.jsp").forward(req, res);
                break;
            default:
                writeError(res, ErrorCode.OPERATION_UNKNOWN);
                LOGGER.warn("requested op "+op);
        }

    }

    /**
     * Provides edit and add scooterrack features
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("scooterrack") + 12);

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
     * Inserts a scooterrack in the database
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    private void insertOperation(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.INSERT_NEW_SCOOTERRACK);

        Integer id = null;
        Integer totalParkingSpots = null;
        Integer availableParkingSpots = null;
        String postalCode = null;
        String address = null;

        Scooterrack s = null;
        Message m = null;

        try {
            // retrieves the request parameters

            totalParkingSpots = Integer.parseInt(req.getParameter("total_parking_spots"));
            availableParkingSpots = Integer.parseInt(req.getParameter("available_parking_spots"));
            postalCode = req.getParameter("postalcode");
            address = req.getParameter("address");

            LogContext.setResource("new scooterrack");

            s = new Scooterrack(-1, totalParkingSpots, availableParkingSpots, postalCode, address);

            new CreateScooterrackDAO(getConnection(), s).access();

            m = new Message("New scooterrack successfully created.");

            LOGGER.info("New scooterrack successfully created in the database.");

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the scooterrack. Invalid input parameters.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the scooterrack. Invalid input parameters.",
                    ex);
        } catch (SQLException ex) {
            m = new Message("Cannot create the scooterrack: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

            LOGGER.error("Cannot create the scooterrack: unexpected error while accessing the database.", ex);
        }

        try {

            req.setAttribute("newScooterrack", s);
            req.setAttribute("message", m);

            req.getRequestDispatcher("/jsp/manage-pages/error-scooterrack.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating scooterrack."), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }


    }

    /**
     * Updates a scooterrack in database.
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    private void updateOperation(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_SCOOTERRACK);

        Integer id = null;
        Integer totalParkingSpots = null;
        Integer availableParkingSpots = null;
        String postalCode = null;
        String address = null;

        Scooterrack s = null;
        Message m = null;

        try {
            // retrieves the request parameters

            id = Integer.parseInt(req.getParameter("id"));
            totalParkingSpots = Integer.parseInt(req.getParameter("total_parking_spots"));
            availableParkingSpots = Integer.parseInt(req.getParameter("available_parking_spots"));
            postalCode = req.getParameter("postalcode");
            address = req.getParameter("address");

            // set the id of the scooterrack as the resource in the log context
            // at this point we know it is a valid integer

            LogContext.setResource(req.getParameter("id"));

            // creates a new scooterrack from the request parameters
            s = new Scooterrack(id, totalParkingSpots, availableParkingSpots, postalCode, address);

            new UpdateScooterrackDAO(getConnection(), s).access();

            m = new Message(String.format("Scooterrack %d successfully updated.", id));

            LOGGER.info("Scooterrack %d successfully updated in the database.", id);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the scooterrack. Invalid input parameters",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the scooterrack. Invalid input parameters",
                    ex);
        } catch (SQLException ex) {

                m = new Message("Cannot create the scooterrack: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot create the scooterrack: unexpected error while accessing the database.", ex);

        }

        try {
            // stores the scooterrack and the message as a request attribute
            req.setAttribute("updateScooterrack", s);
            req.setAttribute("message", m);


            req.getRequestDispatcher("/jsp/manage-pages/error-scooterrack.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when updating scooterrack %s.", id), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }
    }
}
