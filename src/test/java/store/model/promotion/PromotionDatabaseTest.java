package store.model.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import store.constant.Path;

class PromotionDatabaseTest {
    @Test
    void Promotions_생성테스트() {
        // given
        String filePath = Path.PATH_TO_RESOURCES + "promotions.md";
        String firstPromotionName = "promotion1";

        // when
        PromotionDatabase.fetchPromotionsFile(filePath);
        Promotion promotion1 = PromotionDatabase.getPromotionByName(firstPromotionName);

        // then
        assertThat(promotion1.getName()).isEqualTo(firstPromotionName);
    }
}
