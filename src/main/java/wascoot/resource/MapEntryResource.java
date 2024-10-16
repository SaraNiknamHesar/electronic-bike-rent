package wascoot.resource;

import java.io.IOException;
import java.io.OutputStream;

public class MapEntryResource implements Resource {
    private final String postalCode;
    private final double averageAge;

    public MapEntryResource(String postalCode, double averageAge) {
        this.postalCode = postalCode;
        this.averageAge = averageAge;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public double getAverageAge() {
        return averageAge;
    }

    @Override
    public void toJSON(OutputStream out) throws IOException {
        String json = "{\"postalCode\": \"" + postalCode + "\", \"averageAge\": " + averageAge + "}";
        out.write(json.getBytes());
    }
}