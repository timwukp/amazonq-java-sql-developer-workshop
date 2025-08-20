# Amazon Q Developer Workshop Setup Checklist

## Pre-Workshop Preparation (1 week before)

### Technical Setup
- [ ] **VS Code Installation**
  - Latest version of VS Code installed
  - Amazon Q Developer extension installed and updated
  - Extension authenticated with AWS credentials
  - Test basic chat functionality

- [ ] **Sample Project Setup**
  - Clone or create the sample Spring Boot project
  - Verify project structure matches the demo requirements
  - Test project compilation and basic functionality
  - Ensure all dependencies are resolved

- [ ] **Demo Environment**
  - All demo scripts tested and working
  - Vulnerable code examples prepared
  - Sample files created and accessible
  - Screen sharing and presentation setup tested

### Content Preparation
- [ ] **Rules and Prompts**
  - `.amazonq/rules/` folder created with all rule files
  - `~/.aws/amazonq/prompts/` folder created with template files
  - Test rules toggle functionality in Amazon Q chat
  - Verify prompt templates are accessible via @Prompts

- [ ] **Demo Scripts**
  - All demo scripts reviewed and practiced
  - Timing verified for each section
  - Backup plans prepared for technical issues
  - Interactive elements and audience questions prepared

---

## Day of Workshop Setup (30 minutes before)

### Technical Verification
- [ ] **VS Code and Amazon Q**
  - VS Code opens without issues
  - Amazon Q Developer extension is active
  - Chat panel opens and responds to basic queries
  - Rules toggle works correctly
  - @Prompts command shows available templates

- [ ] **Project Structure**
  - Sample project opens correctly
  - All files are accessible and readable
  - Git repository is initialized (for Git demo)
  - Database connection properties are configured

- [ ] **Demo Files**
  - All vulnerable code examples are ready
  - Demo scripts are easily accessible
  - Screen sharing is configured and tested
  - Backup files are available if needed

### Presentation Setup
- [ ] **Display Configuration**
  - Screen resolution optimized for sharing
  - Font sizes increased for visibility
  - VS Code theme set to high contrast
  - Amazon Q chat panel positioned for visibility

- [ ] **Audio/Video**
  - Microphone tested and working
  - Camera positioned correctly (if using video)
  - Screen sharing tested with audience view
  - Backup communication method available

---

## Workshop Flow Checklist

### Opening (5 minutes)
- [ ] Welcome and introductions completed
- [ ] Session objectives clearly stated
- [ ] Amazon Q Developer value proposition explained
- [ ] Demo environment overview shown
- [ ] Audience engagement established

### Part 1: Advanced Prompt Engineering (20 minutes)
- [ ] **Project Rules Demo (4 min)**
  - Rules folder structure shown
  - Rules toggle demonstrated
  - Before/after code comparison completed
  - Audience understands rules impact

- [ ] **Prompt Templates Demo (4 min)**
  - @Prompts command demonstrated
  - Template selection and usage shown
  - Generated code quality highlighted
  - Template customization explained

- [ ] **Context Commands Demo (6 min)**
  - @workspace context demonstrated
  - @files context shown with multiple files
  - @code context used for specific methods
  - Context impact on responses explained

- [ ] **AWS Integration Demo (4 min)**
  - Native AWS service integration shown
  - Documentation, CloudWatch, Redshift, RDS examples
  - AWS-specific guidance demonstrated
  - Best practices integration highlighted

- [ ] **/dev Command Demo (2 min)**
  - Multi-file feature generation shown
  - Iterative feedback process demonstrated
  - Generated code quality reviewed
  - Integration capabilities highlighted

### Part 2: Security Scanning (15 minutes)
- [ ] **Vulnerability Detection (10 min)**
  - SQL injection example demonstrated
  - Hardcoded secrets detection shown
  - Resource leak identification completed
  - Input validation issues highlighted
  - Authentication/authorization gaps shown

- [ ] **Review Types (3 min)**
  - Auto reviews vs full project reviews explained
  - Quotas and limits discussed
  - Different scanning categories covered
  - Performance impact addressed

- [ ] **Remediation Demo (2 min)**
  - Fix acceptance process shown
  - Before/after security comparison
  - Best practices implementation demonstrated
  - Security improvement validation completed

