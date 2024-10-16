package wascoot.servlet;

import wascoot.rest.*;
import wascoot.resource.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Dispatches the request to the proper REST resource.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class RestDispatcherServlet extends AbstractDatabaseServlet {

    /**
     * The JSON UTF-8 MIME media type
     */
    private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse res) throws IOException {

        LogContext.setIPAddress(req.getRemoteAddr());

        final OutputStream out = res.getOutputStream();

        try {

            // if the requested resource was a Model, delegate its processing and return
            if (processModel(req, res)) {
                return;
            }

            if (processScooterRack(req, res)) {
                return;
            }

            if (processCustomer(req, res)) {
                return;
            }

            if (processScooter(req, res)) {
                return;
            }

            if (processAdministrator(req, res)) {
                return;
            }

            if (processCustomerAvgAge(req, res)){
                return;
            }

            if (processCustomerGender(req,res)){
                return;
            }

            if (processRevenue(req,res)){
                return;
            }

            if (procesScooterRackPos(req, res)){
                return;
            }


            // if none of the above process methods succeeds, it means an unknown resource has been requested
            LOGGER.warn("Unknown resource requested: %s.", req.getRequestURI());

            final Message m = new Message("Unknown resource requested.", "E4A6",
                    String.format("Requested resource is %s.", req.getRequestURI()));
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.setContentType(JSON_UTF_8_MEDIA_TYPE);
            m.toJSON(out);
        } catch (Throwable t) {
            LOGGER.error("Unexpected error while processing the REST resource.", t);

            final Message m = new Message("Unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(out);
        } finally {

            // ensure to always flush and close the output stream
            if (out != null) {
                out.flush();
                out.close();
            }

            LogContext.removeIPAddress();
        }
    }


    /**
     * Checks whether the request if for an {@link Model} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code Employee}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean processModel(final HttpServletRequest req, final HttpServletResponse res) throws Exception {

        final String method = req.getMethod();

        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a model
        if (path.lastIndexOf("rest/model/") <= 0) {
            return false;
        }


        path = path.substring(path.lastIndexOf("model/") + 6);
//
//        // I can have multiple paths. Split on "/"
//        String[] splitted_path = path.split("/");


        // the request URI is: /model
        // if method GET, list model
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new ListModelRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /model: %s.", method);

                    m = new Message("Unsupported operation for URI /model.", "E4A5",
                            String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        } else {
            // IN THIS CASE I HAVE AN URI SUCH AS /rest/model/{id_mode}
            // we can use get method to retrieve the information about a secific model

        }


        return true;

    }

    /**
     * Checks whether the request is for an {@link Scooter} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code Scooter}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean processScooter(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        final String method = req.getMethod();
        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a scooter
        if (path.lastIndexOf("rest/scooter/") <= 0) {
            return false;
        }

        path = path.substring(path.lastIndexOf("scooter/") + 8);
//
//        // I can have multiple paths. Split on "/"
//        String[] splitted_path = path.split("/");

        LOGGER.warn(path);
        // the request URI is: /model
        // if method GET, list model
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new ListScooterRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /scooter: %s.", method);

                    m = new Message("Unsupported operation for URI /scooter.", "E4A5",
                            String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        } else {
            // IN THIS CASE I HAVE AN URI SUCH AS /rest/scooter/{id_mode}
            // we can use get method to retrieve the information about a specific scooter

        }


        return true;
    }

    /**
     * Checks whether the request is for an {@link Scooterrack} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code Scooterrack}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean processScooterRack(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        final String method = req.getMethod();
        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a scooterrack
        if (path.lastIndexOf("rest/scooterrack/") <= 0) {
            return false;
        }

        path = path.substring(path.lastIndexOf("scooterrack/") + 12);
//
//        // I can have multiple paths. Split on "/"
//        String[] splitted_path = path.split("/");

        LOGGER.warn(path);
        // the request URI is: /model
        // if method GET, list model
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new ListScooterracksRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /scooterrack: %s.", method);

                    m = new Message("Unsupported operation for URI /scooterrack.", "E4A5",
                            String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        } else {
            // IN THIS CASE I HAVE AN URI SUCH AS /rest/scooterracks/{id_mode}
            // we can use get method to retrieve the information about a specific scooterrack

        }


        return true;
    }

    /**
     * Checks whether the request is for an {@link Customer} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code Customer}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean processCustomer(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        final String method = req.getMethod();
        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a customer
        if (path.lastIndexOf("rest/customer/") <= 0) {
            return false;
        }

        path = path.substring(path.lastIndexOf("customer/") + 9);
//
//        // I can have multiple paths. Split on "/"
//        String[] splitted_path = path.split("/");

        LOGGER.warn(path);
        // the request URI is: /customer
        // if method GET, list customer(s)
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new CustomerRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /customer: %s.", method);

                    m = new Message("Unsupported operation for URI /model.", "E4A5", String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        } else {

        }
        return true;
    }

    /**
     * Checks whether the request is for an {@link Administrator} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code Administrator}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean processAdministrator(final HttpServletRequest req, final HttpServletResponse res) throws Exception {

        final String method = req.getMethod();

        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not an administrator
        if (path.lastIndexOf("rest/administrator") <= 0) {
            return false;
        }

        // strip everything until after the /administrator
        path = path.substring(path.lastIndexOf("administrator/") + 14);

        // the request URI is: /administrator
        // if method GET, list administrator
        // if method POST, create administrator
        if (path.length() == 0 || path.equals("/")) {

            switch (method) {
                case "GET":
                    new ListAdministratorRR(req, res, getConnection()).serve();
                    break;
                case "POST":
                    new CreateAdministratorRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /administrator: %s.", method);

                    m = new Message("Unsupported operation for URI /administrator.", "E4A5",
                            String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        } else{
            // the request URI is: /administrator/email/{email}
            if (path.contains("email")) {
                path = path.substring(path.lastIndexOf("email") + 6);

                if (path.length() == 0 || path.equals("/")) {
                    LOGGER.warn("Wrong format for URI /administrator/email/{email}: no {email} specified. Requested URI: %s.", req.getRequestURI());

                    m = new Message("Wrong format for URI /administrator/email/{email}: no {email} specified.", "E4A7",
                            String.format("Requested URI: %s.", req.getRequestURI()));
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    m.toJSON(res.getOutputStream());
                } else {
                    switch (method) {
                        case "GET":
                            new SearchAdministratorByEmailRR(req, res, getConnection()).serve();

                            break;
                        default:
                            LOGGER.warn("Unsupported operation for URI /administrator/email/{email}: %s.", method);

                            m = new Message("Unsupported operation for URI /administrator/email/{email}.", "E4A5",
                                    String.format("Requested operation %s.", method));
                            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                            m.toJSON(res.getOutputStream());
                            break;
                    }
                }
            } else if (path.contains("id")) {
                ///administrator/id/{id}

                path = path.substring(path.lastIndexOf("id") + 1);

                if (path.length() == 0 || path.equals("/")) {
                    LOGGER.warn("Wrong format for URI /administrator/id/{id}: no {id} specified. Requested URI: %s.", req.getRequestURI());

                    m = new Message("Wrong format for URI /administrator/id/{id}: no {id} specified.", "E4A7",
                            String.format("Requested URI: %s.", req.getRequestURI()));
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    m.toJSON(res.getOutputStream());
                } else {
                    switch (method) {
                        case "GET":
                            new SearchAdministratorByIdRR(req, res, getConnection()).serve();

                            break;
                        default:
                            LOGGER.warn("Unsupported operation for URI /administrator/id/{id}: %s.", method);

                            m = new Message("Unsupported operation for URI /administrator/id/{id}.", "E4A5",
                                    String.format("Requested operation %s.", method));
                            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                            m.toJSON(res.getOutputStream());
                            break;
                    }
                }
            }
            else {
                // the request URI is: /administrator/{id}

                switch (method) {
                    case "GET":
                        new ReadAdministratorRR(req, res, getConnection()).serve();
                        break;
                    case "PUT":
                        new UpdateAdministratorRR(req, res, getConnection()).serve();
                        break;
                    case "DELETE":
                        new DeleteAdministratorRR(req, res, getConnection()).serve();
                        break;
                    default:
                        LOGGER.warn("Unsupported operation for URI /administrator/{id}: %s.", method);

                        m = new Message("Unsupported operation for URI /administrator/{id}.", "E4A5",
                                String.format("Requested operation %s.", method));
                        res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        m.toJSON(res.getOutputStream());
                }
            }
        }

        return true;
    }

    /**
     * Checks whether the request is for an {@link CustomerAvgAge} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code CustomerAvgAge}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean processCustomerAvgAge(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        final String method = req.getMethod();
        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a customer
        if (path.lastIndexOf("rest/customerAvgAge/") <= 0) {
            return false;
        }

        path = path.substring(path.lastIndexOf("customerAvgAge/") + 15);
//
//        // I can have multiple paths. Split on "/"
//        String[] splitted_path = path.split("/");

        LOGGER.warn(path);
        // the request URI is: /customer
        // if method GET, list customer(s)
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new CustomerAvgAgeRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /customer: %s.", method);

                    m = new Message("Unsupported operation for URI /customerAvgAge.", "E4A5", String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        } else {

        }
        return true;
    }

    /**
     * Checks whether the request is for an {@link CustomerGender} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code CustomerGender}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean processCustomerGender(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        final String method = req.getMethod();
        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a customer
        if (path.lastIndexOf("rest/customerGender/") <= 0) {
            return false;
        }

        path = path.substring(path.lastIndexOf("customerGender/") + 15);
//
//        // I can have multiple paths. Split on "/"
//        String[] splitted_path = path.split("/");

        LOGGER.warn(path);
        // the request URI is: /customer
        // if method GET, list customer(s)
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new CustomerGenderDisRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /customerGender: %s.", method);

                    m = new Message("Unsupported operation for URI /customerGender.", "E4A5", String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        } else {

        }
        return true;
    }


    private boolean processRevenue(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        final String method = req.getMethod();
        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a customer
        if (path.lastIndexOf("rest/revenue/") <= 0) {
            return false;
        }

        path = path.substring(path.lastIndexOf("revenue/") + 8);


        LOGGER.warn(path);

        if (path.length() == 0) {

            if (method.equals("GET")) {
                new RevenueRR(req, res, getConnection()).serve();
            } else {
                LOGGER.warn("Unsupported operation for URI /revenue: %s.", method);

                m = new Message("Unsupported operation for URI /revenue.", "E4A5", String.format("Requested operation %s.", method));
                res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                m.toJSON(res.getOutputStream());
            }
        }

        return true;
    }

    /**
     * Checks whether the request is for an {@link procesScooterRackPos} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code procesScooterRackPos}; {@code false} otherwise.
     * @throws Exception if any error occurs.
     */
    private boolean procesScooterRackPos(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        final String method = req.getMethod();
        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not a customer
        if (path.lastIndexOf("rest/scooterRackPos/") <= 0) {
            return false;
        }

        path = path.substring(path.lastIndexOf("scooterRackPos/") + 15);

        LOGGER.warn(path);
        // the request URI is: /customer
        // if method GET, list customer(s)
        if (path.length() == 0) {

            switch (method) {
                case "GET":
                    new ScooterRackPosRR(req, res, getConnection()).serve();
                    break;
                default:
                    LOGGER.warn("Unsupported operation for URI /scooterRackPos: %s.", method);

                    m = new Message("Unsupported operation for URI /scooterRackPos.", "E4A5", String.format("Requested operation %s.", method));
                    res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    m.toJSON(res.getOutputStream());
                    break;
            }
        } else {

        }
        return true;
    }

}