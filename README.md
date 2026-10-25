# Engineering Technical Assessment: Order Quote Calculator Service

Welcome! This production engineering assessment evaluates your skills in **Java 21, Spring Boot REST APIs, Relational Database Persistence (Flyway/JPA), Software Architecture, Docker Containerization, and Kubernetes Infrastructure Deployment**.

---

## 📋 Mission Briefing

You have been assigned to repair, complete, and refactor an existing microservice repository (`order-quote-calculator`). The repository contains skeletal starter code, incomplete feature implementations, and broken infrastructure configurations.

> **Note:** The starter code fails tests and the CI pipeline on purpose. Getting everything green is part of the assessment.

### Target Duration: **120–180 Minutes**
*(Document your assumptions, trade-offs, and architectural decisions in `NOTES.md`.)*

---

## ⚙️ Requirements & System Expectations

### 1. Business Domain & Calculation Logic
Implement `OrderQuoteCalculatorService.calculateQuote(OrderRequest request)` adhering strictly to domain rules:
* **Input Validation**: Reject `null` requests, `null` item collections, `null` elements in collection, missing product IDs (blank after trim), missing customer type (`null`), invalid quantities (`<= 0`), invalid unit prices (`null`, negative, or `> 2` decimal places), or unrecognized non-blank coupon codes by throwing an `IllegalArgumentException`.
* **Discount Eligibility**:
  * `PREMIUM` customer type receives a 10% discount on item subtotal.
  * Coupon code `SAVE10` receives a 10% discount on item subtotal (surrounding whitespace must be trimmed).
  * **Non-Stacking**: Maximum 10% discount total if either condition applies.
* **Delivery Fees**:
  * Free delivery (`0.00`) when subtotal *after* discount is `≥ 500.00`.
  * Otherwise, standard delivery fee is `49.00`.
* **Empty Orders**: Zero items in order returns `0.00` for subtotal, discount, delivery fee, and final total.
* **Precision**: Monetary calculations must use `BigDecimal`. Round discount **ONCE to 2 decimal places using `RoundingMode.HALF_UP`**.

### 2. REST API & Exception Handling (`POST /api/v1/quotes`)
* Ensure the REST API endpoint accepts valid JSON requests, delegates calculation to `OrderQuoteCalculatorService`, and returns a `200 OK` `QuoteResponseDTO`.
* Ensure invalid request payloads or business validation exceptions return a structured `400 Bad Request` JSON error response using Spring `@RestControllerAdvice`.

### 3. Database Persistence & Migrations
* Ensure Flyway database migration scripts initialize the required schema correctly.
* Persist calculated quotes and their items to the database via Spring Data JPA repositories with proper transaction boundary management.

### 4. Containerization & Deployment Infrastructure
* **Docker**: Ensure `Dockerfile` builds cleanly, packages the Spring Boot application efficiently, and exposes the application correctly.
* **Kubernetes**: Ensure all Kubernetes manifests in `k8s/` (`deployment.yaml`, `service.yaml`, `configmap.yaml`) are valid, route traffic properly, and define health probes and resource limits suitable for production.

---

## 🧪 Local Verification Commands

```bash
# Execute candidate test suite
./mvnw test

# Execute full build & code quality checks
./mvnw verify

# Build application container image
docker build -t order-quote-service:latest .

# Validate Kubernetes manifests
kubectl apply --dry-run=client -f k8s/
```

---

## 🔀 Git Workflow & Submission Options

You can complete and submit your assessment using either of the following two Git workflows:

### Option A: Fork & Pull Request Workflow (Recommended)

1. **Fork**: Click **Fork** at the top right of [https://github.com/mfayaazu/java-order-quote-assessment](https://github.com/mfayaazu/java-order-quote-assessment) to create a copy under your personal GitHub account.
2. **Clone**: Clone your fork to your local machine:
   ```bash
   git clone https://github.com/<your-username>/java-order-quote-assessment.git
   cd java-order-quote-assessment
   ```
3. **Develop & Commit**: Fix infrastructure issues, complete the implementation, write tests, and update `NOTES.md`. Push your commits to a branch or `main` on your fork.
4. **Create Pull Request**: Go to your fork on GitHub and click **New Pull Request** targeting `mfayaazu/java-order-quote-assessment:main`.
   * *Automated Evaluation*: Once opened, GitHub Actions will automatically trigger the evaluation pipeline.

---

### Option B: Clone & Standalone Repository Submission

1. **Clone Directly**:
   ```bash
   git clone https://github.com/mfayaazu/java-order-quote-assessment.git
   cd java-order-quote-assessment
   ```
2. **Develop**: Complete your implementation, fix Docker/K8s files, and document architectural decisions in `NOTES.md`.
3. **Push to Your GitHub**:
   * Create a new repository on your personal GitHub account (e.g. `java-order-quote-assessment-submission`).
   * Update remote origin and push your work:
     ```bash
     git remote set-url origin https://github.com/<your-username>/java-order-quote-assessment-submission.git
     git push -u origin main
     ```
4. **Submit Link**: Send your repository URL back to the hiring team (if private, grant read access to `@mfayaazu`).
