package store.model.purchase;

import store.constants.ErrorMessage;
import store.model.product.Product;
import store.model.product.Products;

public class Purchase {
    private final Product product;
    private final int count;

    public Purchase(final String productName, final int count) {
        validateProductExist(productName);
        this.product = Products.getProductByName(productName);
        this.count = count;
    }

    private void validateProductExist(final String productName) {
        if (!Products.hasProduct(productName)) {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_NOT_EXIST);
        }
    }
}
