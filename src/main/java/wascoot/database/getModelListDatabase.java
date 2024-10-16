package wascoot.database;

import wascoot.resource.Administrator;
import wascoot.resource.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public final class getModelListDatabase extends AbstractDAO<List<Model>>{
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT name, brand, battery_life, price_per_min FROM public.model";

    /**
     * Creates a new object for listing all the models.
     *
     * @param con
     *            the connection to the database.
     */
    public getModelListDatabase(final Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Model> models = new ArrayList<Model>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                models.add(new Model(rs.getString("name"), rs
                        .getString("brand"), Time.valueOf(rs.getString("battery_life")), rs.getInt("price_per_model")));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        outputParam = models;
    }

    /**
     * Lists all the models in the database.
     *
     * @return a list of {@code model} object.
     *
     * @throws SQLException
     *             if any error occurs while searching for employees.
     */
    public List<Model> getModelList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Model> models = new ArrayList<Model>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                models.add(new Model(rs.getString("name"), rs
                        .getString("brand"), Time.valueOf(rs.getString("battery_life")), rs.getInt("price_per_model")));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return models;
    }
}
