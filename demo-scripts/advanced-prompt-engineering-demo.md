# Demo Script: Advanced Prompt Engineering with Amazon Q Developer

## Demo Overview
This script demonstrates advanced prompt engineering techniques using Amazon Q Developer IDE extension in VS Code, focusing on Java and SQL development with project rules and context-aware prompting.

---

## Demo 1: Project Rules in Action (5 minutes)

### Setup
1. Open VS Code with the sample project
2. Ensure `.amazonq/rules/` folder is visible with the three rule files
3. Open Amazon Q chat panel

### Demo Steps

#### Step 1: Show Rules Toggle (1 minute)
1. Open Amazon Q chat
2. Point out the "Rules" button in the chat interface
3. Show that rules are currently enabled
4. Briefly explain the three rule files:
   - `java-coding-standards.md`
   - `sql-best-practices.md`
   - `security-guidelines.md`

#### Step 2: Create Code Without Rules (2 minutes)
1. **Disable rules** by clicking the Rules button
2. Ask Amazon Q to create a simple controller:

```
Create a REST controller for user management with basic CRUD operations
```

3. **Show the generated code** - it will be basic without following the established standards
4. Point out missing elements:
   - No comprehensive Javadoc
   - Basic error handling
   - No validation annotations
   - Simple structure

#### Step 3: Create Code With Rules Enabled (2 minutes)
1. **Enable rules** by clicking the Rules button again
2. Ask the same question:

```
Create a REST controller for user management with basic CRUD operations
```

3. **Show the enhanced generated code** that now follows all the rules:
   - Comprehensive Javadoc comments
   - Proper validation annotations
   - Constructor injection
   - Proper exception handling
   - Security annotations
   - Logging statements
   - Proper HTTP status codes

#### Key Demonstration Points
- Rules automatically apply to ALL conversations
- No need to repeat requirements in every prompt
- Consistent code quality across the team
- Rules can be toggled on/off per conversation

---

## Demo 2: Prompt Templates in Action (4 minutes)

### Setup
1. Ensure prompt templates are in `~/.aws/amazonq/prompts/`
2. Open Amazon Q chat panel

### Demo Steps

#### Step 1: Access Prompt Templates (1 minute)
1. In Amazon Q chat, type `@Prompts`
2. Show the available prompt templates:
   - `java-templates.md`
   - `sql-optimization.md`
   - `security-review.md`

#### Step 2: Use Java Template (1.5 minutes)
1. Select or type:

```
@Prompts java-templates.md Service Layer Template
```

2. Then specify the domain:

```
Create a service class for Order processing with payment validation and inventory checks
```

3. **Show the comprehensive service class** generated with:
   - Business logic structure
   - Transaction management
   - Error handling
   - Logging
   - Validation
   - Repository integration

#### Step 3: Use SQL Optimization Template (1.5 minutes)
1. Open the `UserRepository.java` file
2. Use the SQL optimization template:

```
@Prompts sql-optimization.md JPA Query Optimization

Optimize this repository for better performance:
@files UserRepository.java
```

3. **Show Amazon Q's analysis** of the repository:
   - N+1 query problem identification
   - JOIN FETCH suggestions
   - Pagination recommendations
   - Index optimization suggestions
   - Query restructuring advice

#### Key Demonstration Points
- Templates provide consistent, detailed prompts
- Reusable across projects and team members
- Can be combined with context (@files, @workspace)
- Save time and ensure comprehensive requirements

---

## Demo 3: Context-Aware Prompting (6 minutes)

### Setup
1. Have the sample project open with multiple files
2. Open Amazon Q chat panel

### Demo Steps

#### Step 1: @workspace Context (2 minutes)
1. Use workspace-wide context:

```
@workspace Analyze the current Spring Boot application architecture and suggest improvements for:
1. Database connection pooling configuration
2. Caching strategy implementation
3. API rate limiting
4. Security configuration enhancements
```

2. **Show Amazon Q's comprehensive analysis**:
   - Understanding of the entire project structure
   - Specific recommendations based on existing code
   - Configuration suggestions
   - Integration points identification

#### Step 2: @files Context (2 minutes)
1. Select specific files for context:

```
@files User.java Department.java Role.java UserRepository.java

Review these entity and repository files and generate:
1. Comprehensive integration tests
2. Performance optimization suggestions
3. Security vulnerability assessment
4. Data consistency validation methods
```

2. **Show Amazon Q's targeted analysis**:
   - Understanding of entity relationships
   - Repository method optimization
   - Test generation based on actual entity structure
   - Security considerations for the specific entities

#### Step 3: @code Context (2 minutes)
1. Focus on specific methods:

```
@code UserRepository.findUsersByDepartment UserRepository.searchUsersByName

Optimize these specific query methods for:
1. Better performance with large datasets
2. Proper pagination implementation
3. N+1 query prevention
4. Index optimization recommendations
```

