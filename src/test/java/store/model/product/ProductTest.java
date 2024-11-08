package store.model.product;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.constant.Path;
import store.constants.ErrorMessage;
import store.model.promotion.Promotions;

class ProductTest {
    @BeforeEach
    void setUp() {
        Promotions.fetchPromotionsData(Path.PATH_TO_RESOURCES + "promotions.md");
    }

    @Test
    void 재고를초과하여구매할경우_예외가발생한다() {
        // given
        ProductData productData = new ProductData(List.of("콜라", "1000", "10", "null"));
        ProductData promotionProductData = new ProductData(List.of("콜라", "1000", "10", "탄산2+1"));
        Product product = new Product(productData);
        product.updateQuantity(promotionProductData);

        // when & then
        assertThatThrownBy(() -> product.purchase(21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_STOCK);
    }

    @Test
    void 재고를초과하지않은경우_예외가발생하지않는다() {
        // given
        ProductData productData = new ProductData(List.of("콜라", "1000", "10", "null"));
        ProductData promotionProductData = new ProductData(List.of("콜라", "1000", "10", "탄산2+1"));
        Product product = new Product(productData);
        product.updateQuantity(promotionProductData);

        // when & then
        assertThatNoException().isThrownBy(() -> product.purchase(20));
    }
}
