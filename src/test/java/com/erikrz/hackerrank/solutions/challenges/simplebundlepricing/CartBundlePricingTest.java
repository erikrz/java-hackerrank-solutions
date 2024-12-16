package com.erikrz.hackerrank.solutions.challenges.simplebundlepricing;


import com.erikrz.hackerrank.solutions.challenges.simplebundlepricing.dto.Bundle;
import com.erikrz.hackerrank.solutions.challenges.simplebundlepricing.dto.Cart;
import com.erikrz.hackerrank.solutions.challenges.simplebundlepricing.dto.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class CartBundlePricingTest {

    private final Item mango = new Item("Mango", new BigDecimal("2.50"));
    private final Item apple = new Item("Apple", new BigDecimal("1.50"));
    private final Item bread = new Item("Bread", new BigDecimal("1.00"));
    private final Item banana = new Item("Banana", new BigDecimal("1.00"));
    private final Item margarine = new Item("Margarine", new BigDecimal("1.00"));
    private final Item orange = new Item("Orange", new BigDecimal("2.00"));
    private final Item grape = new Item("Grape", new BigDecimal("3.00"));

    private final Bundle mangoAppleBundle = new Bundle("mango-apple Bundle", List.of(mango, apple), new BigDecimal("3.15"));
    private final Bundle breadMargarineBundle = new Bundle("bread-margarine Bundle", List.of(bread, margarine), new BigDecimal("1.80"));
    private final Bundle grapeMangoBundle = new Bundle("grape-mango Bundle", List.of(grape, mango), new BigDecimal("5.00"));
    private final Bundle appleAppleBundle = new Bundle("apple-apple Bundle", List.of(apple, apple), new BigDecimal("2.15"));

    private final List<Bundle> allBundles = List.of(mangoAppleBundle, breadMargarineBundle, grapeMangoBundle, appleAppleBundle);

    private final CartBundlePricing cartBundlePricing = new CartBundlePricing();

    @Test
    void applyBundlePricingToEmptyCart() {
        var cart = new Cart(emptyList());
        var expectedCart = new Cart(emptyList());
        
        var result = cartBundlePricing.applyBundlePricing(cart, allBundles);
        
        assertThat(result).isEqualTo(expectedCart);
    }

    @Test
    void applyBundlePricingToCartWithNoAvailableBundles() {
        var cart = new Cart(List.of(mango, banana, orange));
        var expectedCart = new Cart(List.of(mango, banana, orange));
        
        var result = cartBundlePricing.applyBundlePricing(cart, allBundles);
        
        assertThat(result).isEqualTo(expectedCart);
    }

    @Test
    void applyBundlePricingToCartWithSingleAvailableBundle() {
        var cart = new Cart(List.of(mango, apple));
        var expectedCart = new Cart(List.of(toItem(mangoAppleBundle)));
        
        var result = cartBundlePricing.applyBundlePricing(cart, allBundles);
        
        assertThat(result).isEqualTo(expectedCart);
    }

    @Test
    void applyBundlePricingToCartWith2TimesSingleAvailableBundle() {
        var cart = new Cart(List.of(mango, mango, apple, apple));
        var expectedCart = new Cart(List.of(toItem(mangoAppleBundle), toItem(mangoAppleBundle)));
        
        var result = cartBundlePricing.applyBundlePricing(cart, allBundles);
        
        assertThat(result).isEqualTo(expectedCart);
    }

    @Test
    void applyBundlePricingToCartWithCompetingBundles() {
        var cart = new Cart(List.of(mango, apple, grape));
        var expectedCart = new Cart(List.of(grape, toItem(mangoAppleBundle)));
        
        var result = cartBundlePricing.applyBundlePricing(cart, allBundles);
        
        assertThat(result).isEqualTo(expectedCart);
    }

    private Item toItem(Bundle bundle) {
        return new Item(bundle.name(), bundle.price());
    }

}