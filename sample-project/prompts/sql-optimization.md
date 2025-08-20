# SQL Optimization Templates

## Query Performance Analysis
Analyze this SQL query for performance issues and suggest improvements:
- Identify missing indexes that could improve query performance
- Check for N+1 query problems in JPA/Hibernate implementations
- Suggest proper JOIN strategies (INNER, LEFT, RIGHT) based on data relationships
- Validate WHERE clause efficiency and suggest index-friendly conditions
- Recommend query restructuring for better execution plans
- Identify opportunities for query result caching
- Suggest pagination strategies for large result sets

Example usage: "Analyze this user search query that's running slowly in production"

## Database Schema Review
Review this database schema design for optimization opportunities:
- Evaluate proper normalization levels (1NF, 2NF, 3NF) and denormalization opportunities
- Identify index optimization opportunities for frequently queried columns
- Review foreign key relationships and constraint efficiency
- Suggest data type optimization for storage and performance
- Recommend table partitioning strategies for large datasets
- Evaluate column ordering for optimal storage
- Suggest archiving strategies for historical data

Example usage: "Review the e-commerce database schema for performance optimization"

## JPA Query Optimization
Optimize this JPA/Hibernate query for better performance:
- Convert N+1 queries to batch fetching with JOIN FETCH
- Use @EntityGraph for optimized eager loading strategies
- Implement proper pagination with Pageable interface
- Add appropriate query hints for database-specific optimizations
- Suggest native query alternatives when JPQL is insufficient
- Optimize bulk operations with @Modifying queries
- Implement proper caching strategies with @Cacheable

Example usage: "Optimize this user profile query that loads related entities"

## Connection Pool Optimization
Optimize database connection pool configuration for:
- Proper pool sizing based on application load and database capacity
- Connection timeout and idle timeout configurations
- Connection validation and health check strategies
- Pool monitoring and alerting setup
- Connection leak detection and prevention
- Load balancing across multiple database instances
- Failover and retry strategies for connection failures

Example usage: "Optimize HikariCP configuration for high-traffic Spring Boot application"

## Bulk Operations Optimization
Optimize bulk database operations for:
- Batch insert operations with proper batch sizing
- Bulk update operations using @Modifying queries
- Efficient data migration strategies
- Bulk delete operations with proper cascading
- Transaction management for large operations
- Memory optimization for processing large datasets
- Progress tracking and error handling for long-running operations

Example usage: "Optimize bulk user import process that handles millions of records"

## Query Caching Strategy
Implement effective query caching that:
- Identifies cacheable queries based on access patterns
- Implements proper cache invalidation strategies
- Uses appropriate caching levels (first-level, second-level, query cache)
- Configures cache providers (Redis, Hazelcast, EhCache)
- Implements cache warming strategies
- Monitors cache hit ratios and performance metrics
- Handles cache consistency in distributed environments

Example usage: "Implement caching strategy for product catalog queries"

## Database Monitoring and Metrics
Implement database monitoring that:
- Tracks query execution times and identifies slow queries
- Monitors connection pool usage and health
- Implements database performance metrics collection
- Sets up alerting for performance degradation
- Tracks database resource utilization (CPU, memory, I/O)
- Monitors transaction deadlocks and blocking queries
- Implements query execution plan analysis

Example usage: "Set up comprehensive database monitoring for production Spring Boot application"

## Migration and Versioning
Create database migration strategies that:
- Use Flyway or Liquibase for version-controlled schema changes
- Implement zero-downtime migration strategies
- Handle data migration with proper validation
- Create rollback strategies for failed migrations
- Implement proper testing of migration scripts
- Handle schema changes in distributed environments
- Document migration procedures and dependencies

Example usage: "Create migration strategy for adding new user authentication features"

## Read/Write Splitting
Implement read/write database splitting that:
- Configures separate data sources for read and write operations
- Implements proper transaction routing based on operation type
- Handles replication lag and consistency requirements
- Implements failover strategies for read replicas
- Monitors replication health and performance
- Balances read load across multiple replicas
- Handles connection pooling for multiple data sources

Example usage: "Implement read/write splitting for high-traffic user management system"

## Database Security Optimization
Optimize database security while maintaining performance:
- Implement proper connection encryption without performance impact
- Use connection pooling with secure authentication
- Implement query parameter validation to prevent SQL injection
- Set up database access logging and monitoring
- Implement proper privilege management for application users
- Use database-level encryption for sensitive data
- Implement secure backup and recovery procedures

Example usage: "Secure database connections while maintaining optimal performance"