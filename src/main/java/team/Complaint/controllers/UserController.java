package team.Complaint.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.RequestDto.UserUpdateRequestDTO;
import team.Complaint.domains.DTO.ResponseDto.UserResponseDTO;
import team.Complaint.domains.Enum.Roles;
import team.Complaint.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserResponseDTO> getAllUsers(HttpServletRequest request) {
        requireAdmin(request);
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateRequestDTO request,
            HttpServletRequest httpRequest) {
        requireAdmin(httpRequest);
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id, HttpServletRequest request) {
        requireAdmin(request);
        return userService.deleteUser(id);
    }

    // Only a logged-in ADMIN may manage users.
    private void requireAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object user = session == null ? null : session.getAttribute(AuthController.SESSION_USER);
        if (!(user instanceof UserResponseDTO) || ((UserResponseDTO) user).role() != Roles.ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admins only");
        }
    }

}
