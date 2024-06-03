package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.TokenDTO;
import org.example.DTO.UsuarioDTO;
import org.example.Model.Usuario;
import org.example.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity efetuaLogin(@RequestBody @Valid UsuarioDTO usuarioDTO){
        var token = new UsernamePasswordAuthenticationToken(usuarioDTO.username(),usuarioDTO.password());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
