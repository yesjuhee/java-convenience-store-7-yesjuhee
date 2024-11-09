package store.model.purchase;

import store.constants.ErrorMessage;
import store.model.product.Product;
import store.model.product.Products;

public class Purchase {
    private final Product product;
    private int amount;

    public Purchase(final String productName, final int amount) {
        validateProductExist(productName);
        this.product = Products.getProductByName(productName);
        product.validatePurchaseAmount(amount);
        this.amount = amount;
    }

    private void validateProductExist(final String productName) {
        if (!Products.hasProduct(productName)) {
            throw new IllegalArgumentException(ErrorMessage.PRODUCT_NOT_EXIST);
        }
    }

    public boolean canNotApplyPromotion() {
        return !product.isInPromotion();
    }

    public void purchaseWithoutPromotion() {
        product.reduceQuantityWithoutPromotion(amount);
        Products.switchProduct(product);
    }

//    public Present purchaseWithPromotion() {
//        // product에서 수량 차감 -> 프로모션 재고 먼저, 일반 재고 나중에
//        // present 추가 여기서?
//    }

    public boolean notEnoughPromotionQuantity() {
        return amount > product.getPromotionQuantity();
    }

    public int calculateNonPromotionAmount() {
        return amount - product.getPromotionQuantity();
    }

    public void excludeNonPromotedAmount() {
        amount -= calculateNonPromotionAmount();
    }

    public String getProductName() {
        return product.getName();
    }

    public int getTotalPurchasePrice() {
        return amount * product.getPrice();
    }

    public int getAmount() {
        return amount;
    }
}
