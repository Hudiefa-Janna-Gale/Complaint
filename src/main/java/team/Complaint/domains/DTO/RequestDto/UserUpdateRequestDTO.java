package team.Complaint.domains.DTO.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO(
    @NotBlank(message = "Name is required")
    String fullName,

    @Email(message = "Email Must valid email..!")
    @NotBlank(message = "Email is required")
    String email,

    @NotBlank(message = "Phone is required")
    @Size(min = 8, max = 15, message = "Phone number must be between 8 and 15 digits")
    String phone,

    @NotNull(message = "Department is required..!")
    Long departmentId
) {
}
