package wascoot.database;

import wascoot.resource.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Checks if the administrator credentials match to an admin in database.
 */
public class AdminLoginDAO extends AbstractDAO<Administrator>  {

    /**
     * The SQL statament to be executed
     */
    private static final String STATEMENT_ADMIN_INFO = "SELECT id, email, password , name FROM public.admin WHERE email=? AND password=?";

    /**
     * The administrator that has to be logged in
     */
    private final Administrator administrator;

    /**
     * Creates a new object to check the login of an administrator
     * @param con   connection to database
     * @param administrator
     */
    public AdminLoginDAO(final Connection con, final Administrator administrator) {
        super(con);
        this.administrator = administrator;
    }

    @Override
    protected void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        // the results of the search
        Administrator admin_new = null;
        try {
            stmnt = con.prepareStatement(STATEMENT_ADMIN_INFO);
            stmnt.setString(1, administrator.getEmail());
            stmnt.setString(2, administrator.getPassword());
            rs = stmnt.executeQuery();


            if(rs.next()){

                admin_new = new Administrator(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("name"), null, "image/png");
                LOGGER.info("administrator logged in %d.", admin_new.getId());

            }else{
                LOGGER.error("error logging in the administrator %s",administrator.getEmail());

            }

//            return null;



        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

        }
        this.outputParam = admin_new;
    }




}
