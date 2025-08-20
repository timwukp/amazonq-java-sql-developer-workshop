package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class demonstrating secure and optimized SQL patterns
 * This shows the \"after\" state that Amazon Q would suggest
 */
@Service
@Validated
public class OptimizedDatabaseService {
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * SECURE: Parameterized query preventing SQL injection
     * Amazon Q would suggest this as the secure alternative
     */
    public List<Map<String, Object>> searchUsersByName(@NotBlank String searchTerm) {
        String sql = \"\"\"
            SELECT u.id, u.first_name, u.last_name, u.email, u.active, d.name as department_name
            FROM users u
            LEFT JOIN departments d ON u.department_id = d.id
            WHERE (LOWER(u.first_name) LIKE LOWER(:searchPattern) 
                   OR LOWER(u.last_name) LIKE LOWER(:searchPattern))
            AND u.active = true
            ORDER BY u.last_name, u.first_name
            LIMIT 100
            \"\"\";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue(\"searchPattern\", \"%\" + searchTerm + \"%\");
            
        return namedParameterJdbcTemplate.queryForList(sql, params);
    }
    
    /**
     * SECURE: Parameterized query with input validation
     * Amazon Q would suggest this approach for dynamic sorting
     */
    public List<Map<String, Object>> getUsersByDepartment(
            @NotBlank String departmentName, 
            @NotBlank String sortOrder) {
        
        // Validate sort order to prevent injection
        if (!sortOrder.equalsIgnoreCase(\"ASC\") && !sortOrder.equalsIgnoreCase(\"DESC\")) {
            sortOrder = \"ASC\";
        }
        
        String sql = \"\"\"
            SELECT u.id, u.first_name, u.last_name, u.email, u.active, 
                   d.name as department_name, d.code as department_code
            FROM users u
            INNER JOIN departments d ON u.department_id = d.id
            WHERE d.name = :departmentName
            AND u.active = true
            ORDER BY u.last_name \"\"\" + sortOrder + \"\"\", u.first_name ASC
            \"\"\";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue(\"departmentName\", departmentName);
            
        return namedParameterJdbcTemplate.queryForList(sql, params);
    }
    
    /**
     * SECURE: Batch operation with proper parameterization
     * Amazon Q would suggest this for bulk operations
     */
    @Transactional
    public int updateUserDepartments(@NotNull List<Long> userIds, @Positive Long departmentId) {
        if (userIds.isEmpty()) {
            return 0;
        }
        
        String sql = \"\"\"
            UPDATE users 
            SET department_id = :departmentId, 
                last_modified_date = CURRENT_TIMESTAMP
            WHERE id IN (:userIds)
            AND active = true
            \"\"\";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue(\"departmentId\", departmentId)
            .addValue(\"userIds\", userIds);
            
        return namedParameterJdbcTemplate.update(sql, params);
    }
    
    /**
     * OPTIMIZED: Efficient query with proper indexing strategy
     * Amazon Q would suggest these optimizations
     */
    public List<Map<String, Object>> getInactiveUsersReport() {
        String sql = \"\"\"
            SELECT u.id, u.first_name, u.last_name, u.email, u.created_date, 
                   u.last_login_date, d.name as department_name
            FROM users u
            LEFT JOIN departments d ON u.department_id = d.id
            WHERE u.active = false
            AND (u.last_login_date IS NULL OR u.last_login_date < :cutoffDate)
            ORDER BY u.last_login_date DESC NULLS LAST, u.created_date DESC
            LIMIT 1000
            \"\"\";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue(\"cutoffDate\", java.time.LocalDateTime.now().minusDays(90));
            
        return namedParameterJdbcTemplate.queryForList(sql, params);
    }
    
    /**
     * SECURE: Proper authentication without exposing sensitive data
     * Amazon Q would suggest this secure approach
     */
    public Optional<Map<String, Object>> getUserForAuthentication(@NotBlank String email) {
        String sql = \"\"\"
            SELECT id, email, password_hash, active, failed_login_attempts, 
                   account_locked_until, last_login_date
            FROM users 
            WHERE LOWER(email) = LOWER(:email)
            AND active = true
            \"\"\";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue(\"email\", email);
            
        try {
            Map<String, Object> user = namedParameterJdbcTemplate.queryForMap(sql, params);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    
    /**
     * OPTIMIZED: Efficient data export with streaming and pagination
     * Amazon Q would suggest this approach for large datasets
     */
    public List<Map<String, Object>> exportUserDataPaginated(int offset, int limit) {
        String sql = \"\"\"
            SELECT u.id, u.first_name, u.last_name, u.email, u.active, 
                   u.created_date, d.name as department_name
            FROM users u
            LEFT JOIN departments d ON u.department_id = d.id
            ORDER BY u.id
            OFFSET :offset LIMIT :limit
            \"\"\";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue(\"offset\", offset)
            .addValue(\"limit\", limit);
            
        return namedParameterJdbcTemplate.queryForList(sql, params);
    }
    
    /**
     * SECURE: Input validation and proper transaction management
     * Amazon Q would suggest this comprehensive approach
     */
    @Transactional
    public void createUser(
            @NotBlank String firstName, 
            @NotBlank String lastName, 
            @NotBlank String email, 
            @Positive Long departmentId) {
        
        // Validate department exists
        String deptCheckSql = \"SELECT COUNT(*) FROM departments WHERE id = :departmentId AND active = true\";
        MapSqlParameterSource deptParams = new MapSqlParameterSource()
            .addValue(\"departmentId\", departmentId);
        
        Integer deptCount = namedParameterJdbcTemplate.queryForObject(deptCheckSql, deptParams, Integer.class);
        if (deptCount == null || deptCount == 0) {
            throw new IllegalArgumentException(\"Invalid department ID: \" + departmentId);
        }
        
        // Check for duplicate email
        String emailCheckSql = \"SELECT COUNT(*) FROM users WHERE LOWER(email) = LOWER(:email)\";
        MapSqlParameterSource emailParams = new MapSqlParameterSource()
            .addValue(\"email\", email);
        
        Integer emailCount = namedParameterJdbcTemplate.queryForObject(emailCheckSql, emailParams, Integer.class);
        if (emailCount != null && emailCount > 0) {
            throw new IllegalArgumentException(\"User with email already exists: \" + email);
        }
        
        // Insert new user
        String insertSql = \"\"\"
            INSERT INTO users (first_name, last_name, email, department_id, active, created_date, last_modified_date)
            VALUES (:firstName, :lastName, :email, :departmentId, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            \"\"\";
        
        MapSqlParameterSource insertParams = new MapSqlParameterSource()
            .addValue(\"firstName\", firstName.trim())
            .addValue(\"lastName\", lastName.trim())
            .addValue(\"email\", email.toLowerCase().trim())
            .addValue(\"departmentId\", departmentId);
            
        namedParameterJdbcTemplate.update(insertSql, insertParams);
    }
    
    /**
     * SECURE: Proper transaction management with rollback capability
     * Amazon Q would suggest this transactional approach
     */
    @Transactional
    public void transferUserBetweenDepartments(
            @Positive Long userId, 
            @Positive Long fromDeptId, 
            @Positive Long toDeptId) {
        
        // Validate user exists and is in the expected department
        String validateSql = \"\"\"
            SELECT COUNT(*) FROM users 
            WHERE id = :userId AND department_id = :fromDeptId AND active = true
            \"\"\";
        
        MapSqlParameterSource validateParams = new MapSqlParameterSource()
            .addValue(\"userId\", userId)
            .addValue(\"fromDeptId\", fromDeptId);
        
        Integer userCount = namedParameterJdbcTemplate.queryForObject(validateSql, validateParams, Integer.class);
        if (userCount == null || userCount == 0) {
            throw new IllegalArgumentException(\"User not found in expected department\");
        }
        
        // Validate target department exists
        String deptValidateSql = \"SELECT COUNT(*) FROM departments WHERE id = :toDeptId AND active = true\";
        MapSqlParameterSource deptParams = new MapSqlParameterSource()
            .addValue(\"toDeptId\", toDeptId);
        
        Integer deptCount = namedParameterJdbcTemplate.queryForObject(deptValidateSql, deptParams, Integer.class);
        if (deptCount == null || deptCount == 0) {
            throw new IllegalArgumentException(\"Target department not found or inactive\");
        }
        
        // Update user department
        String updateUserSql = \"\"\"
            UPDATE users 
            SET department_id = :toDeptId, last_modified_date = CURRENT_TIMESTAMP
            WHERE id = :userId
            \"\"\";
        
        MapSqlParameterSource updateParams = new MapSqlParameterSource()
            .addValue(\"userId\", userId)
            .addValue(\"toDeptId\", toDeptId);
        
        namedParameterJdbcTemplate.update(updateUserSql, updateParams);
        
        // Log the transfer
        String logTransferSql = \"\"\"
            INSERT INTO department_transfers (user_id, from_department_id, to_department_id, transfer_date, created_by)
            VALUES (:userId, :fromDeptId, :toDeptId, CURRENT_TIMESTAMP, 'SYSTEM')
            \"\"\";
        
        MapSqlParameterSource logParams = new MapSqlParameterSource()
            .addValue(\"userId\", userId)
            .addValue(\"fromDeptId\", fromDeptId)
            .addValue(\"toDeptId\", toDeptId);
        
        namedParameterJdbcTemplate.update(logTransferSql, logParams);
    }
    
    /**
     * OPTIMIZED: Complex analytics query with proper performance considerations
     * Amazon Q would suggest these optimization techniques
     */
    public List<Map<String, Object>> getDepartmentAnalyticsOptimized() {
        String sql = \"\"\"
            WITH department_stats AS (
                SELECT 
                    d.id,
                    d.name,
                    COUNT(u.id) as total_users,
                    COUNT(CASE WHEN u.active = true THEN 1 END) as active_users,
                    COUNT(CASE WHEN u.last_login_date > CURRENT_DATE - INTERVAL '30 days' THEN 1 END) as recent_active_users,
                    AVG(EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - u.created_date))/86400) as avg_tenure_days
                FROM departments d
                LEFT JOIN users u ON d.id = u.department_id
                WHERE d.active = true
                GROUP BY d.id, d.name
            )
            SELECT 
                ds.*,
                CASE 
                    WHEN ds.total_users > 0 
                    THEN ROUND((ds.active_users::numeric / ds.total_users) * 100, 2)
                    ELSE 0 
                END as active_percentage,
                CASE 
                    WHEN ds.active_users > 0 
                    THEN ROUND((ds.recent_active_users::numeric / ds.active_users) * 100, 2)
                    ELSE 0 
                END as recent_activity_percentage
            FROM department_stats ds
            ORDER BY ds.active_users DESC, ds.name
            \"\"\";
        
        return namedParameterJdbcTemplate.queryForList(sql, new MapSqlParameterSource());
    }
}