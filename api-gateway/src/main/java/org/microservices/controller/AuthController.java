package org.microservices.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.microservices.model.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping
    public ResponseEntity<AuthUserModel> login() {
        return null;
    }
}
