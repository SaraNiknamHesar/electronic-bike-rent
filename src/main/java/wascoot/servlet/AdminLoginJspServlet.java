package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import wascoot.database.AdminLoginDAO;
import wascoot.resource.Administrator;
import wascoot.resource.Message;
import wascoot.utils.ErrorCode;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Admin Login Servlet
 */
@WebServlet(name = "AdminLoginJspServlet", value = "/admin/*")
public class AdminLoginJspServlet extends AbstractDatabaseServlet {
    /**
     * provide login feature
     * @param request   HTTP request from client
     * @param response  HTTP response from server
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getRequestURI();
        //remove everything prior to /admin/ (included) and use the remainder as
        //indicator for the required operation
        op = op.substring(op.lastIndexOf("admin") + 5);

        switch (op) {
            case "login/":
                request.getSession().invalidate();
                // the requested operation is login
                request.getRequestDispatcher("/jsp/login-page.jsp").forward(request, response);
                break;
            case "logout/":
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/jsp/homepage.jsp");
                break;
            default:
                // the requested operation is unknown
                writeError(response, ErrorCode.OPERATION_UNKNOWN);
        }
    }

    /**
     * doPost method
     * @param request   HTTP request from client
     * @param response  HTTP response from server
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getRequestURI();
        //remove everything prior to /admin/ (included) and use the remainder as
        //indicator for the required operation
        op = op.substring(op.lastIndexOf("admin") + 6);


        switch (op) {
            case "login/":
                loginOperations(request, response);
                break;

            default:
                // the requested operation is unknown
                writeError(response, ErrorCode.OPERATION_UNKNOWN);
        }
    }


    public void loginOperations(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Administrator u;
        Message m = null;
        int id =-1;

        try {
            //take from the request, the parameters (email and password)
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String photoMediaType = "image/png";

            if (email == null || email.equals("")) {
                //the email is null (was not set on the parameters) or an empty string
                //notify this to the user
                ErrorCode ec = ErrorCode.EMAIL_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message( "missing email");
                LOGGER.error("{}", m.getMessage());

                //we used jsp for the login page: thus we forward the request
                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/login-page.jsp").forward(req, res);

            } else if (password == null || password.equals("")) {
                //the password was empty
                ErrorCode ec = ErrorCode.PASSWORD_MISSING;
                res.setStatus(ec.getHTTPCode());
                m = new Message( "missing password");
                LOGGER.error("{}", m.getMessage());

                req.setAttribute("message", m);
                req.getRequestDispatcher("/jsp/login-page.jsp").forward(req, res);

            } else {
                //try to authenticate the user
                email = email.toLowerCase();
                u = new Administrator(id, email, password, null, null, "image/png");
                // try to find the user in the database
                Administrator administrator = new AdminLoginDAO(getConnection(), u).access().getOutputParam();

                //the UserDAO will tell us if the email exists and the password
                //matches
                if (administrator == null) {
                    //if not, tell it to the user
                    ErrorCode ec = ErrorCode.WRONG_CREDENTIALS;
                    res.setStatus(ec.getHTTPCode());
                    m = new Message("Credentials are wrong");
                    req.setAttribute("message", m);
                    req.getRequestDispatcher("/jsp/login-page.jsp").forward(req, res);

                    m = new Message("The administrator does not exist","E200","Missing administrator");
                    LOGGER.error("problems with administrator: {}", m.getMessage());
                } else {
                    // activate a session to keep the user data
                    LOGGER.info("Administrator %s logged in", email);
                    HttpSession session = req.getSession();
                    session.setAttribute("id", administrator.getId());
                    session.setAttribute("email", administrator.getEmail());
                    session.setAttribute("name", administrator.getName());
                    res.sendRedirect(req.getContextPath() + "/dashboard");


                    m = new Message("Login success");
                    LOGGER.info("the administrator {} LOGGED IN",administrator.getEmail());
                }
            }
        } catch (SQLException e) {
            //something unexpected happened: we write it into the logger
            writeError(res, ErrorCode.INTERNAL_ERROR);
            LOGGER.error("stacktrace:", e);
        }

    }


}