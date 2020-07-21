package li.antonio.coding_challenges.interview.shopping_cart;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ShoppingCart {
    List<ShoppingCartItem> list = new ArrayList<>();

    void addItem(final ShoppingCartItem shoppingCartItem) {
        list.add(shoppingCartItem);
    }

    double totalPrice() {
        final List<List<Function<Double, Double>>> pricingList = new ArrayList<>();
        for (final ShoppingCartItem shoppingCartItem : list) {
            shoppingCartItem.apply(pricingList);
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).apply(i, list, pricingList);
        }

        double price = 0;
        for (final List<Function<Double, Double>> functions : pricingList) {
            double itemPrice = 0;
            for (final Function<Double, Double> function : functions) {
                itemPrice = function.apply(itemPrice);
            }
            price += itemPrice;
        }
        return price;
    }
}
