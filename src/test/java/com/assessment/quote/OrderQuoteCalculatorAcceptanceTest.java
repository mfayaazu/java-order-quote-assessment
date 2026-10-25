package com.assessment.quote;

import com.assessment.quote.model.CustomerType;
import com.assessment.quote.model.OrderItem;
import com.assessment.quote.model.OrderRequest;
import com.assessment.quote.model.QuoteResult;
import com.assessment.quote.service.OrderQuoteCalculatorService;
import com.assessment.quote.service.OrderQuoteCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("acceptance")
class OrderQuoteCalculatorAcceptanceTest {

    private OrderQuoteCalculatorService calculator;

    @BeforeEach
    void setUp() {
        calculator = new OrderQuoteCalculatorServiceImpl();
    }

    @Test
    @DisplayName("Acceptance Scenario: REGULAR customer with subtotal 200.00 and no coupon should total 249.00")
    void scenarioRegularCustomer200NoCoupon() {
        OrderRequest request = new OrderRequest(
                List.of(new OrderItem("PROD-1", new BigDecimal("200.00"), 1)),
                CustomerType.REGULAR,
                null
        );

        QuoteResult quote = calculator.calculateQuote(request);

        assertThat(quote.subtotal()).isEqualByComparingTo("200.00");
        assertThat(quote.discount()).isEqualByComparingTo("0.00");
        assertThat(quote.deliveryCharge()).isEqualByComparingTo("49.00");
        assertThat(quote.finalTotal()).isEqualByComparingTo("249.00");
    }
}
