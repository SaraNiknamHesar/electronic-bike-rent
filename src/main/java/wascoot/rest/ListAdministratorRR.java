
package wascoot.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.ListAdministratorDAO;
import wascoot.resource.Actions;
import wascoot.resource.Administrator;
import wascoot.resource.Message;
import wascoot.resource.ResourceList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public final class ListAdministratorRR extends AbstractRR {

	/**
	 * Creates a new REST resource for listing {@code Administrator}s.
	 *
	 * @param req the HTTP request.
	 * @param res the HTTP response.
	 * @param con the connection to the database.
	 */
	public ListAdministratorRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
		super(Actions.LIST_ADMINISTRATOR, req, res, con);
	}


	@Override
	protected void doServe() throws IOException {

		List<Administrator> el = null;
		Message m = null;

		try {

			// creates a new DAO for accessing the database and lists the administrator(s)
			el = new ListAdministratorDAO(con).access().getOutputParam();

			if (el != null) {
				LOGGER.info("Administrator(s) successfully listed.");

				res.setStatus(HttpServletResponse.SC_OK);
				new ResourceList(el).toJSON(res.getOutputStream());
			} else { // it should not happen
				LOGGER.error("Fatal error while listing administrator(s).");

				m = new Message("Cannot list administrator(s): unexpected error.", "E5A1", null);
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				m.toJSON(res.getOutputStream());
			}
		} catch (SQLException ex) {
			LOGGER.error("Cannot list administrator(s): unexpected database error.", ex);

			m = new Message("Cannot list administrator(s): unexpected database error.", "E5A1", ex.getMessage());
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			m.toJSON(res.getOutputStream());
		}
	}


}
