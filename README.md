# 💊 Pharmacy Stock Management System (V1)

A full-stack pharmacy stock management system developed as a personal portfolio project after completing my Bachelor's degree in Computer Science (Application Development).

The goal of this project is to demonstrate clean architecture, business rule implementation, security best practices, testing strategies, and DevOps integration in a real-world scenario.
---
## 🚀 Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- Flyway
- Spring Validation

### Frontend
- Angular
- Angular Material
- RxJS

### Testing
- JUnit 5
- Mockito
- Testcontainers (PostgreSQL integration testing)

### DevOps & Tools
- Docker & Docker Compose
- Git & GitHub
- GitHub Actions (CI/CD ready)
- Postman (API testing)

---

## 🔐 Authentication & Roles

JWT-based authentication with Role-Based Access Control (RBAC):

- **ADMIN**
  - Full access
  - Manage users

- **MANAGER**
  - Full access (except user management)

- **STAFF**
  - Read-only access
  - Manage stock movements
  - View alerts

---

## 📦 Main Features (V1)

### User Management
- Secure login (JWT)
- Role-based access control
- User activation/deactivation

### Medication Management
- CRUD operations
- Categories & internal locations
- Soft delete
- Pagination, filtering & sorting

### Suppliers & Customers
- CRUD operations
- Locality filtering
- Pagination & search

### Stock Management
- Stock movements:
  - IN (purchase)
  - OUT (sale)
  - ADJUST (inventory correction)
- Business rules:
  - Stock cannot go below zero
  - Automatic stock update
  - Full traceability

### Stock Alerts
- Automatic detection of low-stock medications

### Dashboard
- Total medications
- Low stock overview
- Top 5 most sold medications (last 30 days)
- IN vs OUT movement chart

---

## 🗄 Database Model
V1 includes 8 main tables:
- users
- customers
- suppliers
- categories
- locations
- medications
- medication_suppliers
- stock_movements
Designed to be extensible for future versions.
---
## 🐳 Running with Docker

### Prerequisites
- Docker & Docker Compose
- Java 17+
- Node.js (LTS)
### Start the application
```bash
docker compose up -d
