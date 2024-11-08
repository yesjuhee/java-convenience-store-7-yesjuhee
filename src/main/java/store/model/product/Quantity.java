package store.model.product;

import store.constants.ErrorMessage;

public class Quantity {
    private int quantity;

    public Quantity() {
        this.quantity = 0;
    }

    public Quantity(final String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }

    public void subtract(int amount) {
        if (amount > quantity) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_STOCK);
        }
        quantity -= amount;
    }

    public int getQuantity() {
        return quantity;
    }
}
