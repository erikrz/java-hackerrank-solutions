package com.erikrz.hackerrank.solutions.challenges.simplebundlepricing;

import com.erikrz.hackerrank.solutions.challenges.simplebundlepricing.dto.Bundle;
import com.erikrz.hackerrank.solutions.challenges.simplebundlepricing.dto.Cart;
import com.erikrz.hackerrank.solutions.challenges.simplebundlepricing.dto.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CartBundlePricing {

    public Cart applyBundlePricing(Cart cart, List<Bundle> bundles) {
        System.out.println("Initial cart price: " + cart.getTotalPrice());
        var validBundles = getPotentialBundles(cart, bundles);
        if (validBundles.isEmpty()) {
            return cart;
        }
        var cartWithBundlePricing= validBundles.stream()
                .flatMap(bundle -> applyBundle(cart, bundle).stream())
                .map(cartIteration -> applyBundlePricing(cartIteration, validBundles))
                .min(Comparator.comparing(Cart::getTotalPrice))
                .orElse(cart);
        System.out.println("BundlePricing cart price: " + cartWithBundlePricing.getTotalPrice());
        return cartWithBundlePricing;
    }

    private List<Bundle> getPotentialBundles(Cart cart, List<Bundle> bundles) {
        return bundles.stream().filter(
                bundle -> cart.items().containsAll(bundle.items())
        ).toList();
    }

    private Optional<Cart> applyBundle(Cart cart, Bundle bundle) {
        var newCartItems = new ArrayList<>(cart.items());
        var removedItems = bundle.items().stream()
                .map(newCartItems::remove)
                .filter(removed -> removed)
                .count();
        if (removedItems != bundle.items().size()) {
            return Optional.empty();
        }
        newCartItems.add(bundleToItem(bundle));
        return Optional.of(new Cart(List.copyOf(newCartItems)));
    }

    private Item bundleToItem(Bundle bundle) {
        return new Item(bundle.name(), bundle.price());
    }

}
