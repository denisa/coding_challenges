package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.List;
import java.util.function.Function;

/**
 * Take P% off the next product in the cart
 * <p>
 * For example, take 20% off your next product
 */
public class DiscountNextCoupon extends Coupon {
    private final double discount;

    public DiscountNextCoupon(final double discount) {
        this.discount = discount;
    }

    @Override
    public void apply(final List<ShoppingCartItem> shoppingList, final int i, final List<List<Function<Double, Double>>> pricingList) {
        for (int j = i; j < pricingList.size(); j++) {
            if (pricingList.get(j).isEmpty()) {
                continue;
            }
            pricingList.get(j).add(d -> d * (1.0 - discount));
            return;
        }
    }
}
