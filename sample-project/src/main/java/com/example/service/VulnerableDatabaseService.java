package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service class with vulnerable SQL patterns for Amazon Q security scanning demo
 * This class intentionally contains security vulnerabilities for demonstration purposes
 */
@Service
public class VulnerableDatabaseService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * SQL Injection Vulnerability #1 - String concatenation
     * Amazon Q will detect this as a critical security issue
     */
    public List<Map<String, Object>> searchUsersByName(String searchTerm) {
        // VULNERABLE: Direct string concatenation
        String sql = \"SELECT * FROM users WHERE first_name LIKE '%\" + searchTerm + \"%' OR last_name LIKE '%\" + searchTerm + \"%'\";
        return jdbcTemplate.queryForList(sql);
    }
    
    /**
     * SQL Injection Vulnerability #2 - Dynamic WHERE clause
     * Amazon Q will suggest parameterized queries
     */
    public List<Map<String, Object>> getUsersByDepartment(String departmentName, String sortOrder) {
        // VULNERABLE: Dynamic SQL construction
        String sql = \"SELECT u.*, d.name as dept_name FROM users u \" +
                    \"JOIN departments d ON u.department_id = d.id \" +
                    \"WHERE d.name = '\" + departmentName + \"' \" +
                    \"ORDER BY u.last_name \" + sortOrder;
        return jdbcTemplate.queryForList(sql);
    }
    
    /**
     * SQL Injection Vulnerability #3 - Dynamic table/column names
     * Amazon Q will flag this as extremely dangerous
     */
    public List<Map<String, Object>> getDataFromTable(String tableName, String columnName, String value) {
        // VULNERABLE: Dynamic table and column names
        String sql = \"SELECT * FROM \" + tableName + \" WHERE \" + columnName + \" = '\" + value + \"'\";
        return jdbcTemplate.queryForList(sql);
    }
    
    /**
     * SQL Injection Vulnerability #4 - IN clause construction
     * Amazon Q will suggest proper parameterization
     */
    public List<Map<String, Object>> getUsersByIds(String userIds) {
        // VULNERABLE: Direct string insertion in IN clause
        String sql = \"SELECT * FROM users WHERE id IN (\" + userIds + \")\";
        return jdbcTemplate.queryForList(sql);
    }
    
    /**
     * SQL Injection Vulnerability #5 - LIKE pattern injection
     * Amazon Q will detect pattern injection vulnerability
     */
    public List<Map<String, Object>> searchUsersWithPattern(String pattern) {
        // VULNERABLE: User-controlled LIKE pattern
        String sql = \"SELECT * FROM users WHERE email LIKE '\" + pattern + \"'\";
        return jdbcTemplate.queryForList(sql);
    }
    
    /**
     * Performance Issue #1 - N+1 Query Problem
     * Amazon Q will suggest batch loading or JOIN optimization
     */
    public void updateUserDepartments(List<Long> userIds, Long departmentId) {
        // INEFFICIENT: N+1 query problem
        for (Long userId : userIds) {
            String sql = \"UPDATE users SET department_id = \" + departmentId + \" WHERE id = \" + userId;
            jdbcTemplate.update(sql);
        }
    }
    
    /**
     * Performance Issue #2 - Missing indexes
     * Amazon Q will suggest adding indexes
     */
    public List<Map<String, Object>> getInactiveUsersReport() {
        // INEFFICIENT: Full table scan without proper indexes
        String sql = \"\"\"
            SELECT u.first_name, u.last_name, u.email, u.created_date, d.name as department
            FROM users u
            LEFT JOIN departments d ON u.department_id = d.id
            WHERE u.active = false
            AND u.last_login_date < NOW() - INTERVAL '90 days'
            ORDER BY u.created_date DESC
            \"\"\";
        return jdbcTemplate.queryForList(sql);
    }
    
    /**
     * Security Issue - Information Disclosure
     * Amazon Q will flag potential information leakage
     */
    public Map<String, Object> authenticateUser(String email, String password) {
        // VULNERABLE: Password in plain text query and potential timing attack
        String sql = \"SELECT id, email, password_hash, first_name, last_name FROM users WHERE email = '\" + email + \"'\";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        
        if (!results.isEmpty()) {
            Map<String, Object> user = results.get(0);
            // VULNERABLE: Direct password comparison (should use proper hashing)
            if (password.equals(user.get(\"password_hash\"))) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Resource Management Issue
     * Amazon Q will detect potential resource leaks
     */
    public String exportUserData(String format) {
        // VULNERABLE: No input validation and potential resource exhaustion
        String sql = \"SELECT * FROM users ORDER BY created_date\";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        
        StringBuilder export = new StringBuilder();
        for (Map<String, Object> user : users) {
            // INEFFICIENT: String concatenation in loop
            export.append(user.toString() + \"\\n\");
        }
        
        return export.toString();
    }
    
    /**
     * Privilege Escalation Vulnerability
     * Amazon Q will flag unauthorized data access
     */
    public List<Map<String, Object>> getAdminData(String userId) {
        // VULNERABLE: No authorization check
        String sql = \"SELECT * FROM admin_settings WHERE created_by = '\" + userId + \"'\";
        return jdbcTemplate.queryForList(sql);
    }
    
    /**
     * Data Validation Issue
     * Amazon Q will suggest input validation
     */
    public void createUser(String firstName, String lastName, String email, String departmentId) {
        // VULNERABLE: No input validation
        String sql = \"INSERT INTO users (first_name, last_name, email, department_id, active, created_date) \" +
                    \"VALUES ('\" + firstName + \"', '\" + lastName + \"', '\" + email + \"', \" + departmentId + \", true, NOW())\";
        jdbcTemplate.update(sql);
    }
    
    /**
     * Transaction Management Issue
     * Amazon Q will suggest proper transaction boundaries
     */
    public void transferUserBetweenDepartments(Long userId, Long fromDeptId, Long toDeptId) {
        // VULNERABLE: No transaction management for related operations
        String updateUser = \"UPDATE users SET department_id = \" + toDeptId + \" WHERE id = \" + userId;
        String logTransfer = \"INSERT INTO department_transfers (user_id, from_dept, to_dept, transfer_date) \" +
                           \"VALUES (\" + userId + \", \" + fromDeptId + \", \" + toDeptId + \", NOW())\";
        
        jdbcTemplate.update(updateUser);
        // If this fails, user is updated but transfer is not logged
        jdbcTemplate.update(logTransfer);
    }
}