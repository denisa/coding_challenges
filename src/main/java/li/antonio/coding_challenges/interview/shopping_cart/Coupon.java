package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.Optional;
import java.util.function.Function;

/**
 * A coupon has no Sku, nor a a pricing.
 */
public abstract class Coupon implements ShoppingCartItem {

    @Override
    public Optional<Function<Double, Double>> getPricing() {
        return Optional.empty();
    }

    @Override
    public String getSku() {
        return "";
    }
}
