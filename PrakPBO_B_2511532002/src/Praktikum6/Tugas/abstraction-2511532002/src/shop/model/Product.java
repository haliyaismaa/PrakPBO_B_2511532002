package shop.model;

public abstract class Product {
    private final String sku;
    private final String name;
    private final long price; //in rupiah

    protected Product(String sku, String name, long price) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.sku = sku;
        this.name = name;
        this.price = price;
    }
public String getSku(){return sku;}
public String getName(){return name;}
public long getPrice(){return price;}

/**Price the customer actually pays. Subclasses may override it. */
public long getFinalPrice() {
    return price;
}
/** Every concrete product must state its category. */
public abstract String category();

/** A concrete method that relies on the abstract methods category(). */
public String getInfo() {
    return "["+category() + "] "+getName()+" - Rp"+getFinalPrice();
}
}

