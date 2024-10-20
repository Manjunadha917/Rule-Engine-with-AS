# Rule-Engine-with-AS
Project Overview
This project implements a 3-tier rule engine application using Abstract Syntax Tree (AST) to dynamically create, combine, and evaluate rules. The system determines user eligibility based on attributes such as age, department, income, and spend. The rule engine allows flexible rule definition, modification, and evaluation with efficient data handling.

Features
Dynamic Rule Creation: Create complex eligibility rules using logical operators (AND, OR) and conditions (e.g., age > 30, department = 'Sales').
Rule Combination: Combine multiple rules efficiently to minimize redundant checks and optimize performance.
Rule Evaluation: Evaluate rules against user data in real-time to determine eligibility.
AST Representation: Rules are represented as an Abstract Syntax Tree (AST) for easy manipulation.
Database Integration: Rules and their AST structure are stored in a PostgreSQL database.
Bonus Features:
Error handling for invalid rules or data formats.
Rule modification support for updating existing rules.
Extendability for future custom functions in rule definitions.
Technologies Used
Java (for the backend logic and AST implementation)
PostgreSQL (for data storage)
JDBC (for database connectivity)
HTML, CSS (Bootstrap) for UI
Maven (for project management and build)
GitHub (for codebase version control)
Project Structure
less
/src
   /main
      /java
         /com/ruleengine
            - RuleEngine.java     // Main rule engine logic
            - Node.java           // AST node structure
            - RuleEvaluator.java  // Rule evaluation logic
            - RuleCombiner.java   // Rule combination logic
      /resources
         - application.properties // Database configurations
   /test
      /java
         - RuleEngineTest.java    // Test cases for rule creation, combination, and evaluation
Installation and Setup
Prerequisites
Java 8+
PostgreSQL
Maven (for building the project)
Database Setup
Install PostgreSQL and create a database named rules.
Execute the following SQL scripts to set up the required tables:
sql
Copy code
CREATE TABLE nodes (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50),
    value VARCHAR(100),
    left_node_id INT REFERENCES nodes(id),
    right_node_id INT REFERENCES nodes(id)
);

CREATE TABLE rules (
    id SERIAL PRIMARY KEY,
    rule_name VARCHAR(100),
    root_node_id INT REFERENCES nodes(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Database Configuration
Update the application.properties file with your database connection details:

properties
Copy code
jdbc.url=jdbc:postgresql://localhost:5432/rules
jdbc.username=postgres
jdbc.password=yourpassword
Build and Run
Clone the repository from GitHub:
bash
Copy code
git clone https://github.com/yourusername/rule-engine-ast.git
Navigate to the project directory:
bash
Copy code
cd rule-engine-ast
Build the project using Maven:
bash
Copy code
mvn clean install
Run the application:
bash
Copy code
mvn exec:java -Dexec.mainClass="com.ruleengine.RuleEngine"
API Endpoints
Create Rule (/create_rule)

Description: Creates a new rule by parsing a rule string and storing the AST in the database.
Method: POST
Input: Rule string (e.g., "age > 30 AND department = 'Sales'")
Output: JSON representation of the AST.
Combine Rules (/combine_rules)

Description: Combines multiple rules into a single AST and optimizes it.
Method: POST
Input: List of rule strings.
Output: Combined AST.
Evaluate Rule (/evaluate_rule)

Description: Evaluates a rule against user-provided data to check eligibility.
Method: POST
Input: JSON object with user attributes (e.g., {"age": 32, "department": "Sales"})
Output: Boolean (True/False) indicating if the rule conditions are met.
Testing
Test Case 1: Create Rule

Create a rule using the create_rule API and verify the AST structure is correctly stored.
Test Case 2: Combine Rules

Combine multiple rules and ensure the resulting AST reflects the correct logical combination.
Test Case 3: Evaluate Rule

Provide different user data and verify the rule evaluation results as expected.
Test Case 4: Invalid Rule/Error Handling

Test for invalid rule formats and ensure error handling is robust.
Additional Features
Rule Modification: Modify existing rules by updating operators or operand values using the create_rule function or a separate function.
Security: Secure the API with basic authentication for rule creation and modification endpoints.
Performance: Optimized AST traversal for faster evaluation when combining multiple complex rules.
Future Enhancements
Support for user-defined functions in rule language for more advanced conditions.
Integration with a front-end framework like React or Angular for a more dynamic UI experience.
Contribution
Feel free to contribute to this project by submitting pull requests or reporting issues on the GitHub repository.

License
This project is licensed under the MIT License. See the LICENSE file for more details.
