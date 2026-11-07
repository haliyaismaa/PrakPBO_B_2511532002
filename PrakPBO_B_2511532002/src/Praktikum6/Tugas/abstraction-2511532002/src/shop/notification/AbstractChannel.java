package shop.notification;

public abstract class AbstractChannel implements NotificationChannel {
    private final String channelName;
    private int sentCount;
    private String lastDelivered = "";

    protected AbstractChannel(String channelName) {
        if (channelName == null || channelName.isBlank()) {
            throw new IllegalArgumentException("Channel name must not be blank");
        }
        this.channelName = channelName;
    }

    @Override
    public String channelName() {
        return channelName;
    }

    @Override
    public final boolean send(CustomerContact contact, String message) {
        if (contact == null || message == null || message.isBlank()) {
            return false;
        }
        String recipient = resolveRecipient(contact);
        if (recipient == null || recipient.isEmpty()) {
            return false;
        }
        String prepared = prepare(message);
        deliver(recipient, prepared);
        sentCount++;
        lastDelivered = prepared;
        return true;
    }

    protected abstract String resolveRecipient(CustomerContact contact);

    protected abstract void deliver(String recipient, String message);

    protected String prepare(String message) {
        return message;
    }

    public int getSentCount() { return sentCount; }

    public String getLastDelivered() { return lastDelivered; }
}