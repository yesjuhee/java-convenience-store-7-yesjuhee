package store.constants;

import java.util.Arrays;
import java.util.List;

public class ProductsFile {
    public static final String FILE_PATH = "src/main/resources/products.md";
    public static final int NAME_COLUMN_NUMBER = 0;
    public static final int PRICE_COLUMN_NUMBER = 1;
    public static final int QUANTITY_COLUMN_NUMBER = 2;
    public static final int PROMOTION_COLUMN_NUMBER = 3;
    public static final String NO_VALUE = "null";
    public static final List<String> FILE_HEADER = Arrays.asList("name", "price", "quantity", "promotion");
}
