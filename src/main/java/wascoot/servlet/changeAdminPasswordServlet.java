
package wascoot.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.UpdateAdministratorDAO;
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
public final class changeAdminPasswordServlet extends AbstractDatabaseServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.UPDATE_ADMINISTRATOR);

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
			email = req.getParameter("email");
			password = req.getParameter("password");
			e = new Administrator(id, email, password, name, null, photoMediaType);

			// creates a new object for accessing the database and stores the administrator
			new UpdateAdministratorDAO(getConnection(), e).access().getOutputParam();

			m = new Message(String.format("Password %s successfully changed.", password));

			LOGGER.info("Password %s successfully changed in the database.", password);
			res.sendRedirect(req.getContextPath() + "/dashboard");

		} catch (NumberFormatException ex) {
			m = new Message(
					"Cannot change the password. Invalid input parameters.",
					"E100", ex.getMessage());

			LOGGER.error(
					"Cannot change the password. Invalid input parameters.",
					ex);
		} catch (SQLException ex) {
			if (ex.getSQLState().equals("23505")) {
				m = new Message(String.format("Cannot change the password "), "E300",
						ex.getMessage());

				LOGGER.error(
						new StringFormattedMessage("Cannot change the password"),
						ex);
			} else {
				m = new Message("Cannot change the password: unexpected error while accessing the database.", "E200",
						ex.getMessage());

				LOGGER.error("Cannot change the password: unexpected error while accessing the database.", ex);
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
			out.printf("<title>Change Administrator Password</title>%n");
			out.printf("</head>%n");

			out.printf("<body>%n");
			out.printf("<h1>Change Administrator Password</h1>%n");
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
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating administrator %s.", password), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}

	}

}
