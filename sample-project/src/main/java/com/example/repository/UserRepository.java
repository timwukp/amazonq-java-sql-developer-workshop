package com.example.repository;

import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity operations
 * This repository demonstrates various query patterns that Amazon Q can help optimize
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Basic query methods - Amazon Q can suggest optimizations
    
    /**
     * Find user by email - Amazon Q will suggest case-insensitive search
     * and proper Optional return type
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Find users by department name - Amazon Q will suggest JOIN optimization
     * and pagination for large result sets
     */
    List<User> findByDepartmentName(String departmentName);
    
    /**
     * Find active users - Amazon Q will suggest adding indexes
     */
    List<User> findByActiveTrue();
    
    // Custom queries that Amazon Q can help optimize
    
    /**
     * Basic query that Amazon Q will suggest optimizing with JOIN FETCH
     * to avoid N+1 query problems
     */
    @Query("SELECT u FROM User u WHERE u.department.name = :departmentName")
    List<User> findUsersByDepartment(@Param("departmentName") String departmentName);
    
    /**
     * Query that Amazon Q will suggest optimizing with proper pagination
     * and JOIN FETCH for related entities
     */
    @Query("SELECT u FROM User u WHERE u.active = true")
    List<User> findAllActiveUsers();
    
    /**
     * Search query that Amazon Q will suggest optimizing for performance
     * and adding proper case-insensitive search
     */
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:searchTerm% OR u.lastName LIKE %:searchTerm%")
    List<User> searchUsersByName(@Param("searchTerm") String searchTerm);
    
    /**
     * Date range query that Amazon Q will suggest optimizing with proper indexing
     * and pagination
     */
    @Query("SELECT u FROM User u WHERE u.createdDate BETWEEN :startDate AND :endDate")
    List<User> findUsersCreatedBetween(@Param("startDate") LocalDateTime startDate, 
                                      @Param("endDate") LocalDateTime endDate);
    
    /**
     * Complex query that Amazon Q will suggest breaking down or optimizing
     * with better JOIN strategies
     */
    @Query("SELECT u FROM User u WHERE u.department.name = :deptName AND u.active = true AND u.roles IS NOT EMPTY")
    List<User> findActiveUsersWithRolesInDepartment(@Param("deptName") String deptName);
    
    // Queries that demonstrate common performance issues Amazon Q can detect
    
    /**
     * Query with potential N+1 problem - Amazon Q will suggest JOIN FETCH
     */
    @Query("SELECT u FROM User u WHERE u.id IN :userIds")
    List<User> findUsersByIds(@Param("userIds") List<Long> userIds);
    
    /**
     * Count query that Amazon Q might suggest optimizing
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.department.name = :departmentName")
    long countUsersByDepartment(@Param("departmentName") String departmentName);
    
    /**
     * Native query that Amazon Q will analyze for security and performance
     */
    @Query(value = "SELECT * FROM users u WHERE u.email = ?1", nativeQuery = true)
    User findUserByEmailNative(String email);
    
    // Method names that Amazon Q will suggest improving
    
    /**
     * Method that Amazon Q will suggest renaming for clarity
     */
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    
    /**
     * Method that Amazon Q will suggest adding pagination to
     */
    List<User> findByDepartmentNameAndActiveTrue(String departmentName);
    
    /**
     * Method that Amazon Q will suggest optimizing with proper sorting
     */
    List<User> findByActiveTrueOrderByLastNameAsc();
}