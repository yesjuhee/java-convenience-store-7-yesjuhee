package store.model.product;

import java.util.List;
import store.constants.ProductsFile;

public class ProductData {
    private final String name;
    private final String price;
    private final String quantify;
    private final String promotion;

    public ProductData(List<String> productData) {
        this.name = productData.get(ProductsFile.NAME_COLUMN_NUMBER);
        this.price = productData.get(ProductsFile.PRICE_COLUMN_NUMBER);
        this.quantify = productData.get(ProductsFile.QUANTITY_COLUMN_NUMBER);
        this.promotion = productData.get(ProductsFile.PROMOTION_COLUMN_NUMBER);
    }

    public boolean hasPromotion() {
        return !promotion.equals(ProductsFile.NO_VALUE);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return Integer.parseInt(price);
    }

    public String getQuantify() {
        return quantify;
    }

    public String getPromotion() {
        return promotion;
    }
}
