

package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wascoot.database.LoadAdministratorPhotoDAO;
import wascoot.resource.Actions;
import wascoot.resource.Administrator;
import wascoot.resource.LogContext;

import java.io.IOException;

/**
 * Servlet to load the photo of the administrator
 */
public final class LoadAdministratorPhotoServlet extends AbstractDatabaseServlet {

	/**
	 * Get the administrator photo
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LogContext.setIPAddress(req.getRemoteAddr());
		LogContext.setAction(Actions.LOAD_ADMINISTRATOR_PHOTO);

		// request parameter
		int id = -1;

		// model
		Administrator e = null;

		try {

			// retrieves the request parameter
			id = Integer.parseInt(req.getParameter("id"));
			LogContext.setResource(req.getParameter("id"));

			// creates a new object for accessing the database and loading the photo of an Administrator
			e = new LoadAdministratorPhotoDAO(getConnection(), id).access().getOutputParam();

			if (e.hasPhoto()) {
				res.setContentType(e.getPhotoMediaType());
				res.getOutputStream().write(e.getPhoto());
				res.getOutputStream().flush();

				LOGGER.info("Photo for administrator %d successfully sent.", id);
			} else {
				LOGGER.info("Administrator %d has no profile photo and/or valide MIME media type specified.", id);

				res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		} catch (Exception ex) {
			LOGGER.error("Unable to load the photo of the administrator.", ex);

			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			LogContext.removeIPAddress();
			LogContext.removeAction();
			LogContext.removeUser();
		}

	}

}
