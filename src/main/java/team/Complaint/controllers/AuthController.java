package team.Complaint.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final AuthService authService;

    @PostMapping("/register")
public UserResponseDTO addingUser(@Valid @RequestBody UserRequestDTO request){

    return authService.addUser(request);
}

    @PostMapping("/login")
public UserResponseDTO login(@Valid @RequestBody LoginRequestDTO request){

    return authService.login(request);
}
}
