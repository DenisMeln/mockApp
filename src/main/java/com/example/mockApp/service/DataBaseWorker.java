package com.example.mockApp.service;

import com.example.mockApp.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;
@Component
public class DataBaseWorker {
    private static final String url = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/postgres");
    private static final String username = System.getenv().getOrDefault("DB_USER", "admin");
    private static final String password = System.getenv().getOrDefault("DB_PASSWORD", "admin");

    public DataBaseWorker() {
    }

    public User selectUserByLogin(String login) {
        String selectSQL = "SELECT ud.login, ud.password, ue.email, ud.date " +
                "FROM user_data ud JOIN user_emails ue ON ud.login = ue.login " +
                "WHERE ud.login = ?";

        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)
        ) {
            pstmt.setString(1, login);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("email"),
                            new Date(rs.getTimestamp("date").getTime())
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertUser(User user) {
        String insertSQL = "INSERT INTO user_data (login, password, date) VALUES (?, ?, ?);\n" +
                "INSERT INTO user_emails (login, email) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setTimestamp(3, new Timestamp(user.getDate().getTime()));

            pstmt.setString(4, user.getLogin());
            pstmt.setString(5, user.getEmail());

            int result = pstmt.executeUpdate();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
