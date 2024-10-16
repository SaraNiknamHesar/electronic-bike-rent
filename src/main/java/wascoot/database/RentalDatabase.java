package wascoot.database;

import wascoot.resource.Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalDatabase extends AbstractDAO<Rental> {

    private static final String STATEMENT = "select scooterrack_collection , count(scooterrack_collection) as ct from rental group by scooterrack_collection order by ct desc";

    public RentalDatabase(Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

    }


    public List<Integer> getTopLocation() throws SQLException  {



        PreparedStatement pstmt = null;
        ResultSet rs = null;


        final List<Integer> topLocations = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                topLocations.add(rs.getInt("scooterrack_collection"));
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

        return topLocations;
    }



}
