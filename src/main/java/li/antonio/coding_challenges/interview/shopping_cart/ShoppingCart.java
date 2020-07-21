package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This solution builds a parallel list of pricing functions: for any item i of the shopping cart list,
 * there is composite function at index i of the pricing list that computes the price of the item.
 * For the sake of simplicity here, the composite function is build as a list of individual functions
 * to apply sequentially. The very first function is a constant function that returns an item’s price,
 * followed by any coupon’s price modifying function.
 * <br/>
 * For example, a 10% discount on a $100 product is <pre>[ d-> 100.0, d -> d * 0.90 ]</pre>.
 * <br/>
 * The pricing list entry for a coupon is an empty list.
 * <p/>
 * This solution has an O(N*C), where N is the total size of the cart and C is the number of coupon.
 * This is acceptable given that most e-commerce shopping carts are small.
 */
public class ShoppingCart {
    final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    void addItem(final ShoppingCartItem shoppingCartItem) {
        shoppingCartItems.add(shoppingCartItem);
    }

    double totalPrice() {
        final var pricingList = shoppingCartItems
                .stream()
                .map(shoppingCartItem -> shoppingCartItem
                        .getPricing()
                        .map(f -> (List<Function<Double, Double>>) new ArrayList<>(List.of(f)))
                        .orElseGet(List::<Function<Double, Double>>of)
                )
                .collect(Collectors.toList());

        for (var i = 0; i < shoppingCartItems.size(); i++) {
            shoppingCartItems.get(i).apply(shoppingCartItems, i, pricingList);
        }

        var price = 0.0;
        for (final var functions : pricingList) {
            var itemPrice = 0.0;
            for (final var function : functions) {
                itemPrice = function.apply(itemPrice);
            }
            price += itemPrice;
        }
        return price;
    }
}
