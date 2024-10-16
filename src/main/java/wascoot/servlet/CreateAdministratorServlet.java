
package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.CreateAdministratorDAO;
import wascoot.resource.Actions;
import wascoot.resource.Administrator;
import wascoot.resource.LogContext;
import wascoot.resource.Message;

import java.awt.datatransfer.MimeTypeParseException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

/**
 * Servlet to create an administrator
 */
public final class CreateAdministratorServlet extends AbstractDatabaseServlet {

	/**
	 * Creates a new Administrator into the database.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 *
	 * @throws IOException if any error occurs in the client/server communication.
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.CREATE_ADMINISTRATOR);

		// model
		Administrator e = null;
		Message m = null;

		try {
			e = parseRequest(req);
			LogContext.setResource(Integer.toString(e.getId()));

			// creates a new object for accessing the database and stores the administrator
			new CreateAdministratorDAO(getConnection(), e).access();

			m = new Message(String.format("Administrator %d successfully created.", e.getId()));

			LOGGER.info("Administrator %d successfully created in the database.", e.getId());

		} catch (NumberFormatException ex) {
			m = new Message(
					"Cannot create the administrator. Invalid input parameters: badge, age, and salary must be integer.",
					"E100", ex.getMessage());

			LOGGER.error(
					"Cannot create the administrator. Invalid input parameters: badge, age, and salary must be integer.",
					ex);
		} catch (SQLException ex) {
			if ("23505".equals(ex.getSQLState())) {
				m = new Message(String.format("Cannot create the administrator: administrator %d already exists.", e.getId()), "E300",
						ex.getMessage());

				LOGGER.error(
						new StringFormattedMessage("Cannot create the administrator: Administrator %d already exists.", e.getId()),
						ex);
			} else {
				m = new Message("Cannot create the administrator: unexpected error while accessing the database.", "E200",
						ex.getMessage());

				LOGGER.error("Cannot create the administrator: unexpected error while accessing the database.", ex);
			}
		}catch (MimeTypeParseException ex) {
			m = new Message(
					String.format("Unsupported MIME media type for employee photo. Expected: image/png or image/jpeg."),
					"E400", ex.getMessage());
		}

		try {
			// stores the administrator and the message as a request attribute
			req.setAttribute("administrator", e);
			req.setAttribute("message", m);

			// forwards the control to the create-employee-result JSP
			req.getRequestDispatcher("/jsp/create-administrator-result.jsp").forward(req, res);

		} catch (IOException ex) {
			LOGGER.error(new StringFormattedMessage("Unable to send response when creating administrator %d.", e.getId()), ex);
			throw ex;
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeResource();
		}

	}

	private Administrator parseRequest(HttpServletRequest req) throws ServletException, IOException, MimeTypeParseException {

		// request parameters
		int id = -1;
		String email = null;
		String password = null;
		String name =null;
		byte[] photo = null;
		String photoMediaType = null;

		// retrieves the request parameters
		for (Part p : req.getParts()) {

			switch (p.getName()) {
				case "id":

					try (InputStream is = p.getInputStream()) {
						id = Integer.parseInt(new String(is.readAllBytes(), StandardCharsets.UTF_8).trim());
					}
					break;

				case "email":
					try (InputStream is = p.getInputStream()) {
						email = new String(is.readAllBytes(), StandardCharsets.UTF_8).trim();
					}
					break;

				case "password":
					try (InputStream is = p.getInputStream()) {
						password = new String(is.readAllBytes(), StandardCharsets.UTF_8).trim();
					}
					break;

				case "name":
					try (InputStream is = p.getInputStream()) {
						name = new String(is.readAllBytes(), StandardCharsets.UTF_8).trim();
					}
					break;

				case "photo":
					photoMediaType = p.getContentType();

					switch (photoMediaType.toLowerCase().trim()) {

						case "image/png":
						case "image/jpeg":
						case "image/jpg":
							// nothing to do
							break;

						default:
							LOGGER.error("Unsupported MIME media type %s for employee photo.", photoMediaType);

							throw new MimeTypeParseException(
									String.format("Unsupported MIME media type %s for employee photo.",
											photoMediaType));
					}

					try (InputStream is = p.getInputStream()) {
						photo = is.readAllBytes();
					}

					break;
			}

		}

		// creates a new employee from the request parameters
		return new Administrator(id, email, password, name, photo, photoMediaType);
	}

}
