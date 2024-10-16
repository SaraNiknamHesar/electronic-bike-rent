package wascoot.database;

import wascoot.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that provide the functionality of searching by id, among administrators
 */
public final class SearchAdministratorByIdDAO extends AbstractDAO<List<Administrator>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String SEARCH_ADMINISTRATOR_BY_ID = "SELECT id, email, password , name FROM public.admin WHERE id= ?";

	/**
	 * The ID of the administrator
	 */
	private final int id;

	/**
	 * Creates a new object for searching administrator by id.
	 *
	 * @param con    the connection to the database.
	 * @param id the id of the administrator.
	 */
	public SearchAdministratorByIdDAO(final Connection con, final int id) {
		super(con);
		this.id = id;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Administrator> administrators = new ArrayList<Administrator>();

		try {
			pstmt = con.prepareStatement(SEARCH_ADMINISTRATOR_BY_ID);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				administrators.add(new Administrator(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("name")));
			}

			LOGGER.info("Administrators successfully listed.");
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
