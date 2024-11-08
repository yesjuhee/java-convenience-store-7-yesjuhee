package store.model.product;

import java.util.HashMap;
import java.util.List;
import store.constants.ProductsFile;
import store.utils.FileUtils;

public class Products {
    private static Products instance;
    private final HashMap<String, Product> products;

    private Products() {
        this.products = new HashMap<>();
        fetchProductsData();
    }

    private void fetchProductsData() {
        List<ProductData> productsData = FileUtils.readCsvBody(ProductsFile.FILE_PATH)
                .stream().map(ProductData::new).toList();
        for (ProductData productData : productsData) {
            String productName = productData.getName();
            updateProducts(productName, productData);
        }
    }

    private void updateProducts(final String key, final ProductData productData) {
        if (products.containsKey(key)) {
            Product product = products.remove(key);
            product.updateQuantity(productData);
            products.put(key, product);
            return;
        }
        products.put(key, new Product(productData));
    }

    public List<Product> getProducts() {
        return products.values().stream().toList();
    }

    public static Products getInstance() {
        if (instance == null) {
            instance = new Products();
        }
        return instance;
    }
}
