package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.CreateModelDAO;
import wascoot.database.ListModelDAO;
import wascoot.database.UpdateModelDAO;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;
import wascoot.resource.Model;
import wascoot.utils.ErrorCode;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * Servlet that provides list, insert and update features
 * of models
 */
public class ModelServlet extends AbstractDatabaseServlet{

    /**
     * Feature of list models
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("model") + 6);

        switch (op){

            case "list/":
                List<Model> el = null;
                Message m = null;

                try {
                    el = new ListModelDAO(getConnection()).access().getOutputParam();

                    m = new Message("Models successfully searched.");
                }catch (NumberFormatException ex) {
                    m = new Message("Cannot search for models.",
                            "E100", ex.getMessage());
                } catch (SQLException ex) {
                    m = new Message("Cannot search for models: unexpected error while accessing the database.",
                            "E200", ex.getMessage());
                }
                req.setAttribute("modelList", el);
                req.setAttribute("message", m);

                req.getRequestDispatcher("/jsp/manage-pages/model-list.jsp").forward(req, res);
                break;
            default:
                writeError(res, ErrorCode.OPERATION_UNKNOWN);
                LOGGER.warn("requested op "+op);

        }

    }

    /**
     * Insert and edit features of models
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("model") + 6);

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
        LogContext.setAction(Actions.CREATE_MODEL);

        String name = null;
        String brand = null;
        String batteryLife = null;
        double pricePerMin = -1;

        // model
        Model e = null;
        Message m = null;

        try {
            name = req.getParameter("name");
            brand = req.getParameter("brand");
            batteryLife = req.getParameter("battery_life");
            pricePerMin = Double.parseDouble(req.getParameter("price_per_min"));

            LogContext.setResource(req.getParameter("name"));


            e = new Model(name, brand, Time.valueOf(batteryLife), pricePerMin);

            // creates a new object for accessing the database and stores the model
            new CreateModelDAO(getConnection(), e).access();

            m = new Message(String.format("Model %s successfully created.", name));

            LOGGER.info("Model %s successfully created in the database.", name);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot create the model. Invalid input parameters: Price per min should be double.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the model. Invalid input parameters: Price per min should be double.",
                    ex);
        } catch (SQLException ex) {
            if ("23505".equals(ex.getSQLState())) {
                m = new Message(String.format("Cannot create the model: model %s already exists.", name), "E300",
                        ex.getMessage());

                LOGGER.error(
                        new StringFormattedMessage("Cannot create the model: model %s already exists.", name),
                        ex);
            } else {
                m = new Message("Cannot create the model: unexpected error while accessing the database.", "E200",
                        ex.getMessage());

                LOGGER.error("Cannot create the model: unexpected error while accessing the database.", ex);
            }
        }

        try {
            req.setAttribute("newModel", e);
            req.setAttribute("message", m);

            req.getRequestDispatcher("/jsp/manage-pages/error-model.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when creating model %s.", name), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }

    }

    private void updateOperation(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction(Actions.UPDATE_MODEL);

        String name = null;
        String brand = null;
        String batteryLife = null;
        double pricePerMin = -1;

        // model
        Model e = null;
        Message m = null;

        try {

            brand = req.getParameter("brand");
            batteryLife = req.getParameter("battery_life");
            pricePerMin = Double.parseDouble(req.getParameter("price_per_min"));
            name = req.getParameter("name");

            LogContext.setResource(req.getParameter("name"));

            e = new Model(name, brand, Time.valueOf(batteryLife), pricePerMin);


            new UpdateModelDAO(getConnection(), e).access();

            m = new Message(String.format("Model %s successfully updated.", name));

            LOGGER.info("Model %s successfully updated in the database.", name);

        } catch (NumberFormatException ex) {
            m = new Message(
                    "Cannot update the model. Invalid input parameters: Price per min should be double.",
                    "E100", ex.getMessage());

            LOGGER.error(
                    "Cannot create the model. Invalid input parameters: Price per min should be double.",
                    ex);
        } catch (SQLException ex) {
            m = new Message("Cannot create the model: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

            LOGGER.error("Cannot create the model: unexpected error while accessing the database.", ex);

        }

        try {
            // stores the employee and the message as a request attribute
            req.setAttribute("updateModel", e);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            req.getRequestDispatcher("/jsp/manage-pages/error-model.jsp").forward(req, res);
        } catch(Exception ex) {
            LOGGER.error(new StringFormattedMessage("Unable to send response when updating model %s.", name), ex);
            throw ex;
        } finally {
            LogContext.removeIPAddress();
            LogContext.removeAction();
            LogContext.removeResource();
        }


    }
}
