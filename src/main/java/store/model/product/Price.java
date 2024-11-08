package store.model.product;

public class Price {
    private final int price;

    public Price(final String price) {
        this.price = Integer.parseInt(price);
    }

    public int getPrice() {
        return price;
    }
}
