

package wascoot.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.SearchAdministratorByIdDAO;
import wascoot.resource.Actions;
import wascoot.resource.Administrator;
import wascoot.resource.LogContext;
import wascoot.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Search administrators by id - servlet
 */
public final class SearchAdministratorByIdServlet extends AbstractDatabaseServlet {
	/**
	 * doPost method
	 * @param req HTTP request from client
	 * @param res HTTP response from server
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.SEARCH_ADMINISTRATOR_BY_ID);


		// request parameter
		int id = -1;

		// model
		List<Administrator> el = null;
		Message m = null;

		try {

			// retrieves the request parameter
			id = Integer.parseInt(req.getParameter("id"));

			// creates a new object for accessing the database and searching the administrator
			el = new SearchAdministratorByIdDAO(getConnection(), id).access().getOutputParam();

			m = new Message("Administrator successfully searched.");

			LOGGER.info("Administrator successfully searched by ID %d.", id);

		} catch (NumberFormatException ex) {
			m = new Message("Cannot search for administrator. Invalid input parameters: id must be string.", "E100",
					ex.getMessage());

			LOGGER.error("Cannot search for administrator. Invalid input parameters: id must be string.", ex);
		} catch (SQLException ex) {
			m = new Message("Cannot search for administrator: unexpected error while accessing the database.", "E200",
					ex.getMessage());

			LOGGER.error("Cannot search for administrator: unexpected error while accessing the database.", ex);
		}

		try {
			// set the MIME media type of the response
			res.setContentType("text/html; charset=utf-8");

			// get a stream to write the response
			PrintWriter out = res.getWriter();

			// write the HTML page
			out.printf("<!DOCTYPE html>%n");

			out.printf("<html lang=\"en\">%n");
			out.printf("<head>%n");
			out.printf("<meta charset=\"utf-8\">%n");
			out.printf("<title>Search Administrator</title>%n");
			out.printf("</head>%n");

			out.printf("<body>%n");
			out.printf("<h1>Search Administrator</h1>%n");
			out.printf("<hr/>%n");

			if (m.isError()) {
				out.printf("<ul>%n");
				out.printf("<li>error code: %s</li>%n", m.getErrorCode());
				out.printf("<li>message: %s</li>%n", m.getMessage());
				out.printf("<li>details: %s</li>%n", m.getErrorDetails());
				out.printf("</ul>%n");
			} else {
				out.printf("<p>%s</p>%n", m.getMessage());

				out.printf("<table>%n");
				out.printf("<tr>%n");
				out.printf("<td>Email</td><td>Password</td>%n");
				out.printf("</tr>%n");

				for (Administrator e : el) {
					out.printf("<tr>%n");
					out.printf("<td>%s</td><td>%s</td><td>%s</td>%n", e.getEmail(), e.getPassword());
					out.printf("</tr>%n");
				}
				out.printf("</table>%n");
			}

			out.printf("</body>%n");

			out.printf("</html>%n");


			// flush the output stream buffer
			out.flush();

			// close the output stream
			out.close();
		} catch (IOException ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating administrator %d.", id), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeUser();
		}
	}

}
