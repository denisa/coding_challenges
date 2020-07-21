package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.List;
import java.util.function.Function;

/**
 * Take N% off each individual product in the cart
 * <p>
 * For example, a general "10% off" coupon
 */
public class DiscountAllCoupon extends Coupon {
    private final double discount;

    public DiscountAllCoupon(final double discount) {
        this.discount = discount;
    }

    @Override
    public void apply(final List<ShoppingCartItem> shoppingList, final int i, final List<List<Function<Double, Double>>> pricingList) {
        for (final List<Function<Double, Double>> functions : pricingList) {
            if (!functions.isEmpty()) { // products have at least their own price
                functions.add(d -> d * (1.0 - discount));
            }
        }
    }
}
