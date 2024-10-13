package com.example.springteste.controllers;


import com.example.springteste.config.seguranca.FiltroSeguranca;
import com.example.springteste.config.seguranca.ServicoToken;
import com.example.springteste.dtos.AutenticacaoDTO;
import com.example.springteste.dtos.LoginAuth;
import com.example.springteste.dtos.RegistroDto;
import com.example.springteste.models.UsuariosModel;
import com.example.springteste.repositories.UsuariosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class ControleAutenticacao {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private ServicoToken servicoToken;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AutenticacaoDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = servicoToken.geradorToken((UsuariosModel) auth.getPrincipal());
            return ResponseEntity.ok(new LoginAuth(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegistroDto data){
        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        UsuariosModel novoUsuario = new UsuariosModel(data.nome(), data.email(), encryptedPassword, data.regra());
        this.repository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
