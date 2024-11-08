package store.model.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.model.promotion.Promotions;

class ProductsTest {
    @BeforeEach
    void setPromotions() {
        Promotions.fetchPromotionsData("src/test/resources/promotions.md");
    }

    @Test
    void 일반재고만있는경우_테스트() {
        // given
        String filePath = "src/test/resources/products-base-only.md";

        // when
        Products.fetchProductsData(filePath);

        // then
        Product baseOnlyProduct = Products.getProducts().getFirst();
        assertThat(baseOnlyProduct.getName()).isEqualTo("콜라");
        assertThat(baseOnlyProduct.getPrice()).isEqualTo(1000);
        assertThat(baseOnlyProduct.getBaseQuantity()).isEqualTo(10);
        assertThat(baseOnlyProduct.hasPromotion()).isFalse();
    }

    @Test
    void 프로모션재고만있는경우_테스트() {
        // given
        String filePath = "src/test/resources/products-promotion-only.md";

        // when
        Products.fetchProductsData(filePath);

        // then
        Product promotionOnlyProduct = Products.getProducts().getFirst();
        assertThat(promotionOnlyProduct.getName()).isEqualTo("콜라");
        assertThat(promotionOnlyProduct.getPrice()).isEqualTo(1000);
        assertThat(promotionOnlyProduct.getBaseQuantity()).isEqualTo(0);
        assertThat(promotionOnlyProduct.getPromotionQuantity()).isEqualTo(10);
        assertThat(promotionOnlyProduct.hasPromotion()).isTrue();
    }

    @Test
    void 일반재고_프로모션재고_둘다있는경우_테스트() {
        // given
        String filePath = "src/test/resources/products-base-promotion.md";

        // when
        Products.fetchProductsData(filePath);

        // then
        Product baseAndPromotionProduct = Products.getProducts().getFirst();
        assertThat(baseAndPromotionProduct.getName()).isEqualTo("콜라");
        assertThat(baseAndPromotionProduct.getPrice()).isEqualTo(1000);
        assertThat(baseAndPromotionProduct.getBaseQuantity()).isEqualTo(10);
        assertThat(baseAndPromotionProduct.getPromotionQuantity()).isEqualTo(10);
        assertThat(baseAndPromotionProduct.hasPromotion()).isTrue();
    }
}
