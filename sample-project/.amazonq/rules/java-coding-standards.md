# Java Coding Standards

## Code Style Requirements
- All public methods must have Javadoc comments with @param and @return tags
- Use meaningful variable names (no single letters except for loop counters)
- All classes must follow PascalCase naming convention
- All methods must follow camelCase naming convention
- Maximum line length: 120 characters
- Use Optional<T> instead of returning null
- All exceptions must be logged with appropriate context

## Spring Boot Specific Rules
- All REST controllers must use @RestController annotation
- All service classes must be annotated with @Service
- All repository interfaces must extend JpaRepository
- Use @Transactional for methods that modify data
- All configuration properties must be externalized
- Use constructor injection instead of @Autowired field injection
- Implement proper exception handling with @ControllerAdvice

## Validation Requirements
- Use @Valid annotation for request body validation
- Implement Bean Validation annotations (@NotNull, @NotBlank, @Email, etc.)
- Validate path variables and request parameters
- Return proper HTTP status codes (200, 201, 400, 404, 409, 500)

## Logging Standards
- Use SLF4J with Logback for logging
- Include contextual information in log messages
- Use appropriate log levels (DEBUG, INFO, WARN, ERROR)
- Never log sensitive information (passwords, tokens, PII)
- Use structured logging with meaningful messages

## Example Implementation Pattern
```java
/**
 * Retrieves user information by ID
 * @param userId the unique identifier for the user
 * @return Optional containing user data if found, empty otherwise
 * @throws IllegalArgumentException if userId is null or negative
 */
@Transactional(readOnly = true)
public Optional<UserDto> findUserById(@NotNull @Min(1) Long userId) {
    log.debug("Finding user by ID: {}", userId);
    
    if (userId == null || userId <= 0) {
        log.warn("Invalid user ID provided: {}", userId);
        throw new IllegalArgumentException("User ID must be positive");
    }
    
    try {
        Optional<User> user = userRepository.findById(userId);
        return user.map(userMapper::toDto);
    } catch (Exception e) {
        log.error("Error finding user by ID: {}", userId, e);
        throw new UserServiceException("Failed to retrieve user", e);
    }
}
```

## Security Requirements
- Never concatenate user input directly into SQL queries
- Use parameterized queries or JPA criteria API
- Validate and sanitize all user inputs
- Implement proper authentication and authorization
- Use HTTPS for all API endpoints
- Implement rate limiting for public endpoints
- Log security-related events for audit purposes