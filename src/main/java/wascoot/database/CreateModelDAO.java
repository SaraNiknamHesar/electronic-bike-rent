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

import wascoot.resource.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Inserts a new model in the database
 */
public final class CreateModelDAO extends AbstractDAO {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "INSERT INTO public.Model (name, brand, battery_life, price_per_Min) VALUES (?, ?, ?, ?)";

	/**
	/**
	 * The model to be stored into the database
	 */
	private final Model model;

	/**
	 * Creates a new object for storing a model into the database.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param model
	 *            the model to be stored into the database.
	 */
	public CreateModelDAO(final Connection con, final Model model) {
		super(con);

		if (model == null) {
			LOGGER.error("The model cannot be null.");
			throw new NullPointerException("The model cannot be null.");
		}

		this.model = model;
	}

	@Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, model.getName());
			pstmt.setString(2, model.getBrand());
			pstmt.setTime(3, model.getBatteryLife());
			pstmt.setDouble(4, model.getPricePerMin());

			pstmt.execute();

			LOGGER.info("Model %s successfully stored in the database.", model.getName());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

	}
}
