package com.assessment.quote.api.dto;

import com.assessment.quote.model.CustomerType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record QuoteRequestDTO(
    @NotNull(message = "Items collection cannot be null")
    @Valid
    List<OrderItemDTO> items,

    @NotNull(message = "Customer type cannot be null")
    CustomerType customerType,

    String coupon
) {}
