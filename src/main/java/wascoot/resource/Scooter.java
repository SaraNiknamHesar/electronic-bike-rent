package wascoot.resource;

import com.fasterxml.jackson.core.*;

import java.io.*;
import java.sql.Date;

public class Scooter extends AbstractResource implements Comparable<Scooter> {
    /**
     * The identifier of the scooter
     */
    private final int id;

    /**
     * The date of purchase of the scooter
     */
    private final Date dateOfPurchase;

    /**
     * The km traveled by the scooter
     */
    private final double kmTraveled;

    /**
     * The model of a scooter
     */
    private final String model;

    /**
     * The percentage of remaining battery life of the scooter
     */
    private final double remainingBatteryLife;
    /**
     * The id of the scooterrack where this scooter is parked
     */
    private final int idScooterrack;


    /**
     * Creates a new scooter
     *
     * @param id
     *          id of the scooter
     * @param dateOfPurchase
     *          date of purchase
     * @param kmTraveled
     *          km traveled by the scooter
     * @param model
     *          model of the scooter
     * @param remainingBatteryLife
     *          percentage of remaining battery life
     * @param idScooterrack
     *          id of the scooterrack where the scooter is parked
     */
    public Scooter(final int id, final Date dateOfPurchase, final double kmTraveled, final String model, final double remainingBatteryLife, final int idScooterrack ) {
        this.id = id;
        this.dateOfPurchase = dateOfPurchase;
        this.kmTraveled = kmTraveled;
        this.model = model;
        this.remainingBatteryLife = remainingBatteryLife;
        this.idScooterrack = idScooterrack;
    }

    /** GETTERS **/

    public final int getId() {
        return id;
    }
    public final Date getDateOfPurchase() {
        return dateOfPurchase;
    }
    public final double getKmTraveled() {
        return kmTraveled;
    }
    public final String getModel() {
        return model;
    }
    public final double getRemainingBatteryLife() {
        return remainingBatteryLife;
    }
    public final int getIdScooterrack() {
        return idScooterrack;
    }

    @Override
    public void writeJSON(OutputStream out) throws IOException {
        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("scooter");

        jg.writeStartObject();

        jg.writeNumberField("id", id);

        jg.writeStringField("dateOfPurchase", dateOfPurchase.toString());

        jg.writeNumberField("kmTraveled", kmTraveled);

        jg.writeStringField("model", model);

        jg.writeNumberField("remainingBatteryLife", remainingBatteryLife);

        jg.writeNumberField("idScooterrack", idScooterrack);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

    @Override
    public int compareTo(Scooter o) {
        return Integer.compare(this.id, o.id);
    }
}
