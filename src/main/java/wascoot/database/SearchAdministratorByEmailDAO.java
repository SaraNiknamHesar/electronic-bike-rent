

package wascoot.database;

import wascoot.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that provide the functionality of searching by email, among administrators
 */
public final class SearchAdministratorByEmailDAO extends AbstractDAO<List<Administrator>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String SEARCH_ADMINISTRATOR_BY_EMAIL = "SELECT id, email, password, name FROM public.admin WHERE email= ?";

	/**
	 * The email of the administrator
	 */
	private final String email;


	/**
	 * Creates the object to search administrators by email
	 */
	public SearchAdministratorByEmailDAO(final Connection con, final String email) {
		super(con);
		this.email = email;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Administrator> administrators = new ArrayList<Administrator>();

		try {
			pstmt = con.prepareStatement(SEARCH_ADMINISTRATOR_BY_EMAIL);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				administrators.add(new Administrator(rs.getInt("id"), rs.getString("email"),
						rs.getString("password"), rs.getString("name")));
			}

			LOGGER.info("Administrator successfully returned.");
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = administrators;
	}
}
