package team.Complaint.domains.DTO.ResponseDto;

import java.io.Serializable;

import team.Complaint.domains.Enum.Roles;
import team.Complaint.domains.Model.User;

public record UserResponseDTO(
    String id,
    String fullName,
    String email,
    String phone,
    Roles role,
    boolean status,
    String department,
    Long departmentId
) implements Serializable {

    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getRole(),
            user.getStatus(),
            user.getDepartment().getDepName(),
            user.getDepartment().getId()
        );
    }
}
