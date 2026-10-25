package com.assessment.quote;

import com.assessment.quote.api.QuoteController;
import com.assessment.quote.api.dto.OrderItemDTO;
import com.assessment.quote.api.dto.QuoteRequestDTO;
import com.assessment.quote.model.CustomerType;
import com.assessment.quote.model.QuoteResult;
import com.assessment.quote.service.OrderQuoteCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuoteController.class)
class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderQuoteCalculatorService quoteService;

    @Test
    @DisplayName("POST /api/v1/quotes should calculate quote and return 200 OK")
    void shouldCalculateQuoteSuccessfully() throws Exception {
        QuoteResult mockResult = new QuoteResult(
                new BigDecimal("200.00"),
                new BigDecimal("20.00"),
                new BigDecimal("49.00"),
                new BigDecimal("229.00")
        );

        given(quoteService.calculateQuote(any())).willReturn(mockResult);

        QuoteRequestDTO request = new QuoteRequestDTO(
                List.of(new OrderItemDTO("PROD-1", new BigDecimal("200.00"), 1)),
                CustomerType.PREMIUM,
                null
        );

        mockMvc.perform(post("/api/v1/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subtotal").value(200.00))
                .andExpect(jsonPath("$.discount").value(20.00))
                .andExpect(jsonPath("$.deliveryCharge").value(49.00))
                .andExpect(jsonPath("$.finalTotal").value(229.00));
    }
}
