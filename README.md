# Order Quote Calculator - Candidate Coding Exercise

Welcome! This is a practical coding assessment designed to demonstrate your Java programming skills, design clarity, testing practices, and problem-solving rationale.

---

## 1. Exercise Overview

You are tasked with implementing the `OrderQuoteCalculatorService` interface to calculate price quotes for e-commerce orders.

Target duration: **90–120 minutes**. Incomplete submissions accompanied by structured notes in `NOTES.md` are completely acceptable.

---

## 2. Business Rules & Specifications

### Order Items & Constraints
* Each item has a `productId` (`String`), `unitPrice` (`BigDecimal`), and `quantity` (`int`).
* `quantity` must be a **positive integer** (`> 0`).
* `unitPrice` must be **non-negative** (`>= 0.00`) and have **at most two decimal places** (e.g. `10.50` is valid; `10.505` must be rejected).

### Customer Types & Discounts
* Customer types are `REGULAR` and `PREMIUM`.
* `PREMIUM` customers receive a **10% discount** on the item subtotal.
* Coupon code `SAVE10` gives a **10% discount**.
* **Discounts DO NOT STACK**: Apply exactly one 10% discount if the customer is `PREMIUM` OR if the coupon is `SAVE10`.
* A `null` or blank coupon means no coupon. Surrounding whitespace must be trimmed. Any other non-blank coupon string (e.g. `SAVE20`, `save10`) must be rejected.

### Delivery Charges
* Delivery is **FREE (`0.00`)** when the subtotal after discount is **at least `500.00`** (`>= 500.00`).
* Otherwise, delivery costs **`49.00`** (`< 500.00`).
* An **empty order** (zero items) returns **`0.00`** for subtotal, discount, delivery charge, and final total.

### Validation & Error Handling
Reject the request by throwing an `IllegalArgumentException` (or a clear descriptive subtype) for any of the following:
* `null` request object
* `null` items collection
* `null` item inside the collection
* Missing product identifier (`null` or blank after trim)
* Missing customer type (`null`)
* Invalid quantity (`<= 0`)
* Invalid price (`null`, negative, or > 2 decimal places)
* Invalid non-blank coupon code (not matching `SAVE10`)

### Precision & Rounding Rules
* Use `BigDecimal` for all monetary amounts.
* Calculate item subtotal = `sum(unitPrice * quantity)`.
* If a discount applies, round the discount **ONCE to two decimal places using `RoundingMode.HALF_UP`**.
* Discounted subtotal = `subtotal - discount`.
* Determine delivery eligibility based on `discountedSubtotal`.
* Final total = `discountedSubtotal + deliveryCharge`.
* Do NOT mutate the incoming request.

---

## 3. Published Examples

| Customer Type | Subtotal | Coupon | Subtotal After Discount | Delivery Charge | Final Total | Notes |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `REGULAR` | 200.00 | `null` | 200.00 | 49.00 | **249.00** | No discount; delivery applied |
| `PREMIUM` | 200.00 | `null` | 180.00 | 49.00 | **229.00** | 10% discount (20.00); delivery applied |
| `REGULAR` | 500.00 | `null` | 500.00 | 0.00 | **500.00** | No discount; free delivery |
| `PREMIUM` | 500.00 | `null` | 450.00 | 49.00 | **499.00** | 10% discount (50.00); discounted subtotal 450.00 < 500.00 so delivery 49.00 |
| `PREMIUM` | 200.00 | `SAVE10` | 180.00 | 49.00 | **229.00** | Discounts do not stack (still 10%) |

---

## 4. Submission & Expectations

### Tasks
1. Implement the business logic in `OrderQuoteCalculatorServiceImpl.java` (or refactor internal classes while maintaining the public interface contract `OrderQuoteCalculatorService`).
2. Write unit tests targeting isolated logic, validation, and boundary conditions.
3. Write acceptance tests demonstrating business scenarios through the public interface.
4. Fill out `NOTES.md` explaining your assumptions, design choices, trade-offs, and any unfinished work.

### Unit vs. Acceptance Tests Distinction
* **Unit Tests**: Focus on specific components, methods, validations, rounding edge cases, and error branches.
* **Acceptance Tests**: Verify end-to-end business outcomes through `OrderQuoteCalculatorService.calculateQuote(...)` using realistic request/response scenarios. No external servers or HTTP calls are required.

---

## 5. Build & Execution Commands

Requires Java 21+.

```bash
# Run tests
./mvnw test

# Run full build, coverage, static analysis, and mutation tests
./mvnw verify
```

> **Note**: Initial starter tests will fail until `OrderQuoteCalculatorServiceImpl` is implemented.

---
Good luck! We look forward to reviewing your solution.
