package store.model.purchase;

import store.model.product.Product;
import store.model.product.ProductDatabase;

public class Present {
    private final Product product;
    private final int amount;

    public Present(Purchase purchase) {
        this.product = ProductDatabase.getProductByName(purchase.getProductName());
        this.amount = purchase.calculatePresentAmount();
    }

    public int getTotalPresentPrice() {
        return product.getPrice() * amount;
    }

    public String getProductName() {
        return product.getName();
    }

    public int getAmount() {
        return amount;
    }
}
