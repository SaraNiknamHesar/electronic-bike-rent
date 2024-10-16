/*
 * Copyright 2023 University of Padua, Italy
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

import java.sql.SQLException;
/**
 * Represents a generic Data Access Object (DAO). The {@link #access()} method performs the actual access to the
 * database, while the {@link #getOutputParam()} allows for retrieving any output parameters resulting from the access,
 * if any.
 *
 * @param <T> the type of the output parameter returned by the DAO.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.0
 * @since 1.0
 */
public interface DataAccessObject<T> {

    /**
     * Accesses the database.
     *
     * @return a reference to this {@code DataAccessObject} object.
     *
     * @throws SQLException if something goes wrong while accessing the database.
     */
    DataAccessObject<T> access() throws SQLException;

    /**
     * Retrieves any output parameters, after the access to the database.
     *
     * @return output parameter, or @code{null} if there is no output parameter.
     */
    T getOutputParam();
}
