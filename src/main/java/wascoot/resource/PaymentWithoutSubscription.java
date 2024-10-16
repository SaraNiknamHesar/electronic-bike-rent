package wascoot.resource;

public class PaymentWithoutSubscription {
    /**
     * The identifier of the payment without subscription (PK).
     */
    private final int id;

    /**
     * The price of the payment without subscription.
     */
    private final int price;

    /**
     * The date with hour of the payment without subscription.
     */
    private final String dateHour;

    /**
     * The order id of the payment without subscription.
     */
    private final int orderId;

    /**
     * Creates a payment without subscription.
     *
     * @param id
     *            the id of the payment without subscription.
     * @param price
     *            the price of the payment without subscription.
     * @param dateHour
     *            the date and hour of the payment without subscription.
     * @param orderId
     *            the order id of the payment without subscription.
     */
    public PaymentWithoutSubscription(final int id, final int price, final String dateHour, final int orderId ) {
        this.id = id;
        this.price = price;
        this.dateHour = dateHour;
        this.orderId = orderId;
    }

    /**
     * Returns the id of the payment without subscription.
     *
     * @return the id of the payment without subscription.
     */
    public final int getId() {
        return id;
    }

    /**
     * Returns the price of the payment without subscription.
     *
     * @return the price of the payment without subscription.
     */
    public final int getPrice() {
        return price;
    }

    /**
     * Returns the date and hour of the payment without subscription.
     *
     * @return the date and hour of the payment without subscription.
     */
    public final String getDateHour() {
        return dateHour;
    }

    /**
     * Returns the order id of the payment without subscription.
     *
     * @return the order id of the payment without subscription.
     */
    public final int getOrderId() {
        return orderId;
    }

}
