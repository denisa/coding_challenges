# Shopping cart with coupon

## Problem statement
A shopping cart can contain a mix of products and coupons.

Coupons affect the products’ prices. There are three kinds of coupons:

* Take N% off each individual product in the cart.  
    For example, a general "10% off" coupon.
* Take P% off the next product in the cart.  
    For example, take 20% off your next product.
* Take $D off of the Nth product of type T.  
    For example, take $5 off your third business card holder.

If multiple coupons of different types could apply to a single product, apply all the discounts in the sequence they were presented.

The cart contents must be considered in sequence.

### Pricing Examples: Cart #1

1) Coupon: Take 10% off the next product in the cart
2) $10 postcard sorter
3) $20 stationery organizer

Total = ($10 * 90%) + $20 = $29

The coupon applies to the postcard sorter, and the cart total is $29.

### Pricing Examples: Cart #2

1) $10 postcard sorter
2) Coupon: Take 10% off the next product in the cart
3) $20 stationery organizer

Total = $10 + ($20 * 90%) = $28

### Pricing Examples: Cart #3

1) $10 postcard sorter
2) Coupon: Take $2 off your 2nd postcard sorter
3) Coupon: 25% off each individual item
4) Coupon: Take 10% off the next item in the cart
5) $10 postcard sorter

Total = ($10 * 75%) + (($10 - $2) * 75% * 90%) = $12.90

## Goal
1. Class definitions for the necessary components, such as _Cart_. No UI, no input reading.
1. _Cart.TotalPrice()_, implemented in terms of your classes.