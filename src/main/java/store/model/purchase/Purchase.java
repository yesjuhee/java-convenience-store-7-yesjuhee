package store.model.purchase;

import store.constants.ErrorMessage;
import store.model.product.Product;
import store.model.product.Products;

public class Purchase {
    private final Product product;
    private final int amount;

    public Purchase(final String productName, final int amount) {
        validateProductExist(productName);
        this.product = Products.getProductByName(productName);
        product.purchase(amount);
        this.amount = amount;
    }

    private void validateProductExist(final String productName) {
        if (!Products.hasProduct(productName)) {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_NOT_EXIST);
        }
    }

}
