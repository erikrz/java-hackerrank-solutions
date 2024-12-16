package com.erikrz.hackerrank.solutions.challenges.simplebundlepricing.dto;

import java.math.BigDecimal;
import java.util.List;

public record Cart(List<Item> items) {

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(Item::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
