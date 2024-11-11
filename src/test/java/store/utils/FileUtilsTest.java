package store.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import store.constant.Path;

class FileUtilsTest {
    @Test
    void 프로덕트_파일을읽어서_2차원배열로반환한다() {
        // given
        String filePath = Path.PATH_TO_RESOURCES + "products.md";

        // when
        List<List<String>> data = FileUtils.readCsvBody(filePath);

        // then
        assertThat(data)
                .hasSize(16)
                .contains(List.of("컵라면", "1700", "10", "null"), Index.atIndex(15));
    }

    @Test
    void 프로모션_파일을읽어서_2차원배열로반환한다() {
        // given
        String filePath = Path.PATH_TO_RESOURCES + "promotions.md";

        // when
        List<List<String>> data = FileUtils.readCsvBody(filePath);

        // then
        assertThat(data)
                .hasSize(5)
                .contains(List.of("promotion1", "2", "1", "2024-01-01", "2024-12-31"), Index.atIndex(0));
    }
}
