package store.model.product;

public class Quantity {
    private int quantity;

    public Quantity() {
        this.quantity = 0;
    }

    public Quantity(final String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }

    public int getQuantity() {
        return quantity;
    }
}
