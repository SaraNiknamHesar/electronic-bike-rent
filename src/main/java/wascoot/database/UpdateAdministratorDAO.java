package wascoot.database;
import wascoot.resource.Administrator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Updates a given administrator
 */
public final class UpdateAdministratorDAO extends AbstractDAO<Administrator> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String UPDATE_ADMINISTRATOR = "UPDATE public.admin SET password = ? WHERE email = ? RETURNING *";

	/**
	 * The administrator to be updated in the database
	 */
	private final Administrator administrator;

	/**
	 * Creates a new object to update an administrator.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param administrator
	 *            the administrator to be updated in the database.
	 */
	public UpdateAdministratorDAO(final Connection con, final Administrator administrator) {
		super(con);

		if (administrator == null) {
			LOGGER.error("The administrator cannot be null.");
			throw new NullPointerException("The administrator cannot be null.");
		}

		this.administrator = administrator;
	}

	@Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the updated administrator
		Administrator e = null;

		try {
			pstmt = con.prepareStatement(UPDATE_ADMINISTRATOR);
			pstmt.setString(1, administrator.getPassword());
			pstmt.setString(2, administrator.getEmail());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				e = new Administrator(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("name"));

				LOGGER.info("Administrator %d successfully updated in the database.", e.getId());
			}
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}
		}

		outputParam = e;
	}
}
