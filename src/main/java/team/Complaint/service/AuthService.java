package team.Complaint.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.RequestDto.LoginRequestDTO;
import team.Complaint.domains.DTO.RequestDto.UserRequestDTO;
import team.Complaint.domains.DTO.ResponseDto.UserResponseDTO;
import team.Complaint.domains.Enum.Roles;
import team.Complaint.domains.Model.DepartMent;
import team.Complaint.domains.Model.User;
import team.Complaint.domains.repository.DepartmentRepository;
import team.Complaint.domains.repository.UserRepository;

@AllArgsConstructor
@Service
public class AuthService {


    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;





public UserResponseDTO addUser(UserRequestDTO request){

    if(userRepository.existsByEmail(request.email())){
        throw new RuntimeException("Email already exists");
    }


    DepartMent department = departmentRepository.findById(request.departmentId())
            .orElseThrow(() -> new RuntimeException("Department not found"));


    User user = new User();

    user.setFullName(request.fullName());
    user.setEmail(request.email());
    user.setPhone(request.phone());
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setRole(Roles.USER);
    user.setStatus(true);
    user.setDepartment(department);


    User savedUser = userRepository.save(user);

    return UserResponseDTO.from(savedUser);
}


public UserResponseDTO login(LoginRequestDTO request){

    User user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

    if(!passwordEncoder.matches(request.password(), user.getPassword())){
        throw new RuntimeException("Invalid email or password");
    }

    return UserResponseDTO.from(user);
}

}
