# phasezerocatlog
PhaseZero Catalog Service
A Spring Boot microservice that manages a product catalog via REST APIs. Built to fulfill the PhaseZero Backend Java Practical Assignment requirements using in-memory H2 database and clean layered architecture.

# 🛠️ Tech Stack
Java 17
Spring Boot 3.3.x
Spring Web, Validation, Data JPA
H2 In-Memory Database (compliant with assignment rules)
Maven
# 🚀 How to Run
Clone the repository  git clone https://github.com/nageswarreddy934/phasezerocatlog.git
cd phasezerocatlog

Application starts at:
http://localhost:8080
(Optional) H2 Console (for debugging):
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:catalogdb
Username: sa
Password: (leave empty)
# 📦 Data Model
Each Product includes:

partNumber (String) – unique business key
partName (String) – stored in lowercase (normalized on save)
category (String)
price (double) – must be ≥ 0
stock (int) – must be ≥ 0
# ✅ Business Rules Enforced:

partName is auto-converted to lowercase
Duplicate partNumber → rejected (409 Conflict)
Negative price or stock → rejected (400 Bad Request)
# 🌐 API Endpoints
Endpoint	Description
Method	Endpoint	Description
`POST`	`/products`	Add a new product
`GET`	`/products`	List all products
`GET`	`/products/search?name={text}`	Search by `partName` (case-insensitive partial match)
`GET`	`/products?category={name}`	Filter by category (query parameter)
`GET`	`/products/sort`	Sort by price (ascending)
`GET`	`/products/sort/stock`	Sort by stock (ascending)
`GET`	`/products/inventory/value`	Total inventory value = Σ(price × stock)
# Example Requests
# 1. Add Product
http
POST /products
Content-Type: application/json

{
  "partNumber": "PZ-1001",
  "partName": "HYDRAULIC FILTER",
  "category": "Automotive",
  "price": 25.50,
  "stock": 100
}
# 2.Response created(201 OK)
{
  "id": 1,
  "partNumber": "PZ-1001",
  "partName": "hydraulic filter",
  "category": "Automotive",
  "price": 25.5,
  "stock": 100
}
# ERROR HANDLING
Scenario	HTTP Status	Response
Missing/empty field	400 Bad Request	{ "message": "partNumber must not be blank" }
Negative price/stock	400 Bad Request	{ "message": "Price and stock must be non-negative" }
Duplicate partNumber	409 Conflict	{ "message": "Product with partNumber already exists" }
<img width="271" height="97" alt="image" src="https://github.com/user-attachments/assets/4a1e6013-d984-4d1c-a22d-b7a1003e8bf7" />

# 🏗️ Design & Architecture
Layered Structure:
Controller: Handles HTTP requests/responses
Service: Enforces business rules (normalization, validation, uniqueness)
Repository: JPA-based data access (H2 in-memory)
Storage: H2 in-memory database (fully compliant with assignment §6)
Validation: Bean Validation (@NotBlank) + custom logic
# 📌 Why H2?

Meets the assignment’s “in-memory database such as H2” requirement
No external setup → evaluator can run instantly
Supports JPA, constraints, and SQL queries
🧪 Assumptions & Limitations
All string inputs (partNumber, partName, category) are non-null (validated via @NotBlank)
Category matching is case-sensitive
Data is ephemeral — lost on application restart (expected for in-memory storage)
No authentication or pagination (not required by assignment)
📬 Submission
This repository satisfies all requirements of the PhaseZero Backend Java Practical Assignment.

🔗 GitHub Link:
https://github.com/nageswarreddy934/phasezerocatlog

✨ Built with clarity, correctness, and compliance in mind.
