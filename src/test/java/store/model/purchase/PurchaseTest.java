package store.model.purchase;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.constant.Path;
import store.model.product.Product;
import store.model.product.ProductDatabase;
import store.model.promotion.PromotionDatabase;

class PurchaseTest {
    @BeforeEach
    void setUp() {
        PromotionDatabase.fetchPromotionsFile(Path.PATH_TO_RESOURCES + "promotions.md");
        ProductDatabase.fetchProductsFile(Path.PATH_TO_RESOURCES + "products.md");
    }

    @Test
    void 프로모션적용안되는상품은_기본재고차가_선차감된다() {
        // given
        Purchase purchase = new Purchase("물", 5);

        // when
        if (purchase.canNotApplyPromotion()) {
            purchase.purchaseWithoutPromotion();
        }

        // then
        Product resultProduct = ProductDatabase.getProductByName(purchase.getProductName());
        assertThat(resultProduct.getBaseQuantity()).isEqualTo(5);
    }

    @Test
    void 프로모션적용되는상품은_프로모션재고가_선차감된후_기본재고가차감된다() {
        // given
        Purchase purchase = new Purchase("콜라", 15); // 기본재고 10, 프로모션재고 10

        // when
        purchase.purchaseWithPromotion();

        // then
        Product resultProduct = ProductDatabase.getProductByName(purchase.getProductName());
        assertThat(resultProduct.getPromotionQuantity()).isEqualTo(0);
        assertThat(resultProduct.getBaseQuantity()).isEqualTo(5);
    }

    @Test
    void 프로모션재고가_부족한지_확인할수있다() {
        // given & when
        Purchase purchase = new Purchase("콜라", 11); // 프로모션재고 10

        // then
        assertThat(purchase.notEnoughPromotionQuantity()).isTrue();
    }

    @Test
    void 추가증정이가능한지_확인할수있다() {
        // given & when
        Purchase purchase = new Purchase("콜라", 2); // 프로모션재고10, 2 + 1 행사

        // then
        assertThat(purchase.canGetMoreFreeProduct()).isTrue();
    }

    @Test
    void 프로모션_재고가부족한경우_추가증정할수없다() {
        // given & when
        Purchase purchase = new Purchase("탄산수", 5); // 프로모션재고5, 2 + 1 행사

        // then
        assertThat(purchase.canGetMoreFreeProduct()).isFalse();
    }

    @Test
    void 구매한수량중_프로모션미적용수량을_계산할수있다() {
        // given & when
        Purchase purchase = new Purchase("탄산수", 4); // 2 + 1 행사

        // then
        assertThat(purchase.calculateAmountWithoutPromotion()).isEqualTo(1);
    }

    @Test
    void 구매한수량중_프로모션미적용수량을_제외할수있다() {
        // given
        Purchase purchase = new Purchase("탄산수", 4); // 2 + 1 행사

        // when
        purchase.excludeNonPromotedAmount();

        // then
        assertThat(purchase.getAmount()).isEqualTo(3);
    }

    @Test
    void 증정상품을_하나_추가할수있다() {
        // given
        Purchase purchase = new Purchase("콜라", 2); // 프로모션재고10, 2 + 1 행사

        // when
        purchase.addOneFreeProduct();

        // then
        assertThat(purchase.getAmount()).isEqualTo(3);
    }
}
