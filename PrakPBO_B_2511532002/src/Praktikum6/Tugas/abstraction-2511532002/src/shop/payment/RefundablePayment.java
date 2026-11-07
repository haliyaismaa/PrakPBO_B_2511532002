package shop.payment;

public interface RefundablePayment extends PaymentMethod {
    boolean refund(long amount);

    default String policy(){
        return "Refungd maksmimal 7 hari";
    }
    
}

