package store.model.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PromotionsTest {
    Promotions promotions = Promotions.getInstance();

    @Test
    void Promotions_생성테스트() {
        // given
        String filePath = "src/test/resources/promotions.md";
        String firstPromotionName = "promotion1";

        // when
        promotions.fetchPromotionsData(filePath);
        Promotion promotion1 = promotions.getPromotionByName(firstPromotionName);

        // then
        assertThat(promotion1.getName()).isEqualTo(firstPromotionName);
    }
}
