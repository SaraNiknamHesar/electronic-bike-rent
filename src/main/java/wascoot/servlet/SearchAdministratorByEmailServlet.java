

package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.SearchAdministratorByEmailDAO;
import wascoot.resource.Actions;
import wascoot.resource.Administrator;
import wascoot.resource.LogContext;
import wascoot.resource.Message;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet that provide the feature of :
 * 		Search administrators by email
 */
public final class SearchAdministratorByEmailServlet extends AbstractDatabaseServlet {

	/**
	 * doPost method, actually does the search
	 * @param req request from client
	 * @param res response from server
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.SEARCH_ADMINISTRATOR_BY_EMAIL);


		// request parameter
		String email = null;

		// model
		List<Administrator> el = null;
		Message m = null;

		try {

			// retrieves the request parameter
			email = req.getParameter("email");

			// creates a new object for accessing the database and searching the administrator
			el = new SearchAdministratorByEmailDAO(getConnection(), email).access().getOutputParam();

			m = new Message("Administrator successfully searched.");

			LOGGER.info("Administrator successfully searched by Email %s.", email);

		} catch (NumberFormatException ex) {
			m = new Message("Cannot search for administrator. Invalid input parameters: Email must be string.", "E100",
					ex.getMessage());

			LOGGER.error("Cannot search for administrator. Invalid input parameters: Email must be string.", ex);
		} catch (SQLException ex) {
			m = new Message("Cannot search for administrator: unexpected error while accessing the database.", "E200",
					ex.getMessage());

			LOGGER.error("Cannot search for administrator: unexpected error while accessing the database.", ex);
		}

		try {

			// stores the administrator list and the message as a request attribute
			req.setAttribute("administratorList", el);
			req.setAttribute("message", m);

			// forwards the control to the search-administrator-result JSP
			req.getRequestDispatcher("/jsp/search-administrator-result.jsp").forward(req, res);


		} catch (IOException ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating administrator %s.", email), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeUser();
		}
	}

}
