package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.ScooterRackPosDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public final class ScooterRackPosRR extends AbstractRR {
    public ScooterRackPosRR(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(Actions.GET_SCOOTERRACK_POS,req, res, con);
    }

    @Override
    protected void doServe() throws IOException {

        List<ScooterRackPos> el = null;
        Message m = null;

        try {

            // creates a new DAO for accessing the database and lists the employee(s)
            el = new ScooterRackPosDAO(con).access().getOutputParam();

            if (el != null) {
                LOGGER.info("ScooterRackPosition(s) successfully listed.");

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(el).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while listing scooterracks position(s).");

                m = new Message("Cannot list scooterrack position(s): unexpected error.", "E10A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (SQLException ex) {
            LOGGER.error("Cannot list scooterrack position(s): unexpected database error.", ex);

            m = new Message("Cannot list scooterrack position(s): unexpected database error.", "E10A2", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }


}
