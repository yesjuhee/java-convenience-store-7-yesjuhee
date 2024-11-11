package store.model.purchase;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.constant.Path;
import store.model.product.Product;
import store.model.product.ProductDatabase;
import store.model.promotion.Promotions;

class PurchaseTest {
    @BeforeEach
    void setUp() {
        Promotions.fetchPromotionsData(Path.PATH_TO_RESOURCES + "promotions.md");
        ProductDatabase.fetchProductsFile(Path.PATH_TO_RESOURCES + "products.md");
    }

    @Test
    void 프로모션적용안되는상품_재고차감_테스트() {
        // given
        Purchase purchase = new Purchase("물", 5);

        // when
        purchase.purchaseWithoutPromotion();

        // then
        Product resultProduct = ProductDatabase.getProductByName(purchase.getProductName());
        assertThat(resultProduct.getBaseQuantity()).isEqualTo(5);
    }
}
