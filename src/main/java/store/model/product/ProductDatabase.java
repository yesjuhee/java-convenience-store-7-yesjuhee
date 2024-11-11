package store.model.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import store.constants.ProductsFile;
import store.utils.FileUtils;

public class ProductDatabase {
    private static final HashMap<String, Product> products = new HashMap<>();

    public static void fetchProductsFile(final String filePath) {
        List<ProductData> productsData = FileUtils.readCsvBody(filePath)
                .stream().map(ProductData::new).toList();
        productsData.forEach((productData) -> updateProductDatabase(productData.getName(), productData));
    }

    public static void saveProductsFile(final String filePath) {
        List<List<String>> updatedData = new ArrayList<>();
        for (Entry<String, Product> productEntry : products.entrySet()) {
            Product product = productEntry.getValue();
            addBaseData(product, updatedData);
            if (product.hasPromotion()) {
                addPromotionData(product, updatedData);
            }
        }
        FileUtils.saveCsvFile(filePath, updatedData, ProductsFile.FILE_HEADER);
    }

    public static void updateProductDatabase(Product product) {
        String key = product.getName();
        products.put(key, product);
    }

    public static Product getProductByName(String name) {
        return products.get(name);
    }

    public static List<Product> getProducts() {
        return products.values().stream().toList();
    }

    public static boolean hasProduct(String productName) {
        return products.containsKey(productName);
    }

    private static void updateProductDatabase(final String key, final ProductData productData) {
        if (products.containsKey(key)) {
            Product product = products.remove(key);
            product.updateQuantity(productData);
            products.put(key, product);
            return;
        }
        products.put(key, new Product(productData));
    }

    private static void addBaseData(Product product, List<List<String>> data) {
        List<String> rowData = Arrays.asList(product.getName(), Integer.toString(product.getPrice()),
                Integer.toString(product.getBaseQuantity()), ProductsFile.NO_VALUE);
        data.add(rowData);
    }

    private static void addPromotionData(Product product, List<List<String>> data) {
        List<String> rowData = Arrays.asList(product.getName(), Integer.toString(product.getPrice()),
                Integer.toString(product.getPromotionQuantity()), product.getPromotion().getName());
        data.add(rowData);
    }
}
