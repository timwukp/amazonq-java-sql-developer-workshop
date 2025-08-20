# Demo Script: Vulnerable Code Examples for Amazon Q Security Scanning

## Demo Flow Overview
This script provides step-by-step instructions for demonstrating Amazon Q Developer's security scanning capabilities in VS Code. Each example shows vulnerable code that Amazon Q will detect and provide remediation suggestions.

---

## Demo 1: SQL Injection Vulnerability (5 minutes)

### Setup
1. Open VS Code with Amazon Q Developer extension
2. Create new Java file: `UserSearchController.java`
3. Type the vulnerable code below

### Vulnerable Code to Type
```java
package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserSearchController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/search")
    public List<Map<String, Object>> searchUsers(@RequestParam String searchTerm) {
        // This will trigger Amazon Q SQL injection detection
        String sql = "SELECT * FROM users WHERE name LIKE '%" + searchTerm + "%'";
        return jdbcTemplate.queryForList(sql);
    }
    
    @GetMapping("/by-department")
    public List<Map<String, Object>> getUsersByDepartment(@RequestParam String dept) {
        // Another SQL injection vulnerability
        String query = "SELECT u.*, d.name as dept_name FROM users u " +
                      "JOIN departments d ON u.dept_id = d.id " +
                      "WHERE d.name = '" + dept + "'";
        return jdbcTemplate.queryForList(query);
    }
}
```

### Expected Amazon Q Detection
- **Vulnerability:** SQL Injection in string concatenation
- **Severity:** High/Critical
- **Suggestion:** Use parameterized queries with named parameters

### Demonstration Points
1. Show real-time detection as you type
2. Hover over the highlighted vulnerability
3. Show Amazon Q's explanation of the risk
4. Accept the suggested fix
5. Show the corrected code with parameterized queries

---

## Demo 2: Hardcoded Secrets Detection (4 minutes)

### Vulnerable Code to Type
```java
package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    
    // Amazon Q will detect these hardcoded secrets
    private static final String DB_PASSWORD = "mySecretPassword123";
    private static final String API_KEY = "sk-1234567890abcdef";
    private static final String JWT_SECRET = "myJwtSecretKey2023!";
    
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/mydb");
        config.setUsername("admin");
        config.setPassword(DB_PASSWORD); // Hardcoded password usage
        return new HikariDataSource(config);
    }
    
    public String getApiKey() {
        return API_KEY; // Direct exposure of API key
    }
}
```

### Expected Amazon Q Detection
- **Vulnerability:** Hardcoded secrets (passwords, API keys)
- **Severity:** High
- **Suggestion:** Use environment variables or external configuration

### Demonstration Points
1. Show detection of multiple secret types
2. Explain security risks of hardcoded secrets
3. Show suggested remediation with environment variables
4. Demonstrate proper externalization

---

## Demo 3: Resource Leak Detection (3 minutes)

### Vulnerable Code to Type
```java
package com.example.service;

import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileProcessingService {
    
    public String readFileContent(String filename) throws IOException {
        // Amazon Q will detect resource leak
        FileInputStream fis = new FileInputStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        
        // Missing proper resource cleanup - resource leak!
        return content.toString();
    }
    
    public void writeToFile(String filename, String data) throws IOException {
        // Another resource leak example
        FileWriter writer = new FileWriter(filename);
        writer.write(data);
        // Missing writer.close() - resource leak!
    }
}
```

### Expected Amazon Q Detection
- **Vulnerability:** Resource leak (unclosed streams)
- **Severity:** Medium
- **Suggestion:** Use try-with-resources pattern

### Demonstration Points
1. Show resource leak detection
2. Explain memory and performance implications
3. Show try-with-resources remediation
4. Demonstrate automatic resource management

---

## Demo 4: Input Validation Issues (4 minutes)