### Part 3: DevSecOps Integration (10 minutes)
- [ ] **Git Command Generation (5 min)**
  - Natural language to Git commands shown
  - CodeCommit-specific examples demonstrated
  - Common scenarios covered
  - Team workflow integration explained

- [ ] **Code Review Integration (3 min)**
  - Pre-commit scanning demonstrated
  - Quality checks shown
  - Documentation generation highlighted
  - Workflow integration explained

- [ ] **Database Migration (2 min)**
  - SQL transformation capabilities shown
  - Oracle to PostgreSQL example demonstrated
  - Query optimization suggestions shown
  - Migration workflow explained

### Part 4: Real-World Scenario (15 minutes)
- [ ] **Setup (3 min)**
  - Project environment shown
  - Context and objectives established
  - Demo scenario introduced
  - Expected outcomes outlined

- [ ] **Development Workflow (8 min)**
  - Project understanding demonstrated
  - Feature implementation shown
  - Database integration optimized
  - Security enhancement completed

- [ ] **Code Review and Validation (4 min)**
  - Security scanning results shown
  - Quality improvements demonstrated
  - Performance optimizations highlighted
  - Final validation completed

---

## Post-Demo Checklist

### Immediate Follow-up
- [ ] **Q&A Session**
  - All audience questions addressed
  - Technical concerns resolved
  - Implementation guidance provided
  - Next steps clarified

- [ ] **Resource Sharing**
  - Workshop materials shared with attendees
  - Sample project files distributed
  - Documentation links provided
  - Contact information exchanged

### Workshop Evaluation
- [ ] **Feedback Collection**
  - Audience feedback gathered
  - Technical issues documented
  - Improvement suggestions noted
  - Success metrics recorded

- [ ] **Follow-up Actions**
  - Additional support needs identified
  - Implementation timeline discussed
  - Training requirements assessed
  - Success criteria established

---

## Troubleshooting Guide

### Common Issues and Solutions

#### Amazon Q Extension Issues
- **Problem:** Extension not responding
- **Solution:** Restart VS Code, check AWS credentials, verify internet connection

- **Problem:** Rules not applying
- **Solution:** Check rules folder location, verify file format, toggle rules off/on

- **Problem:** Prompts not accessible
- **Solution:** Verify prompts folder location, check file permissions, restart extension

#### Demo Technical Issues
- **Problem:** Code generation not working
- **Solution:** Check context selection, simplify prompt, verify project structure

- **Problem:** Security scanning not detecting issues
- **Solution:** Ensure vulnerable code is properly formatted, check file types, refresh extension

- **Problem:** Git commands not generating
- **Solution:** Verify Git repository status, check natural language clarity, provide more context

#### Presentation Issues
- **Problem:** Screen sharing quality poor
- **Solution:** Adjust resolution, increase font sizes, use high contrast theme

- **Problem:** Audio issues during demo
- **Solution:** Use backup microphone, check audio settings, have written backup

- **Problem:** Audience engagement low
- **Solution:** Ask more questions, provide hands-on opportunities, relate to their specific use cases

---

## Success Metrics

### Technical Metrics
- [ ] All demo sections completed successfully
- [ ] No major technical issues encountered
- [ ] All features demonstrated as planned
- [ ] Audience able to follow along

### Engagement Metrics
- [ ] Active audience participation
- [ ] Relevant questions asked
- [ ] Positive feedback received
- [ ] Clear understanding demonstrated

### Outcome Metrics
- [ ] Implementation plans discussed
- [ ] Next steps identified
- [ ] Support needs clarified
- [ ] Success criteria established

---

## Emergency Backup Plans

### Technical Failures
- **Complete Extension Failure:** Use pre-recorded demo videos
- **Internet Connectivity Issues:** Use offline documentation and slides
- **VS Code Crashes:** Have backup IDE with similar setup
- **Project Corruption:** Maintain multiple backup copies

### Content Alternatives
- **Time Constraints:** Prioritize security scanning and rules demos
- **Audience Level Mismatch:** Adjust technical depth accordingly
- **Specific Use Case Focus:** Adapt examples to audience needs
- **Interactive Session Request:** Have hands-on exercises ready

### Communication Backups
- **Audio Issues:** Use chat for communication
- **Video Problems:** Continue with audio only
- **Screen Sharing Failure:** Use pre-prepared screenshots
- **Platform Issues:** Have alternative meeting platform ready