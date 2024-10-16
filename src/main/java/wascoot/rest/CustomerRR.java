package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.CustomerDAO;
import wascoot.database.getModelListDatabase;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public final class CustomerRR extends AbstractRR {
    public CustomerRR(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(Actions.GET_ALL_CUSTOMERS,req, res, con);
    }

    @Override
    protected void doServe() throws IOException {

        List<Customer> el = null;
        Message m = null;

        try {

            // creates a new DAO for accessing the database and lists the employee(s)
            el = new CustomerDAO(con).access().getOutputParam();

            if (el != null) {
                LOGGER.info("Customer(s) successfully listed.");

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(el).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while listing customer(s).");

                m = new Message("Cannot list customer(s): unexpected error.", "E10A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (SQLException ex) {
            LOGGER.error("Cannot list customer(s): unexpected database error.", ex);

            m = new Message("Cannot list customer(s): unexpected database error.", "E10A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }


}
