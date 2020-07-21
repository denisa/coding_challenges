package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Product implements ShoppingCartItem {
    private final double price;
    private final String sku;

    public Product(final double price, final String sku) {
        this.price = price;
        this.sku = sku;
    }

    @Override
    public Optional<Function<Double, Double>> getPricing() {
        return Optional.of(d -> price);
    }

    @Override
    public void apply(final List<ShoppingCartItem> shoppingList, final int i, final List<List<Function<Double, Double>>> pricingList) {
        // no-op
    }

    @Override
    public String getSku() {
        return sku;
    }
}
