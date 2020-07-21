package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Product implements ShoppingCartItem {
    private final double price;
    private final String sku;

    public Product(final double price, final String sku) {
        this.price = price;
        this.sku = sku;
    }

    @Override
    public Function<Double, Double> getPriceModifier() {
        return d -> price;
    }

    @Override
    public void apply(final List<List<Function<Double, Double>>> list) {
        final List<Function<Double, Double>> functions = new ArrayList<>();
        functions.add(getPriceModifier());
        list.add(functions);
    }

    @Override
    public void apply(final int i, final List<ShoppingCartItem> shoppingList, final List<List<Function<Double, Double>>> pricingList) {
        // no-op
    }

    @Override
    public String getSku() {
        return sku;
    }
}
