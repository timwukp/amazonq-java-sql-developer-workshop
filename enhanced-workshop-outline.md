# Enhanced Amazon Q Developer Workshop: Maximizing Java Developer Productivity with SQL Integration

## Workshop Overview
**Duration:** 60 minutes with live demonstrations  
**Target Audience:** Java developers working with SQL in their SDLC  
**Focus:** Amazon Q Developer IDE extension for VS Code - Advanced prompt engineering, security scanning, and DevSecOps integration

---

## Pre-Workshop Setup Requirements

### VS Code IDE Configuration
- VS Code with Amazon Q Developer extension installed and authenticated
- Sample Java project with Maven/Gradle structure
- Database connection examples (PostgreSQL/MySQL)
- Multi-file project structure for context demonstrations

### Project Structure Setup
```
sample-java-project/
├── .amazonq/
│   ├── rules/
│   │   ├── java-coding-standards.md
│   │   ├── sql-best-practices.md
│   │   └── security-guidelines.md
├── src/main/java/
│   ├── com/example/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── model/
│   └── resources/
│       └── application.properties
└── ~/.aws/amazonq/prompts/
    ├── java-templates.md
    ├── sql-optimization.md
    └── security-review.md
```

---

## Session Agenda

### Opening (5 minutes)
- Welcome and introductions
- Session objectives: Maximize productivity, ensure security, streamline SDLC
- Amazon Q Developer IDE extension value proposition for Java/SQL developers
- Live demo environment overview in VS Code

---

## Part 1: Advanced Prompt Engineering Techniques (20 minutes)

### 1.1 Project Rules for Consistent Standards (4 minutes)

**Live Demo Setup:** Show `.amazonq/rules/` folder structure in VS Code

#### Java Coding Standards Rule Example
**File:** `.amazonq/rules/java-coding-standards.md`
```markdown
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

## Example Implementation Pattern
```java
/**
 * Retrieves user information by ID
 * @param userId the unique identifier for the user
 * @return Optional containing user data if found, empty otherwise
 */
@Transactional(readOnly = true)
public Optional<User> findUserById(Long userId) {
    log.debug("Finding user by ID: {}", userId);
    return userRepository.findById(userId);
}
```
```

#### SQL Best Practices Rule Example
**File:** `.amazonq/rules/sql-best-practices.md`
```markdown
# SQL Best Practices

## Query Standards
- Always use parameterized queries to prevent SQL injection
- Use meaningful table and column aliases
- Avoid SELECT * in production code
- Use proper indexing strategies
- Include proper error handling for database operations
- Use connection pooling for database connections

## JPA/Hibernate Guidelines
- Use @Query annotation for complex queries
- Prefer JPQL over native SQL when possible
- Use pagination for large result sets
- Implement proper transaction boundaries
- Use @EntityGraph to avoid N+1 query problems

## Example Implementation Pattern
```java
@Query("SELECT u FROM User u WHERE u.email = :email AND u.active = true")
Optional<User> findActiveUserByEmail(@Param("email") String email);

@Query(value = "SELECT * FROM users u WHERE u.created_date >= :startDate", nativeQuery = true)
List<User> findUsersCreatedAfter(@Param("startDate") LocalDateTime startDate);
```
```

#### Security Guidelines Rule Example
**File:** `.amazonq/rules/security-guidelines.md`
```markdown
# Security Guidelines

## Input Validation
- Validate all user inputs using Bean Validation annotations
- Sanitize data before database operations
- Use whitelist validation for user inputs
- Implement proper authentication and authorization

## Database Security
- Never concatenate user input directly into SQL queries
- Use parameterized queries or prepared statements
- Implement proper access controls at database level
- Log security-related events for audit purposes

## Spring Security Integration
- Use @PreAuthorize for method-level security
- Implement proper CORS configuration
- Use HTTPS for all communications
- Implement proper session management
```

**Live Demo:** Show how rules are automatically applied when chatting with Amazon Q in VS Code

### 1.2 Prompt Library and Templates (4 minutes)

**Live Demo:** Show `~/.aws/amazonq/prompts/` folder and access via @Prompts in VS Code chat

