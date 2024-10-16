package wascoot.database;

import wascoot.resource.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reads all the models in the database.
 */
public final class ListModelDAO extends AbstractDAO<List<Model>> {

    /**
     * The SQL statement to be executed.
     */
    private static final String STATEMENT = "SELECT name, brand, battery_life, price_per_min FROM public.model";

    /**
     * Creates a new object to read all models.
     * @param con connection to DB
     */
    public ListModelDAO(final Connection con){
        super(con);
    }

    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        List<Model> models = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                models.add(new Model(rs.getString("name"), rs.getString("brand"), Time.valueOf(rs.getString("battery_life")),
                        rs.getDouble("price_per_min")));
            }

            LOGGER.info("Model(s) successfully listed.");

            Collections.sort(models);

            LOGGER.info("Model(s) successfully sorted.");
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        this.outputParam = models;
    }
}