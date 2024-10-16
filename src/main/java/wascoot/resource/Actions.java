package wascoot.resource;

public class Actions {
    public static final String LIST_MODELS = "LIST_MODELS";
    public static final String LIST_SCOOTERRACKS = "LIST_SCOOTERRACKS";
    public static final String LIST_SCOOTERS = "LIST_SCOOTERS";
    public static final String UPDATE_SCOOTERRACK = "UPDATE_SCOOTERRACK";
    public static final String INSERT_NEW_SCOOTERRACK = "INSERT_NEW_SCOOTERRACK";
    public static final String INSERT_NEW_SCOOTER = "INSERT_NEW_SCOOTER";
    public static final String LIST_RENTALS = "LIST_RENTALS";
    public static final String UPDATE_PAYMENT_METHOD = "UPDATE_PAYMENT_METHOD";
    public static final String CREATE_SUBSCRIPTION = "CREATE_SUBSCRIPTION";
    public static final String UPDATE_SUBSCRIPTION = "UPDATE_SUBSCRIPTION";
    public static final String CREATE_MODEL = "CREATE_MODEL";
    public static final String UPDATE_MODEL = "UPDATE_MODEL";
    public static final String UPDATE_SCOOTER = "UPDATE_SCOOTER";
    public static final String GET_ALL_CUSTOMERS= "GET_ALL_CUSTOMERS";
    public static final String CREATE_ADMINISTRATOR = "CREATE_ADMINISTRATOR";
    public static final String LOAD_ADMINISTRATOR_PHOTO = "LOAD_ADMINISTRATOR_PHOTO";
    public static final String SEARCH_ADMINISTRATOR_BY_ID = "SEARCH_ADMINISTRATOR_BY_ID";
    public static final String SEARCH_ADMINISTRATOR_BY_EMAIL = "SEARCH_ADMINISTRATOR_BY_EMAIL";
    public static final String READ_ADMINISTRATOR = "READ_ADMINISTRATOR";
    public static final String UPDATE_ADMINISTRATOR = "UPDATE_ADMINISTRATOR";
    public static final String DELETE_ADMINISTRATOR = "DELETE_ADMINISTRATOR";
    public static final String LIST_ADMINISTRATOR = "LIST_ADMINISTRATOR";
    public static final String GET_AVG_AGE_CUSTOMERS = "GET_AVG_AGE_CUSTOMERS";
    public static final String GET_GENDER_DIS_CUSTOMERS = "GET_GENDER_DIS_CUSTOMERS";
    public static final String GET_SCOOTERRACK_POS = "GET_SCOOTERRACK_POS";

    public static final String GET_REVENUE = "GET_REVENUE";


    private Actions() {
        throw new AssertionError(String.format("No instances of %s allowed.", Actions.class.getName()));
    }
}
