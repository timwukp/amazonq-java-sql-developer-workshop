package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Repository with complex SQL queries for analytics and reporting
 * Demonstrates various SQL patterns that Amazon Q can help optimize
 */
@Repository
public interface UserAnalyticsRepository extends JpaRepository<User, Long> {
    
    // Complex JOIN queries that Amazon Q can optimize
    
    /**
     * Complex analytics query with multiple JOINs
     * Amazon Q will suggest optimization strategies
     */
    @Query(value = """
        SELECT 
            d.name as department_name,
            COUNT(u.id) as user_count,
            COUNT(CASE WHEN u.active = true THEN 1 END) as active_users,
            AVG(EXTRACT(YEAR FROM AGE(CURRENT_DATE, u.created_date))) as avg_tenure_years,
            COUNT(DISTINCT ur.role_id) as unique_roles
        FROM users u
        JOIN departments d ON u.department_id = d.id
        LEFT JOIN user_roles ur ON u.id = ur.user_id
        WHERE u.created_date >= :startDate
        GROUP BY d.id, d.name
        ORDER BY user_count DESC
        """, nativeQuery = true)
    List<Map<String, Object>> getDepartmentAnalytics(@Param("startDate") LocalDateTime startDate);
    
    /**
     * User activity report with window functions
     * Amazon Q will suggest performance improvements
     */
    @Query(value = """
        SELECT 
            u.id,
            u.first_name,
            u.last_name,
            u.email,
            u.last_login_date,
            d.name as department,
            RANK() OVER (PARTITION BY d.id ORDER BY u.last_login_date DESC) as login_rank,
            COUNT(*) OVER (PARTITION BY d.id) as dept_user_count
        FROM users u
        JOIN departments d ON u.department_id = d.id
        WHERE u.active = true
        AND u.last_login_date >= :sinceDate
        ORDER BY d.name, login_rank
        """, nativeQuery = true)
    List<Map<String, Object>> getUserActivityReport(@Param("sinceDate") LocalDateTime sinceDate);
    
    /**
     * Subquery example that Amazon Q might suggest optimizing with JOINs
     */
    @Query("""
        SELECT u FROM User u 
        WHERE u.department.id IN (
            SELECT d.id FROM Department d 
            WHERE d.name IN :departmentNames
        )
        AND u.id NOT IN (
            SELECT ur.user.id FROM UserRole ur 
            WHERE ur.role.name = 'INACTIVE'
        )
        """)
    List<User> findUsersInDepartmentsExcludingInactive(@Param("departmentNames") List<String> departmentNames);
    
    /**
     * Bulk update operation that Amazon Q will analyze for performance
     */
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.lastLoginDate = :loginDate WHERE u.id IN :userIds")
    int bulkUpdateLastLoginDate(@Param("userIds") List<Long> userIds, @Param("loginDate") LocalDateTime loginDate);
    
    /**
     * Complex aggregation query with HAVING clause
     */
    @Query(value = """
        SELECT 
            d.name as department,
            COUNT(u.id) as total_users,
            COUNT(CASE WHEN u.last_login_date > :recentDate THEN 1 END) as recent_logins,
            ROUND(
                COUNT(CASE WHEN u.last_login_date > :recentDate THEN 1 END) * 100.0 / COUNT(u.id), 
                2
            ) as activity_percentage
        FROM departments d
        LEFT JOIN users u ON d.id = u.department_id
        WHERE d.active = true
        GROUP BY d.id, d.name
        HAVING COUNT(u.id) > :minUsers
        ORDER BY activity_percentage DESC
        """, nativeQuery = true)
    List<Map<String, Object>> getDepartmentActivityStats(
        @Param("recentDate") LocalDateTime recentDate,
        @Param("minUsers") int minUsers
    );
    
    /**
     * Recursive CTE query (PostgreSQL specific)
     * Amazon Q will suggest alternatives for better compatibility
     */
    @Query(value = """
        WITH RECURSIVE user_hierarchy AS (
            SELECT u.id, u.first_name, u.last_name, u.department_id, 0 as level
            FROM users u
            WHERE u.email = :managerEmail
            
            UNION ALL
            
            SELECT u.id, u.first_name, u.last_name, u.department_id, uh.level + 1
            FROM users u
            JOIN user_hierarchy uh ON u.department_id = uh.department_id
            WHERE uh.level < 3
        )
        SELECT * FROM user_hierarchy
        ORDER BY level, last_name
        """, nativeQuery = true)
    List<Map<String, Object>> getUserHierarchy(@Param("managerEmail") String managerEmail);
    
    /**
     * Time-based partitioned query
     * Amazon Q will suggest indexing strategies
     */
    @Query(value = """
        SELECT 
            DATE_TRUNC('month', u.created_date) as month,
            COUNT(*) as new_users,
            COUNT(CASE WHEN u.active = true THEN 1 END) as active_new_users
        FROM users u
        WHERE u.created_date >= :startDate
        AND u.created_date < :endDate
        GROUP BY DATE_TRUNC('month', u.created_date)
        ORDER BY month
        """, nativeQuery = true)
    List<Map<String, Object>> getMonthlyUserGrowth(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    /**
     * Query with potential SQL injection vulnerability
     * Amazon Q will detect and suggest parameterization
     */
    @Query(value = "SELECT * FROM users WHERE email LIKE CONCAT('%', :searchTerm, '%')", nativeQuery = true)
    List<User> searchUsersByEmailUnsafe(@Param("searchTerm") String searchTerm);
    
    /**
     * Performance-heavy query that Amazon Q will suggest optimizing
     */
    @Query(value = """
        SELECT u.*, 
               (SELECT COUNT(*) FROM user_roles ur WHERE ur.user_id = u.id) as role_count,
               (SELECT d.name FROM departments d WHERE d.id = u.department_id) as dept_name,
               (SELECT MAX(r.name) FROM roles r 
                JOIN user_roles ur ON r.id = ur.role_id 
                WHERE ur.user_id = u.id) as primary_role
        FROM users u
        WHERE u.active = true
        ORDER BY u.last_name, u.first_name
        """, nativeQuery = true)
    List<Map<String, Object>> getUsersWithDetailsInefficient();
    
    /**
     * Batch insert simulation that Amazon Q will suggest optimizing
     */
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO user_audit_log (user_id, action, timestamp, details)
        SELECT u.id, 'LOGIN', :timestamp, CONCAT('User ', u.first_name, ' ', u.last_name, ' logged in')
        FROM users u
        WHERE u.id IN :userIds
        """, nativeQuery = true)
    int createBulkAuditEntries(@Param("userIds") List<Long> userIds, @Param("timestamp") LocalDateTime timestamp);
    
    /**
     * Query with missing indexes that Amazon Q will identify
     */
    @Query(value = """
        SELECT u.* FROM users u
        WHERE LOWER(u.first_name) LIKE LOWER(:pattern)
        OR LOWER(u.last_name) LIKE LOWER(:pattern)
        OR LOWER(u.email) LIKE LOWER(:pattern)
        ORDER BY u.created_date DESC
        """, nativeQuery = true)
    List<User> fullTextSearchUsers(@Param("pattern") String pattern);
}