Simple Spring Boot Todo Application (no DB)
Features:
- Spring Boot + Thymeleaf UI
- Spring Security with in-memory users (user/password, admin/admin)
- Validation using Jakarta Validation annotations
- Global exception handling with @ControllerAdvice
- In-memory storage (Map) — no database required
How to run:
1. Ensure JDK 17+ and Maven are installed.
2. From project root, run: mvn spring-boot:run
3. Open http://localhost:8080 in your browser.
Credentials:
- user / password  (ROLE_USER)
- admin / admin    (ROLE_ADMIN)
