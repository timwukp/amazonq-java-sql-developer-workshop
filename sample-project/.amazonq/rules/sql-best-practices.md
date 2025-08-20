# SQL Best Practices

## Query Security Standards
- Always use parameterized queries to prevent SQL injection
- Never concatenate user input directly into SQL strings
- Use JPA @Query with named parameters (:paramName)
- Validate all input parameters before query execution
- Use whitelist validation for dynamic query components

## Performance Optimization Rules
- Avoid SELECT * in production code - specify required columns
- Use proper indexing strategies for WHERE clauses
- Implement pagination for large result sets using Pageable
- Use JOIN FETCH to avoid N+1 query problems
- Prefer batch operations for bulk data modifications

## JPA/Hibernate Guidelines
- Use @Query annotation for complex queries
- Prefer JPQL over native SQL when possible
- Use @EntityGraph to optimize entity loading
- Implement proper transaction boundaries with @Transactional
- Use @Modifying for UPDATE and DELETE operations
- Always specify query timeout for long-running operations

## Database Connection Management
- Use connection pooling (HikariCP recommended)
- Configure proper connection pool sizing
- Implement connection health checks
- Use read-only transactions for query operations
- Handle database connection failures gracefully

## Query Naming Conventions
- Use descriptive method names that indicate the query purpose
- Follow Spring Data JPA naming conventions
- Use findBy, countBy, deleteBy prefixes appropriately
- Include entity relationships in method names when joining

## Example Implementation Patterns

### Secure Parameterized Query
```java
@Query("SELECT u FROM User u WHERE u.email = :email AND u.active = true")
Optional<User> findActiveUserByEmail(@Param("email") String email);
```

### Optimized Query with JOIN FETCH
```java
@Query("""
    SELECT DISTINCT u FROM User u 
    JOIN FETCH u.department d 
    LEFT JOIN FETCH u.roles r
    WHERE d.name = :departmentName 
    AND u.active = true
    ORDER BY u.lastName, u.firstName
    """)
Page<User> findActiveUsersByDepartment(
    @Param("departmentName") String departmentName, 
    Pageable pageable);
```

### Bulk Update Operation
```java
@Modifying
@Transactional
@Query("UPDATE User u SET u.lastLoginDate = :loginDate WHERE u.id = :userId")
int updateLastLoginDate(@Param("userId") Long userId, @Param("loginDate") LocalDateTime loginDate);
```

### Native Query with Proper Mapping
```java
@Query(value = """
    SELECT u.id, u.email, u.first_name, u.last_name, 
           COUNT(o.id) as order_count
    FROM users u 
    LEFT JOIN orders o ON u.id = o.user_id 
    WHERE u.created_date >= :startDate
    GROUP BY u.id, u.email, u.first_name, u.last_name
    ORDER BY order_count DESC
    """, nativeQuery = true)
List<UserOrderSummary> findUserOrderSummary(@Param("startDate") LocalDateTime startDate);
```

## Error Handling Requirements
- Wrap database operations in try-catch blocks
- Log database errors with appropriate context
- Convert technical exceptions to business exceptions
- Implement retry logic for transient failures
- Provide meaningful error messages to clients

## Testing Standards
- Use @DataJpaTest for repository integration tests
- Test both positive and negative scenarios
- Verify query performance with large datasets
- Test transaction rollback scenarios
- Mock external dependencies in unit tests