2. **Show Amazon Q's method-specific analysis**:
   - Detailed query optimization
   - Specific JOIN FETCH recommendations
   - Pagination implementation
   - Performance metrics considerations

#### Key Demonstration Points
- Different context levels provide different insights
- @workspace gives architectural overview
- @files provides targeted analysis
- @code gives method-specific optimization
- Context improves accuracy and relevance

---

## Demo 4: Feature Development with /dev (4 minutes)

### Setup
1. Clear workspace or create new folder for feature
2. Open Amazon Q chat panel

### Demo Steps

#### Step 1: Initiate Feature Development (1 minute)
1. Use the /dev command:

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

#### Step 2: Review Generated Files (2 minutes)
1. **Show the multiple files generated**:
   - `AuthenticationController.java`
   - `AuthenticationService.java`
   - `JwtTokenProvider.java`
   - `SecurityConfig.java`
   - `AuthenticationRequest.java`
   - `AuthenticationResponse.java`
   - Test files

2. **Highlight key features**:
   - Complete implementation across multiple files
   - Proper separation of concerns
   - Security best practices
   - Comprehensive error handling

#### Step 3: Iterative Feedback (1 minute)
1. **Use "Provide feedback & regenerate"** option
2. Give specific feedback:

```
Add email verification for new user registration and implement remember-me functionality
```

3. **Show updated implementation** with requested features

#### Key Demonstration Points
- Multi-file code generation
- Complete feature implementation
- Iterative improvement capability
- Integration with existing project structure

---

## Demo 5: Real-World Scenario Integration (6 minutes)

### Setup
1. Open the complete sample project
2. Have Amazon Q chat ready

### Demo Steps

#### Step 1: Project Understanding (2 minutes)
1. Start with comprehensive analysis:

```
@workspace I'm new to this project. Help me understand:
1. The overall architecture and design patterns used
2. Database schema and entity relationships
3. Security implementation approach
4. Areas that might need improvement or modernization
5. Testing strategy and coverage
```

2. **Show Amazon Q's project analysis**:
   - Architecture pattern identification
   - Entity relationship understanding
   - Security assessment
   - Improvement recommendations

#### Step 2: Feature Enhancement (2 minutes)
1. Request a specific enhancement:

```
@files UserRepository.java UserService.java

I need to add user analytics functionality. Create:
1. Methods to track user login patterns
2. Department-wise user activity reports
3. User engagement metrics calculation
4. Performance-optimized queries for large datasets
5. Proper caching strategy for analytics data
```

2. **Show the enhanced implementation**:
   - New repository methods
   - Service layer enhancements
   - Caching integration
   - Performance optimizations

#### Step 3: Security and Quality Review (2 minutes)
1. Comprehensive review:

```
@Prompts security-review.md

@files UserController.java AuthenticationController.java UserService.java

Perform a comprehensive security review of the user management system focusing on:
1. Authentication and authorization vulnerabilities
2. Input validation and sanitization
3. SQL injection prevention
4. Secrets management
5. Error handling security
```

2. **Show Amazon Q's security analysis**:
   - Vulnerability identification
   - Security recommendations
   - Best practice suggestions
   - Compliance considerations

#### Key Demonstration Points
- Integration of all techniques (rules, prompts, context)
- Real-world development workflow
- Comprehensive analysis capabilities
- Security-first development approach

---

## Demo Wrap-up: Best Practices Summary (5 minutes)

### Key Takeaways to Emphasize

#### 1. Layered Approach
- **Rules** provide consistent standards
- **Prompts** offer reusable templates
- **Context** ensures relevant responses
- **Iteration** allows refinement

#### 2. Productivity Gains
- 40-60% faster development
- Consistent code quality
- Reduced context switching
- Automated best practices

#### 3. Team Collaboration
- Shared rules and prompts
- Consistent coding standards
- Knowledge sharing through templates
- Reduced onboarding time

#### 4. Security Integration
- Built-in security scanning
- Real-time vulnerability detection
- Security-aware code generation
- Compliance support

### Audience Interaction Points
1. **Ask about current pain points** in their development workflow
2. **Discuss integration** with existing team processes
3. **Address concerns** about AI-generated code quality
4. **Explore customization** for their specific needs

### Next Steps Recommendations
1. **Start with project rules** for immediate consistency
2. **Create team-specific prompt templates**
3. **Integrate security scanning** into daily workflow
4. **Train team members** on context-aware prompting
5. **Measure productivity improvements** over time

### Common Questions and Answers
- **Q: How do rules affect performance?**
  - A: Minimal impact, rules are processed client-side
- **Q: Can rules be shared across projects?**
  - A: Yes, rules can be version controlled and shared
- **Q: What if Amazon Q suggestions don't match our standards?**
  - A: Rules can be customized and refined iteratively
- **Q: How accurate is the security scanning?**
  - A: High accuracy with low false positive rates, continuously improving