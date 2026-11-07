package shop.notification;

public class SmsChannel extends AbstractChannel {
    private final int maxLength;

    public SmsChannel(int maxLength) {
        super("SMS");
        if (maxLength < 4) {
            throw new IllegalArgumentException("Max length must be at least 4");
        }
        this.maxLength = maxLength;
    }

    @Override
    protected String resolveRecipient(CustomerContact contact) {
        String phone = contact.getPhone();
        return phone.startsWith("08") ? phone : null;
    }

    @Override
    protected String prepare(String message) {
        if (message.length() <= maxLength) {
            return message;
        }
        return message.substring(0, maxLength - 3) + "...";
    }

    @Override
    protected void deliver(String recipient, String message) {
        System.out.println("[SMS to " + recipient + "] " + message);
    }
}