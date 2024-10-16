package wascoot.resource;

import com.fasterxml.jackson.core.*;

import java.io.*;
import java.sql.Date;

public class Rental extends AbstractResource {
    private final int id;
    private final Date dateHourDelivery;
    private final Date dateHourCollection;
    private final int idScooter;
    private final int scooterrackDelivery;
    private final int scooterrackCollection;
    private final String customer;
    private final double kmTraveled;

    public Rental(final int id, final Date dateHourDelivery, final Date dateHourCollection, final int idScooter, final int scooterrackDelivery, final int scooterrackCollection, final String customer, final double kmTraveled) {
        this.id = id;
        this.dateHourDelivery = dateHourDelivery;
        this.dateHourCollection = dateHourCollection;
        this.idScooter = idScooter;
        this.scooterrackDelivery = scooterrackDelivery;
        this.scooterrackCollection = scooterrackCollection;
        this.customer = customer;
        this.kmTraveled = kmTraveled;
    }

    /** GETTERS **/

    public final int getId() {
        return id;
    }
    public final Date getDateHourDelivery() {
        return dateHourDelivery;
    }
    public final Date getDateHourCollection() {
        return dateHourCollection;
    }
    public int getIdScooter() {
        return idScooter;
    }
    public int getScooterrackDelivery() {
        return scooterrackDelivery;
    }
    public int getScooterrackCollection() {
        return scooterrackCollection;
    }
    public String getCustomer() {
        return customer;
    }
    public final double getKmTraveled() {
        return kmTraveled;
    }

    @Override
    public void writeJSON(OutputStream out) throws IOException {

    }
}
