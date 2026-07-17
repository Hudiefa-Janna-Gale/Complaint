package team.Complaint.domains.DTO.ResponseDto;

import java.time.LocalDateTime;

import team.Complaint.domains.Model.Feedback;

public record FeedbackResponseDTO(
    Long feedbackId,
    String title,
    String message,
    String userFullName,
    String departmentName,
    LocalDateTime createdAt
) {

    public static FeedbackResponseDTO from(Feedback feedback) {
        return new FeedbackResponseDTO(
            feedback.getFeedbackId(),
            feedback.getTitle(),
            feedback.getMessage(),
            feedback.getUser().getFullName(),
            feedback.getDepartment().getDepName(),
            feedback.getCreatedAt()
        );
    }
}
