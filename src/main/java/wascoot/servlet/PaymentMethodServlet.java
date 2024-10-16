package wascoot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import wascoot.database.ListPaymentMethodDAO;
import wascoot.database.UpdatePaymentMethodDAO;
import wascoot.resource.Actions;
import wascoot.resource.LogContext;
import wascoot.resource.Message;
import wascoot.resource.PaymentMethod;
import wascoot.utils.ErrorCode;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet that provides list, edit and add functionalities on payment methods
 */
public class PaymentMethodServlet extends AbstractDatabaseServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("paymentmethod") + 14);

        switch (op) {

            case "list/":
                List<PaymentMethod> el = null;
                Message m = null;

                try {

                    el = new ListPaymentMethodDAO(getConnection()).access().getOutputParam();

                    m = new Message("Payment Methods successfully searched.");

                } catch (NumberFormatException ex) {
                    m = new Message("Cannot search for Payment Methods.",
                            "E100", ex.getMessage());
                } catch (SQLException ex) {
                    m = new Message("Cannot search for payment method: unexpected error while accessing the database.",
                            "E200", ex.getMessage());
                }


                req.setAttribute("paymentList", el);
                req.setAttribute("message", m);

                req.getRequestDispatcher("/jsp/manage-pages/payment-list.jsp").forward(req, res);
                break;
            default:
                writeError(res, ErrorCode.OPERATION_UNKNOWN);
                LOGGER.warn("requested op "+op);

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getRequestURI();
        op = op.substring(op.lastIndexOf("paymentmethod") + 14);

        switch (op) {

            case "update/":
                LogContext.setIPAddress(req.getRemoteAddr());
                LogContext.setAction(Actions.UPDATE_PAYMENT_METHOD);

                String type = null;
                String activation = null;



                PaymentMethod e = null;
                Message m = null;

                try {
                    // retrieves the request parameters
                    type = req.getParameter("type");
                    activation = req.getParameter("activation");


                    LogContext.setResource(req.getParameter("type"));

                    e = new PaymentMethod( type, activation);


                    new UpdatePaymentMethodDAO(getConnection(), e).access();

                    m = new Message(String.format("Payment method %s successfully updated.", type));

                    LOGGER.info("Payment method %s successfully updated in the database.", type);

                } catch (SQLException ex) {

                        m = new Message("Cannot update the payment method: unexpected error while accessing the database.", "E200",
                                ex.getMessage());

                        LOGGER.error("Cannot create the payment method: unexpected error while accessing the database.", ex);

                }

                try {

                    req.setAttribute("updatePaymentMethod", e);
                    req.setAttribute("message", m);

                    req.getRequestDispatcher("/jsp/manage-pages/update-paymentmethod.jsp").forward(req, res);
                } catch(Exception ex) {
                    LOGGER.error(new StringFormattedMessage("Unable to send response when updating payment method %s.", type), ex);
                    throw ex;
                } finally {
                    LogContext.removeIPAddress();
                    LogContext.removeAction();
                    LogContext.removeResource();
                }

        }
    }
}
