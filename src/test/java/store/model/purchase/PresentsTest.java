package store.model.purchase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.constant.Path;
import store.model.product.ProductDatabase;
import store.model.promotion.PromotionDatabase;

class PresentsTest {
    @BeforeEach
    void setUp() {
        PromotionDatabase.fetchPromotionsFile(Path.PATH_TO_RESOURCES + "promotions.md");
        ProductDatabase.fetchProductsFile(Path.PATH_TO_RESOURCES + "products.md");
    }

    @Test
    void 구매정보를바탕으로_증정정보를생성할수있다() {
        // given
        Purchase purchase = new Purchase("콜라", 5); // 콜라, 1000원, 2+1 행사 중
        Presents presents = new Presents();

        // when
        presents.addPresent(purchase);
        Present cokePresent = presents.getPresents().getFirst();

        // then
        assertThat(cokePresent.getProductName()).isEqualTo("콜라");
        assertThat(cokePresent.getAmount()).isEqualTo(1);
        assertThat(cokePresent.getTotalPresentPrice()).isEqualTo(1000);
    }

    @Test
    void 구매정보를바탕으로_프로모션할인금액을_계산할수있다() {
        // given
        Purchase purchase = new Purchase("콜라", 5); // 콜라, 1000원, 2+1 행사 중
        Presents presents = new Presents();

        // when
        presents.addPresent(purchase);

        // then
        assertThat(presents.calculatePromotionDiscount()).isEqualTo(1000);
    }
}
