package team.Complaint.domains.DTO.RequestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
    @NotBlank(message = "Name is required")
    String fullName,

    @Email(message = "Email Must valid email..!")
    String email,

    @NotBlank(message = "Phone is required")
    @Size(min = 8, max = 15, message = "Phone number must be between 8 and 15 digits")
    String phone,

    @NotBlank(message = "Password is required...!")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password,

    @NotNull(message = "Department is required..!")
    Long departmentId
) {
}
