package com.assessment.quote.model;

import java.util.List;

public record OrderRequest(
    List<OrderItem> items,
    CustomerType customerType,
    String coupon
) {}
