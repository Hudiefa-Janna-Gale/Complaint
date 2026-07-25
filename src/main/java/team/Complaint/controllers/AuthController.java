package team.Complaint.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.RequestDto.LoginRequestDTO;
import team.Complaint.domains.DTO.RequestDto.UserRequestDTO;
import team.Complaint.domains.DTO.ResponseDto.UserResponseDTO;
import team.Complaint.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    public static final String SESSION_USER = "AUTH_USER";

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponseDTO addingUser(@Valid @RequestBody UserRequestDTO request, HttpServletRequest httpRequest) {
        UserResponseDTO user = authService.addUser(request);
        httpRequest.getSession(true).setAttribute(SESSION_USER, user);
        return user;
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO request, HttpServletRequest httpRequest) {
        UserResponseDTO user = authService.login(request);
        httpRequest.getSession(true).setAttribute(SESSION_USER, user);
        return user;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        Object user = session == null ? null : session.getAttribute(SESSION_USER);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok((UserResponseDTO) user);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.noContent().build();
    }
}
