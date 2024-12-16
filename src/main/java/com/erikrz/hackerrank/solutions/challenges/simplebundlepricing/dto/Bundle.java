package com.erikrz.hackerrank.solutions.challenges.simplebundlepricing.dto;

import java.math.BigDecimal;
import java.util.List;

public record Bundle (String name, List<Item> items, BigDecimal price){
}
