
package wascoot.resource;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Administrator extends AbstractResource{

	/**
	 * The badge number (identifier) of the Administrator
	 */
	public final int id;

	/**
	 * The surname of the Administrator
	 */
	public final String email;

	/**
	 * The age of the Administrator
	 */
	public final String password;

	public String name;

	public byte[] photo;

	/**
	 * The MIME media type of photo of the Administrator
	 */
	public String photoMediaType;

	/*
	 * @param id
	 *            the id of the Administrator
	 * @param email
	 *            the email of the Administrator.
	 * @param password
	 *            the password of the Administrator.
	 */
	public Administrator(final int id, final String email, final String password,  String name, byte[] photo, String photoMediaType) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.photo = photo;
		this.photoMediaType = photoMediaType;
	}
	public Administrator(final int id, final String email, final String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	public Administrator(final int id, final String email, final String password, String name) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}

	/**
	 * Returns the id of the Administrator.
	 * 
	 * @return the id of the Administrator.
	 */
	public final int getId() { return id; }

	/**
	 * Returns the Email of the Administrator.
	 * 
	 * @return the Email of the Administrator.
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * Returns the password of the Administrator.
	 * 
	 * @return the password of the Administrator.
	 */
	public final String getPassword() {
		return password;
	}
	public final String getName() {
		return name;
	}


	public final byte[] getPhoto() {
		return photo;
	}
	public final String getPhotoMediaType() {
		return photoMediaType;
	}
	public final boolean hasPhoto() {
		return photo != null && photo.length > 0 && photoMediaType != null && !photoMediaType.isBlank();
	}
	public final int getPhotoSize() {
		return photo != null ? photo.length : Integer.MIN_VALUE;
	}

	@Override
	protected final void writeJSON(final OutputStream out) throws IOException {

		final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

		jg.writeStartObject();

		jg.writeFieldName("administrator");

		jg.writeStartObject();

		jg.writeNumberField("id", id);

		jg.writeStringField("email", email);

		jg.writeStringField("password", password);

		jg.writeStringField("name", name);

		jg.writeEndObject();

		jg.writeEndObject();

		jg.flush();
	}

	public static Administrator fromJSON(final InputStream in) throws IOException  {

		// the fields read from JSON
		int jId = -1;
		String jEmail = null;
		String jPassword = null;
		String jName = null;

		try {
			final JsonParser jp = JSON_FACTORY.createParser(in);

			// while we are not on the start of an element or the element is not
			// a token element, advance to the next element (if any)
			while (jp.getCurrentToken() != JsonToken.FIELD_NAME || !"administrator".equals(jp.getCurrentName())) {

				// there are no more events
				if (jp.nextToken() == null) {
					LOGGER.error("No Administrator object found in the stream.");
					throw new EOFException("Unable to parse JSON: no Administrator object found.");
				}
			}

			while (jp.nextToken() != JsonToken.END_OBJECT) {

				if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

					switch (jp.getCurrentName()) {
						case "id":
							jp.nextToken();
							jId = jp.getIntValue();
							break;
						case "email":
							jp.nextToken();
							jEmail = jp.getText();
							break;
						case "password":
							jp.nextToken();
							jPassword = jp.getText();
							break;
						case "name":
							jp.nextToken();
							jName = jp.getText();
							break;

					}
				}
			}
		} catch(IOException e) {
			LOGGER.error("Unable to parse an Administrator object from JSON.", e);
			throw e;
		}

		return new Administrator(jId, jEmail, jPassword, jName);
	}
}
