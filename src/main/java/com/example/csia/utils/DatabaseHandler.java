package com.example.csia.utils;

import com.example.csia.models.Chemical;
import com.example.csia.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:8889/CSIA_Database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertChemical(Chemical chemical) {
        String query = "INSERT INTO chemicals (id, name, hcode) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, chemical.getId());
            stmt.setString(2, chemical.getName());
            stmt.setString(3, chemical.getHCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Chemical getChemicalById(int id) {
        String query = "SELECT * FROM chemicals WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Chemical(rs.getInt("id"), rs.getString("name"), rs.getString("HCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Chemical getChemicalByName(String name) {
        String query = "SELECT * FROM chemicals WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Chemical(rs.getInt("id"), rs.getString("name"), rs.getString("HCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Chemical getChemicalByHCode(String hCode) {
        String query = "SELECT * FROM chemicals WHERE HCode = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, hCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Chemical(rs.getInt("id"), rs.getString("name"), rs.getString("HCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateChemical(Chemical chemical) {
        String query = "UPDATE chemicals SET name = ?, hcode = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, chemical.getName());
            stmt.setString(2, chemical.getHCode());
            stmt.setInt(3, chemical.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChemical(int id) {
        String query = "DELETE FROM chemicals WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String id, String name, String password) {
        String query = "INSERT INTO User (id, name, password) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String id) {
        String query = "DELETE FROM User WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT Name, ID, Password FROM User";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("Name");
                String id = rs.getString("ID");
                String password = rs.getString("Password");

                users.add(new User(name, id, password));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}
