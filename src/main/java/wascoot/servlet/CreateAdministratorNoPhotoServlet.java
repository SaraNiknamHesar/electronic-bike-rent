
package wascoot.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.CreateAdministratorDAO;
import wascoot.resource.Actions;
import wascoot.resource.Administrator;
import wascoot.resource.LogContext;
import wascoot.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * servlet to create an administrator without a photo
 */
public final class CreateAdministratorNoPhotoServlet extends AbstractDatabaseServlet {

	/**
	 * Creates a new Administrator into the database.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.CREATE_ADMINISTRATOR);

		// request parameters
		int id = -1;
		String email = null;
		String password = null;
		String name = null;
		byte[] photo = null;
		String photoMediaType = "image/png";

		// model
		Administrator e = null;
		Message m = null;

		try {
			// retrieves the request parameters
			id = Integer.parseInt(req.getParameter("id"));
			email = req.getParameter("email");
			password = req.getParameter("password");
			name = req.getParameter("name");

			// creates a new administrator from the request parameters
			e = new Administrator(id, email, password, name, null, photoMediaType);

			// creates a new object for accessing the database and stores the administrator
			new CreateAdministratorDAO(getConnection(), e).access();

			m = new Message(String.format("Administrator %s successfully created.", id));

			LOGGER.info("Administrator %s successfully created in the database.", id);

		} catch (NumberFormatException ex) {
			m = new Message(
					"Cannot create the administrator. Invalid input parameters: badge, age, and salary must be integer.",
					"E100", ex.getMessage());

			LOGGER.error(
					"Cannot create the administrator. Invalid input parameters: badge, age, and salary must be integer.",
					ex);
		} catch (SQLException ex) {
			if (ex.getSQLState().equals("23505")) {
				m = new Message(String.format("Cannot create the administrator: administrator %s already exists.", id), "E300",
						ex.getMessage());

				LOGGER.error(
						new StringFormattedMessage("Cannot create the administrator: Administrator %s already exists.", id),
						ex);
			} else {
				m = new Message("Cannot create the administrator: unexpected error while accessing the database.", "E200",
						ex.getMessage());

				LOGGER.error("Cannot create the administrator: unexpected error while accessing the database.", ex);
			}
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
			out.printf("<title>Create Administrator</title>%n");
			out.printf("</head>%n");

			out.printf("<body>%n");
			out.printf("<h1>Create Administrator</h1>%n");
			out.printf("<hr/>%n");

			if (m.isError()) {
				out.printf("<ul>%n");
				out.printf("<li>error code: %s</li>%n", m.getErrorCode());
				out.printf("<li>message: %s</li>%n", m.getMessage());
				out.printf("<li>details: %s</li>%n", m.getErrorDetails());
				out.printf("</ul>%n");
			} else {
				out.printf("<p>%s</p>%n", m.getMessage());
				out.printf("<ul>%n");
				out.printf("<li>ID: %s</li>%n", e.getId());
				out.printf("<li>Email: %s</li>%n", e.getEmail());
				out.printf("<li>Password: %s</li>%n", e.getPassword());
				out.printf("</ul>%n");
			}

			out.printf("</body>%n");

			out.printf("</html>%n");

			// flush the output stream buffer
			out.flush();

			// close the output stream
			out.close();
		} catch (IOException ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating administrator %s.", id), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}

	}

}
