/*
 * Copyright 2023 University of Padua, Italy
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

package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.UpdateAdministratorDAO;
import wascoot.resource.Actions;
import wascoot.resource.Administrator;
import wascoot.resource.LogContext;
import wascoot.resource.Message;

import java.io.EOFException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A REST resource for updating {@link Administrator}s.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class UpdateAdministratorRR extends AbstractRR {

	/**
	 * Creates a new REST resource for updating {@code Administrator}s.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 * @param con the connection to the database.
	 */
	public UpdateAdministratorRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
		super(Actions.UPDATE_ADMINISTRATOR, req, res, con);
	}


	@Override
	protected void doServe() throws IOException {

		Administrator e = null;
		Message m = null;

		try {
			// parse the URI path to extract the badge
			String path = req.getRequestURI();
			path = path.substring(path.lastIndexOf("administrator") + 13);

			final int id = Integer.parseInt(path.substring(1));

			LogContext.setResource(Integer.toString(id));

			final Administrator administrator = Administrator.fromJSON(req.getInputStream());

			if (id != administrator.getId()) {
				LOGGER.warn("Cannot update the administrator: URI request (%d) and administrator resource (%d) badges differ.",
						id, administrator.getId());

				m = new Message("Cannot update the administrator: URI request and Administrator resource ids differ.", "E4A8",
						String.format("Request URI id %d; administrator resource id %d.", id, administrator.getId()));
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				m.toJSON(res.getOutputStream());
				return;
			}


			// creates a new DAO for accessing the database and updates the administrator
			e = new UpdateAdministratorDAO(con, administrator).access().getOutputParam();

			if (e != null) {
				LOGGER.info("Administrator successfully updated.");

				res.setStatus(HttpServletResponse.SC_OK);
				e.toJSON(res.getOutputStream());
			} else {
				LOGGER.warn("Administrator not found. Cannot update it.");

				m = new Message(String.format("Administrator %d not found. Cannot update it.", id), "E5A3", null);
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				m.toJSON(res.getOutputStream());
			}
		} catch (IndexOutOfBoundsException | NumberFormatException ex) {
			LOGGER.warn("Cannot delete the administrator: wrong format for URI /administrator/{id}.", ex);

			m = new Message("Cannot delete the administrator: wrong format for URI /administrator/{id}.", "E4A7",
					ex.getMessage());
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			m.toJSON(res.getOutputStream());
		} catch (EOFException ex) {
			LOGGER.warn("Cannot updated the administrator: no Administrator JSON object found in the request.", ex);

			m = new Message("Cannot update the administrator: no Administrator JSON object found in the request.", "E4A8",
					ex.getMessage());
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			m.toJSON(res.getOutputStream());
		} catch (SQLException ex) {
			if ("23503".equals(ex.getSQLState())) {
				LOGGER.warn("Cannot delete the administrator: other resources depend on it.");

				m = new Message("Cannot delete the administrator: other resources depend on it.", "E5A4", ex.getMessage());
				res.setStatus(HttpServletResponse.SC_CONFLICT);
				m.toJSON(res.getOutputStream());
			} else {
				LOGGER.error("Cannot delete the administrator: unexpected database error.", ex);

				m = new Message("Cannot delete the administrator: unexpected database error.", "E5A1", ex.getMessage());
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				m.toJSON(res.getOutputStream());
			}
		}
	}

}
