package team.Complaint.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.ResponseDto.NotificationResponseDTO;
import team.Complaint.domains.Model.Notification;
import team.Complaint.domains.repository.NotificationRepository;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationResponseDTO> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(NotificationResponseDTO::from)
                .toList();
    }

    public List<NotificationResponseDTO> getNotificationsByUser(String userId) {
        return notificationRepository.findByUserId(userId)
                .stream()
                .map(NotificationResponseDTO::from)
                .toList();
    }

    public NotificationResponseDTO markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        Notification savedNotification = notificationRepository.save(notification);
        return NotificationResponseDTO.from(savedNotification);
    }

}
