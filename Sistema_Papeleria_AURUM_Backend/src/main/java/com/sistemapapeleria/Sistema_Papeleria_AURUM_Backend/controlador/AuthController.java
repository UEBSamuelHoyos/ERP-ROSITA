package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private DataSource dataSource;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT 1 FROM mysql.user WHERE user = ? AND password = PASSWORD(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, request.getUsername());
            statement.setString(2, request.getPassword());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