### Vulnerable Code to Type
```java
package com.example.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @PostMapping("/users/{userId}/role")
    public ResponseEntity<String> updateUserRole(
            @PathVariable String userId,  // No validation
            @RequestParam String role,    // No validation
            @RequestBody String permissions) { // No validation
        
        // Amazon Q will detect missing input validation
        
        // Direct usage without validation - security risk
        if (userId.equals("admin")) {
            return ResponseEntity.ok("Cannot modify admin user");
        }
        
        // No validation of role parameter
        updateRole(userId, role, permissions);
        
        return ResponseEntity.ok("Role updated successfully");
    }
    
    @GetMapping("/users/{userId}/data")
    public ResponseEntity<String> getUserData(@PathVariable String userId) {
        // No input validation - potential for injection attacks
        String query = "SELECT * FROM user_data WHERE user_id = " + userId;
        
        // Simulate database call
        return ResponseEntity.ok("User data for: " + userId);
    }
    
    private void updateRole(String userId, String role, String permissions) {
        // Simulate role update
        System.out.println("Updating role for user: " + userId);
    }
}
```

### Expected Amazon Q Detection
- **Vulnerability:** Missing input validation
- **Severity:** Medium/High
- **Suggestion:** Add validation annotations and proper sanitization

### Demonstration Points
1. Show missing validation detection
2. Explain injection attack risks
3. Show Bean Validation annotations
4. Demonstrate proper input sanitization

---

## Demo 5: Authentication/Authorization Issues (4 minutes)

### Vulnerable Code to Type
```java
package com.example.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/secure")
public class SecureController {
    
    // Amazon Q will detect missing security annotations
    @GetMapping("/admin/users")
    public ResponseEntity<String> getAllUsers() {
        // No authentication/authorization check!
        return ResponseEntity.ok("All users data");
    }
    
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        // Critical operation without proper authorization
        deleteUserFromDatabase(userId);
        return ResponseEntity.ok("User deleted");
    }
    
    @PostMapping("/transfer-funds")
    public ResponseEntity<String> transferFunds(
            @RequestParam String fromAccount,
            @RequestParam String toAccount,
            @RequestParam double amount) {
        
        // Financial operation without authentication/authorization
        processTransfer(fromAccount, toAccount, amount);
        return ResponseEntity.ok("Transfer completed");
    }
    
    private void deleteUserFromDatabase(String userId) {
        System.out.println("Deleting user: " + userId);
    }
    
    private void processTransfer(String from, String to, double amount) {
        System.out.println("Transferring " + amount + " from " + from + " to " + to);
    }
}
```

### Expected Amazon Q Detection
- **Vulnerability:** Missing authentication/authorization
- **Severity:** Critical
- **Suggestion:** Add Spring Security annotations

### Demonstration Points
1. Show detection of unsecured endpoints
2. Explain security risks of unprotected operations
3. Show @PreAuthorize and security annotations
4. Demonstrate proper role-based access control

---

## Demo Script Instructions

### Before Starting Demo
1. **Environment Setup:**
   - Ensure VS Code is open with Amazon Q Developer extension
   - Have a Spring Boot project structure ready
   - Clear any existing files to start fresh

2. **Demo Flow:**
   - Start with simple vulnerabilities (SQL injection)
   - Progress to more complex issues (authentication)
   - Show real-time detection as you type
   - Demonstrate remediation acceptance

### During Each Demo Section
1. **Type the vulnerable code** slowly to show real-time detection
2. **Pause when Amazon Q highlights issues** to explain the vulnerability
3. **Show the suggested fix** by hovering or clicking on the issue
4. **Accept the remediation** and show the corrected code
5. **Explain the security improvement** made by the fix

### Key Demonstration Points
- **Real-time Detection:** Show how Amazon Q detects issues as you type
- **Severity Levels:** Explain different severity levels (Critical, High, Medium, Low)
- **Comprehensive Coverage:** Show various vulnerability types
- **Practical Remediation:** Demonstrate how fixes improve security
- **Best Practices:** Explain why each fix follows security best practices

### Audience Interaction
- **Ask questions** about security practices in their current projects
- **Encourage hands-on practice** with their own code
- **Discuss integration** into their development workflow
- **Address concerns** about false positives or performance impact

### Wrap-up Points
- Amazon Q provides **comprehensive security scanning**
- **Real-time feedback** improves code quality during development
- **Automated remediation** suggestions save time and improve security
- **Integration with IDE** makes security a natural part of development workflow