#### Java Development Templates
**File:** `~/.aws/amazonq/prompts/java-templates.md`
```markdown
# Java Development Templates

## REST API Controller Template
Create a Spring Boot REST controller with the following requirements:
- CRUD operations for [ENTITY_NAME]
- Proper HTTP status codes (200, 201, 400, 404, 500)
- Input validation using @Valid and Bean Validation
- Exception handling with proper error responses
- Swagger/OpenAPI documentation annotations
- Logging at appropriate levels

## Service Layer Template
Generate a service class that:
- Implements business logic for [DOMAIN_OBJECT]
- Uses repository pattern for data access
- Includes proper transaction management with @Transactional
- Has comprehensive error handling and custom exceptions
- Includes debug and info level logging
- Follows single responsibility principle

## JPA Repository Template
Create a JPA repository that:
- Extends JpaRepository<Entity, ID>
- Includes custom query methods with proper naming
- Uses @Query for complex operations
- Implements pagination and sorting
- Has proper parameter validation
```

#### SQL Optimization Templates
**File:** `~/.aws/amazonq/prompts/sql-optimization.md`
```markdown
# SQL Optimization Templates

## Query Performance Analysis
Analyze this SQL query for performance issues and suggest improvements:
- Identify missing indexes
- Check for N+1 query problems
- Suggest proper JOIN strategies
- Validate WHERE clause efficiency
- Recommend query restructuring if needed

## Database Schema Review
Review this database schema design for:
- Proper normalization (1NF, 2NF, 3NF)
- Index optimization opportunities
- Foreign key relationships and constraints
- Data type optimization
- Table partitioning strategies for large datasets

## JPA Query Optimization
Optimize this JPA/Hibernate query for better performance:
- Convert N+1 queries to batch fetching
- Use @EntityGraph for eager loading
- Implement proper pagination
- Add query hints where appropriate
- Suggest native query alternatives if needed
```

**Live Demo:** Show accessing templates via @Prompts in Amazon Q chat within VS Code

### 1.3 Context-Aware Prompting with @ Commands (6 minutes)

**Live Demo Examples in VS Code Amazon Q Chat:**

#### Using @workspace for Full Project Context
```
@workspace Analyze the current Spring Boot application architecture and suggest improvements for:
1. Database connection pooling configuration in application.properties
2. Caching strategy implementation using Spring Cache
3. API rate limiting with Spring Security
4. Exception handling strategy across all controllers
5. Logging configuration and best practices
```

#### Using @files for Specific Context
```
@files UserController.java UserService.java UserRepository.java User.java
Review these user management files and:
1. Generate comprehensive integration tests
2. Identify potential security vulnerabilities
3. Suggest performance optimizations
4. Check for proper error handling patterns
5. Validate adherence to our coding standards
```

#### Using @folders for Directory-Level Context
```
@folders src/main/java/com/example/controller src/main/java/com/example/service
Analyze all controllers and services to:
1. Ensure consistent error handling patterns
2. Validate proper dependency injection usage
3. Check for missing validation annotations
4. Suggest common utility methods that could be extracted
```

#### Using @code for Method-Level Context
```
@code UserService.createUser UserService.updateUser
Generate comprehensive unit tests for these user management methods including:
- Valid input scenarios with different user types
- Invalid input validation testing
- Database constraint violation handling
- Concurrent access scenarios
- Transaction rollback testing
```

#### Using @images for Visual Context
```
@images database-schema.png api-flow-diagram.png
Based on these diagrams, generate:
1. JPA entity classes with proper relationships
2. Repository interfaces with custom queries
3. Service layer methods that follow the API flow
4. Integration tests that validate the complete workflow
```

**Live Demo:** Show how each @ command provides different levels of context to Amazon Q

### 1.4 Native AWS Integration Capabilities (4 minutes)

**Live Demo:** Show native AWS service integration within VS Code Amazon Q extension

#### AWS Documentation Integration
```
Help me implement AWS RDS connection pooling in my Spring Boot application following AWS best practices for:
- Connection pool sizing for production workloads
- SSL/TLS configuration for RDS connections
- Monitoring and metrics collection
- Failover and retry strategies
```

#### CloudWatch Integration Examples
```
Generate CloudWatch monitoring code for my Java application that:
- Tracks custom business metrics
- Monitors database connection pool health
- Logs application performance metrics
- Sets up alarms for critical thresholds
- Implements proper error tracking
```

