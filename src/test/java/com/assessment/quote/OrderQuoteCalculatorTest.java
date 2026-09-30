package com.assessment.quote;

import com.assessment.quote.model.CustomerType;
import com.assessment.quote.model.OrderItem;
import com.assessment.quote.model.OrderRequest;
import com.assessment.quote.service.OrderQuoteCalculatorService;
import com.assessment.quote.service.OrderQuoteCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Tag("unit")
class OrderQuoteCalculatorTest {

    private OrderQuoteCalculatorService calculator;

    @BeforeEach
    void setUp() {
        calculator = new OrderQuoteCalculatorServiceImpl();
    }

    @Test
    @DisplayName("Should reject null request with IllegalArgumentException")
    void shouldRejectNullRequest() {
        assertThatThrownBy(() -> calculator.calculateQuote(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
