/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wascoot.database;

import wascoot.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads all the administrators in the database.
 */
public final class ListAdministratorDAO extends AbstractDAO<List<Administrator>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String LIST_ADMINISTRATOR = "SELECT id, email, password ,name FROM pubic.admin";

	/**
	 * Creates a new object for listing all the administrator.
	 *
	 * @param con the connection to the database.
	 */
	public ListAdministratorDAO(final Connection con) {
		super(con);
	}

	@Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Administrator> administrators = new ArrayList<Administrator>();

		try {
			pstmt = con.prepareStatement(LIST_ADMINISTRATOR);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				administrators.add(new Administrator(rs.getInt("id"), rs.getString("email"),
						rs.getString("password"), rs.getString("name")));
			}

			LOGGER.info("Administrator(s) successfully listed.");
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		outputParam = administrators;
	}
}