#### Redshift Integration Examples
```
Create a data access layer for Amazon Redshift that:
- Uses JDBC with proper connection pooling
- Implements batch insert operations
- Handles large result set pagination
- Includes proper error handling and retries
- Follows Redshift query optimization best practices
```

#### RDS Integration Examples
```
Implement a robust RDS integration that:
- Configures multiple data sources (read/write replicas)
- Implements proper transaction management
- Handles connection failures gracefully
- Includes database health checks
- Supports both PostgreSQL and MySQL configurations
```

**Live Demo:** Show how Amazon Q provides AWS-specific guidance and code examples

### 1.5 Feature Development with /dev (2 minutes)

**Live Demo Example in VS Code:**
```
/dev Create a comprehensive user authentication system that includes:
- JWT token generation and validation
- Password hashing with BCrypt
- Rate limiting for login attempts
- Account lockout after failed attempts
- Password reset functionality via email
- Integration with Spring Security
- Comprehensive audit logging
- Unit and integration tests
```

**Live Demo:** Show multi-file code generation and iterative feedback process

---

## Part 2: Automated Security Scanning and Vulnerability Detection (15 minutes)

### 2.1 Built-in Code Review Features (10 minutes)

**Live Demo:** Show real-time security scanning in VS Code with prepared vulnerable code examples

#### SQL Injection Vulnerability Example
```java
// Vulnerable code example for live demo
@RestController
public class UserSearchController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String searchTerm) {
        // Amazon Q will detect SQL injection vulnerability here
        String sql = "SELECT * FROM users WHERE name LIKE '%" + searchTerm + "%'";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
}
```

**Amazon Q Detection and Remediation:**
- Identifies SQL injection vulnerability
- Suggests parameterized query solution
- Provides secure code alternative

#### Hardcoded Secrets Detection Example
```java
// Vulnerable code example for live demo
@Configuration
public class DatabaseConfig {
    
    // Amazon Q will detect hardcoded secrets
    @Value("${spring.datasource.password:mySecretPassword123}")
    private String dbPassword;
    
    private static final String API_KEY = "sk-1234567890abcdef";
    
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setPassword(dbPassword);
        return new HikariDataSource(config);
    }
}
```

**Amazon Q Detection and Remediation:**
- Identifies hardcoded secrets
- Suggests externalization to environment variables
- Recommends AWS Secrets Manager integration

#### Resource Leak Detection Example
```java
// Vulnerable code example for live demo
@Service
public class FileProcessingService {
    
    public String processFile(String filename) throws IOException {
        // Amazon Q will detect resource leak
        FileInputStream fis = new FileInputStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        
        // Missing proper resource cleanup
        return content.toString();
    }
}
```

**Amazon Q Detection and Remediation:**
- Identifies resource leak vulnerability
- Suggests try-with-resources pattern
- Provides secure implementation

#### Code Quality Issues Example
```java
// Code quality issues for live demo
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Amazon Q will suggest improvements
    public User createUser(User user) {
        if (user.getEmail() != null) {
            if (user.getEmail().contains("@")) {
                if (user.getName() != null && user.getName().length() > 0) {
                    User existingUser = userRepository.findByEmail(user.getEmail());
                    if (existingUser == null) {
                        return userRepository.save(user);
                    }
                }
            }
        }
        return null;
    }
}
```

**Amazon Q Suggestions:**
- Improve method structure and readability
- Add proper validation
- Use Optional instead of null returns
- Add proper exception handling

### 2.2 Review Types and Quotas (3 minutes)

**Live Demo in VS Code:**

#### Auto Reviews (Real-time scanning)
- **Limit:** 200 KB input/source limit
- **Trigger:** Automatic as you type
- **Coverage:** SQL injection, XSS, resource leaks, secrets detection
- **Demo:** Show real-time vulnerability highlighting

#### Full Project Reviews
- **Limit:** 500 MB input artifact size, 50 MB source code limit
- **Trigger:** Manual project-wide scan
- **Coverage:** Comprehensive security and quality analysis
- **Demo:** Initiate full project scan and show results

#### Security Scanning Categories
- **SAST (Static Application Security Testing):** Code vulnerabilities
- **Secrets Detection:** Hardcoded passwords, API keys, tokens
- **IaC Security:** Infrastructure as Code misconfigurations
- **Code Quality:** Performance, maintainability issues
- **SCA (Software Composition Analysis):** Third-party dependency vulnerabilities

