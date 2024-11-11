package store.model.purchase;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.constant.Path;
import store.constants.ErrorMessage;
import store.model.product.ProductDatabase;
import store.model.promotion.PromotionDatabase;

class PurchasesTest {
    @BeforeEach
    void setUp() {
        PromotionDatabase.fetchPromotionsFile(Path.PATH_TO_RESOURCES + "promotions.md");
        ProductDatabase.fetchProductsFile(Path.PATH_TO_RESOURCES + "products.md");
    }

    @ParameterizedTest
    @ValueSource(strings = {"[사이다-2][감자칩-1]",
            "[사이다2]", "사이다-2]", "[사이다-2",
            "[사이다-0]", "[사이다-두개]", "[사이다--1]",
            "[-]", "[[]]", "abc"})
    void 입력형식오류_테스트(String purchasesInput) {
        assertThatThrownBy(() -> new Purchases(purchasesInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_PURCHASE_FORMAT.getFormatMessage());
    }

    @Test
    void 존재하지않는상품오류_테스트() {
        String purchasesInput = "[제로콜라-2]";
        assertThatThrownBy(() -> new Purchases(purchasesInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PRODUCT_NOT_EXIST.getFormatMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"[사이다-2],[감자칩-1]", "[사이다-2] , [감자칩-1]", "[사이다 - 2],[ 감자칩-1 ]"})
    void 올바른입력_테스트(String purchasesInput) {
        assertThatNoException().isThrownBy((() -> new Purchases(purchasesInput)));
    }
}
