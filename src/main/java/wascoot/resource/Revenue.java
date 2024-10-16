package wascoot.resource;

import java.io.IOException;
import java.io.OutputStream;

public class Revenue implements Resource{

    private String date;
    private int price;

    public Revenue(String date, int price) {
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void toJSON(OutputStream out) throws IOException {
        String json = "{\"date\": \"" + date + "\", \"price\": " + price + "}";
        out.write(json.getBytes());
    }
}
