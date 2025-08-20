# Security Guidelines

## Input Validation Requirements
- Validate all user inputs using Bean Validation annotations
- Sanitize data before database operations and external API calls
- Use whitelist validation for user inputs when possible
- Implement proper size limits for string inputs
- Validate file uploads for type, size, and content
- Never trust client-side validation alone

## Authentication and Authorization
- Implement proper authentication mechanisms (JWT, OAuth2)
- Use @PreAuthorize for method-level security
- Implement role-based access control (RBAC)
- Use Spring Security for authentication and authorization
- Implement proper session management
- Use secure password hashing (BCrypt with proper salt rounds)

## Database Security
- Never concatenate user input directly into SQL queries
- Use parameterized queries or prepared statements
- Implement proper access controls at database level
- Use database connection pooling with authentication
- Encrypt sensitive data at rest and in transit
- Log security-related events for audit purposes

## API Security Standards
- Use HTTPS for all communications
- Implement proper CORS configuration
- Use API rate limiting to prevent abuse
- Implement request size limits
- Validate Content-Type headers
- Use proper HTTP status codes for security responses

## Secrets Management
- Never hardcode passwords, API keys, or tokens in source code
- Use environment variables or external secret management
- Rotate secrets regularly
- Use AWS Secrets Manager or similar services for production
- Implement proper secret access logging
- Use different secrets for different environments

## Error Handling Security
- Never expose sensitive information in error messages
- Log security exceptions with appropriate detail
- Use generic error messages for client responses
- Implement proper exception handling hierarchy
- Avoid stack trace exposure in production

## Example Security Implementations

### Secure Password Handling
```java
@Service
public class AuthenticationService {
    
    private final PasswordEncoder passwordEncoder;
    
    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    /**
     * Securely hashes a password using BCrypt
     * @param rawPassword the plain text password
     * @return hashed password
     */
    public String hashPassword(@NotBlank String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    
    /**
     * Verifies a password against its hash
     * @param rawPassword the plain text password
     * @param hashedPassword the stored hash
     * @return true if password matches
     */
    public boolean verifyPassword(@NotBlank String rawPassword, @NotBlank String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
```

### Secure API Endpoint
```java
@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('USER')")
@Validated
public class SecureUserController {
    
    /**
     * Securely retrieves user profile with proper authorization
     * @param userId the user ID to retrieve
     * @param authentication the current user's authentication
     * @return user profile data
     */
    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public ResponseEntity<UserProfileDto> getUserProfile(
            @PathVariable @Min(1) Long userId,
            Authentication authentication) {
        
        try {
            UserProfileDto profile = userService.getUserProfile(userId);
            return ResponseEntity.ok(profile);
            
        } catch (UserNotFoundException e) {
            // Don't expose whether user exists to unauthorized users
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
            
        } catch (AccessDeniedException e) {
            log.warn("Unauthorized access attempt to user profile: {} by user: {}", 
                userId, authentication.getName());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }
}
```

### Secure Database Configuration
```java
@Configuration
public class SecureDatabaseConfig {
    
    @Value("${app.datasource.url}")
    private String datasourceUrl;
    
    @Value("${app.datasource.username}")
    private String datasourceUsername;
    
    // Use environment variable or secret manager
    @Value("${app.datasource.password}")
    private String datasourcePassword;
    
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(datasourceUrl);
        config.setUsername(datasourceUsername);
        config.setPassword(datasourcePassword);
        
        // Security configurations
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setLeakDetectionThreshold(60000);
        
        // Enable SSL
        config.addDataSourceProperty("useSSL", "true");
        config.addDataSourceProperty("requireSSL", "true");
        config.addDataSourceProperty("verifyServerCertificate", "true");
        
        return new HikariDataSource(config);
    }
}
```

## Logging Security Events
- Log all authentication attempts (success and failure)
- Log authorization failures with context
- Log sensitive data access attempts
- Log configuration changes
- Log security exception occurrences
- Use structured logging for security events

## Security Testing Requirements
- Test authentication and authorization mechanisms
- Perform input validation testing
- Test for SQL injection vulnerabilities
- Test for XSS vulnerabilities
- Verify proper error handling
- Test rate limiting and throttling mechanisms