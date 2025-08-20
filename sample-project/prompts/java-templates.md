# Java Development Templates

## REST API Controller Template
Create a Spring Boot REST controller with the following requirements:
- CRUD operations for [ENTITY_NAME]
- Proper HTTP status codes (200, 201, 400, 404, 409, 500)
- Input validation using @Valid and Bean Validation
- Exception handling with proper error responses
- Swagger/OpenAPI documentation annotations
- Logging at appropriate levels (DEBUG for method entry/exit, INFO for business events, WARN/ERROR for exceptions)
- Constructor injection for dependencies
- Proper security annotations (@PreAuthorize where needed)

Example usage: "Create a REST controller for Product entity with inventory management capabilities"

## Service Layer Template
Generate a service class that:
- Implements business logic for [DOMAIN_OBJECT]
- Uses repository pattern for data access
- Includes proper transaction management with @Transactional
- Has comprehensive error handling and custom exceptions
- Includes debug and info level logging with contextual information
- Follows single responsibility principle
- Uses Optional<T> for nullable return types
- Implements proper validation of input parameters
- Has comprehensive unit tests with Mockito

Example usage: "Create a service class for Order processing with payment validation and inventory checks"

## JPA Repository Template
Create a JPA repository that:
- Extends JpaRepository<Entity, ID>
- Includes custom query methods with proper naming conventions
- Uses @Query for complex operations with named parameters
- Implements pagination and sorting capabilities
- Has proper parameter validation with Bean Validation
- Uses JOIN FETCH to avoid N+1 query problems
- Includes bulk operations with @Modifying where appropriate
- Has integration tests with @DataJpaTest

Example usage: "Create a repository for Customer entity with search, filtering, and analytics queries"

## Entity/Model Template
Generate a JPA entity that:
- Uses proper JPA annotations (@Entity, @Table, @Id, etc.)
- Implements proper equals() and hashCode() methods
- Has comprehensive validation annotations
- Uses appropriate data types and constraints
- Includes audit fields (createdDate, lastModifiedDate, createdBy, lastModifiedBy)
- Has proper relationship mappings (@OneToMany, @ManyToOne, etc.)
- Implements Serializable if needed
- Has builder pattern for object creation

Example usage: "Create a User entity with profile information, roles, and audit tracking"

## DTO and Mapper Template
Create Data Transfer Objects and mappers that:
- Separate internal entity structure from API contracts
- Use proper validation annotations for API inputs
- Implement MapStruct mappers for entity-DTO conversion
- Have separate DTOs for create, update, and response operations
- Include proper documentation for all fields
- Handle nested object mapping appropriately
- Implement proper null handling

Example usage: "Create DTOs and mappers for User entity with role management and profile updates"

## Exception Handling Template
Generate comprehensive exception handling that:
- Creates custom exception classes extending appropriate base exceptions
- Implements @ControllerAdvice for global exception handling
- Returns proper HTTP status codes and error responses
- Logs exceptions with appropriate levels and context
- Handles validation errors with field-specific messages
- Implements proper error response DTOs
- Handles database constraint violations appropriately

Example usage: "Create exception handling for e-commerce application with order, payment, and inventory exceptions"

## Configuration Template
Create Spring configuration classes that:
- Use @Configuration annotation appropriately
- Implement proper bean definitions with @Bean
- Handle environment-specific configurations
- Use @ConfigurationProperties for complex configurations
- Implement proper validation of configuration properties
- Include security configurations where needed
- Have proper documentation for all configuration options

Example usage: "Create database configuration with connection pooling, caching, and monitoring"

## Security Template
Generate security implementation that:
- Implements JWT token generation and validation
- Uses Spring Security for authentication and authorization
- Implements proper password hashing with BCrypt
- Has role-based access control with method-level security
- Includes rate limiting and account lockout mechanisms
- Implements proper CORS configuration
- Has comprehensive security testing

Example usage: "Create authentication system with JWT, role-based access, and password reset functionality"

## Testing Template
Create comprehensive test suites that:
- Include unit tests with JUnit 5 and Mockito
- Have integration tests with @SpringBootTest
- Use @DataJpaTest for repository testing
- Include @WebMvcTest for controller testing
- Have proper test data setup and cleanup
- Use TestContainers for database integration tests
- Include performance and load testing scenarios
- Have proper assertion strategies and error validation

Example usage: "Create test suite for user management system with authentication, authorization, and data persistence testing"

## Microservice Template
Generate microservice implementation that:
- Follows Spring Boot best practices
- Implements proper health checks and metrics
- Has service discovery and load balancing
- Includes distributed tracing and logging
- Implements circuit breaker patterns
- Has proper API versioning and documentation
- Includes containerization with Docker
- Has proper configuration management

Example usage: "Create order processing microservice with payment integration and inventory management"