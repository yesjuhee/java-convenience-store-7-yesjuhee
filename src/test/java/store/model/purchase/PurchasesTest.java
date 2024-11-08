package store.model.purchase;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.constant.Path;
import store.constants.ErrorMessage;
import store.model.product.Products;
import store.model.promotion.Promotions;

class PurchasesTest {
    @BeforeEach
    void setUp() {
        Promotions.fetchPromotionsData(Path.PATH_TO_RESOURCES + "promotions.md");
        Products.fetchProductsData(Path.PATH_TO_RESOURCES + "products.md");
    }

    @ParameterizedTest
    @ValueSource(strings = {"[사이다-2][감자칩-1]",
            "[사이다2]", "사이다-2]", "[사이다-2",
            "[사이다-0]", "[사이다-두개]", "[사이다--1]",
            "[-]", "[[]]", "abc"})
    void 입력형식오류_테스트(String purchasesInput) {
        assertThatThrownBy(() -> new Purchases(purchasesInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_PURCHASE_FORMAT);
    }

    @ParameterizedTest
    @ValueSource(strings = {"[사이다-2],[감자칩-1]", "[사이다-2] , [감자칩-1]", "[사이다 - 2],[ 감자칩-1 ]"})
    void 올바른입력_테스트(String purchasesInput) {
        assertThatNoException().isThrownBy((() -> new Purchases(purchasesInput)));
    }
}