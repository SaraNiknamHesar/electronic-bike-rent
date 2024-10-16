package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.CustomerAvgAgeDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import wascoot.resource.MapEntryResource;



public final class CustomerAvgAgeRR extends AbstractRR {
    public CustomerAvgAgeRR(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(Actions.GET_AVG_AGE_CUSTOMERS, req, res, con);
    }

    @Override
    protected void doServe() throws IOException {
        List<MapEntryResource> entries = new ArrayList<>();
        Message m = null;

        try {
            Map<String, Double> el = new CustomerAvgAgeDAO(con).access().getOutputParam();

            if (el != null) {
                LOGGER.info("Customer(s) avg age successfully listed.");

                for (Map.Entry<String, Double> entry : el.entrySet()) {
                    entries.add(new MapEntryResource(entry.getKey(), entry.getValue()));
                }

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(entries).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while listing avg age customer(s).");

                m = new Message("Cannot list avg age customer(s): unexpected error.", "E10A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (SQLException ex) {
            LOGGER.error("Cannot list avg age customer(s): unexpected database error.", ex);

            m = new Message("Cannot list avg customer(s): unexpected database error.", "E10A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}