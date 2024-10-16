package wascoot.resource;

import com.fasterxml.jackson.core.*;

import java.io.*;

public class Scooterrack extends AbstractResource implements Comparable<Scooterrack> {
    /**
     * The identifier of the scooter rack (PK)
     */
    private final int id;

    /**
     * The total parking spots in a scooter rack
     */
    private final int totalParkingSpots;

    /**
     * The available parking spots in a scooter rack
     */
    private final int availableParkingSpots;

    /**
     * The postal code of a scooter rack
     */
    private final String postalCode;

    /**
     * The address of the scooter rack
     */
    private final String address;



    /**
     * Creates a new scooter rack
     *
     * @param id
     *            the id of a scooter rack.
     * @param totalParkingSpots
     *            the total parking spots of a scooter rack.
     * @param availableParkingSpots
     *            the total available parking spots of one scooter rack.
     * @param postalCode
     *            the postal code of a scooter rack.
     * @param address
     *            the address of a scooter rack.
     */
    public Scooterrack(final int id, final int totalParkingSpots, final int availableParkingSpots, final String postalCode, final String address ) {
        this.id = id;
        this.totalParkingSpots = totalParkingSpots;
        this.availableParkingSpots = availableParkingSpots;
        this.postalCode = postalCode;
        this.address = address;
    }

    /**
     * Returns the id of the scooter rack.
     *
     * @return the id of scooter rack.
     */
    public final int getId() {
        return id;
    }

    /**
     * Returns the total parking spots.
     *
     * @return the total parking spots.
     */
    public final int getTotalParkingSpots() {
        return totalParkingSpots;
    }

    /**
     * Returns the available parking spots of a scooter rack.
     *
     * @return the available parking spots of a scooter rack.
     */
    public final int getAvailableParkingSpots() {
        return availableParkingSpots;
    }

    /**
     * Returns the postal code of a scooter rack.
     *
     * @return the postal code of a scooter rack.
     */
    public final String getPostalCode() {
        return postalCode;
    }

    /**
     * Returns the address of a scooter rack.
     *
     * @return the address of a scooter rack.
     */
    public final String getAddress() {
        return address;
    }

    @Override
    public void writeJSON(OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("scooterrack");

        jg.writeStartObject();

        jg.writeNumberField("id", id);

        jg.writeNumberField("totalParkingSpots", totalParkingSpots);

        jg.writeNumberField("availableParkingSpots", availableParkingSpots);

        jg.writeStringField("postalCode", postalCode);

        jg.writeStringField("address", address);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

    @Override
    public int compareTo(Scooterrack o) {
        return Integer.compare(this.id, o.id);
    }
}
