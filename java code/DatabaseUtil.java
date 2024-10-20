package com.ruleengine.ast;

import java.sql.*;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/your_db";
    private static final String USER = "your_user";
    private static final String PASSWORD = "your_password";

    public void saveRule(String ruleName, String ruleString) throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        String query = "INSERT INTO rules (rule_name, rule_string) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, ruleName);
        stmt.setString(2, ruleString);
        stmt.executeUpdate();
        conn.close();
    }

    public String getRule(String ruleName) throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        String query = "SELECT rule_string FROM rules WHERE rule_name = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, ruleName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("rule_string");
        }
        conn.close();
        return null;
    }
}
