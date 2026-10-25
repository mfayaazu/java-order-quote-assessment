package com.assessment.quote.api;

import com.assessment.quote.api.dto.OrderItemDTO;
import com.assessment.quote.api.dto.QuoteRequestDTO;
import com.assessment.quote.api.dto.QuoteResponseDTO;
import com.assessment.quote.model.OrderItem;
import com.assessment.quote.model.OrderRequest;
import com.assessment.quote.model.QuoteResult;
import com.assessment.quote.service.OrderQuoteCalculatorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {

    private final OrderQuoteCalculatorService quoteService;

    public QuoteController(OrderQuoteCalculatorService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<QuoteResponseDTO> calculateQuote(@Valid @RequestBody QuoteRequestDTO requestDTO) {
        List<OrderItem> items = requestDTO.items() != null ?
                requestDTO.items().stream()
                        .map(dto -> new OrderItem(dto.productId(), dto.unitPrice(), dto.quantity()))
                        .toList() : null;

        OrderRequest domainRequest = new OrderRequest(items, requestDTO.customerType(), requestDTO.coupon());
        QuoteResult result = quoteService.calculateQuote(domainRequest);

        QuoteResponseDTO responseDTO = new QuoteResponseDTO(
                result.subtotal(),
                result.discount(),
                result.deliveryCharge(),
                result.finalTotal()
        );

        return ResponseEntity.ok(responseDTO);
    }
}
