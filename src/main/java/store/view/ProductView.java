package store.view;

import java.util.List;
import store.model.product.Product;
import store.model.promotion.Promotion;

public class ProductView {
    private static final int OUT_OF_STOCK = 0;

    public void displayProducts(final List<Product> products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");

        products.forEach(this::displayProduct);
        System.out.println();
    }

    private void displayProduct(final Product product) {
        if (product.hasPromotion()) {
            displayPromotionProduct(product);
        }
        if (product.getBaseQuantity() == OUT_OF_STOCK) {
            System.out.printf("- %s %,d원 재고 없음%n", product.getName(), product.getPrice());
            return;
        }
        System.out.printf("- %s %,d원 %,d개%n", product.getName(), product.getPrice(), product.getBaseQuantity());
    }

    private void displayPromotionProduct(final Product product) {
        Promotion promotion = product.getPromotion();
        if (product.getPromotionQuantity() == OUT_OF_STOCK) {
            System.out.printf("- %s %,d원 재고 없음 %s%n", product.getName(), product.getPrice(), promotion.getName());
            return;
        }
        System.out.printf("- %s %,d원 %,d개 %s%n", product.getName(), product.getPrice(), product.getPromotionQuantity(),
                promotion.getName());
    }
}
