package wascoot.resource;

import java.io.IOException;
import java.io.OutputStream;

public class GenderDistributionResource implements Resource {
    private final int maleCount;
    private final int femaleCount;

    public GenderDistributionResource(int maleCount, int femaleCount) {
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
    }

    public int getMaleCount() {
        return maleCount;
    }

    public int getFemaleCount() {
        return femaleCount;
    }

    @Override
    public void toJSON(OutputStream out) throws IOException {
        String json = "{\"maleCount\": " + maleCount + ", \"femaleCount\": " + femaleCount + "}";
        out.write(json.getBytes());
    }
}
