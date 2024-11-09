package store.model.purchase;

import store.constants.ErrorMessage;
import store.model.product.Product;
import store.model.product.Products;
import store.model.promotion.Promotion;

public class Purchase {
    private final Product product;
    private int amount;
    private int amountWithPromotion = 0;

    public Purchase(final String productName, final int amount) {
        validateProductExist(productName);
        this.product = Products.getProductByName(productName);
        product.validatePurchaseAmount(amount);
        this.amount = amount;
        initializeAmountWithPromotion();
    }

    private void initializeAmountWithPromotion() {
        if (!product.isInPromotion()) {
            return;
        }
        int promotionQuantity = product.getPromotionQuantity();
        int promotionUnit = product.getPromotionUnit();
        while (amountWithPromotion + promotionUnit < this.amount
                && amountWithPromotion + promotionUnit < promotionQuantity) {
            amountWithPromotion += promotionUnit;
        }
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

    public int calculateAmountWithoutPromotion() {
        return amount - amountWithPromotion;
    }

    public boolean purchaseAmountLessThanPromotionQuantity() {
        return amount < product.getPromotionQuantity();
    }

    public void excludeNonPromotedAmount() {
        amount = amountWithPromotion;
    }

    public void addOneFreeProduct() {
        amount += 1;
        amountWithPromotion += 1;
    }

    public boolean canGetMoreFreeProduct() {
        Promotion promotion = product.getPromotion();
        return promotion.canGetMoreFreeProduct(amount);
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
