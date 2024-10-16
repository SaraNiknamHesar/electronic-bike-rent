package wascoot.servlet;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import wascoot.utils.ErrorCode;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Gets the {@code DataSource} for managing the connection pool to the database.
 *
 * @version 1.00
 * @since 1.00
 */
public abstract class AbstractDatabaseServlet extends HttpServlet {

    protected static final Logger LOGGER = LogManager.getLogger(AbstractDatabaseServlet.class,
            StringFormatterMessageFactory.INSTANCE);

    /**
     * The connection pool to the database.
     */
    private DataSource ds;

    /**
     * Gets the {@code DataSource} for managing the connection pool to the database.
     *
     * @param config
     *          a {@code ServletConfig} object containing the servlet's
     *          configuration and initialization parameters.
     *
     * @throws ServletException
     *          if an exception has occurred that interferes with the servlet's normal operation
     */
    public void init(ServletConfig config) throws ServletException {

        // the JNDI lookup context
        InitialContext cxt;

        try {
            cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/wascoot");
        } catch (NamingException e) {
            ds = null;

            throw new ServletException(
                    String.format("Impossible to access the connection pool to the database: %s",
                            e.getMessage()));
        }
    }

    /**
     * Releases the {@code DataSource} for managing the connection pool to the database.
     */
    public void destroy() {
        ds = null;
    }

    /**
     * Returns the {@code DataSource} for managing the connection pool to the database.
     *
     * @return the {@code DataSource} for managing the connection pool to the database
     */
    protected final DataSource getDataSource() {
        return ds;
    }


    /**
     * Returns a {@link  Connection} for accessing the database.
     *
     * @return a {@link Connection} for accessing the database
     *
     * @throws SQLException if anything goes wrong in obtaining the connection.
     */
    protected final Connection getConnection() throws SQLException {
        try {
            return ds.getConnection();
        } catch (final SQLException e) {
            LOGGER.error("Unable to acquire the connection from the pool.", e);
            throw e;
        }
    }

    public void writeError(HttpServletResponse res, ErrorCode ec) throws IOException {
        res.setStatus(ec.getHTTPCode());
        res.getWriter().write(ec.toJSON().toString());
    }
}
