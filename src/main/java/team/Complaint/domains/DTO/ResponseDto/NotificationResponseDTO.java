package team.Complaint.domains.DTO.ResponseDto;

import java.time.LocalDateTime;

import team.Complaint.domains.Model.Notification;

public record NotificationResponseDTO(
    Long notificationId,
    String title,
    String message,
    boolean isRead,
    LocalDateTime createdAt
) {

    public static NotificationResponseDTO from(Notification notification) {
        return new NotificationResponseDTO(
            notification.getNotificationId(),
            notification.getTitle(),
            notification.getMessage(),
            notification.isRead(),
            notification.getCreatedAt()
        );
    }
}
