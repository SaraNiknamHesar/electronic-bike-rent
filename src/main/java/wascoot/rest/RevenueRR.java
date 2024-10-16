package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.PaymentWithSubscriptionDatabase;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RevenueRR extends AbstractRR{


    /**
     * Creates a new REST resource.
     *
     * @param req    the HTTP request.
     * @param res    the HTTP response.
     * @param con    the connection to the database.
     */
    public RevenueRR(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(Actions.GET_REVENUE, req, res, con);
    }

    @Override
    protected void doServe() throws IOException {
        Message m = null;
        try {
            List<Revenue> el = new PaymentWithSubscriptionDatabase(con).getRevenueInfo();

            res.setStatus(HttpServletResponse.SC_OK);
            new ResourceList(el).toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            LOGGER.error("Cannot list Revenue Info: unexpected database error.", ex);

            m = new Message("Cannot list  Revenue Info: unexpected database error.", "E10A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
