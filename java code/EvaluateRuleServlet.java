package com.ruleengine.ast;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;  // Import Jackson

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EvaluateRuleServlet extends HttpServlet {

    private RuleService ruleService = new RuleService();
    private DatabaseUtil dbUtil = new DatabaseUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ruleName = request.getParameter("ruleName");

        // Parse JSON request body to a Map using Jackson
        ObjectMapper objectMapper = new ObjectMapper();  // Jackson object mapper
        Map<String, Object> userData = objectMapper.readValue(request.getInputStream(), Map.class);  // Parse the JSON

        try {
            String ruleString = dbUtil.getRule(ruleName);
            Node ruleAst = ruleService.createRule(ruleString);  // Recreate AST from the rule string
            boolean result = ruleService.evaluateRule(ruleAst, userData);  // Evaluate the rule
            response.getWriter().write("Evaluation result: " + result);
        } catch (Exception e) {
            response.getWriter().write("Error evaluating rule: " + e.getMessage());
        }
    }
}
