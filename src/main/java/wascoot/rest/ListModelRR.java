package wascoot.rest;

import wascoot.database.*;
import wascoot.resource.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Manages the REST API for the {@link Model} resource.
 *
 * @version 1.00
 * @since 1.00
 */

public final class ListModelRR extends AbstractRR {
    /**
     * Creates a new REST resource for managing {@code Model} resources.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public ListModelRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(Actions.LIST_MODELS, req, res, con);
    }

    /**
     * Lists all the models.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    @Override
    protected void doServe() throws IOException {

        List<Model> el = null;
        Message m = null;

        try {

            // creates a new DAO for accessing the database and lists the model(s)
            el = new ListModelDAO(con).access().getOutputParam();

            if (el != null) {
                LOGGER.info("Model(s) successfully listed.");

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(el).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while listing model(s).");

                m = new Message("Cannot list model(s): unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (SQLException ex) {
            LOGGER.error("Cannot list model(s): unexpected database error.", ex);

            m = new Message("Cannot list model(s): unexpected database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
