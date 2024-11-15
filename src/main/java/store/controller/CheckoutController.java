package store.controller;

import store.constants.ProductsFile;
import store.model.product.ProductDatabase;
import store.model.purchase.Membership;
import store.model.purchase.Presents;
import store.model.purchase.Purchases;

public class CheckoutController extends StoreController {
    public void displayReceipt(final Purchases purchases, final Presents presents, final Membership membership) {
        receiptView.displayReceipt(purchases, presents, membership);
    }

    public boolean confirmToPurchaseMore() {
        ProductDatabase.saveProductsFile(ProductsFile.FILE_PATH);
        return retryUntilSuccess(confirmView::confirmToPurchaseMore);
    }
}
