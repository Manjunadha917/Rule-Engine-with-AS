<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
</head>
<body>

<h1>Rule Engine with AST</h1>

<section>
    <h2>📋 Project Overview</h2>
    <p>
        This project implements a 3-tier rule engine system that determines user eligibility based on attributes like age, department, income, and spending. The rules are represented using an Abstract Syntax Tree (AST), allowing for dynamic creation, combination, and modification of conditional rules. The system supports complex rule logic and efficient data handling, making it flexible and scalable for use in various scenarios.
    </p>
    <h2>✨ Features</h2>
    <ul>
        <li><strong>Dynamic Rule Creation:</strong> Users can create and modify rules using an AST-based structure.</li>
        <li><strong>Rule Combination:</strong> Supports combining multiple rules into a single logical structure.</li>
        <li><strong>Rule Evaluation:</strong> Evaluate the rules in real-time against user attributes and return eligibility status.</li>
        <li><strong>Error Handling:</strong> Robust error handling for invalid rules or incorrect data input.</li>
    </ul>
    <h2>🛠 Data Structure</h2>
    <p>
        The core of the rule engine is represented as an Abstract Syntax Tree (AST). Each node in the tree represents either an operator (like AND/OR) or an operand (conditions like age > 30). The structure is defined as follows:
    </p>
    <pre><code>class Node {
    String type; // "operator" for AND/OR, "operand" for conditions
    Node left;   // Reference to left child node
    Node right;  // Reference to right child node (for operators)
    String value; // Optional value for operand nodes (e.g., age, salary)
}</code></pre>
    <h2>🗂 Data Storage</h2>
    <p>
        The rules and their AST structures are stored in a relational database like PostgreSQL. The schema can be defined as follows:
    </p>
    <pre><code>CREATE TABLE nodes (
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
);</code></pre>
    <h2>📑 Sample Rules</h2>
    <p>Here are some example rules expressed in natural language:</p>
    <ul>
        <li><strong>Rule 1:</strong> ((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)</li>
        <li><strong>Rule 2:</strong> (age > 30 AND department = 'Marketing') AND (salary > 20000 OR experience > 5)</li>
    </ul>
    <h2>🔧 API Design</h2>
    <p>
        The following API endpoints allow for creating and managing rules:
    </p>
    <ul>
        <li><strong>create_rule(rule_string):</strong> Takes a rule string and returns a Node object representing the AST.</li>
        <li><strong>combine_rules(rules):</strong> Takes multiple rule strings, combines them into a single AST, and optimizes the rule logic.</li>
        <li><strong>evaluate_rule(JSON data):</strong> Takes the AST and user attributes as input and evaluates the rule to determine eligibility.</li>
    </ul>
    <h2>🧪 Test Cases</h2>
    <p>Several test cases are designed to verify the rule engine's functionality:</p>
    <ul>
        <li>Create individual rules using <strong>create_rule</strong> and verify the AST structure.</li>
        <li>Combine multiple rules using <strong>combine_rules</strong> and check that the combined logic is accurate.</li>
        <li>Evaluate rules with sample data using <strong>evaluate_rule</strong> and ensure correctness.</li>
        <li>Test error handling by passing invalid rules or malformed data.</li>
    </ul>
    <h2>🚨 Error Handling and Validations</h2>
    <p>The system includes the following error-handling features:</p>
    <ul>
        <li><strong>Invalid Rule String:</strong> The system detects invalid rule formats and provides descriptive error messages.</li>
        <li><strong>Data Validation:</strong> Ensures that the input attributes (age, department, salary, etc.) match the expected catalog before evaluating the rules.</li>
        <li><strong>Modifications:</strong> Supports modifying existing rules by updating operators or operand values.</li>
    </ul>
    <h2>🌟 Bonus Features</h2>
    <ul>
        <li>Support for user-defined functions within the rule language for more complex conditions.</li>
        <li>Advanced optimization strategies for AST traversal, minimizing redundant checks and improving performance.</li>
        <li>Extendable architecture to add new types of conditions and operators as needed.</li>
    </ul>
    <h2>📜 License</h2>
    <p>This project is licensed under the MIT License. See the <a href="#">LICENSE</a> file for more details.</p>
</section>

</body>
</html>
