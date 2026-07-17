package team.Complaint.domains.DTO.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @Email(message = "Email Must valid email..!")
    String email,

    @NotBlank(message = "Password is required...!")
    String password
) {
}
