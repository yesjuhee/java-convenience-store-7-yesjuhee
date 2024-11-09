package store.model.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import store.constants.ProductsFile;
import store.utils.FileUtils;

public class Products {
    private static HashMap<String, Product> products;

    public static void fetchProductsData(final String filePath) {
        products = new HashMap<>();
        List<ProductData> productsData = FileUtils.readCsvBody(filePath)
                .stream().map(ProductData::new).toList();
        for (ProductData productData : productsData) {
            String productName = productData.getName();
            updateProducts(productName, productData);
        }
    }

    public static void saveProductsData(final String filePath) {
        List<List<String>> data = new ArrayList<>();
        for (Entry<String, Product> productEntry : products.entrySet()) {
            Product product = productEntry.getValue();
            data = addBaseData(product, data);
            if (product.hasPromotion()) {
                data = addPromotionData(product, data);
            }
        }
        FileUtils.saveCsvFile(filePath, data, ProductsFile.FILE_HEADER);
    }

    private static List<List<String>> addBaseData(Product product, List<List<String>> data) {
        List<String> rowData = Arrays.asList(product.getName(), Integer.toString(product.getPrice()),
                Integer.toString(product.getBaseQuantity()), ProductsFile.NO_VALUE);
        data.add(rowData);
        return data;
    }

    private static List<List<String>> addPromotionData(Product product, List<List<String>> data) {
        List<String> rowData = Arrays.asList(product.getName(), Integer.toString(product.getPrice()),
                Integer.toString(product.getPromotionQuantity()), product.getPromotion().getName());
        data.add(rowData);
        return data;
    }

    private static void updateProducts(final String key, final ProductData productData) {
        if (products.containsKey(key)) {
            Product product = products.remove(key);
            product.updateQuantity(productData);
            products.put(key, product);
            return;
        }
        products.put(key, new Product(productData));
    }

    public static void switchProduct(Product product) {
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

}
