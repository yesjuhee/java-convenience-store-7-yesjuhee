package store.controller;

import java.util.function.Supplier;
import store.view.ConfirmView;
import store.view.ErrorView;
import store.view.ProductView;
import store.view.PurchaseView;
import store.view.ReceiptView;

public class StoreController {
    protected final ProductView productView = new ProductView();
    protected final PurchaseView purchaseView = new PurchaseView();
    protected final ConfirmView confirmView = new ConfirmView();
    protected final ReceiptView receiptView = new ReceiptView();
    protected final ErrorView errorView = new ErrorView();

    protected <T> T retryUntilSuccess(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                errorView.displayError(exception.getMessage());
            }
        }
    }
}
