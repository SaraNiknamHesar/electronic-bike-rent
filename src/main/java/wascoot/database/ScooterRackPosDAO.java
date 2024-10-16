package wascoot.database;

import wascoot.resource.ScooterRackPos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;

/**
 * Reads the list of all scooterracks in the database.
 */
public final class ScooterRackPosDAO extends AbstractDAO<List<ScooterRackPos>> {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT id, total_parking_spots, available_parking_spots, postalcode, address FROM public.scooterracks";

    /**
     * Creates a new object for listing all the scooterracks.
     *
     * @param con the connection to the database.
     */
    public ScooterRackPosDAO(final Connection con) {
        super(con);
    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<ScooterRackPos> scooterracks = new ArrayList<ScooterRackPos>();

        try {
            pstmt = con.prepareStatement(STATEMENT);

            rs = pstmt.executeQuery();
            //id, total_parking_spots, available_parking_spots, postalcode, address
            while (rs.next()) {
                int id = rs.getInt("id");
                int totalParkingSpots = rs.getInt("total_parking_spots");
                int availableParkingSpots = rs.getInt("available_parking_spots");
                String postalCode = rs.getString("postalCode");
                String address = rs.getString("address");

                // Fetch the latitude and longitude using jQuery
                String query = postalCode;
                String latitude = "";
                String longitude = "";

                try {
                    JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("27313886255a4b358bc58bee46f6889d");
                    JOpenCageForwardRequest request = new JOpenCageForwardRequest(query);
                    // Optional: Restrict results to a specific country
                    request.setRestrictToCountryCode("it");
                    // Optional: Restrict results to a geographic bounding box (southWestLng, southWestLat, northEastLng, northEastLat)
                    //request.setBounds(18.367, -34.109, 18.770, -33.704);

                    JOpenCageResponse response = jOpenCageGeocoder.forward(request);
                    JOpenCageLatLng firstResultLatLng = response.getFirstPosition();

                    latitude = String.valueOf(firstResultLatLng.getLat());
                    longitude = String.valueOf(firstResultLatLng.getLng());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                scooterracks.add(new ScooterRackPos(id, totalParkingSpots, availableParkingSpots, postalCode, address, latitude, longitude));
            }

            LOGGER.info("Scooterrack(s) successfully listed.");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        outputParam = scooterracks;
    }
}