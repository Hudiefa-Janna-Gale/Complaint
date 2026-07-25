package team.Complaint.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.RequestDto.UserUpdateRequestDTO;
import team.Complaint.domains.DTO.ResponseDto.UserResponseDTO;
import team.Complaint.domains.Model.DepartMent;
import team.Complaint.domains.Model.User;
import team.Complaint.domains.repository.DepartmentRepository;
import team.Complaint.domains.repository.FeedbackRepository;
import team.Complaint.domains.repository.NotificationRepository;
import team.Complaint.domains.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final FeedbackRepository feedbackRepository;
    private final NotificationRepository notificationRepository;

 // get all users
    public List<UserResponseDTO> getAllUsers() {

    return userRepository.findAll()
            .stream()
            .map(UserResponseDTO::from)
            .toList();
}

    public UserResponseDTO updateUser(String id, UserUpdateRequestDTO request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already in use");
        }

        DepartMent department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setDepartment(department);

        return UserResponseDTO.from(userRepository.save(user));
    }

    @Transactional
    public String deleteUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        notificationRepository.deleteAll(notificationRepository.findByUserId(id));
        feedbackRepository.deleteAll(feedbackRepository.findByUserId(id));
        userRepository.delete(user);

        return "User deleted successfully";
    }

}