### 2.3 Live Security Remediation Demonstration (2 minutes)

**Live Demo Process:**
1. **Identify:** Show Amazon Q detecting vulnerability in real-time
2. **Explain:** Amazon Q explains the security risk and impact
3. **Remediate:** Amazon Q provides secure code alternative
4. **Validate:** Show how the fix resolves the security issue

---

## Part 3: Integration with DevSecOps SDLC (10 minutes)

### 3.1 Git Command Generation for CodeCommit (5 minutes)

**Live Demo:** Natural language to Git commands using Amazon Q chat in VS Code

#### Common Git Scenarios:
```
User: "I need to check what files have changed since last commit"
Amazon Q Response:
```bash
# Check status of working directory
git status

# Show files changed since last commit
git diff --name-only HEAD~1

# Show detailed changes
git diff HEAD~1
```

User: "Show me the differences in my Java files from yesterday"
Amazon Q Response:
```bash
# Show Java files modified since yesterday
git log --since="yesterday" --name-only --pretty=format: -- "*.java" | sort -u

# Show actual differences in Java files
git diff HEAD~1 -- "*.java"

# Show commit history for Java files since yesterday
git log --since="yesterday" --oneline -- "*.java"
```

User: "I want to create a feature branch for user authentication"
Amazon Q Response:
```bash
# Create and switch to new feature branch
git checkout -b feature/user-authentication

# Push new branch to remote repository
git push -u origin feature/user-authentication

# Verify current branch
git branch -v
```

User: "How do I safely merge my feature branch back to main?"
Amazon Q Response:
```bash
# Switch to main branch
git checkout main

# Pull latest changes from remote
git pull origin main

# Merge feature branch (no fast-forward for clear history)
git merge --no-ff feature/user-authentication

# Push merged changes
git push origin main

# Clean up local feature branch (optional)
git branch -d feature/user-authentication
```
```

### 3.2 Code Review Integration (3 minutes)

**Live Demo:** Show integration with development workflow in VS Code

#### Pre-commit Security Scanning
- Show how Amazon Q scans code before commits
- Demonstrate blocking commits with critical vulnerabilities
- Show security issue resolution workflow

#### Automated Code Quality Checks
- Real-time code quality feedback
- Performance optimization suggestions
- Best practice recommendations

#### Documentation Generation
- Auto-generate README files
- Create API documentation
- Generate code comments and Javadoc

### 3.3 Database Migration and SQL Transformation (2 minutes)

**Live Demo:** Show SQL transformation capabilities in VS Code

#### Oracle to PostgreSQL Migration
```
/transform
```
- Select "SQL conversion" option
- Upload DMS schema metadata file
- Show automated SQL conversion process
- Review and accept generated changes

#### SQL Query Optimization
```
@files UserRepository.java
Optimize these SQL queries for better performance:
1. Identify N+1 query problems
2. Suggest proper indexing strategies
3. Recommend query restructuring
4. Provide pagination improvements
```

---

## Part 4: Real-World Development Scenario (15 minutes)

### 4.1 Setup (3 minutes)
**Live Demo Environment in VS Code:**
- Open existing Spring Boot project with Amazon Q extension
- Show project structure with `.amazonq/rules/` and prompt templates
- Display Amazon Q chat panel and available context options

### 4.2 Development Workflow (8 minutes)

#### Scenario: Building a User Management API with Database Integration

**Step 1: Project Understanding (2 minutes)**
```
@workspace Analyze this Spring Boot application and help me understand:
1. Current architecture patterns and design decisions
2. Database schema design and relationships
3. Security implementation and potential gaps
4. Performance considerations and optimization opportunities
5. Areas for improvement and modernization
```

**Step 2: Feature Implementation (3 minutes)**
```
/dev Create a comprehensive user management system with:
- User registration with email verification workflow
- Secure password reset functionality with time-limited tokens
- Role-based access control with hierarchical permissions
- Comprehensive audit logging for all user actions
- Integration with existing database schema and constraints
- RESTful API endpoints with proper HTTP status codes
- Input validation and error handling
- Unit and integration tests with high coverage
```

