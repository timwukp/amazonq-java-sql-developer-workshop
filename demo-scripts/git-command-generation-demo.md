# Demo Script: Git Command Generation for CodeCommit Integration

## Demo Overview
This script demonstrates how Amazon Q Developer can help generate Git commands using natural language, specifically for teams using AWS CodeCommit who need assistance with Git operations.

**Duration:** 5 minutes  
**Focus:** Natural language to Git command translation

---

## Demo Setup

### Prerequisites
1. VS Code with Amazon Q Developer extension
2. Sample project with Git repository initialized
3. Some sample commits and branches for demonstration
4. Amazon Q chat panel open

### Context Setting
- Explain that the customer uses AWS CodeCommit as their repository
- CodeCommit is end-of-life but they're not replacing it short-term
- Team needs help with Git commands using natural language

---

## Demo Script

### Introduction (30 seconds)
"Many teams, especially those transitioning from other version control systems or new to Git, find Git commands challenging. Amazon Q Developer can help by translating natural language requests into proper Git commands."

---

## Demo 1: Basic Status and Information Commands (1 minute)

### Scenario 1: Checking Current State
**User Request:** "I want to see what files I've changed since my last commit"

**Amazon Q Response:**
```bash
# Check the status of your working directory
git status

# Show files that have been modified since the last commit
git diff --name-only

# Show detailed changes since the last commit
git diff

# Show files changed in the last commit
git diff --name-only HEAD~1
```

### Scenario 2: Understanding Project History
**User Request:** "Show me what happened in this project over the last week"

**Amazon Q Response:**
```bash
# Show commit history for the last 7 days
git log --since="1 week ago" --oneline

# Show detailed commit history with file changes
git log --since="1 week ago" --stat

# Show commits by all authors in the last week
git log --since="1 week ago" --pretty=format:"%h - %an, %ar : %s"
```

---

## Demo 2: Branch Management (1.5 minutes)

### Scenario 1: Creating Feature Branches
**User Request:** "I need to start working on a new user authentication feature"

**Amazon Q Response:**
```bash
# Create and switch to a new feature branch
git checkout -b feature/user-authentication

# Alternative using newer Git syntax
git switch -c feature/user-authentication

# Push the new branch to CodeCommit
git push -u origin feature/user-authentication

# Verify you're on the correct branch
git branch -v
```

### Scenario 2: Switching Between Branches
**User Request:** "I need to switch back to the main branch and see what's different"

**Amazon Q Response:**
```bash
# Switch to the main branch
git checkout main

# Alternative using newer Git syntax
git switch main

# Pull the latest changes from CodeCommit
git pull origin main

# Compare your feature branch with main
git diff main..feature/user-authentication

# Show commits that are in feature branch but not in main
git log main..feature/user-authentication --oneline
```

---

## Demo 3: Handling Changes and Commits (1.5 minutes)

### Scenario 1: Staging and Committing Changes
**User Request:** "I've made changes to several Java files and want to commit only the controller changes"

**Amazon Q Response:**
```bash
# See what files have been changed
git status

# Add only Java controller files
git add src/main/java/com/example/controller/*.java

# Or add specific files
git add src/main/java/com/example/controller/UserController.java

# Check what's staged for commit
git diff --cached

# Commit with a descriptive message
git commit -m "Add user authentication endpoints to UserController"

# Push changes to CodeCommit
git push origin feature/user-authentication
```

### Scenario 2: Undoing Changes
**User Request:** "I made some mistakes in my last commit and want to fix them"

**Amazon Q Response:**
```bash
# If you haven't pushed yet, amend the last commit
git add <fixed-files>
git commit --amend -m "Updated commit message"

# If you've already pushed, create a new commit with fixes
git add <fixed-files>
git commit -m "Fix issues in user authentication implementation"

# To completely undo the last commit (if not pushed)
git reset --soft HEAD~1  # Keeps changes staged
git reset --mixed HEAD~1 # Keeps changes unstaged
git reset --hard HEAD~1  # Removes changes completely (be careful!)
```

---

## Demo 4: Advanced Git Operations (1 minute)

### Scenario 1: Merging and Integration
**User Request:** "My feature is ready and I want to merge it back to main safely"

**Amazon Q Response:**
```bash
# First, switch to main and get latest changes
git checkout main
git pull origin main

# Merge your feature branch (creates merge commit)
git merge --no-ff feature/user-authentication

# Alternative: Rebase your feature branch first for cleaner history
git checkout feature/user-authentication
git rebase main
git checkout main
git merge feature/user-authentication

# Push the merged changes
git push origin main

# Clean up the feature branch
git branch -d feature/user-authentication
git push origin --delete feature/user-authentication
```

### Scenario 2: Handling Conflicts
**User Request:** "I'm getting merge conflicts when trying to merge my branch"

**Amazon Q Response:**
```bash
# Start the merge (this will show conflicts)
git merge feature/user-authentication

# See which files have conflicts
git status

# Open conflicted files in your editor and resolve conflicts
# Look for <<<<<<< HEAD, =======, and >>>>>>> markers

# After resolving conflicts, stage the resolved files
git add <resolved-files>

# Complete the merge
git commit -m "Resolve merge conflicts in user authentication feature"

# If you want to abort the merge instead
git merge --abort
```

---

## Demo 5: CodeCommit-Specific Operations (30 seconds)

### Scenario: Working with CodeCommit Remote
**User Request:** "How do I set up my local repository to work with our CodeCommit repository?"

**Amazon Q Response:**
```bash
# Clone from CodeCommit (using HTTPS)
git clone https://git-codecommit.us-east-1.amazonaws.com/v1/repos/your-repo-name

# Add CodeCommit as remote to existing repository
git remote add origin https://git-codecommit.us-east-1.amazonaws.com/v1/repos/your-repo-name

# Configure credential helper for CodeCommit
git config credential.helper '!aws codecommit credential-helper $@'
git config credential.UseHttpPath true

# Verify remote configuration
git remote -v

# Push to CodeCommit
git push -u origin main
```

---

## Demo Wrap-up and Key Points (30 seconds)

### Key Benefits Demonstrated
1. **Natural Language Interface:** No need to memorize Git commands
2. **Context-Aware Suggestions:** Commands appropriate for the situation
3. **Safety First:** Includes warnings and safe alternatives
4. **CodeCommit Integration:** Specific guidance for AWS CodeCommit
5. **Learning Tool:** Explanations help users understand Git concepts

### Audience Interaction
**Ask the audience:**
- "What Git operations do you find most challenging?"
- "How would this help your team's daily workflow?"
- "What other Git scenarios would you like to see demonstrated?"

### Practical Tips for Implementation
1. **Start with common scenarios** your team faces daily
2. **Use Amazon Q to learn Git concepts** while getting work done
3. **Share generated commands** with team members for consistency
4. **Build confidence** with Git through guided assistance
5. **Gradually reduce dependency** as team becomes more proficient

### Integration with Development Workflow
- Use during code reviews to understand changes
- Help junior developers learn Git best practices
- Standardize Git workflows across the team
- Reduce time spent looking up Git commands
- Improve commit message quality and branch naming

**Closing Statement:** "Amazon Q Developer transforms Git from a barrier into a productivity tool, helping your team focus on coding rather than command syntax."