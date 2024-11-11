package store.view;

import java.util.List;
import store.constants.OutputMessage;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class ProductView {
    private static final int OUT_OF_STOCK = 0;

    public void displayProducts(final List<Product> products) {
        System.out.printf(OutputMessage.WELCOME.getFormatMessage());
        for (Product product : products) {
            if (product.hasPromotion()) {
                displayPromotionProduct(product, product.getPromotion());
            }
            displayProduct(product);
        }
    }

    private void displayProduct(final Product product) {
        int baseQuantity = product.getBaseQuantity();
        if (baseQuantity == OUT_OF_STOCK) {
            displayOutOfStockProduct(product);
            return;
        }
        String name = product.getName();
        int price = product.getPrice();
        System.out.printf(OutputMessage.PRODUCT.getFormatMessage(name, price, baseQuantity));
    }

    private void displayPromotionProduct(final Product product, final Promotion promotion) {
        int promotionQuantity = product.getPromotionQuantity();
        if (promotionQuantity == OUT_OF_STOCK) {
            displayOutOfStockPromotionProduct(product, promotion);
            return;
        }
        String name = product.getName();
        int price = product.getPrice();
        String promotionName = promotion.getName();
        System.out.printf(
                OutputMessage.PROMOTION_PRODUCT.getFormatMessage(name, price, promotionQuantity, promotionName));
    }

    private void displayOutOfStockProduct(final Product product) {
        String name = product.getName();
        int price = product.getPrice();
        System.out.printf(
                OutputMessage.PRODUCT_OUT_OF_STOCK.getFormatMessage(name, price));
    }

    private void displayOutOfStockPromotionProduct(final Product product, final Promotion promotion) {
        String name = product.getName();
        int price = product.getPrice();
        String promotionName = promotion.getName();
        System.out.printf(OutputMessage.PROMOTION_PRODUCT_OUT_OF_STOCK.getFormatMessage(name, price, promotionName));
    }
}
