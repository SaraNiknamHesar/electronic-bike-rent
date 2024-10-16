/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wascoot.resource;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractResource implements Resource {

	/**
	 * A LOGGER available for all the subclasses.
	 */
	protected static final Logger LOGGER = LogManager.getLogger(AbstractResource.class, StringFormatterMessageFactory.INSTANCE);

	/**
	 * The JSON factory to be used for creating JSON parsers and generators.
	 */
	protected static final JsonFactory JSON_FACTORY;

	static {
		// set up the JSON factory
		JSON_FACTORY = new JsonFactory();
		JSON_FACTORY.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
		JSON_FACTORY.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);

		LOGGER.debug("JSON factory successfully setup.");
	}

	@Override
	public void toJSON(final OutputStream out) throws IOException {

		if(out == null) {
			LOGGER.error("The output stream cannot be null.");
			throw new IOException("The output stream cannot be null.");
		}

		try {
			writeJSON(out);
		} catch(Exception e) {
			LOGGER.error("Unable to serialize the resource to JSON.", e);
			throw new IOException("Unable to serialize the resource to JSON.", e);
		}

	}

	/**
	 * Performs the actual writing of JSON.
	 *
	 * Subclasses have to implement this method to provide the actual logic needed for representing the {@code
	 * Resource} to JSON.
	 *
	 * @param out the stream to which the JSON representation of the {@code Resource} has to be written.
	 *
	 * @throws Exception if something goes wrong during writing.
	 */
	protected abstract void writeJSON(OutputStream out) throws Exception;
}
