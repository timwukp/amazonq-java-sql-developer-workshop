# Amazon Q Developer Workshop: Best Practices for Java Developers

A comprehensive 60-minute workshop demonstrating Amazon Q Developer IDE extension capabilities for Java developers working with SQL in their SDLC.

## 🎯 Workshop Overview

This workshop focuses exclusively on **Amazon Q Developer IDE extension for VS Code** and demonstrates:

- **Advanced Prompt Engineering** with project rules and templates
- **Real-time Security Scanning** and vulnerability detection
- **Context-Aware Development** using @ commands
- **DevSecOps Integration** with Git command generation
- **SQL Optimization** and database best practices

## 📁 Repository Structure

```
├── enhanced-workshop-outline.md          # Complete 60-minute workshop guide
├── workshop-setup-checklist.md           # Pre-workshop setup and checklist
├── sample-project/                       # Demo Spring Boot project
│   ├── .amazonq/
│   │   └── rules/                        # Project-specific coding standards
│   ├── src/main/java/com/example/
│   │   ├── model/                        # JPA entities with relationships
│   │   ├── repository/                   # Repository interfaces with SQL examples
│   │   └── service/                      # Service classes (vulnerable & optimized)
│   └── src/main/resources/
│       └── application.properties        # Configuration examples
├── prompts/                              # Reusable prompt templates
│   ├── java-templates.md                 # Java development templates
│   ├── sql-optimization.md               # SQL optimization templates
│   └── security-review.md                # Security review templates
└── demo-scripts/                         # Step-by-step demo guides
    ├── vulnerable-code-examples.md       # Security scanning demonstrations
    ├── advanced-prompt-engineering-demo.md # Prompt engineering techniques
    └── git-command-generation-demo.md    # Git command generation for CodeCommit
```

## 🚀 Quick Start

### Prerequisites
- VS Code with Amazon Q Developer extension installed
- AWS credentials configured
- Java 17+ and Maven/Gradle (for sample project)

### Setup Instructions
1. Clone this repository
2. Open the `sample-project` folder in VS Code
3. Ensure Amazon Q Developer extension is active
4. Follow the `workshop-setup-checklist.md` for detailed setup

## 🎓 Workshop Content

### Part 1: Advanced Prompt Engineering (20 minutes)
- **Project Rules**: Consistent coding standards with `.amazonq/rules/`
- **Prompt Templates**: Reusable templates in `~/.aws/amazonq/prompts/`
- **Context Commands**: `@workspace`, `@files`, `@folders`, `@code`
- **Feature Development**: Multi-file generation with `/dev`

### Part 2: Security Scanning (15 minutes)
- **Real-time Vulnerability Detection**: SQL injection, secrets, resource leaks
- **Code Quality Analysis**: Performance and maintainability issues
- **Automated Remediation**: Accept/reject security fixes

### Part 3: DevSecOps Integration (10 minutes)
- **Git Command Generation**: Natural language to Git commands
- **CodeCommit Integration**: Specific guidance for AWS CodeCommit users
- **Database Migration**: SQL transformation capabilities

### Part 4: Real-World Scenarios (15 minutes)
- **Complete Development Workflow**: From analysis to deployment
- **Security-First Development**: Integrated security scanning
- **Performance Optimization**: Database and application tuning

## 🔧 Demo Components

### Sample Project Features
- **Spring Boot Application** with JPA entities and repositories
- **Vulnerable Code Examples** for security scanning demonstrations
- **Optimized Code Patterns** showing best practices
- **Complex SQL Queries** for performance optimization demos

### Security Demonstrations
- SQL injection vulnerabilities and fixes
- Hardcoded secrets detection and remediation
- Resource leak identification and resolution
- Input validation and authorization issues

### Performance Examples
- N+1 query problems and solutions
- Database connection optimization
- Bulk operation improvements
- Query performance analysis

## 📋 Workshop Delivery

### For Instructors
1. Review `enhanced-workshop-outline.md` for complete session plan
2. Practice with `demo-scripts/` for step-by-step guidance
3. Use `workshop-setup-checklist.md` for preparation
4. Test all examples in the `sample-project/`

### For Participants
1. Follow setup instructions in `workshop-setup-checklist.md`
2. Have VS Code with Amazon Q Developer extension ready
3. Clone this repository for hands-on practice
4. Review `sample-project/` structure before the session

## 🎯 Learning Outcomes

After this workshop, participants will be able to:
- **Increase development productivity** by 40-60% using advanced prompt engineering
- **Implement security-first development** with real-time vulnerability detection
- **Optimize SQL queries and database operations** using Amazon Q suggestions
- **Integrate Amazon Q** into existing development workflows
- **Use natural language** for Git operations and command generation

## 🔒 Security and Privacy

This repository contains:
- ✅ **Intentionally vulnerable code** for educational purposes (clearly marked)
- ✅ **No real secrets or credentials** (uses environment variables)
- ✅ **Demo data only** (no production information)
- ✅ **Best practices examples** for secure development

**Note**: The vulnerable code examples are for demonstration purposes only and should not be used in production environments.

## 🤝 Contributing

This workshop material is designed for educational purposes. If you find issues or have suggestions:
1. Review the existing examples and documentation
2. Test changes with Amazon Q Developer extension
3. Ensure security best practices are maintained
4. Update relevant demo scripts if needed

## 📄 License

This workshop material is provided for educational purposes. Please ensure compliance with your organization's policies when using Amazon Q Developer and handling code examples.

## 🆘 Support

For workshop delivery support:
- Review `workshop-setup-checklist.md` for troubleshooting
- Test all examples before delivery
- Have backup plans for technical issues
- Ensure Amazon Q Developer extension is properly configured

---

**Target Audience**: Java developers working with SQL in their SDLC  
**Duration**: 60 minutes with live demonstrations  
**Focus**: Amazon Q Developer IDE extension for VS Code capabilities