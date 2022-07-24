package com.example.goldengateway.validator;

import com.example.goldengateway.config.TokenUser;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Signature;
import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class GoldenGatewayValidator {

    @Autowired
    private TokenUser tokenUser;
    private String body;

    public boolean isValid(String body){
        this.body = body;

        String[] chunks = body.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        String signature = chunks[2];

        String plainToken = header + "." + payload;

        return checkToken(plainToken,signature);
    }

    public boolean checkToken(String plainToken, String signature){
        SignatureAlgorithm sa = HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(tokenUser.getSecretKey().getBytes(), sa.getJcaName());

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

        // Might need a throw new exception
        return validator.isValid(plainToken, signature);
    }

}
