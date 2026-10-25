package com.assessment.quote.service.strategy;

import com.assessment.quote.model.OrderRequest;
import java.math.BigDecimal;

public interface DiscountStrategy {
    boolean isApplicable(OrderRequest request);
    BigDecimal calculateDiscount(BigDecimal subtotal);
}
