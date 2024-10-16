package wascoot.resource;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;

public class Customer extends AbstractResource{

    private final String cf;

    private final String name;

    private final String surname;

    private final String email;

    private final String sex;

    private final String birthdate;

    private final String postalCode;

    private final String paymentType;

    public Customer(final String cf, final String name, final String surname, final String email, final String sex,
                    final String birthdate, final String postalCode, final String paymentType){
        this.cf = cf;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
        this.birthdate = birthdate;
        this.postalCode = postalCode;
        this.paymentType = paymentType;
    }
    public final String getCF() {
        return cf;
    }
    public final String getName() {
        return name;
    }
    public final String getSurname() {
        return surname;
    }
    public final String getEmail() {
        return email;
    }
    public final String getSex() {
        return sex;
    }
    public final String getBirthdate() {
        return birthdate;
    }
    public final String getPostalCode() {
        return postalCode;
    }
    public final String getPaymentType() {
        return paymentType;
    }

    public void writeJSON(OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("customer");

        jg.writeStartObject();

        jg.writeStringField("cf", cf);

        jg.writeStringField("name", name);

        jg.writeStringField("surname", surname);

        jg.writeStringField("email", email);

        jg.writeStringField("sex", sex);

        jg.writeStringField("birthdate", birthdate);

        jg.writeStringField("postalCode", postalCode);

        jg.writeStringField("paymentType", paymentType);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }
}
