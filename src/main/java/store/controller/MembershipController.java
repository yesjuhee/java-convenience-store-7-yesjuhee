package store.controller;

import store.model.purchase.Membership;
import store.model.purchase.Purchases;

public class MembershipController extends StoreController {
    public Membership confirmToApplyMembership(Purchases purchases) {
        boolean applyMembership = retryUntilSuccess(confirmView::confirmToApplyMembership);
        if (applyMembership) {
            return Membership.of(purchases);
        }
        return Membership.notApplied();
    }
}
