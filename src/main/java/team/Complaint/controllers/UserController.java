package team.Complaint.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.ResponseDto.UserResponseDTO;
import team.Complaint.domains.Model.User;
import team.Complaint.domains.repository.UserRepository;
import team.Complaint.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserResponseDTO> getAllUsers() {

        return userService.getAllUsers();
    }

}
