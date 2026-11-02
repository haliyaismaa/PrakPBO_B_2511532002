package shop.payment;

/** Contract for every payment method. */
public interface PaymentMethod {
    String getName();

    /**return true if payment succeeds */
    boolean pay(long amount);


}
