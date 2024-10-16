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
import wascoot.resource.Scooter;
import wascoot.resource.Scooterrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Inserts a new scooter in the database.
 */
public final class CreateScooterDAO extends AbstractDAO<Scooter> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO public.scooter (date_of_purchase, km_traveled, model, remaining_battery_life, id_scooter_rack) VALUES (?, ?, ?, ?, ?)";

    /**
     /**
     * The scooter to be stored into the database
     */
    private final Scooter scooter;

    /**
     * Creates a new object for storing a scooter into the database.
     *
     * @param con
     *            the connection to the database.
     * @param scooter
     *            the scooter to be stored into the database.
     */
    public CreateScooterDAO(final Connection con, final Scooter scooter) {
        super(con);

        if (scooter == null) {
            LOGGER.error("The scooter cannot be null.");
            throw new NullPointerException("The scooter cannot be null.");
        }

        this.scooter = scooter;
    }

    @Override
    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setDate(1, scooter.getDateOfPurchase());
            pstmt.setDouble(2, scooter.getKmTraveled());
            pstmt.setString(3, scooter.getModel());
            pstmt.setDouble(4, scooter.getRemainingBatteryLife());
            pstmt.setInt(5, scooter.getIdScooterrack());

            pstmt.execute();

            LOGGER.info("Scooterrack successfully stored in the database.");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
