package store.model.product;

import java.util.HashMap;
import java.util.List;
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
