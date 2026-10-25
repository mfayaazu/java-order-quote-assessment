package com.assessment.quote.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quotes")
public class QuoteEntity {

    @Id
    private String id;

    @Column(name = "customer_type", nullable = false)
    private String customerType;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    @Column(name = "delivery_charge", nullable = false)
    private BigDecimal deliveryCharge;

    @Column(name = "final_total", nullable = false)
    private BigDecimal finalTotal;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuoteItemEntity> items = new ArrayList<>();

    public QuoteEntity() {}

    public QuoteEntity(String id, String customerType, String couponCode, BigDecimal subtotal,
                       BigDecimal discount, BigDecimal deliveryCharge, BigDecimal finalTotal, LocalDateTime createdAt) {
        this.id = id;
        this.customerType = customerType;
        this.couponCode = couponCode;
        this.subtotal = subtotal;
        this.discount = discount;
        this.deliveryCharge = deliveryCharge;
        this.finalTotal = finalTotal;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public BigDecimal getDeliveryCharge() { return deliveryCharge; }
    public void setDeliveryCharge(BigDecimal deliveryCharge) { this.deliveryCharge = deliveryCharge; }

    public BigDecimal getFinalTotal() { return finalTotal; }
    public void setFinalTotal(BigDecimal finalTotal) { this.finalTotal = finalTotal; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<QuoteItemEntity> getItems() { return items; }
    public void setItems(List<QuoteItemEntity> items) { this.items = items; }

    public void addItem(QuoteItemEntity item) {
        items.add(item);
        item.setQuote(this);
    }
}
