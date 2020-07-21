package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.List;
import java.util.function.Function;

public interface ShoppingCartItem {
    Function<Double, Double> getPriceModifier();

    void apply(final List<List<Function<Double, Double>>> list);

    /**
     * @param i            position of this item in the shopping list
     * @param shoppingList the shopping cart list
     * @param pricingList  the pricing list build to reflect the shopping list
     */
    void apply(final int i, final List<ShoppingCartItem> shoppingList, final List<List<Function<Double, Double>>> pricingList);

    /**
     * the (product) item type, blank for coupon
     */
    String getSku();
}
