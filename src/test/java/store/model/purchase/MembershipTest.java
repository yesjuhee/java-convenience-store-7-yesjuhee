package store.model.purchase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.constant.Path;
import store.model.product.ProductDatabase;
import store.model.promotion.PromotionDatabase;

class MembershipTest {
    @BeforeEach
    void setUp() {
        PromotionDatabase.fetchPromotionsFile(Path.PATH_TO_RESOURCES + "promotions.md");
        ProductDatabase.fetchProductsFile(Path.PATH_TO_RESOURCES + "products.md");
    }

    @Test
    void 멤버십혜택이없는경우_0원을_설정한다() {
        // given & when
        Membership membership = Membership.notApplied();
        // then
        assertThat(membership.getDiscount()).isEqualTo(0);
    }

    @Test
    void 프로모션미적용금액의_30퍼센트를할인한다() {
        // given
        Purchases purchases = new Purchases("[물-10]");

        // when
        Membership membership = Membership.of(purchases);

        // then
        assertThat(membership.getDiscount()).isEqualTo(1500);
    }

    @Test
    void 프로모션미적용_최대800원할인한다() {
        // given
        Purchases purchases = new Purchases("[물-10],[에너지바-5],[비타민워터-6],[정식도시락-8]");

        // when
        Membership membership = Membership.of(purchases);

        // then
        assertThat(membership.getDiscount()).isEqualTo(8000);
    }
}
