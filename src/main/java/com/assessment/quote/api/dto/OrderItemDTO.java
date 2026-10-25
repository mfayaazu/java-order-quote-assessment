package com.assessment.quote.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OrderItemDTO(
    @NotBlank(message = "Product ID cannot be null or blank")
    String productId,

    @NotNull(message = "Unit price cannot be null")
    @DecimalMin(value = "0.00", message = "Unit price cannot be negative")
    BigDecimal unitPrice,

    @Min(value = 1, message = "Quantity must be a positive integer greater than zero")
    int quantity
) {}
