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
import wascoot.resource.Scooterrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Inserts a new scooterrack in the database.
 */
public final class CreateScooterrackDAO extends AbstractDAO<Scooterrack> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO public.scooterracks (total_parking_spots, available_parking_spots, postalcode, address) VALUES (?, ?, ?, ?)";

    /**
     /**
     * The scooterrack to be stored into the database
     */
    private final Scooterrack scooterrack;

    /**
     * Creates a new object for storing a scooterrack into the database.
     *
     * @param con
     *            the connection to the database.
     * @param scooterrack
     *            the scooterack to be stored into the database.
     */
    public CreateScooterrackDAO(final Connection con, final Scooterrack scooterrack) {
        super(con);

        if (scooterrack == null) {
            LOGGER.error("The scooterrack cannot be null.");
            throw new NullPointerException("The scooterrack cannot be null.");
        }

        this.scooterrack = scooterrack;
    }

    @Override
    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setInt(1, scooterrack.getTotalParkingSpots());
            pstmt.setInt(2, scooterrack.getAvailableParkingSpots());
            pstmt.setString(3, scooterrack.getPostalCode());
            pstmt.setString(4, scooterrack.getAddress());

            pstmt.execute();

            LOGGER.info("Scooterrack successfully stored in the database.");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }

    }
}
