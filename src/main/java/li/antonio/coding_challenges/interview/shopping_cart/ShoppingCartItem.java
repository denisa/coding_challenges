package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Provides a unified interface over Product and Coupon letting the ShoppingCart ignore the actual type of any item.
 */
public interface ShoppingCartItem {
    /**
     * a function that returns the price for this shopping cart item. Typically only used for Product.
     */
    Optional<Function<Double, Double>> getPricing();

    /**
     * Let the shopping cart item at position i modify the pricing list. Typically only used for Coupon.
     *
     * @param shoppingList the shopping cart list
     * @param i            position of this item in the shopping list
     * @param pricingList  the pricing list build to reflect the shopping list
     */
    void apply(final List<ShoppingCartItem> shoppingList, final int i, final List<List<Function<Double, Double>>> pricingList);

    /**
     * the (product) item type, blank for coupon.
     */
    String getSku();
}
