package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.List;
import java.util.function.Function;

public abstract class Coupon implements ShoppingCartItem {
    @Override
    public void apply(final List<List<Function<Double, Double>>> list) {
        list.add(List.of());
    }

    @Override
    public String getSku() {
        return "";
    }
}
