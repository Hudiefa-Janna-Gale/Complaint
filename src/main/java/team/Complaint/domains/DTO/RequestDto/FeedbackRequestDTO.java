package team.Complaint.domains.DTO.RequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FeedbackRequestDTO(
    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must be at most 150 characters")
    String title,

    @NotBlank(message = "Message is required")
    String message,

    @NotBlank(message = "User is required..!")
    String userId,

    @NotNull(message = "Department is required..!")
    Long departmentId
) {
}
