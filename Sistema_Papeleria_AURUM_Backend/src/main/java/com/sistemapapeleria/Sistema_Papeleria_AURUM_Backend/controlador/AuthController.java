package com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.controlador;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.Entidades.Usuario;
import com.sistemapapeleria.Sistema_Papeleria_AURUM_Backend.repositorio.UsuarioRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        Usuario user = usuarioRepo.findByUsername(login.getUsername());
        if (user != null && user.getPassword().equals(login.getPassword())) {
            return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "rol", user.getRol()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
