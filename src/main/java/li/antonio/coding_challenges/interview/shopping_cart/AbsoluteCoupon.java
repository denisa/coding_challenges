package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.List;
import java.util.function.Function;

/**
 * Take $D off of the Nth product of type T
 * <p>
 * For example, take $5 off your third business card holder
 */
public class AbsoluteCoupon extends Coupon {
    private final double discount;
    private final String type;
    private final int count;

    public AbsoluteCoupon(final double discount, final String type, final int count) {
        this.discount = discount;
        this.type = type;
        this.count = count;
    }

    @Override
    public Function<Double, Double> getPriceModifier() {
        return d -> d - discount;
    }

    @Override
    public void apply(final int i, final List<ShoppingCartItem> shoppingList, final List<List<Function<Double, Double>>> pricingList) {
        int hit = 0;
        for (int j = 0; j < shoppingList.size(); j++) {
            if (shoppingList.get(j).getSku().equals(type)) {
                hit++;

                if (hit == count) {
                    pricingList.get(j).add(getPriceModifier());
                    return;
                }
            }
        }
    }

}
