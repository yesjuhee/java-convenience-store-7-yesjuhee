package store;

import store.controller.CheckoutController;
import store.controller.MembershipController;
import store.controller.ProductController;
import store.controller.PromotionController;
import store.controller.PurchaseController;
import store.model.purchase.Membership;
import store.model.purchase.Presents;
import store.model.purchase.Purchases;

public class StoreApplication {
    private final ProductController productController = new ProductController();
    private final PurchaseController purchaseController = new PurchaseController();
    private final PromotionController promotionController = new PromotionController();
    private final MembershipController membershipController = new MembershipController();
    private final CheckoutController checkoutController = new CheckoutController();

    public void run() {
        do {
            productController.displayProducts();
            Purchases purchases = purchaseController.readPurchaseInfo();
            Presents presents = promotionController.confirmPromotionApply(purchases);
            Membership membership = membershipController.confirmToApplyMembership(purchases);
            checkoutController.displayReceipt(purchases, presents, membership);
        } while (checkoutController.confirmToPurchaseMore());
    }
}
