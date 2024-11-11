package store.view;

import java.util.List;
import store.constants.OutputMessageDeprecated;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class ProductView {
    private static final int OUT_OF_STOCK = 0;

    public void displayProducts(final List<Product> products) {
        System.out.printf(OutputMessageDeprecated.WELCOME);
        products.forEach(this::displayProduct);
    }

    private void displayProduct(final Product product) {
        if (product.hasPromotion()) {
            displayPromotionProduct(product);
        }
        if (product.getBaseQuantity() == OUT_OF_STOCK) {
            System.out.printf(OutputMessageDeprecated.PRODUCT_OUT_OF_STOCK, product.getName(), product.getPrice());
            return;
        }
        System.out.printf(OutputMessageDeprecated.PRODUCT, product.getName(), product.getPrice(),
                product.getBaseQuantity());
    }

    private void displayPromotionProduct(final Product product) {
        Promotion promotion = product.getPromotion();
        if (product.getPromotionQuantity() == OUT_OF_STOCK) {
            System.out.printf(OutputMessageDeprecated.PROMOTION_PRODUCT_OUT_OF_STOCK, product.getName(),
                    product.getPrice(),
                    promotion.getName());
            return;
        }
        System.out.printf(OutputMessageDeprecated.PROMOTION_PRODUCT, product.getName(), product.getPrice(),
                product.getPromotionQuantity(),
                promotion.getName());
    }
}
