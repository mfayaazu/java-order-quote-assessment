package com.assessment.quote.api.dto;

import java.math.BigDecimal;

public record QuoteResponseDTO(
    BigDecimal subtotal,
    BigDecimal discount,
    BigDecimal deliveryCharge,
    BigDecimal finalTotal
) {}
