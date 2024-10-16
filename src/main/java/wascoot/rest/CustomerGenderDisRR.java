package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.CustomerGenderDisDAO;
import wascoot.resource.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

import wascoot.resource.GenderDistributionResource;

public final class CustomerGenderDisRR extends AbstractRR {
    public CustomerGenderDisRR(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(Actions.GET_GENDER_DIS_CUSTOMERS, req, res, con);
    }

    @Override
    protected void doServe() throws IOException {
        int[] genderCounts = null;
        Message m = null;

        try {
            genderCounts = new CustomerGenderDisDAO(con).access().getOutputParam();

            if (genderCounts != null) {
                LOGGER.info("Gender distribution of customers successfully retrieved.");

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList<>(Collections.singletonList(
                        new GenderDistributionResource(genderCounts[0], genderCounts[1]))
                ).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while retrieving gender distribution of customers.");

                m = new Message("Cannot retrieve gender distribution of customers: unexpected error.", "E10A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (SQLException ex) {
            LOGGER.error("Cannot retrieve gender distribution of customers: unexpected database error.", ex);

            m = new Message("Cannot retrieve gender distribution of customers: unexpected database error.", "E10A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }
}