**Step 3: Database Integration Optimization (2 minutes)**
```
@files UserRepository.java UserService.java
Generate optimized database operations for:
- User search with pagination, sorting, and filtering
- Complex user analytics queries with proper joins
- Bulk user operations with batch processing
- Performance monitoring and query optimization
- Connection pool management and health checks
```

**Step 4: Security Enhancement (1 minute)**
```
@Prompts security-review
Review the generated code for security vulnerabilities and provide:
1. Security risk assessment
2. Vulnerability remediation suggestions
3. Best practice recommendations
4. Compliance considerations
```

### 4.3 Code Review and Security Validation (4 minutes)

**Live Demo Process:**

#### Automatic Security Scanning
- Show real-time vulnerability detection as code is generated
- Demonstrate different severity levels (Critical, High, Medium, Low)
- Show how Amazon Q explains each vulnerability

#### Code Quality Review
- Performance optimization suggestions
- Maintainability improvements
- AWS best practice recommendations
- Code structure and design pattern suggestions

#### SQL Security Validation
- Parameterized query verification
- SQL injection prevention validation
- Database access pattern review
- Connection security assessment

#### Remediation Workflow
- Accept/reject individual suggestions
- Batch apply security fixes
- Validate fixes resolve issues
- Generate security summary report

---

## Advanced Java and SQL Examples with Amazon Q Rules

### Java Spring Boot Example - Before and After Rules Application

#### Original Code (Triggers Multiple Rules)
```java
@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) {
        return userService.findById(Long.parseLong(id));
    }
}
```

#### Amazon Q Enhanced Code (Rules Applied)
```java
/**
 * REST controller for user management operations
 * Provides CRUD operations for user entities with comprehensive validation and error handling
 * 
 * @author Amazon Q Developer
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/users")
@Validated
@Slf4j
@Tag(name = "User Management", description = "APIs for managing user accounts")
public class UserController {
    
    private final UserService userService;
    
    /**
     * Constructor injection for better testability and immutability
     * @param userService the user service dependency
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Creates a new user in the system
     * @param createUserRequest the user data to create, must be valid
     * @return ResponseEntity containing the created user data
     * @throws UserAlreadyExistsException if user with email already exists
     * @throws ValidationException if user data is invalid
     */
    @PostMapping
    @Operation(summary = "Create new user", description = "Creates a new user account with validation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user data"),
        @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<UserDto> createUser(
            @Valid @RequestBody CreateUserRequest createUserRequest) {
        
        log.info("Creating new user with email: {}", createUserRequest.getEmail());
        
        try {
            UserDto createdUser = userService.createUser(createUserRequest);
            log.info("Successfully created user with ID: {}", createdUser.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
            
        } catch (UserAlreadyExistsException e) {
            log.warn("Attempt to create user with existing email: {}", createUserRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists", e);
            
        } catch (Exception e) {
            log.error("Error creating user with email: {}", createUserRequest.getEmail(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Failed to create user", e);
        }
    }
    
    /**
     * Retrieves a user by their unique identifier
     * @param id the user ID, must be a valid positive number
     * @return ResponseEntity containing the user data if found
     * @throws UserNotFoundException if user with given ID doesn't exist
     * @throws IllegalArgumentException if ID format is invalid
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves user information by unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID format"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> getUser(
            @PathVariable @Min(1) @NotNull Long id) {
        
        log.debug("Retrieving user with ID: {}", id);
        
        try {
            Optional<UserDto> user = userService.findById(id);
            
            if (user.isPresent()) {
                log.debug("Successfully retrieved user with ID: {}", id);
                return ResponseEntity.ok(user.get());
            } else {
                log.warn("User not found with ID: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            
        } catch (Exception e) {
            log.error("Error retrieving user with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Failed to retrieve user", e);
        }
    }
}
```

### SQL Optimization Example - Before and After

