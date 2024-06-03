package org.example.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.example.Model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${pw3.senha.principal.geracao.token}")
    private String secret;

    public String generateToken(Usuario usuario){
        try{
            var algoritmo = Algorithm.HMAC256(secret);

            String token = JWT.create().withIssuer("Disciplina PW3")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);

            return token;

        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token JWT ", e);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algoritmo)
                    .withIssuer("Disciplina PW3")
                    .build();

            return jwtVerifier.verify(tokenJWT).getSubject();

        }catch (JWTVerificationException ex){
            throw new RuntimeException("Token JWT inválido ou expirado.");
        }
    }
    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
