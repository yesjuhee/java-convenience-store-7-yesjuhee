package store.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import store.constants.ProductsFile;

class FileUtilsTest {

    @Test
    void 원하는파일을읽어서_2차원배열로반환한다() {
        // given
        String filePath = ProductsFile.FILE_PATH;

        // when
        List<List<String>> data = FileUtils.readCsvBody(filePath);

        // then
        assertThat(data)
                .hasSize(16)
                .contains(List.of("컵라면", "1700", "10", "null"), Index.atIndex(15));
    }
}
