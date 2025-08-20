# Security Review Templates

## Comprehensive Security Audit
Perform a comprehensive security review of this code/application:
- Identify potential SQL injection vulnerabilities in database queries
- Check for Cross-Site Scripting (XSS) vulnerabilities in web endpoints
- Analyze authentication and authorization mechanisms for weaknesses
- Review input validation and sanitization practices
- Identify hardcoded secrets, passwords, or API keys
- Check for proper error handling that doesn't expose sensitive information
- Analyze logging practices for security event tracking
- Review session management and token handling
- Identify potential privilege escalation vulnerabilities

Example usage: "Review this user management system for security vulnerabilities"

## Authentication Security Review
Review authentication implementation for security best practices:
- Analyze password hashing algorithms and salt usage
- Review JWT token generation, validation, and expiration handling
- Check for proper session management and timeout configurations
- Identify potential brute force attack vulnerabilities
- Review multi-factor authentication implementation
- Analyze account lockout and rate limiting mechanisms
- Check for proper logout and session invalidation
- Review password reset and recovery mechanisms

Example usage: "Review the authentication system for security weaknesses and compliance"

## Authorization Security Review
Analyze authorization mechanisms for proper access control:
- Review role-based access control (RBAC) implementation
- Check for proper method-level security annotations
- Analyze URL-based security configurations
- Identify potential privilege escalation paths
- Review resource-level access controls
- Check for proper handling of administrative functions
- Analyze API endpoint security configurations
- Review cross-tenant data access controls

Example usage: "Review authorization controls for multi-tenant application"

## Input Validation Security Review
Examine input validation and sanitization practices:
- Identify missing input validation on API endpoints
- Review file upload security and validation
- Check for proper SQL injection prevention measures
- Analyze XSS prevention in web applications
- Review parameter validation and type checking
- Identify potential command injection vulnerabilities
- Check for proper handling of special characters
- Review size limits and boundary validations

Example usage: "Review input validation for e-commerce checkout process"

## Database Security Review
Analyze database security implementation:
- Review SQL query construction for injection vulnerabilities
- Check database connection security and encryption
- Analyze database user privileges and access controls
- Review stored procedure and function security
- Check for proper transaction isolation levels
- Analyze database logging and audit trail implementation
- Review backup and recovery security measures
- Check for proper data encryption at rest

Example usage: "Review database security for financial transaction system"

## API Security Review
Examine REST API security implementation:
- Review API authentication and authorization mechanisms
- Check for proper CORS configuration and restrictions
- Analyze rate limiting and throttling implementations
- Review API versioning and backward compatibility security
- Check for proper HTTP method restrictions
- Analyze request/response header security
- Review API documentation security considerations
- Check for proper error handling in API responses

Example usage: "Review REST API security for customer data management system"

## Secrets Management Review
Analyze secrets and sensitive data handling:
- Identify hardcoded passwords, API keys, and tokens in source code
- Review environment variable usage for sensitive configuration
- Check for proper secrets rotation and management
- Analyze encryption key management practices
- Review database connection string security
- Check for proper handling of third-party API credentials
- Analyze certificate and SSL/TLS configuration management
- Review secrets access logging and monitoring

Example usage: "Review secrets management for cloud-deployed microservices"

## Error Handling Security Review
Examine error handling for security implications:
- Review error messages for information disclosure
- Check for proper exception handling and logging
- Analyze stack trace exposure in production environments
- Review custom error pages and responses
- Check for proper handling of security exceptions
- Analyze error logging for security event tracking
- Review client-side error handling security
- Check for proper timeout and resource exhaustion handling

Example usage: "Review error handling security for payment processing system"

## Logging and Monitoring Security Review
Analyze security logging and monitoring implementation:
- Review security event logging coverage and detail
- Check for proper log sanitization and data protection
- Analyze log storage security and access controls
- Review monitoring and alerting for security events
- Check for proper audit trail implementation
- Analyze log retention and archival policies
- Review centralized logging security configuration
- Check for real-time security monitoring capabilities

Example usage: "Review security logging and monitoring for compliance requirements"

## Third-Party Integration Security Review
Examine third-party service integration security:
- Review API integration authentication and authorization
- Check for proper validation of third-party responses
- Analyze data sharing and privacy implications
- Review third-party service availability and failover handling
- Check for proper handling of third-party service errors
- Analyze dependency security and vulnerability management
- Review third-party data processing and storage security
- Check for proper isolation of third-party integration code

Example usage: "Review security of payment gateway and shipping provider integrations"

## Compliance Security Review
Analyze code for regulatory compliance requirements:
- Review GDPR compliance for personal data handling
- Check PCI DSS compliance for payment card data
- Analyze HIPAA compliance for healthcare data
- Review SOX compliance for financial data
- Check for proper data retention and deletion policies
- Analyze consent management and user rights implementation
- Review data processing transparency and documentation
- Check for proper breach notification procedures

Example usage: "Review application for GDPR and PCI DSS compliance requirements"

## Cloud Security Review
Examine cloud deployment security configuration:
- Review AWS/Azure/GCP security group and network configurations
- Check for proper IAM roles and permissions
- Analyze encryption in transit and at rest
- Review container security and image scanning
- Check for proper secrets management in cloud environments
- Analyze monitoring and logging in cloud platforms
- Review backup and disaster recovery security
- Check for proper resource access controls and policies

Example usage: "Review AWS deployment security for production application"