package com.assessment.quote.service;

import com.assessment.quote.model.OrderRequest;
import com.assessment.quote.model.QuoteResult;

public interface OrderQuoteCalculatorService {
    /**
     * Calculates the price quote breakdown for an order request.
     *
     * @param request Incoming order details.
     * @return QuoteResult containing subtotal, discount, delivery charge, and final total.
     * @throws IllegalArgumentException if the request or any item fails validation rules.
     */
    QuoteResult calculateQuote(OrderRequest request);
}
