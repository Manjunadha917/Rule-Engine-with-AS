package com.ruleengine.ast;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CreateRuleServlet extends HttpServlet {

    private RuleService ruleService = new RuleService();
    private DatabaseUtil dbUtil = new DatabaseUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ruleString = request.getParameter("ruleString");
        String ruleName = request.getParameter("ruleName");

        Node ast = ruleService.createRule(ruleString);
        try {
            dbUtil.saveRule(ruleName, ruleString);
            response.getWriter().write("Rule saved successfully!");
        } catch (Exception e) {
            response.getWriter().write("Error saving rule: " + e.getMessage());
        }
    }
}
