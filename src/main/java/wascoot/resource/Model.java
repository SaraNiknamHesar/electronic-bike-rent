package wascoot.resource;

import com.fasterxml.jackson.core.*;

import java.io.*;
import java.sql.Time;


public class Model extends AbstractResource implements Comparable<Model> {
    /**
     * The identifier of the model PK
     */
    private final String name;

    /**
     * The brand of the model
     */
    private final String brand;

    /**
     * The battery life of a model
     */
    //private final String batteryLife;
    private final Time batteryLife;


    /**
     * The price per minute of a model
     */
    private final double pricePerMin;



    /**
     * Creates a new model
     *
     * @param name
     *            the name of a model.
     * @param brand
     *            the brand of a model.
     * @param batteryLife
     *            the battery life of a model.
     * @param pricePerMin
     *            the price_per_min a model.
     */
    public Model(final String name, final String brand, final Time batteryLife, final double pricePerMin) {
        this.name = name;
        this.brand = brand;
        this.batteryLife = batteryLife;
        this.pricePerMin = pricePerMin;
    }

    /**
     * Returns the name of the model.
     *
     * @return the name of the model.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the brand of the model.
     *
     * @return the brand of the model.
     */
    public final String getBrand() {
        return brand;
    }

    /**
     * Returns the battery life of the model.
     *
     * @return the battery life of the model.
     */
    public final Time getBatteryLife() {
        return batteryLife;
    }

    /**
     * Returns the price per min.
     *
     * @return the price per min.
     */
    public final double getPricePerMin() {
        return pricePerMin;
    }


    /**
     * Creates a {@code Model} from its JSON representation.
     *
     * @param in the input stream containing the JSON document.
     *
     * @return the {@code Model} created from the JSON representation.
     *
     * @throws IOException if something goes wrong while parsing.
     */


    public static Model fromJSON(final InputStream in) throws IOException {

        // the fields read from JSON
        String jName = null;
        String jBrand = null;
        Time jBattery_life = null;
        double jPrice_per_min = -1.0;

        final JsonParser jp = JSON_FACTORY.createParser(in);

        // while we are not on the start of an element or the element is not
        // a token element, advance to the next element (if any)
        while (jp.getCurrentToken() != JsonToken.FIELD_NAME || "model".equals(jp.getCurrentName()) == false) {

            // there are no more events
            if (jp.nextToken() == null) {
                throw new IOException("Unable to parse JSON: no models object found.");
            }
        }

        while (jp.nextToken() != JsonToken.END_OBJECT) {

            if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                switch (jp.getCurrentName()) {
                    case "name":
                        jp.nextToken();
                        jName = jp.getText();
                        break;
                    case "brand":
                        jp.nextToken();
                        jBrand = jp.getText();
                        break;
                    case "battery_life":
                        jp.nextToken();
                        jBattery_life = Time.valueOf(jp.getText());
                        break;
                    case "price_per_min":
                        jp.nextToken();
                        jPrice_per_min= jp.getDoubleValue();
                        break;
                }
            }
        }

        return new Model(jName, jBrand, jBattery_life, jPrice_per_min);
    }


    @Override
    public void writeJSON(OutputStream out) throws IOException {
        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("model");

        jg.writeStartObject();

        jg.writeStringField("name", name);

        jg.writeStringField("brand", brand);

        jg.writeStringField("batteryLife", batteryLife.toString());

        jg.writeNumberField("pricePerMin", pricePerMin);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

    @Override
    public int compareTo(Model o) {
        return this.name.compareTo(o.name);
    }
}
