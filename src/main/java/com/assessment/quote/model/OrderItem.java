package com.assessment.quote.model;

import java.math.BigDecimal;

public record OrderItem(
    String productId,
    BigDecimal unitPrice,
    int quantity
) {}