#### Original Repository (Triggers SQL Rules)
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.department.name = ?1")
    List<User> findUsersByDepartment(String departmentName);
    
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
```

#### Amazon Q Optimized Repository (Rules Applied)
```java
/**
 * Repository interface for User entity operations
 * Provides optimized database access methods with proper query strategies
 * 
 * @author Amazon Q Developer
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Finds active users by department name with optimized query
     * Uses JOIN FETCH to avoid N+1 query problem and includes pagination
     * @param departmentName the name of the department, cannot be null
     * @param pageable pagination parameters
     * @return Page of users in the specified department
     */
    @Query("""
        SELECT DISTINCT u FROM User u 
        JOIN FETCH u.department d 
        LEFT JOIN FETCH u.roles r
        WHERE d.name = :departmentName 
        AND u.active = true
        ORDER BY u.lastName, u.firstName
        """)
    Page<User> findActiveUsersByDepartmentOptimized(
        @Param("departmentName") @NotBlank String departmentName, 
        Pageable pageable);
    
    /**
     * Finds a user by email address with case-insensitive search
     * Returns Optional to handle null cases properly
     * @param email the user's email address, cannot be null or empty
     * @return Optional containing user if found, empty otherwise
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email) AND u.active = true")
    Optional<User> findActiveUserByEmailIgnoreCase(@Param("email") @Email @NotBlank String email);
    
    /**
     * Counts users by department for performance metrics
     * Optimized count query without unnecessary joins
     * @param departmentName the department name
     * @return count of active users in the department
     */
    @Query("SELECT COUNT(u) FROM User u JOIN u.department d WHERE d.name = :departmentName AND u.active = true")
    long countActiveUsersByDepartment(@Param("departmentName") String departmentName);
    
    /**
     * Finds users created within a date range with batch fetching
     * Uses @EntityGraph to optimize loading of related entities
     * @param startDate the start date for the range
     * @param endDate the end date for the range
     * @param pageable pagination parameters
     * @return Page of users created within the specified range
     */
    @EntityGraph(attributePaths = {"department", "roles"})
    @Query("""
        SELECT u FROM User u 
        WHERE u.createdDate BETWEEN :startDate AND :endDate 
        AND u.active = true
        ORDER BY u.createdDate DESC
        """)
    Page<User> findUsersCreatedBetweenDates(
        @Param("startDate") @NotNull LocalDateTime startDate,
        @Param("endDate") @NotNull LocalDateTime endDate,
        Pageable pageable);
    
    /**
     * Bulk update user status for administrative operations
     * Uses modifying query for better performance on bulk operations
     * @param userIds list of user IDs to update
     * @param active the new active status
     * @return number of users updated
     */
    @Modifying
    @Query("UPDATE User u SET u.active = :active, u.lastModifiedDate = CURRENT_TIMESTAMP WHERE u.id IN :userIds")
    int bulkUpdateUserStatus(@Param("userIds") List<Long> userIds, @Param("active") boolean active);
}
```

---

## Workshop Conclusion and Next Steps

### Key Takeaways
1. **Productivity Gains:** 40-60% faster development with proper prompt engineering in VS Code
2. **Security First:** Built-in vulnerability detection and real-time remediation
3. **Code Quality:** Consistent standards through project rules and automated suggestions
4. **SDLC Integration:** Seamless workflow from development to deployment within IDE

### Immediate Action Items
1. **Set up project rules** in `.amazonq/rules/` for your team's coding standards
2. **Create custom prompt templates** in `~/.aws/amazonq/prompts/` for common tasks
3. **Configure security scanning** to run automatically in your development workflow
4. **Train team members** on advanced @ command usage for better context
5. **Integrate with existing Git workflows** using natural language commands

### Best Practices Summary
- **Use specific, detailed prompts** for better code generation
- **Leverage @ commands** for appropriate context scope
- **Apply project rules consistently** across all team members
- **Review and accept security suggestions** proactively
- **Combine multiple features** (rules + prompts + context) for optimal results

### Resources for Continued Learning
- [Amazon Q Developer User Guide](https://docs.aws.amazon.com/amazonq/latest/qdeveloper-ug/)
- [Best Practices for Code Generation](https://docs.aws.amazon.com/prescriptive-guidance/latest/best-practices-code-generation/)
- [Security Scanning Documentation](https://docs.aws.amazon.com/amazonq/latest/qdeveloper-ug/security-scans.html)
- [VS Code Extension Documentation](https://docs.aws.amazon.com/amazonq/latest/qdeveloper-ug/q-in-IDE.html)

### Workshop Feedback and Q&A
- Questions about specific use cases
- Troubleshooting common issues
- Advanced configuration options
- Integration with existing development workflows