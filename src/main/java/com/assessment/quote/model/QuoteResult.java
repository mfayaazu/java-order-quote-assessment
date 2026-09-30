package com.assessment.quote.model;

import java.math.BigDecimal;

public record QuoteResult(
    BigDecimal subtotal,
    BigDecimal discount,
    BigDecimal deliveryCharge,
    BigDecimal finalTotal
) {}
