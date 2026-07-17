package team.Complaint.service;

import java.util.List;


import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.Complaint.domains.DTO.ResponseDto.UserResponseDTO;
import team.Complaint.domains.Model.User;
import team.Complaint.domains.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

 // get all users 
    public List<UserResponseDTO> getAllUsers() {

    return userRepository.findAll()
            .stream()
            .map(UserResponseDTO::from)
            .toList();
}
    


}
