package li.antonio.coding_challenges.interview.shopping_cart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartTest {

    /**
     * 1) Coupon: Take 10% off the next product in the cart
     * 2) $10 postcard sorter
     * 3) $20 stationery organizer
     * <p>
     * Total = ($10 * 90%) + $20 = $29
     */
    @Test
    void shoppingCartOne() {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new DiscountNextCoupon(0.1));
        shoppingCart.addItem(new Product(10.00, "postcard sorter"));
        shoppingCart.addItem(new Product(20.00, "stationery organizer"));
        assertEquals(29.0, shoppingCart.totalPrice());
    }

    /**
     * 1) $10 postcard sorter
     * 2) Coupon: Take 10% off the next product in the cart
     * 3) $20 stationery organizer
     * <p>
     * Total = $10 + ($20 * 90%) = $28
     */
    @Test
    void shoppingCartTwo() {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Product(10.00, "postcard sorter"));
        shoppingCart.addItem(new DiscountNextCoupon(0.1));
        shoppingCart.addItem(new Product(20.00, "stationery organizer"));
        assertEquals(28.0, shoppingCart.totalPrice());
    }

    /**
     * 1) $10 postcard sorter
     * 2) Coupon: Take $2 off your 2nd postcard sorter
     * 3) Coupon: 25% off each individual item
     * 4) Coupon: Take 10% off the next item in the cart
     * 5) $10 postcard sorter
     * <p>
     * Total = ($10 * 75%) + (($10 - $2) * 75% * 90%) = $12.90
     */
    @Test
    void shoppingCartThree() {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Product(10.00, "postcard sorter"));
        shoppingCart.addItem(new AbsoluteCoupon(2.0, "postcard sorter", 2));
        shoppingCart.addItem(new DiscountAllCoupon(0.25));
        shoppingCart.addItem(new DiscountNextCoupon(0.1));
        shoppingCart.addItem(new Product(10.00, "postcard sorter"));
        assertEquals(12.9, shoppingCart.totalPrice());
    }
}